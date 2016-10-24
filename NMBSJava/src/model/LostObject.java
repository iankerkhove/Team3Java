package model;

import java.util.Date;

public class LostObject {
private int objectID;
private int stationID;
private String description;
private Date date;
private int trainID;

public LostObject(int objectID, int stationID, String description, Date date, int trainID) {
	super();
	this.objectID = objectID;
	this.stationID = stationID;
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

public int getStationID() {
	return stationID;
}

public void setStationID(int stationID) {
	this.stationID = stationID;
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
	return "LostObject [objectID=" + objectID + ", stationID=" + stationID + ", description=" + description + ", date="
			+ date + ", trainID=" + trainID + "]";
}




}
