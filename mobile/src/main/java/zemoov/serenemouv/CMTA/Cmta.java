package zemoov.serenemouv.CMTA;

import java.util.ArrayList;

import zemoov.serenemouv.CMTA.Exceptions.TrajectException;

/**
 * @author emilien
 */
public class Cmta {
    Preference saPreference;
    Integer puissanceMin;
    Integer puissanceMax;
    Trajet leTrajet;


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
        if (preference.equalsIgnoreCase("le plus économe")) {
            choisirPreferenceTrajet(Preference.ECO);
        }
        if (preference.equalsIgnoreCase("équilibré")) {
            choisirPreferenceTrajet(Preference.NORMAL);
        }
    }

    void choisirPreferenceTrajet(Preference p) {
        this.saPreference = p;
    }

    void trouverTrajet(){
        Trajet t = null;
        try {
            t = new Trajet(saPreference);
        }catch(TrajectException err){
            //TODO afficher que le trajet n'est pas possible ou accesible
            err.printStackTrace();
            return;
        }
        this.leTrajet = t;
    }

    
}
