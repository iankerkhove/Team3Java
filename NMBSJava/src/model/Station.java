package model;

import java.time.Instant;
import java.util.UUID;

public class Station
{
	private UUID stationID;
	private String stationName;
	private String coX;
	private String coY;
	private long lastUpdated;

	public Station()
	{}

	public Station(String stationName, String coX, String coY)
	{
		this.stationName = stationName;
		this.coX = coX;
		this.coY = coY;
		this.stationID = UUID.randomUUID();
		this.lastUpdated = Instant.now().getEpochSecond();
	}

	public UUID getStationID()
	{
		return stationID;
	}

	public void setStationID(UUID stationID)
	{
		this.stationID = stationID;
	}

	public String getStationName()
	{
		return stationName;
	}

	public void setStationName(String stationName)
	{
		this.stationName = stationName;
	}

	public String getCoX()
	{
		return coX;
	}

	public void setCoX(String coX)
	{
		this.coX = coX;
	}

	public String getCoY()
	{
		return coY;
	}

	public void setCoY(String coY)
	{
		this.coY = coY;
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
		return "Station [stationID=" + stationID + ", stationName=" + stationName + ", coX=" + coX + ", coY=" + coY
				+ ", lastUpdated=" + lastUpdated + "]";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coX == null) ? 0 : coX.hashCode());
		result = prime * result + ((coY == null) ? 0 : coY.hashCode());
		result = prime * result + ((stationID == null) ? 0 : stationID.hashCode());
		result = prime * result + ((stationName == null) ? 0 : stationName.hashCode());
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
		Station other = (Station) obj;
		if (coX == null) {
			if (other.coX != null)
				return false;
		}
		else if (!coX.equals(other.coX))
			return false;
		if (coY == null) {
			if (other.coY != null)
				return false;
		}
		else if (!coY.equals(other.coY))
			return false;
		if (stationID == null) {
			if (other.stationID != null)
				return false;
		}
		else if (!stationID.equals(other.stationID))
			return false;
		if (stationName == null) {
			if (other.stationName != null)
				return false;
		}
		else if (!stationName.equals(other.stationName))
			return false;
		return true;
	}

}
