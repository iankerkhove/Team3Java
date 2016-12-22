package model;

import java.time.Instant;
import java.util.UUID;

public class Route {
	private UUID routeID;
	private UUID departureStationID;
	private UUID arrivalStationID;
	private long unixTimestamp;
	public Route(UUID routeID, UUID departureStationID, UUID arrivalStationID) {
		super();
		this.routeID = routeID;
		this.departureStationID = departureStationID;
		this.arrivalStationID = arrivalStationID;
	}
	public Route() {
		
	}
	public UUID getRouteID() {
		return routeID;
	}
	public void setRouteID(UUID routeID) {
		this.routeID = routeID;
	}
	public UUID getDepartureStationID() {
		return departureStationID;
	}
	public void setDepartureStationID(UUID departureStationID) {
		this.departureStationID = departureStationID;
	}
	public UUID getArrivalStationID() {
		return arrivalStationID;
	}
	public void setArrivalStationID(UUID arrivalStationID) {
		this.arrivalStationID = arrivalStationID;
	}
	public void update() {
		this.unixTimestamp = Instant.now().getEpochSecond();
	}
	public void setLastUpdated(long unixTimestamp){
		this.unixTimestamp = unixTimestamp;
	}
	public long getUnixTimestamp() {
		return unixTimestamp;
	}

	
	
}
