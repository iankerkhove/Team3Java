package api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.JSONObject;

public class RouteberekeningAPI {
	JSONObject json = null;
	private String stepOn;
	private String stepOff;
	private ArrayList<RouteAPI> routes = new ArrayList<RouteAPI>();

	public RouteberekeningAPI(String van, String naar) {
		try {
			json = new JSONObject(readUrl("https://traintracks.online/api/Route/" + van + "/" + naar));
			this.stepOn = json.getString("StepOn");
			this.stepOff = json.getString("StepOff");
			for (int i = 0; i < json.getJSONArray("Routes").length(); i++) {
				RouteAPI r = new RouteAPI(json.getJSONArray("Routes").getJSONObject(i));
				routes.add(r);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public RouteberekeningAPI(String van, String naar, String tijd) {
		try {
			json = new JSONObject(readUrl("https://traintracks.online/api/Route/" + van + "/" + naar + "/" + tijd));
			this.stepOn = json.getString("StepOn");
			this.stepOff = json.getString("StepOff");
			for (int i = 0; i < json.getJSONArray("Routes").length(); i++) {
				RouteAPI r = new RouteAPI(json.getJSONArray("Routes").getJSONObject(i));
				routes.add(r);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("joj");
		}
	}

	public String getStepOn() {
		return stepOn;
	}

	public void setStepOn(String stepOn) {
		this.stepOn = stepOn;
	}

	public String getStepOff() {
		return stepOff;
	}

	public void setStepOff(String stepOff) {
		this.stepOff = stepOff;
	}

	public ArrayList<RouteAPI> getRoutes() {
		return routes;
	}

	public void setRoutes(ArrayList<RouteAPI> routes) {
		this.routes = routes;
	}

	public static String readUrl(String urlString) throws Exception {
		BufferedReader reader = null;
		try {
			URL url = new URL(urlString); 
			URLConnection connection = url.openConnection();
			connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.29 Safari/537.36");
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuffer buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read);
			return buffer.toString();

		} finally {
			if (reader != null)
				reader.close();
		}
	}

	public JSONObject getJson() {
		return json;
	}

	public void setJson(JSONObject json) {
		this.json = json;
	}
	public String toString(){
		String ss = "";
		ss += "Van: " + this.stepOn + "\nNaar: " + this.stepOff + "\n";
		ss += "";
		return ss;
	}
}
