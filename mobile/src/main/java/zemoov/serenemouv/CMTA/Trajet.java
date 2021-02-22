package zemoov.serenemouv.CMTA;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;

import zemoov.serenemouv.CMTA.Exceptions.TrajectException;

public class Trajet {

    Localisation start;
    Localisation end;
    ArrayList<Localisation> step;
    Joules puissanceParSecode;
    LocalDateTime timeStart;
    ChronoLocalDateTime tempsTrajet;

    public Trajet(Preference saPreference) throws TrajectException {
        super();

    }
}
