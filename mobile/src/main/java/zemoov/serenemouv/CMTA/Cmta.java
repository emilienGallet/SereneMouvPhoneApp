package zemoov.serenemouv.CMTA;

import java.util.ArrayList;

import zemoov.serenemouv.CMTA.Exceptions.*;

/**
 * @author emilien
 */
public class Cmta {
    Preference saPreference; // Préférence du Trajet voir Preference.java
    Vehicule engage; // Le véhicule qui va être utiliser pour le trajet.
    Integer puissanceMin; // La puissance minimal de rechargement du véhicule exigé par l'utilisateur
    Integer puissanceMax; // La puissance maximal de rechargement du véhicule exigé par l'utilisateur
    Trajet leTrajet; // Le Trajet en lui même voir Trajet.java
    ArrayList<Operator> badgesOperateur; // La liste des badges opérateur que l'utilisateur dispose
    Integer nbPersonnes; // Le nombre de personnes présente dans le véhicule pour ce trajet.
    Boolean gotOnlineCB; // Vérifie si l'utilisateur dispose d'une carte bancaire pour payer en ligne

    Cmta(Integer nombreDePersonnes,Vehicule engage,ArrayList<Operator> badgesPossible,Trajet leTrajet,Integer bagages) throws CMTAException, CMTAWarning {
        // On vérifie si le nombre de personnes est possible dans le véhicule
        engage.avaiblePlace(nombreDePersonnes);
        // On vérifie si il ne risque pas d'avoir un dépacement du poid maximal autorisé
        engage.avaiableWeight(bagages);


    }

    static Cmta build(Integer nombreDePersonnes,ArrayList<Operator> badgesPossible,Vehicule engage,
         Localisation start,Localisation end,ArrayList<Localisation> step, Integer bagages)
            throws CMTAException{

            Trajet leTrajet = null;
            //TODO Vérifier le trajet si il est possible

            //SI OUI on continue
        Cmta a = null;
        try {
            a= new Cmta(nombreDePersonnes,engage,badgesPossible,leTrajet,bagages);
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
