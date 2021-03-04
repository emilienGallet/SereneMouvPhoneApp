package zemoov.serenemouv.CMTA;

import java.util.ArrayList;

import zemoov.serenemouv.CMTA.Exceptions.*;
import zemoov.serenemouv.CPDispo.CPDispo;
import zemoov.serenemouv.GBE.Borne;
import zemoov.serenemouv.GBE.GBE;

/**
 * @author emilien
 */
public class Cmta {
    // REMOVED Preference saPreference; // Préférence du Trajet voir Preference.java
    Vehicule engage; // Le véhicule qui va être utiliser pour le trajet.
    Integer puissanceMin; // La puissance minimal de rechargement du véhicule exigé par l'utilisateur
    Integer puissanceMax; // La puissance maximal de rechargement du véhicule exigé par l'utilisateur
    Trajet leTrajet; // Le Trajet en lui même voir Trajet.java
    ArrayList<Operator> badgesOperateur; // La liste des badges opérateur que l'utilisateur dispose
    Integer nbPersonnes; // Le nombre de personnes présente dans le véhicule pour ce trajet.
    Boolean gotOnlineCB; // Vérifie si l'utilisateur dispose d'une carte bancaire pour payer en ligne

    public Cmta(Vehicule leVehicule, Integer puissanceMax, Integer puissanceMin,
                Trajet leTrajet, ArrayList<Operator> badgesPossible,
                Integer nombreDePersonnes, Boolean gotOnlineCB) {
        super();
        //TODO
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
    private static Cmta build(Integer nombreDePersonnes, Integer bagages, Preference saPreference,
                      ArrayList<Operator> badgesPossible, Vehicule leVehicule,
                      Integer puissanceMax,Integer puissanceMin,
                      Localisation start, Localisation end, ArrayList<Localisation> step,
                      Boolean carrefourDangereux,Boolean travauxSector, Boolean gotOnlineCB)
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
    static Cmta build(Integer nombreDePersonnes, Preference saPreference,
                      ArrayList<Operator> badgesPossible, Vehicule leVehicule,
                      Integer puissanceMax,Integer puissanceMin,
                      Localisation start, Localisation end, ArrayList<Localisation> step,
                      Boolean carrefourDangereux,Boolean travauxSector, Boolean gotOnlineCB) throws CMTAException, CMTAWarning{
            //TODO MAYBE changer la valeur de Bagages par la valeur du PTAC (Masse Max autoriser)
            return build(nombreDePersonnes,0,saPreference,badgesPossible,leVehicule,puissanceMax,puissanceMin,start,end,step,carrefourDangereux,travauxSector,gotOnlineCB);
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

}
