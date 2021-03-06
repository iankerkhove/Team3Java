package model;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class LostObject {
private UUID objectID;
private Station station;
private String description;
private Date date;
private String trainID;
private long unixTimestamp;
public LostObject(Station station, String description, Date date, String trainID) {
	this.station = station;
	this.description = description;
	this.date = date;
	this.trainID = trainID;
	this.objectID = UUID.randomUUID();
	unixTimestamp = Instant.now().getEpochSecond();
}

public LostObject() {
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
public UUID getObjectID() {
	return objectID;
}

public void setObjectID(UUID objectID) {
	this.objectID = objectID;
}

public Station getStation() {
	return station;
}

public void setStation(Station station) {
	this.station = station;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public Date getDate() {
	return date;
}

public void setDate(Date date) {
	this.date = date;
}

public String getTrainID() {
	return trainID;
}

public void setTrainID(String trainID) {
	this.trainID = trainID;
}

@Override
public String toString() {
	return "LostObject [objectID=" + objectID + ", station=" + station.getStationName() + ", description=" + description + ", date="
			+ date + ", trainID=" + trainID + "]";
}




}
