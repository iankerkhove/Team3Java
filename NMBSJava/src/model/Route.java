package model;

import java.time.Instant;
import java.util.UUID;

public class Route
{
	private UUID routeID;
	private UUID departureStationID;
	private UUID arrivalStationID;
	private Station departureStation;
	private Station arrivalStation;
	private long lastUpdated;
	
	public Route()
	{}

	public Route(UUID routeID, UUID departureStationID, UUID arrivalStationID)
	{
		this.routeID = routeID;
		this.departureStationID = departureStationID;
		this.arrivalStationID = arrivalStationID;
		this.lastUpdated = Instant.now().getEpochSecond();
	}

	public UUID getRouteID()
	{
		return routeID;
	}

	public void setRouteID(UUID routeID)
	{
		this.routeID = routeID;
	}

	public UUID getDepartureStationID()
	{
		return departureStationID;
	}

	public void setDepartureStationID(UUID departureStationID)
	{
		this.departureStationID = departureStationID;
	}

	public UUID getArrivalStationID()
	{
		return arrivalStationID;
	}

	public void setArrivalStationID(UUID arrivalStationID)
	{
		this.arrivalStationID = arrivalStationID;
	}

	public Station getDepartureStation()
	{
		return departureStation;
	}

	public void setDepartureStation(Station departureStation)
	{
		this.departureStation = departureStation;
	}

	public Station getArrivalStation()
	{
		return arrivalStation;
	}

	public void setArrivalStation(Station arrivalStation)
	{
		this.arrivalStation = arrivalStation;
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
		result = prime * result + ((arrivalStationID == null) ? 0 : arrivalStationID.hashCode());
		result = prime * result + ((departureStationID == null) ? 0 : departureStationID.hashCode());
		result = prime * result + ((routeID == null) ? 0 : routeID.hashCode());
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
		Route other = (Route) obj;
		if (arrivalStationID == null) {
			if (other.arrivalStationID != null)
				return false;
		}
		else if (!arrivalStationID.equals(other.arrivalStationID))
			return false;
		if (departureStationID == null) {
			if (other.departureStationID != null)
				return false;
		}
		else if (!departureStationID.equals(other.departureStationID))
			return false;
		if (routeID == null) {
			if (other.routeID != null)
				return false;
		}
		else if (!routeID.equals(other.routeID))
			return false;
		return true;
	}

}
