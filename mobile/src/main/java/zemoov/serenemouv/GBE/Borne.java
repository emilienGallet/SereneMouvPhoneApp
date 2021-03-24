package zemoov.serenemouv.GBE;

import zemoov.serenemouv.CMTA.Localisation;
import zemoov.serenemouv.CMTA.Vehicule;

public class Borne extends GBE {

    Localisation position;


    public boolean peuxRechargerIci(Vehicule leVehicule) {

        return false ;
    }

    public Localisation getPosition() {
        return position;
    }

    public void setPosition(Localisation position) {
        this.position = position;
    }
}
