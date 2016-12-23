package model;

import java.time.Instant;
import java.util.UUID;

public class Station
{
	private UUID stationID;
	private String stationName;
	private String cox;
	private String coy;
	private long lastUpdated;

	public Station()
	{}

	public Station(String stationName, String cox, String coy)
	{
		this.stationName = stationName;
		this.cox = cox;
		this.coy = coy;
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

	public String getCox()
	{
		return cox;
	}

	public void setCox(String cox)
	{
		this.cox = cox;
	}

	public String getCoy()
	{
		return coy;
	}

	public void setCoy(String coy)
	{
		this.coy = coy;
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

}
