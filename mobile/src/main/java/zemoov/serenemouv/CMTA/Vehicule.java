package zemoov.serenemouv.CMTA;

import zemoov.serenemouv.CMTA.Exceptions.*;

public class Vehicule {

    public static final int MAX_VALUE_SCENARIO_FULL = 1943;//en Kg
    public static final int MAX_VALUE_SCENARIO_EMPTY = 1468;//en Kg
    public static final Integer POID_MOYEN_KG = 90;
    public Float poidsCourant; // Poid actuel de la voiture. Par défaut c'est le PTAC
    public final Float PTAC;//en Kg
    public final Float poidAVide;//en Kg
    public Integer maxPeoplePlace = 5;//Prendre par défaut la valeur maximal connue du sénario choisit
    public Double températureInterieur;
    public Double températureExterieur;
    public Integer nbPeople;
    public Double chargeActuelle;
    public Double chargeTotalPossible;
    public Float puissanceDC;

    public Vehicule(){
        this.PTAC = Float.valueOf(MAX_VALUE_SCENARIO_FULL);
        this.poidAVide = Float.valueOf(MAX_VALUE_SCENARIO_EMPTY);
        this.poidsCourant = poidAVide;
        this.chargeActuelle = 0d;
    }

    /**
     * @param ptac en Kg
     * @param poidAVide en Kg
     */
    public Vehicule(Float ptac, Float poidAVide) {
        this.PTAC = ptac;
        this.poidAVide = poidAVide;
        this.poidsCourant = PTAC;
    }

    /**
     *
     * @param ptac en Kg
     * @param poidsCourant en Kg
     * @param poidAVide en Kg
     */
    public Vehicule(Float ptac, Float poidsCourant, Float poidAVide) {
        this.PTAC = ptac;
        this.poidsCourant = poidsCourant;
        this.poidAVide = poidAVide;
    }

    /**
     * Permet de savoir si le nombre de personnes indiqué permet d'être dans le véhicule
     * @param nombreDePersonnes actuellement dans le véhicule
     * @throws VehiculeException
     */
    public void avaiblePlace(Integer nombreDePersonnes) throws VehiculeException{
        //define by CMTA-parametre-4.*
        if (nombreDePersonnes<1){
            throw new VehiculeException("No people in car");
        }
        if (nombreDePersonnes>this.maxPeoplePlace){
            //TODO vérifier que this.maxPoeplePlace prend la valeur par défaut
            throw new VehiculeException("Too many people in car");
        }
        //IS VALID
        this.nbPeople = nombreDePersonnes;
    }

    /**
     * Permet de savoir si le poid total autorisé en charge (PTAC) n'est pas dépassé
     * @param bagages
     * @throws VehiculeException
     * @throws VehiculeWarning
     */
    public void avaiableWeight(Integer bagages) throws VehiculeException, VehiculeWarning {
        if (bagages < 0){
            throw new VehiculeException("Negative weight bagages");
        }
        if (bagages+(nbPeople*POID_MOYEN_KG) > PTAC){
            throw new VehiculeWarning("PTAC is to hight");
        }
    }
}
