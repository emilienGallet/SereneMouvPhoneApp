package zemoov.serenemouv.CMTA;

import java.util.ArrayList;

import zemoov.serenemouv.CMTA.Exceptions.*;

/**
 * @author emilien
 */
public class Cmta {
    Preference saPreference;
    Vehicule engage;
    Integer puissanceMin;
    Integer puissanceMax;
    Trajet leTrajet;
    ArrayList<Operator> badgesOperateur;
    Integer personnes;

    Cmta(Integer nombreDePersonnes,Vehicule engage,ArrayList<Operator> badgesPossible,Trajet leTrajet,Integer bagages) throws CMTAException, CMTAWarning {
        engage.avaiblePlace(nombreDePersonnes);
        engage.avaiableWeight(bagages);
    }

    static Cmta build(Integer nombreDePersonnes,ArrayList<Operator> badgesPossible,Vehicule engage,
         Localisation start,Localisation end,ArrayList<Localisation> step, Integer bagages)
            throws CMTAException{

            Trajet t = null;
            //TODO Vérifier le trajet si il est possible

            //SI OUI on continue
        Cmta a = null;
        try {
            a= new Cmta(nombreDePersonnes,engage,badgesPossible,t,bagages);
        } catch (CMTAWarning cmtaWarning) {
            cmtaWarning.printStackTrace();
        }
        return a;
    }

    static Cmta build(Integer nombreDePersonnes,ArrayList<Operator> badgesPossible,Vehicule engage,
         Localisation start,Localisation end,ArrayList<Localisation> step) throws CMTAException{
        return build(nombreDePersonnes,badgesPossible,engage,start,end,step,0);
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
        Trajet t;
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
