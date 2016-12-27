package model;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class LostObject
{
	private UUID objectID;
	private UUID stationID;
	private String description;
	private String date;
	private String trainID;
	private long lastUpdated;
	private Boolean found = false;
	
	public LostObject()
	{}

	public LostObject(UUID station, String description, String date, String trainID, Boolean found)
	{
		this.stationID = station;
		this.description = description;
		this.date = date;
		this.trainID = trainID;
		this.found = found;
		this.objectID = UUID.randomUUID();
		this.lastUpdated = Instant.now().getEpochSecond();
	}

	public UUID getStationID() {
		return stationID;
	}

	public void setStationID(UUID stationID) {
		this.stationID = stationID;
	}

	public UUID getObjectID()
	{
		return objectID;
	}

	public void setObjectID(UUID objectID)
	{
		this.objectID = objectID;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getDate()
	{
		return date;
	}

	public void setDate(String date)
	{
		this.date = date;
	}

	public String getTrainID()
	{
		return trainID;
	}

	public void setTrainID(String trainID)
	{
		this.trainID = trainID;
	}

	public long getLastUpdated()
	{
		return lastUpdated;
	}

	public void setLastUpdated(long lastUpdated)
	{
		this.lastUpdated = lastUpdated;
	}

	public void update()
	{
		this.lastUpdated = Instant.now().getEpochSecond();
	}

	@Override
	public String toString()
	{
		return "LostObject [objectID=" + objectID + ", station=" + stationID + ", description="
				+ description + ", date=" + date + ", trainID=" + trainID + "]";
	}
	public void setFound(Boolean found) {
		this.found = found;
		
	}
	public Boolean getFound() {
		return this.found;
	}
}
