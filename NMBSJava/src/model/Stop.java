package model;

import java.util.ArrayList;

import org.json.JSONObject;

public class Stop {
	private ArrayList<Station> stations = new ArrayList<Station>();
	private int count;

	public Stop(JSONObject json) {
		for (int i = 0; i < json.getJSONArray("Stations").length(); i++) {
			Station s = new Station(json.getJSONArray("Stations").getJSONObject(i));
			stations.add(s);
		}
		this.count = json.getInt("Count");
	}

	public ArrayList<Station> getStations() {
		return stations;
	}

	public void setStations(ArrayList<Station> stations) {
		this.stations = stations;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
