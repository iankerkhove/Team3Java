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

}
