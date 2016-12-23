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

}
