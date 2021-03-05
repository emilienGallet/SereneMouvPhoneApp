package zemoov.serenemouv.GBE;

// import java.io.File;
// import java.io.FileNotFoundException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;

import zemoov.serenemouv.CMTA.Cmta;
import zemoov.serenemouv.CMTA.Trajet;

public class GBE {
    String n_amenageur,n_operateur,n_enseigne,id_station,n_station,ad_station;
    int code_insee;
    double Xlongitude,Ylatitude;
    int nbre_pdc;
    String id_pdc;
    int puiss_max;
    String type_prise,acces_recharge,accessibilite,observations;
    String date_maj;
    String source;

    // @Override
    // protected void onCreate(Bundle savedInstanceState) {
    //     super.onCreate(savedInstanceState);
    //     csvFile csvFile = new csvFile();
    //     GBE gbe = new GBE();
    //     csvFile.readfile();
    // }

    public GBE(){}

    public GBE(String n_amenageur,String n_operateur,String n_enseigne,String id_station,String n_station,
        String ad_station,int code_insee,double Xlongitude,double Ylatitude,int nbre_pdc,String id_pdc,
        int puiss_max,String type_prise,String acces_recharge,String accessibilite,String observations,String date_maj,String source){
            this.n_amenageur=n_amenageur;//0
            this.n_operateur=n_operateur;//1
            this.n_enseigne=n_enseigne; //2
            this.id_station=id_station;//3
            this.n_station=n_station;//4
            this.ad_station=ad_station;//5
            this.code_insee=code_insee;//6
            this.Xlongitude=Xlongitude;//7
            this.Ylatitude=Ylatitude;//8
            this.nbre_pdc=nbre_pdc;//9
            this.id_pdc=id_pdc;//10
            this.puiss_max=puiss_max;//11
            this.type_prise=type_prise;//12
            this.acces_recharge=acces_recharge;//13
            this.accessibilite=accessibilite;//14
            this.observations=observations;//15
            this.date_maj=date_maj;//16
            this.source=source;//17
    }

    public static int main(String[] args) {
        CsvFile csvFile = new CsvFile();
        GBE gbe = new GBE();
        csvFile.readfile();
        return 1;
    }

    /**
     * Liste de borne UTILISABLE, ORDONÉE du départ jusqu'à l'arrivée
     * @param lesConfigUser correspondant au configuration utilisateur
     *                      (véhicule,badgesPossible,puissanceMax,puissanceMin)
     * @return
     */
    public static ArrayList<Borne> bornesAutourDuTrajet(Cmta lesConfigUser) {

        return null;
    }
}
