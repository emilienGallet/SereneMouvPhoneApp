package zemoov.serenemouv.CMTA;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import zemoov.serenemouv.CMTA.Exceptions.*;
import zemoov.serenemouv.CPDispo.CPDispo;
import zemoov.serenemouv.GBE.Borne;
import zemoov.serenemouv.GBE.GBE;

/**
 * @author emilien
 */
public class Cmta implements Serializable {
    // REMOVED Preference saPreference; // Préférence du Trajet voir Preference.java
    Vehicule engage; // Le véhicule qui va être utiliser pour le trajet.
    Integer puissanceMin; // La puissance minimal de rechargement du véhicule exigé par l'utilisateur
    Integer puissanceMax; // La puissance maximal de rechargement du véhicule exigé par l'utilisateur
    Trajet leTrajet; // Le Trajet en lui même voir Trajet.java
    ArrayList<Operator> badgesOperateur; // La liste des badges opérateur que l'utilisateur dispose
    Integer nbPersonnes; // Le nombre de personnes présente dans le véhicule pour ce trajet.
    Boolean gotOnlineCB; // Vérifie si l'utilisateur dispose d'une carte bancaire pour payer en ligne

    public Cmta(Vehicule engage, Integer puissanceMin, Integer puissanceMax, Trajet leTrajet, ArrayList<Operator> badgesOperateur, Integer nbPersonnes, Boolean gotOnlineCB) {
        this.engage = engage;
        this.puissanceMin = puissanceMin;
        this.puissanceMax = puissanceMax;
        this.leTrajet = leTrajet;
        this.badgesOperateur = badgesOperateur;
        this.nbPersonnes = nbPersonnes;
        this.gotOnlineCB = gotOnlineCB;
    }

    /**
     * Basique build possible pour crée un CMTA
     * @param nombreDePersonnes
     * @param bagages
     * @param saPreference
     * @param badgesPossible
     * @param leVehicule
     * @param puissanceMax
     * @param puissanceMin en Kw
     * @param start
     * @param end
     * @param step
     * @param gotOnlineCB
     * @return
     * @throws CMTAException
     * @throws CMTAWarning
     */
    public static Cmta build(Integer nombreDePersonnes, Integer bagages, Preference saPreference,
                      ArrayList<Operator> badgesPossible, Vehicule leVehicule,
                      Integer puissanceMax,Integer puissanceMin,
                      Localisation start, Localisation end, ArrayList<Localisation> step,
                      Boolean carrefourDangereux,Boolean travauxSector, Boolean gotOnlineCB,Boolean peage)
            throws CMTAException,CMTAWarning {

            Trajet leTrajet = null;
            //TODO Vérifier le trajet si il est possible
            //
            leTrajet = Trajet.trajectBuilder(start,end,step,saPreference,carrefourDangereux,travauxSector);//Traitement-1
            //SI OUI on continue
            //DONE Vérifier si le trajet est accesible
            // On vérifie si le nombre de personnes est possible dans le véhicule
            leVehicule.avaiblePlace(nombreDePersonnes);
            // On vérifie si il ne risque pas d'avoir un dépacement du poid maximal autorisé
            leVehicule.avaiableWeight(bagages);


            // On demande au module CP Dispo si le trajet peut être fait
            // !!!!!!!!!!DIRECTEMENT !!!!!!!!!!!
            Cmta configUser;
            try{
                CPDispo.estAccessible(leTrajet.unChemin,leVehicule);
                configUser = new Cmta(leVehicule,puissanceMax,puissanceMin,leTrajet,badgesPossible,nombreDePersonnes,gotOnlineCB);
            }catch (CMTAWarning warning){

                //TODO prendre la liste des bornes de recharge possible sur le trajet
                configUser = new Cmta(leVehicule,puissanceMax,puissanceMin,leTrajet,badgesPossible,nombreDePersonnes,gotOnlineCB);
                ArrayList<Borne> listeBorneUtilisable = GBE.bornesAutourDuTrajet(configUser);
                ArrayList<Localisation> etapesDeRecharge = new ArrayList<Localisation>();
                Trajet boutDeTrajet = Trajet.trajectBuilder(configUser);//On construit une Grande étape

                CPDispo.estAccessibleParRecharge(leTrajet.unChemin,leVehicule,badgesPossible);
                throw warning;
            }
            return configUser;
            //CMTAException si il est innacessible
    }

