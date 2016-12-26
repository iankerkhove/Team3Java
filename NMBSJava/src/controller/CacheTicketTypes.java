package controller;

import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONArray;

@Deprecated
public class CacheTicketTypes {

	private static JSONArray ticketTypes = new JSONArray("[]");

	// cache all types
	public static void cache() {
		try {
			ticketTypes = new JSONArray(URLCon.readUrl("http://nmbs-team.tk/api/typeTicket", "GET"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// get price from ticketID
	public static double getPrice(String typeId) {
		for (int i = 0; i < ticketTypes.length(); i++) {
			if (typeId.equals(ticketTypes.getJSONObject(i).getString("TypeTicketID"))) {
				return ticketTypes.getJSONObject(i).getDouble("Price");
			}			
		}
		return -1;
	}

	// get price from type and class
	public static double getPrice(String ticketType, int comfortClass) {
		for (int i = 0; i < ticketTypes.length(); i++) {
			if (ticketTypes.getJSONObject(i).getString("Name").toUpperCase().equals(ticketType.toUpperCase())
					&& ticketTypes.getJSONObject(i).getInt("ComfortClass") == comfortClass) {
				return ticketTypes.getJSONObject(i).getDouble("Price");
			}
		}
		return -1;
	}

	// get typeticketID from name and class
	public static String getTypeTicketID(String name, int comfortClass) {
		for (int j = 0; j < ticketTypes.length(); j++) {
			if (ticketTypes.getJSONObject(j).getString("Name").toUpperCase().equals(name.toUpperCase())
					&& ticketTypes.getJSONObject(j).getInt("ComfortClass") == comfortClass) {
				return ticketTypes.getJSONObject(j).getString("TypeTicketID");
			}
		}
		return "";
	}

	// get name from typeid
	public static double getName(String typeTicketID) {
		for (int i = 0; i < ticketTypes.length(); i++) {
			if (ticketTypes.getJSONObject(i).getString("TypeTicketID").equals(typeTicketID)) {
				return ticketTypes.getJSONObject(i).getDouble("Price");
			}
		}
		return -1;
	}

	// get amount of objects
	public static int getObjectCount() {
		return ticketTypes.length();
	}

	// get all types
	public static ArrayList<String> getTypeNames() {
		ArrayList<String> tempArrayList = new ArrayList<String>();
		for (int j = 0; j < ticketTypes.length(); j++) {
			tempArrayList.add(ticketTypes.getJSONObject(j).getString("Name") + " (klasse "
					+ ticketTypes.getJSONObject(j).getInt("ComfortClass") + ")");
		}
		return tempArrayList;
	}

	// get comfortclass from typeticketID
	public static int getComfortClass(String typeTicketID) {
		for (int i = 0; i < ticketTypes.length(); i++) {
			if (ticketTypes.getJSONObject(i).getString("TypeTicketID").equals(typeTicketID)) {
				return ticketTypes.getJSONObject(i).getInt("ComfortClass");
			}
		}
		return -1;
	}

	public static JSONArray getJSON() {
		return ticketTypes;
	}

	public static String get(int index) {
		if (index < ticketTypes.length()) {
			return ticketTypes.getJSONObject(index).getString("Name");
		}
		return "";
	}
}