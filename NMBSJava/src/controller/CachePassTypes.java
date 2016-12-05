package controller;

import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONArray;

public class CachePassTypes {
	
	private static JSONArray passTypes = new JSONArray("[]");

	public static void cache() {
		try {
			passTypes = new JSONArray(URLCon.readUrl("http://nmbs-team.tk/api/typePass", "GET"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<String> getAllTypeNames() {
		ArrayList<String> tempArrayList = new ArrayList<String>();
		for (int j = 0; j < passTypes.length(); j++) {
			tempArrayList.add(passTypes.getJSONObject(j).getString("Name"));
		}
		return tempArrayList;
	}

	public static String getTypeName(int typePassID) {
		for (int i = 0; i < passTypes.length(); i++) {
			if (passTypes.getJSONObject(i).getInt("TypePassID") == typePassID) {
				return passTypes.getJSONObject(i).getString("Name");
			}
		}
		return "";
	}

	public static int getTypeID(String s) {
		for (int i = 0; i < passTypes.length(); i++) {
			if (passTypes.getJSONObject(i).getString("Name").toUpperCase().equals(s.toUpperCase())) {
				return passTypes.getJSONObject(i).getInt("TypePassID");
			}
		}
		return -1;
	}
	
	public static int getObjectCount(){
		return passTypes.length();
	}
	
	public static JSONArray getJSON() {
		return passTypes;
	}
}