package zemoov.serenemouv.CMTA;

import zemoov.serenemouv.CMTA.Exceptions.*;

public class Vehicule {

    Float poids;
    Float PTAC;
    Integer maxPeoplePlace;


    public void avaiblePlace(Integer nombreDePersonnes) throws VehiculeException{
        //define by CMTA-parametre-4
        if (nombreDePersonnes<1){
            throw new VehiculeException("No people in car");
        }
        if (nombreDePersonnes>this.maxPeoplePlace){
            throw new VehiculeException("Too many people in car");
        }
    }

    public void avaiableWeight(Integer bagages) throws VehiculeException, VehiculeWarning {
        if (bagages < 0){
            throw new VehiculeException("Negative weight bagages");
        }
        if (bagages > PTAC){
            throw new VehiculeWarning("PTAC is to hight");
        }
    }
}
