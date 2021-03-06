package zemoov.serenemouv.GB;

import android.content.Intent;
import android.content.pm.PackageManager;

/**
 * @author Emilien
 * Module GB
 */
public class Gb {
    private Double capacityBattery; //kw
    private Double curentCapacityBattery; //kw
    private Double puissanceInstantanee;//kw

    public Gb(){
        super();
        //connectWithCanZE();
    }

    /**
     * @author Émilien
     * Get whole infos from the CanZE API
     */
    private void connectWithCanZE() {


        /*Context ctx=this;
        try {
            Intent i = ctx.getPackageManager().getLaunchIntentForPackage("com.whatsapp");
            ctx.startActivity(i);
        } catch (PackageManager.NameNotFoundException e) {
            System.out.println("Error connection");
        }*/
    }


    /**
     * @author Emilien
     * @return total capacity of this battery.
     */
    public Double getCapacityBattery() {
        return capacityBattery;
    }

    /**
     * @author Emilien
     * set total capacity of this battery.
     */
    private void setCapacityBattery(Double capacityBattery) {
        this.capacityBattery = capacityBattery;
    }

    /**
     * @author Emilien
     * @return curent capacity of this battery.
     */
    public Double getCurentCapacityBattery() {
        return curentCapacityBattery;
    }
    /**
     * @author Emilien
     * set curent capacity of this battery.
     */
    private void setCurentCapacityBattery(Double curentCapacityBattery) {
        this.curentCapacityBattery = curentCapacityBattery;
    }
    /**
     * @author Emilien
     * @return get the puissance instant given by the battery.
     * Unit is in w (joules per second)
     */
    public Double getPuissanceInstantanee() {
        return puissanceInstantanee;
    }

    /**
     * @author Emilien
     * define the curent instantaneous power.
     */
    private void setPuissanceInstantanee(Double puissanceInstantanee) {
        this.puissanceInstantanee = puissanceInstantanee;
    }

}