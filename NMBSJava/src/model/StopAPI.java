package model;

import java.util.ArrayList;

import org.json.JSONObject;

public class StopAPI {
	private ArrayList<StationAPI> stations = new ArrayList<StationAPI>();
	private int count;

	public StopAPI(JSONObject json) {
		for (int i = 0; i < json.getJSONArray("Stations").length(); i++) {
			StationAPI s = new StationAPI(json.getJSONArray("Stations").getJSONObject(i));
			stations.add(s);
		}
		this.count = json.getInt("Count");
	}

	public ArrayList<StationAPI> getStations() {
		return stations;
	}

	public void setStations(ArrayList<StationAPI> stations) {
		this.stations = stations;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
