package controller;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;

@Deprecated
public class CacheExistingSations {

	private static ArrayList<String> allStations;
	private static ArrayList<String> allStationIDs;

	public static void cache() {
		allStations = new ArrayList<String>();
		allStationIDs = new ArrayList<String>();
		try {
			JSONArray temp = new JSONArray(URLCon.readUrl("http://nmbs-team.tk/api/station", "GET"));
			for (int i = 0; i < temp.length(); i++) {
				allStations.add(temp.getJSONObject(i).getString("Name"));
				allStationIDs.add(temp.getJSONObject(i).getString("StationID"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<String> getAllStations() {
		return allStations;
	}

	public static ArrayList<String> getAllStationIDs() {
		return allStationIDs;
	}
}