    private static Borne borneLaPlusProche(ArrayList<Borne> listeBorne,Vehicule leVehicule) {
        ArrayList<Borne> bornePossible = new ArrayList<Borne>();
        for (Borne b:listeBorne) {
            if (b.peuxRechargerIci(leVehicule)){
                bornePossible.add(b);
            }
        }
        return null;//TODO
    }

    /**
     * Build an CMTa without know if they go bagages or not
     * @param nombreDePersonnes
     * @param saPreference
     * @param badgesPossible
     * @param leVehicule
     * @param puissanceMax en Kw
     * @param puissanceMin en Kw
     * @param start
     * @param end
     * @param step
     * @param gotOnlineCB
     * @return Cmta
     * @throws CMTAException
     * @throws CMTAWarning
     */
    public static Cmta build(Integer nombreDePersonnes, Preference saPreference,
                      ArrayList<Operator> badgesPossible, Vehicule leVehicule,
                      Integer puissanceMax,Integer puissanceMin,
                      Localisation start, Localisation end, ArrayList<Localisation> step,
                      Boolean carrefourDangereux,Boolean travauxSector, Boolean gotOnlineCB) throws CMTAException, CMTAWarning{
            //TODO MAYBE changer la valeur de Bagages par la valeur du PTAC (Masse Max autoriser)
            return build(nombreDePersonnes,0,saPreference,badgesPossible,leVehicule,puissanceMax,puissanceMin,
                    start,end,step,carrefourDangereux,travauxSector,gotOnlineCB,true);
    }

    public static List<Localisation> getPosibleDestinationName(String nameLocation) {
        List<Localisation> nameList = new ArrayList<>();
        //TODO recupere les vrai adresse

        /**
         * Code from : https://docs.graphhopper.com/#tag/Routing-API little bit edited
         * for our API Need to add
         * https://mvnrepository.com/artifact/com.squareup.okhttp3/okhttp
         */
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://api-adresse.data.gouv.fr/search/?q="+nameLocation).get().build();
        String s;
        Response response;
        try {
            response = client.newCall(request).execute();

            s = response.body().string();
            JSONObject json = new JSONObject(s);
            //System.err.println(json.toString());
            JSONArray jsArry =  json.getJSONArray("features");
            return get3PremierMax(jsArry);
        } catch (IOException | JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return nameList;
        }

        //TODO !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

       // nameList.add(new Localisation("3 impasse berlioz 42350 La Talaudiere",45.4817, 4.43910,0));
       // nameList.add(new Localisation("4 impasse berlioz 42350 La Talaudiere",0,0,0));
       // nameList.add(new Localisation("3 impase berliose 69000 Lyon",0,0,0));
       // return  nameList;
    }

    private static List<Localisation> get3PremierMax(JSONArray jsArry) {
        List<Localisation> nameList = new ArrayList<>();
        try {
            for (int i = 0 ; i<3 ;i++){
                double latitude = jsArry.getJSONObject(i).getJSONObject("geometry").getJSONArray("coordinates").getDouble(0);
                double longitude = jsArry.getJSONObject(i).getJSONObject("geometry").getJSONArray("coordinates").getDouble(1);;
                double hauteur = 0;
                String nameLocation = jsArry.getJSONObject(i).getJSONObject("properties").getString("label");
                nameList.add(new Localisation(nameLocation,latitude,longitude,hauteur));
            }
        }catch (Exception e){
            return nameList;
        }
        return nameList;
    }

    /**
     * Numéro de la spécification principal: paramètre-1
     * Numéro des spécification inclue partielement: paramètre-1.1,paramètre-1.2,paramètre-1.3
     *
     * @param preference correspondant a la chaine de caractère présente dans le document de spécification des exigence
     */
    void choisirPreferenceTrajet(String preference) {
        if (preference.equalsIgnoreCase("le plus rapide")) {
            choisirPreferenceTrajet(Preference.FAST);
        }
        else if (preference.equalsIgnoreCase("équilibré")) {
            choisirPreferenceTrajet(Preference.NORMAL);
        }
        else{
            choisirPreferenceTrajet(Preference.ECO);
        }

    }
    void choisirPreferenceTrajet(Preference p) {
        this.leTrajet.saPreference =p;
    }

    public Trajet getLeTrajet() {
        return leTrajet;
    }
}
