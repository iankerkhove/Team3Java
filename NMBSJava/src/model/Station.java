package model;

import java.util.ArrayList;

public class Station {
	private int stationID;
	private Address address;
	private String stationName;
	private ArrayList<User> users = null;
	
	public Station(Address address, String stationName) {
		this.address = address;
		this.stationName = stationName;
		users = new ArrayList ();
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public void addUser(User u) {
		users.add(u);
	}
	
}
