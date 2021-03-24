package zemoov.serenemouv.CMTA;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class Temperature {
    public Double tempEnC;

    public Temperature(Double temp) {
        this.tempEnC = temp;
    }

    public static Map<String,Object> jsonToMap(String str){
        Map<String,Object> map = new Gson().fromJson(
                str,new TypeToken<HashMap<String,Object>>() {}.getType());
        return map;
    }


    public  static void getTemperatureAtLocalisation(Localisation localisation){
        String API_KEY ="dc246c52db351dc2df2c29a84a267eb9";
        String Localisation= localisation.getNameLocation();
        String latitude = localisation.getLatitude().toString();
        String longitude = localisation.getLongitude().toString();
        //api.openweathermap.org/data/2.5/find?lat={lat}&lon={lon}&cnt={cnt}&appid={API key}
        String urlString ="http://api.openweathermap.org/data/2.5/weather?lat="+latitude+"&lon="+longitude+"&units=metric"+"&appid=" + API_KEY;
        try{
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while((line =rd.readLine())!=null){
                result.append(line);
            }
            rd.close();
            //System.out.println(result);

            Map<String, Object>respMap = jsonToMap(result.toString());
            Map<String, Object>mainMap = jsonToMap(respMap.get("main").toString());

            localisation.actuelle = new Temperature((Double)mainMap.get("temp"));
           // System.out.println("Temperature Courant :" +mainMap.get("temp"));

        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
