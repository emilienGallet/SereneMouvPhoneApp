package zemoov.serenemouv;
import com.graphhopper.directions.api.client.model.RouteResponsePath;
import com.graphhopper.directions.api.client.model.RouteResponse;


public class CPDispo {
    //la gravité
    final double g= 9.18;
    //masse en kg
    double poid=2555;
    //coef RouteResponse routeRde frottement des peneux
    //faute de possibilitées de messure nous prendrons une valeure moyenne de 0.013
    double k=0.013;
    //ρ(rho)= masse volumique de l’air, à 20° elle est de 1,204 kg/m3
    //recherche de sources a effectuer
    final double p=1.204;
    //rapport entre la surface frontale d’un véhicule (S) et sa résistance à l’écoulement de l’air (CX)
    // ici en attente de données sur le modèle on le considère à 0,6583
    final double SCx=0.6583;

    CPDispo(){}


    //13.2kwh 83km   0,15903614457831KWh/km  159,036Wh/km  0,159036Wh/m

    //Donnnées en entrée:
    // liste de données des températures prévisionnelles sur la zone
    //charge actuelle de la batterie en Watt
    //poids transporté par le véhicule en kilogramme
    //sortie: distance maximale pouvant être parcourue en km
    double meth1(double poidTransporte,double chargeBatterie, double [] temperaturesZone){
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
    //sortie:
    // charge necessaire pour réaliser le trajet
    double meth2(double poidTransporte,double chargeBatterie, double [] temperaturesTrajet,RouteResponse RRP){
        double instantCharge,chUnderTenDegre,chAboveTenDegre;
        double poidTotal= poid+poidTransporte;
        int i=0,j=0;
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

        return chUnderTenDegre+chAboveTenDegre;


    }

    //Données en entrée:
    // liste de données de données du trajet
    // liste de données des températures prévisionnelles sur le trajet
    //charge actuelle de la batterie en Watt
    //poids transporté par le véhicule en kilogramme
    //sortie:
    // charge necessaire pour réaliser le trajet si le trajet ne peut pas être réalisé
    // 0  si le trajet peut être finialisé
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
