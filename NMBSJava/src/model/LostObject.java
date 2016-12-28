package model;

import java.time.Instant;
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

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((found == null) ? 0 : found.hashCode());
		result = prime * result + ((objectID == null) ? 0 : objectID.hashCode());
		result = prime * result + ((stationID == null) ? 0 : stationID.hashCode());
		result = prime * result + ((trainID == null) ? 0 : trainID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LostObject other = (LostObject) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		}
		else if (!date.equals(other.date))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		}
		else if (!description.equals(other.description))
			return false;
		if (found == null) {
			if (other.found != null)
				return false;
		}
		else if (!found.equals(other.found))
			return false;
		if (objectID == null) {
			if (other.objectID != null)
				return false;
		}
		else if (!objectID.equals(other.objectID))
			return false;
		if (stationID == null) {
			if (other.stationID != null)
				return false;
		}
		else if (!stationID.equals(other.stationID))
			return false;
		if (trainID == null) {
			if (other.trainID != null)
				return false;
		}
		else if (!trainID.equals(other.trainID))
			return false;
		return true;
	}
}
