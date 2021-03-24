package zemoov.serenemouv.CMTA;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Source code taken and edit from IConvoit (https://github.com/emilienGallet/IConvoit). And also is in part my code ^_^
 * @author Chrithian, reviewed by emilien
 * @editor for the project Émilien
 * @version 0.2
 */
public class Localisation implements Serializable {
    private Long id;
    private String nameLocation;
    private ArrayList<Localisation> subLocalization;
    private Double longitude;
    private Double latitude;
    public Double hauteur;
    public Temperature actuelle;


    /**
     * Build an localization
     * @param nameLocation
     * @param latitude
     * @param longitude
     */
    public Localisation(String nameLocation, double latitude,double longitude, double hauteur) {
        super();
        if (!nameLocation.isEmpty()) {
            this.setNameLocation(nameLocation);
        }else {
            this.setNameLocation(String.valueOf(latitude)+"|"+String.valueOf(longitude));
        }
        this.setLongitude(longitude);
        this.setLatitude(latitude);
        this.hauteur = hauteur;
    }

    public Localisation() {
        super();
    }

    public ArrayList<Localisation> getSubLocalization() {
        return subLocalization;
    }

    public void setSubLocalization(final ArrayList<Localisation> subLocalization) {
        this.subLocalization = subLocalization;
    }

    public String getGeolocalization() {
        return this.getLatitude().toString()+this.getLongitude().toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameLocation() {
        return nameLocation;
    }

    public void setNameLocation(String nameLocation) {
        this.nameLocation = nameLocation;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
