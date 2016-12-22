package model;

import java.time.Instant;
import java.util.UUID;

public class Line {
	private UUID lineID;
	private UUID routeID;
	private String trainType;
	private long unixTimestamp;
	public Line(UUID routeID, String trainType) {
		super();
		this.lineID = UUID.randomUUID();
		this.routeID = routeID;
		this.trainType = trainType;
		this.unixTimestamp = Instant.now().getEpochSecond();
	}
	public UUID getLineID() {
		return lineID;
	}
	public void setLineID(UUID lineID) {
		this.lineID = lineID;
	}
	public UUID getRouteID() {
		return routeID;
	}
	public void setRouteID(UUID routeID) {
		this.routeID = routeID;
	}
	public String getTrainType() {
		return trainType;
	}
	public void setTrainType(String trainType) {
		this.trainType = trainType;
	}
	public void setUnixTimestamp(long unixTimestamp) {
		this.unixTimestamp = unixTimestamp;
	}
	public long getUnixTimestamp() {
		return unixTimestamp;
	}

	public void update() {
		this.unixTimestamp = Instant.now().getEpochSecond();
	}
	public void setLastUpdated(long unixTimestamp){
		this.unixTimestamp = unixTimestamp;
	}
	
	
}
