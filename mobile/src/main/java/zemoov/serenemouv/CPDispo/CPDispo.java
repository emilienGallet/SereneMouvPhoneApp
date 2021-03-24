package zemoov.serenemouv.CPDispo;
import android.util.Log;

import com.graphhopper.directions.api.client.model.RouteResponse;

import java.util.ArrayList;

import zemoov.serenemouv.CMTA.Exceptions.CMTAException;
import zemoov.serenemouv.CMTA.Exceptions.CMTAWarning;
import zemoov.serenemouv.CMTA.Localisation;
import zemoov.serenemouv.CMTA.Operator;
import zemoov.serenemouv.CMTA.Path;
import zemoov.serenemouv.CMTA.Temperature;
import zemoov.serenemouv.CMTA.Trajet;
import zemoov.serenemouv.CMTA.Vehicule;


public class CPDispo {
    //la gravité
    final static double g= 9.18;
    //masse en kg
    static double poid=2555; //TODO Modifier pour le véhicule ^^
    //coef RouteResponse routeRde frottement des peneux
    //faute de possibilitées de messure nous prendrons une valeure moyenne de 0.013
    static double k=0.013;
    //ρ(rho)= masse volumique de l’air, à 20° elle est de 1,204 kg/m3
    //recherche de sources a effectuer
    final static double p=1.204;
    //rapport entre la surface frontale d’un véhicule (S) et sa résistance à l’écoulement de l’air (CX)
    // ici en attente de données sur le modèle on le considère à 0,6583
    final static double SCx=0.6583;

    /**
     * @author Émilien
     * @param unChemin
     * @param leVehicule
     * Cette méthode s'applique uniquement pour un trajet Direct !
     * Renvoie un TrajectWarning en cas d'incapacité d'atteindre le trajet
     */
    public static void estAccessible(Path unChemin, Vehicule leVehicule /*TODO : BESOIN DE LA TEMPERATURE DANS LE VEHIUCLE*/) throws CMTAWarning {
        //TODO Dire si la destination est accesible ou non
        //TODO Manipuler la charge en watt/heure
        CPDispo.meth2(leVehicule.poidsCourant,leVehicule.chargeActuelle,unChemin);
        if (leVehicule.chargeActuelle < unChemin.KwParHNecessaire){//Si la charge actuelle n'est pas assez suffisante
            if (leVehicule.puissanceDC > 0){ //Si le véhicule recharge
                throw new CMTAWarning("La charge actuelle n'est pas suffisante");
            }else if(leVehicule.chargeTotalPossible < unChemin.KwParHNecessaire){
                throw new CMTAWarning("La charge même complète n'est pas suffisante. Il faudra recharger pendant le trajet");
                // Il faut donc recharger a un autre endroit
            }else{
                throw new CMTAWarning("Soit vous pouvez reecharger ici soit pendant votre trajet pour atteindre la destination");
                // Il faut donc recharger a u autre endroit
            }
        }
        return;
    }

    /**
     * @author Émilien
     * @param unChemin
     * @param leVehicule
     * @param badgesPossible
     * @throws zemoov.serenemouv.CMTA.Exceptions.TrajectException en cas d'incapacité totale d'atteindre la destination
     * @throws zemoov.serenemouv.CMTA.Exceptions.TrajectWarning en cas de vigilance particulière a avoir sur la gestion de l'énergie pour le trajet
     */
    public static void estAccessibleParRecharge(Path unChemin, Vehicule leVehicule, ArrayList<Operator> badgesPossible) throws CMTAException {
        CPDispo.meth2(leVehicule.poidsCourant,leVehicule.chargeActuelle,unChemin);
        //TODO
        return;
    }


    //13.2kwh 83km   0,15903614457831KWh/km  159,036Wh/km  0,159036Wh/m

    //Donnnées en entrée:
    // liste de données des températures prévisionnelles sur la zone
    //charge actuelle de la batterie en Watt
    //poids transporté par le véhicule en kilogramme
    //sortie: distance maximale pouvant être parcourue en km
    static double meth1(double poidTransporte,double chargeBatterie, double [] temperaturesZone){
        double instantCharge;
        double poidTotal= poid+poidTransporte;

        //vitesse en m/s fixé a 20 km/h qui est la limitation minimal en france
        int v=20000/3600;


        int i=0,j=0;
        double distancePPkm;

        while(i<=temperaturesZone.length){
            if (temperaturesZone[i]<=10){
                j+=1;
            }
            i+=1;
        }
        // on calcule la charge d'énergie necessaire pour maintenir le véhicule à 20km/h
        instantCharge=poidTotal*g*k*v +1/2*p*SCx*v*3;
        //on calcule la distance pouvant être parcourue avec la charge actuelle de la batterie
        distancePPkm=(v*3600)*(chargeBatterie/(instantCharge/1000));
        //ici on on applique la perte de 20% de charge de la batterie quand la température exterieur est de moins de 10°C
        distancePPkm=distancePPkm-((distancePPkm/temperaturesZone.length)*j)+(((distancePPkm/temperaturesZone.length)*j/100)*20);

        return distancePPkm;
    }

