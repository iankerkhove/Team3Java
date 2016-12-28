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

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lineID == null) ? 0 : lineID.hashCode());
		result = prime * result + ((routeID == null) ? 0 : routeID.hashCode());
		result = prime * result + ((trainType == null) ? 0 : trainType.hashCode());
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
		Line other = (Line) obj;
		if (lineID == null) {
			if (other.lineID != null)
				return false;
		}
		else if (!lineID.equals(other.lineID))
			return false;
		if (routeID == null) {
			if (other.routeID != null)
				return false;
		}
		else if (!routeID.equals(other.routeID))
			return false;
		if (trainType == null) {
			if (other.trainType != null)
				return false;
		}
		else if (!trainType.equals(other.trainType))
			return false;
		return true;
	}

}
