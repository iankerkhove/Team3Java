package model;

import java.util.Date;

public class LostObject {
private int objectID;
private Station station;
private String description;
private Date date;
private int trainID;

public LostObject(Station station, String description, Date date, int trainID) {
	this.station = station;
	this.description = description;
	this.date = date;
	this.trainID = trainID;
}

public int getObjectID() {
	return objectID;
}

public void setObjectID(int objectID) {
	this.objectID = objectID;
}

public Station getStation() {
	return station;
}

public void setStationID(Station station) {
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

public int getTrainID() {
	return trainID;
}

public void setTrainID(int trainID) {
	this.trainID = trainID;
}

@Override
public String toString() {
	return "LostObject [objectID=" + objectID + ", station=" + station.getStationName() + ", description=" + description + ", date="
			+ date + ", trainID=" + trainID + "]";
}




}
