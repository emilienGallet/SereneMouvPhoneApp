package zemoov.serenemouv.CMTA;

import java.util.ArrayList;

/**
 * Source code taken from IConvoit (https://github.com/emilienGallet/IConvoit). And also is in part my code ^_^
 * @author Chrithian, reviewed by emilien
 * @version 0.2
 */
public class Localisation {
    private Long id;
    private String nameLocation;
    private ArrayList<Localisation> subLocalization;
    private Double longitude;
    private Double latitude;



    /**
     * Build an localization
     * @param nameLocation
     * @param latitude
     * @param longitude
     */
    public Localisation(String nameLocation, double latitude,double longitude) {
        super();
        if (!nameLocation.isEmpty()) {
            this.setNameLocation(nameLocation);
        }else {
            this.setNameLocation(String.valueOf(latitude)+"|"+String.valueOf(longitude));
        }
        this.setLongitude(longitude);
        this.setLatitude(latitude);
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
