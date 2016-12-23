package model;

import java.util.Date;
import java.time.Instant;
import java.util.UUID;

public class Reservation
{
	private UUID reservationID;
	private int passengerCount;
	private String trainID;
	private Date reservationDate;
	private double price;
	private Route route;
	private UUID routeID;
	private long lastUpdated;

	public Reservation()
	{}

	public Reservation(int passengerCount, String trainID, double price, Date reservationDate, UUID routeID)
	{
		this.reservationID = UUID.randomUUID();
		this.passengerCount = passengerCount;
		this.trainID = trainID;
		this.price = price;
		this.reservationDate = reservationDate;
		this.routeID = routeID;
		this.lastUpdated = Instant.now().getEpochSecond();
	}

	public UUID getReservationID()
	{
		return reservationID;
	}

	public void setReservationID(UUID reservationID)
	{
		this.reservationID = reservationID;
	}

	public int getPassengerCount()
	{
		return passengerCount;
	}

	public void setPassengerCount(int passengerCount)
	{
		this.passengerCount = passengerCount;
	}

	public String getTrainID()
	{
		return trainID;
	}

	public void setTrainID(String trainID)
	{
		this.trainID = trainID;
	}

	public Date getReservationDate()
	{
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate)
	{
		this.reservationDate = reservationDate;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public Route getRoute()
	{
		return route;
	}

	public void setRouteID(UUID routeID)
	{
		this.routeID = routeID;
	}
	
	public UUID getRouteID()
	{
		return routeID;
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