    //Données en entrée:
    // liste de données de données du trajet
    // liste de données des températures prévisionnelles sur le trajet
    //charge actuelle de la batterie en Watt
    //poids transporté par le véhicule en kilogramme
    //sortie par modification de l'objet Path :
    // Donne la charge necessaire pour réaliser le trajet en Kw/h dans l'attribut KwParHNecessaire.
    static void meth2(double poidTransporte, double chargeBatterie, Path leChemin){
        double instantCharge,chUnderTenDegre,chAboveTenDegre;
        //chUnderTenDegre = Charge en dessous de 10°C
        //chAboveTenDegre = Charge en dessus strictement de 10°C
        double poidTotal= poid+poidTransporte;
        int i=0,j=0; // i = nombre total de température; j = le nombre de fois ou la température passe en dessous de 10°C
        double distanceSoumiseTemp=0,distanceSoumiseSimple=0;
        //distanceSoumiseTemp = La distance parcourue sous une température inférieur ou égale à 10°C
        //distanceSoumiseSimple = La distance parcourue dans une température strictement supérieur à 10°C
        //vitesse en m/s fixé a 20 km/h qui est la limitation minimal en france
        int v=20000/3600; // 20km/h en m/s
        instantCharge=poidTotal*g*k*v +1/2*p*SCx*v*3;// La formule
        Log.i("Résultat de la formule:", String.valueOf(instantCharge));
        for (Localisation l : leChemin.getPoints()){
            Temperature t =  l.actuelle;//TODO A modifier par getTempérature();
            if (t.tempEnC<=10){
                //TODO Besoin d'une coordination avec Christian.
                // Soit on a la température a chaque localisation (Coordonées géographique)
                // Soit on l'a que pour un certain intervale
                distanceSoumiseTemp=0;//leChemin.getIntervale(l); // TODO Affecter des distance via la méthode getIntervale() ?
            }else{
                //TODO Besoin d'une coordination avec Christian.
                // Soit on a la température a chaque localisation (Coordonées géographique)
                // Soit on l'a que pour un certain intervale
                distanceSoumiseSimple= 0;//RRP.getPaths().get(i).getDistance();
            }
            i+=1;
        }


        chUnderTenDegre=(((distanceSoumiseTemp*1000)/v)*instantCharge)*(120/100);

        chAboveTenDegre=(((distanceSoumiseSimple*1000)/v)*instantCharge);

        leChemin.KwParHNecessaire = chUnderTenDegre+chAboveTenDegre;
    }
    //Données en entrée:
    // liste de données de données du trajet
    // liste de données des températures prévisionnelles sur le trajet
    //charge actuelle de la batterie en Watt
    //poids transporté par le véhicule en kilogramme
    //sortie:
    // charge necessaire pour réaliser le trajet si le trajet ne peut pas être réalisé
    // 0  si le trajet peut être finialisé
    // Description :
    // Cette méthode est utilisé pendant la recharge du véhicule
    double meth3(double poidTransporte,double chargeBatterie, double [] temperaturesTrajet,RouteResponse RRP,int indexTrajet){
        double instantCharge,chUnderTenDegre,chAboveTenDegre;
        double poidTotal= poid+poidTransporte;
        int i=indexTrajet,j=0;
        double distancePPkm,distanceSoumiseTemp=0,distanceSoumiseSimple=0;
        //vitesse en m/s fixé a 20 km/h qui est la limitation minimal en france
        int v=20000/3600;
        instantCharge=poidTotal*g*k*v +1/2*p*SCx*v*3;


        while(i<=temperaturesTrajet.length){
            if (temperaturesTrajet[i]<=10){
                distanceSoumiseTemp=RRP.getPaths().get(i).getDistance();
            }else{
                distanceSoumiseSimple=RRP.getPaths().get(i).getDistance();
            }
            i+=1;
        }
        chUnderTenDegre=(((distanceSoumiseTemp*1000)/v)*instantCharge)*(120/100);

        chAboveTenDegre=(((distanceSoumiseSimple*1000)/v)*instantCharge);

        if((chUnderTenDegre+chAboveTenDegre)<=chargeBatterie){

            return 0;
        }else{
            return (chUnderTenDegre+chAboveTenDegre)-chargeBatterie;
        }

    }



}
