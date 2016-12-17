package model;

import java.util.ArrayList;

public class Station {
	private int stationID;
	private Address address;
	private String stationName;
	
	public Station(Address address, String stationName) {
		this.address = address;
		this.stationName = stationName;
		
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getStationID() {
		return stationID;
	}

	public void setStationID(int stationID) {
		this.stationID = stationID;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	
	
}
