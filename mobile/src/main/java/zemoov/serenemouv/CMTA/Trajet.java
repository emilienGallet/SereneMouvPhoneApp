package zemoov.serenemouv.CMTA;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import java.util.List;

import zemoov.serenemouv.CMTA.Exceptions.TrajectException;

/**
 * @author Émilien
 * @version 1.0
 * Un trajet est différent d'un "path"
 * Un trajet est le départ initial et la destination final.
 * Il n'y a pas de subdivision de trajet en sous trajet.
 * En effet cette subdivision est appeler un Path dont la somme de celles-ci forme le Path global soit le Trajet.
 */
public class Trajet {
    Preference saPreference;
    Path unChemin;//Contien une liste de localisation(depart,arrivée inclut) +temps de trajet.
    LocalDateTime timeStart;
    Integer estAccesible;//[0 = ; 1 = Attention ; 2 = Oui et ce quelquesoit la vitesse dans la limite légale]

    public Trajet(Preference saPreference) throws TrajectException {
        super();
    }

    public Integer totalTimeDuration(){
        Integer i = 0;
        //TODO Calculing time
        return i;
    }

    /*
     * Vérifie et retourne le cas échéant un trajet si il en existe un.
     */
    public static Trajet trajectBuilder(Localisation start, Localisation end, List<Localisation> step, Preference saPreference, Boolean carrefourDangereux, Boolean travauxSector) throws TrajectException {
        Trajet leTrajet = new Trajet(saPreference);
        Path unChemin = Graph.planTraject(start,end,step);//TODO vérifier carrefour dangereux et sa préférence
        return leTrajet;
    }

    public Localisation lieuDeDepart(){
        return this.unChemin.getPoints().get(0);
    }
    public Localisation lieuDArrivee(){
        ArrayList<Localisation> list = (ArrayList<Localisation>) this.unChemin.getPoints();
        return list.get(list.size());
    }
}
