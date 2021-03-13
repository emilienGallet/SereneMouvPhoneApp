package zemoov.serenemouv.GBE;

// import java.io.File;
// import java.io.FileNotFoundException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;

import org.graalvm.compiler.nodes.extended.GetClassNode;

import zemoov.serenemouv.CMTA.Cmta;
import zemoov.serenemouv.CMTA.Trajet;

public class GBE {
    public String n_amenageur,n_operateur,n_enseigne,id_station,n_station,ad_station;
    public int code_insee;
    public double Xlongitude,Ylatitude;
    public int nbre_pdc;
    public String id_pdc;
    public int puiss_max;
    public String type_prise,acces_recharge,accessibilite,observations;

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
    }

    public static void main(String[] args) {
        CsvFile csvFile = new CsvFile();
        GBE gbe = new GBE();
        csvFile.readfile();
    }
   
    // getter,setter
    public String getAcces_recharge() {
        return acces_recharge;
    }
    public String getAccessibilite() {
        return accessibilite;
    }
    public String getAd_station() {
        return ad_station;
    }
    public int getCode_insee() {
        return code_insee;
    }
    public String getId_pdc() {
        return id_pdc;
    }
    public String getId_station() {
        return id_station;
    }
    public String getN_amenageur() {
        return n_amenageur;
    }
    public String getN_enseigne() {
        return n_enseigne;
    }
    public String getN_operateur() {
        return n_operateur;
    }
    public String getN_station() {
        return n_station;
    }
    public int getNbre_pdc() {
        return nbre_pdc;
    }
    public String getObservations() {
        return observations;
    }
    public int getPuiss_max() {
        return puiss_max;
    }
    public String getType_prise() {
        return type_prise;
    }
    public double getXlongitude() {
        return Xlongitude;
    }
    public double getYlatitude() {
        return Ylatitude;
    }
    

    // fct
    /**
     * Liste de borne UTILISABLE, ORDONÉE du départ jusqu'à l'arrivée
     * @param lesConfigUser correspondant au configuration utilisateur
     *                      (véhicule,badgesPossible,puissanceMax,puissanceMin)
     * @return
     */
    public static ArrayList<Borne> bornesAutourDuTrajet(Cmta lesConfigUser) {
        Borne borne2;
        double distance, limiteDist=10;
        Cmta chemin;
        int count=0;
        ArrayList<Borne> bornesP = new ArrayList<>();
        // on récupère les points du trajet
        for(int i = 0 ;i< lesConfigUser.unChemin.getPoints().length;i++){
            Position pos = lesConfigUser.unChemin.getPoints().get(0);
            Position posData;
            // pour chaque ligne du csv lue
            for(int j =0;j<100;j++){
                // borne ligne j
                posData = borne2.position.getPoints();
                // calcul des bornes présentes dans les 10km alentours
                distance = distance(pos.latitude,pos.longitude,posData.latitude,posData.longitude) ;
                // borne.position.latitude && borne.position.longitude
                if(distance < limiteDist){
                    // ajoute la ligne ds un tableau des bornes proches
                    bornesP.add(borne2);
                    count++;
                }
            }
            
            
            
        }
        // affiche les bornes proches

        return bornesP;
    }

    /**
     * Calcule la distance entre 2 points géographique(lat,lng)
     * @param lat1
     * @param lon1
     * @param lat2
     * @param lon2
     * @return double distance
    */ 
    public static double distance(double lat1, double lon1, double lat2, double lon2) {
		if ((lat1 == lat2) && (lon1 == lon2)) {
			return 0;
		}
		else {
			double theta = lon1 - lon2;
			double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) 
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515 * 1.609344; // en KM
			return dist;
		}
	}
}
