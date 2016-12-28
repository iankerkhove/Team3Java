package model;

import java.time.Instant;
import java.util.UUID;

public class Reservation
{
	private UUID reservationID;
	private int passengerCount;
	private String trainID;
	private String reservationDate;
	private double price;
	private Route route;
	private UUID routeID;
	private long lastUpdated;

	public Reservation()
	{}

	public Reservation(int passengerCount, String trainID, double price, String reservationDate, UUID routeID)
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

	public String getReservationDate()
	{
		return reservationDate;
	}

	public void setReservationDate(String reservationDate)
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

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + passengerCount;
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((reservationDate == null) ? 0 : reservationDate.hashCode());
		result = prime * result + ((reservationID == null) ? 0 : reservationID.hashCode());
		result = prime * result + ((routeID == null) ? 0 : routeID.hashCode());
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
		Reservation other = (Reservation) obj;
		if (passengerCount != other.passengerCount)
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (reservationDate == null) {
			if (other.reservationDate != null)
				return false;
		}
		else if (!reservationDate.equals(other.reservationDate))
			return false;
		if (reservationID == null) {
			if (other.reservationID != null)
				return false;
		}
		else if (!reservationID.equals(other.reservationID))
			return false;
		if (routeID == null) {
			if (other.routeID != null)
				return false;
		}
		else if (!routeID.equals(other.routeID))
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