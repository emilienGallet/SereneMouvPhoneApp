package zemoov.serenemouv.CMTA;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 
 * @author Émilien 
 * Source code taken from IConvoit (https://github.com/emilienGallet/IConvoit). And also is my code ^_^
 */
public class Graph {

	public static Path planTraject(Localisation start, Localisation end, List<Localisation> step) {
		/*
		 * for (Localization anStep : step) { url += "point=" + anStep.getLatitude() +
		 * "," + anStep.getLongitude(); }
		 */
		/**
		 * Code from : https://docs.graphhopper.com/#tag/Routing-API little bit edited
		 * for our API Need to add
		 * https://mvnrepository.com/artifact/com.squareup.okhttp3/okhttp
		 */
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(urlTravel(start, end)).get().build();
		Response response = null;
		String s;
		try {
			response = client.newCall(request).execute();
			/* END code from graphHooper */
			s = response.body().string();
			JSONObject json = new JSONObject(s);
			System.err.println(json.toString());
			Integer time = tempsDeRoute(json);
			JSONArray jsArry = pointsListCoordinates(json);
			//TODO convert JSONArray to ArrayList<Localization>
			return new Path(JsonArrayToList(jsArry),time);
		} catch (IOException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Parse points in json to arrayList
	 * @param jsArry
	 * @return
	 */
	private static ArrayList<Localisation> JsonArrayToList(JSONArray jsArry) throws JSONException {
		ArrayList<Localisation> list = new ArrayList<Localisation>();
		JSONArray tmp;
		Localisation point;
		Double latitude,longitude;
		for (int i = 0; i < jsArry.length(); i++) {
			tmp =jsArry.getJSONArray(i);
			latitude = tmp.getDouble(1);
			longitude =tmp.getDouble(0);
			point = new Localisation("", latitude, longitude);
			list.add(point);
		}
		return list;
	}

	/**
	 * 
	 * @param start
	 * @param end
	 * @return the specific url for the travel
	 */
	private static String urlTravel(Localisation start, Localisation end) {
		String apiKey = "2f30588d-1eeb-409b-a822-b256accb329f";
		String url = "https://graphhopper.com/api/1/route?";
		url += "point=" + start.getLatitude() + "," + start.getLongitude();
		url += "&point=" + end.getLatitude() + "," + end.getLongitude();
		url += "&points_encoded=false";
		url += "&vehicle=car" + "&locale=en";
		url += "&turn_costs=true";
		url += "&key=" + apiKey;
		return url;
	}

	/**
	 * Get the JSONArray of coordinates
	 * @param o
	 * @return
	 */
	private static JSONArray pointsListCoordinates(JSONObject o) throws JSONException {
		return o.getJSONArray("paths").getJSONObject(0).getJSONObject("points").getJSONArray("coordinates");
	}
	
	private static Integer tempsDeRoute(JSONObject o) throws JSONException {
		return o.getJSONArray("paths").getJSONObject(0).getInt("time");
	}

	public static void main(String[] args) {
		/*
		 * Test of trajectPlan
		 */
		Localisation carnot = new Localisation("UJM Carnot", 45.45218, 4.38656);
		Localisation metare = new Localisation("UJM Metare", 45.42331, 4.42541);
		System.out.println(urlTravel(carnot, metare));
		System.out.println(planTraject(carnot, metare, null).getPoints());
		
	}

}
