package controller;

import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONArray;

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
	public static double getPrice(int typeId) {
		if (typeId < ticketTypes.length()) {
			return ticketTypes.getJSONObject(typeId).getDouble("Price");
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
	public static int getTypeTicketID(String name, int comfortClass) {
		for (int j = 0; j < ticketTypes.length(); j++) {
			if (ticketTypes.getJSONObject(j).getString("Name").toUpperCase().equals(name.toUpperCase())
					&& ticketTypes.getJSONObject(j).getInt("ComfortClass") == comfortClass) {
				return ticketTypes.getJSONObject(j).getInt("TypeTicketID");
			}
		}
		return -1;
	}
	
	// get name from typeid
	public static double getName(int typeTicketID) {
		for (int i = 0; i < ticketTypes.length(); i++) {
			if (ticketTypes.getJSONObject(i).getInt("TypeTicketID") == typeTicketID) {
				return ticketTypes.getJSONObject(i).getDouble("Price");
			}
		}
		return -1;
	}
	
	// get amount of objects
	public static int getObjectCount(){
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
	public static int getComfortClass(int typeTicketID){
		for (int i = 0; i < ticketTypes.length(); i++) {
			if (ticketTypes.getJSONObject(i).getInt("TypeTicketID") == typeTicketID) {
				return ticketTypes.getJSONObject(i).getInt("ComfortClass");
			}
		}
		return -1;
	}

	public static JSONArray getJSON() {
		return ticketTypes;
	}
}