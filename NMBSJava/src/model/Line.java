package model;

import java.time.Instant;
import java.util.UUID;

public class Line
{
	private UUID lineID;
	private UUID routeID;
	private String trainType;
	private Route route;
	private long lastUpdated;
	
	public Line()
	{}

	public Line(UUID routeID, String trainType)
	{
		this.lineID = UUID.randomUUID();
		this.routeID = routeID;
		this.trainType = trainType;
		this.lastUpdated = Instant.now().getEpochSecond();
	}

	public UUID getLineID()
	{
		return lineID;
	}

	public void setLineID(UUID lineID)
	{
		this.lineID = lineID;
	}

	public UUID getRouteID()
	{
		return routeID;
	}

	public void setRouteID(UUID routeID)
	{
		this.routeID = routeID;
	}

	public String getTrainType()
	{
		return trainType;
	}

	public void setTrainType(String trainType)
	{
		this.trainType = trainType;
	}

	public Route getRoute()
	{
		return route;
	}

	public void setRoute(Route route)
	{
		this.route = route;
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
