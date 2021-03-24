package zemoov.serenemouv.ctp1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import com.google.gson.*;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.reflect.*;
import java.util.HashMap;


public class Test {


    public static Map<String,Object> jsonToMap(String str){
        Map<String,Object> map = new Gson().fromJson(
                str,new TypeToken<HashMap<String,Object>>() {}.getType());
        return map;
    }

    public void main(String[] args)
    {
        String API_KEY ="dc246c52db351dc2df2c29a84a267eb9";
        String Localisation="Lyon,FR";
        String latitude = args[0];
        String longitude = args[1];
        String urlString ="http://api.openweathermap.org/data/2.5/weather?q=" + Localisation +
                "&appid" + API_KEY + "&units=imperial";
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
            System.out.println(result);

            Map<String, Object>respMap = jsonToMap(result.toString());
            Map<String, Object>mainMap = jsonToMap(respMap.get("main").toString());


            System.out.println("Temperature Courant :" +mainMap.get("temp"));

            }catch(IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
