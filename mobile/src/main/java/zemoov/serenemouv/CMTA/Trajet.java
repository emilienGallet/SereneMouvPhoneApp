package zemoov.serenemouv.CMTA;

import java.util.ArrayList;

import zemoov.serenemouv.CMTA.Exceptions.TrajectException;

public class Trajet {

    Localisation start;
    Localisation end;
    ArrayList<Localisation> step;
    Joules puissanceParSecode;

    public Trajet(Preference saPreference) throws TrajectException {
        super();

    }
}
