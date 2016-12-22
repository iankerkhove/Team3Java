package model;

import java.time.Instant;
import java.util.UUID;

public class Station {
	private UUID stationID;
	private Address address;
	private String stationName;
	private long unixTimestamp;
	private String cox;
	private String coy;
	

	public Station(Address address, String stationName) {
		this.address = address;
		this.stationName = stationName;
		this.unixTimestamp = Instant.now().getEpochSecond();
		this.stationID=UUID.randomUUID();
	}
	
	public Station() {
	
	}
	public String getCox() {
		return cox;
	}

	public void setCox(String cox) {
		this.cox = cox;
	}

	public String getCoy() {
		return coy;
	}

	public void setCoy(String coy) {
		this.coy = coy;
	}
	public void setLastUpdated(long unixTimestamp){
		this.unixTimestamp = unixTimestamp;
	}
	public long getUnixTimestamp() {
		return unixTimestamp;
	}

	public void update() {
		this.unixTimestamp = Instant.now().getEpochSecond();
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public UUID getStationID() {
		return stationID;
	}

	public void setStationID(UUID stationID) {
		this.stationID = stationID;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	
	
}
