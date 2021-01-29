package zemoov.serenemouv;

public class CPDispo {
    CPDispo(){}


    //Donnnées en entrée:
    // liste de données des températures prévisionnelles sur la zone
    //charge actuelle de la batterie en Watt
    //poids transporté par le véhicule en kilogramme
    //sortie: distance maximale pouvant être parcourue en km
    meth1(){
        int charge;
        int masse;
        int poid;
        //coef de frottement des peneux
        //faute de possibilitées de messure nous prendrons une valeure moyenne de 0.013
        double k=0.013;
        //vitesse en m/s
        int v;
        //ρ(rho)= masse volumique de l’air, à 20° elle est de 1,204 kg/m3
        //recherche de sources a effectuer
        float p;
        //rapport entre la surface frontale d’un véhicule (S) et sa résistance à l’écoulement de l’air (CX)
        // ici en attente de données sur le modèle on le considère à 0,6583
        double SCx=0.6583;

        charge=masse*poid*k*v +1/2*p*SCx*v*3;
    }

    //Données en entrée:
    // liste de données de données du trajet
    // liste de données des températures prévisionnelles sur le trajet
    //charge actuelle de la batterie en Watt
    //poids transporté par le véhicule en kilogramme
    //sortie:
    // charge necessaire pour réaliser le trajet
    meth2(){}

    //Données en entrée:
    // liste de données de données du trajet
    // liste de données des températures prévisionnelles sur le trajet
    //charge actuelle de la batterie en Watt
    //poids transporté par le véhicule en kilogramme
    //sortie:
    // charge necessaire pour réaliser le trajet
    // booléen indiquant si le trajet peut être finialisé
    meth3(){}



}
