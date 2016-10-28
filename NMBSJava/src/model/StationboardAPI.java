package model;

import java.util.ArrayList;

import org.json.JSONArray;

public class StationboardAPI {
	private JSONArray json;
	private ArrayList<TrainAPI> trains = new ArrayList<TrainAPI>();

	public StationboardAPI(String station) {
		try {
			json = new JSONArray(RouteberekeningAPI.readUrl("https://traintracks.online/api/stationboard/" + station));
			for (int i = 0; i < json.length(); i++) {
				TrainAPI t = new TrainAPI(json.getJSONObject(i));
				trains.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<TrainAPI> getTrains() {
		return trains;
	}

	public void setTrains(ArrayList<TrainAPI> trains) {
		this.trains = trains;
	}

	public JSONArray getJson() {
		return json;
	}

	public void setJson(JSONArray json) {
		this.json = json;
	}
}
