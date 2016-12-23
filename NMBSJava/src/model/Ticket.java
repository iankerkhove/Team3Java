package model;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class Ticket
{
	// members
	private UUID routeID;
	private Route route;
	private Date date;
	private double price;
	private Date validFrom;
	private Date validUntil;
	private UUID ticketID;
	private long lastUpdated;
	private ComfortClass comfortClass;

	// enumeratie
	public enum ComfortClass
	{
		First, Second
	}

	public Ticket()
	{}
	
	public Ticket(UUID routeID, Date date, double price, Date validFrom, Date validUntil, ComfortClass comfortClass)
	{
		this.date = date;
		this.price = price;
		this.validFrom = validFrom;
		this.validUntil = validUntil;
		this.comfortClass = comfortClass;
		this.routeID = UUID.randomUUID();
		this.lastUpdated = Instant.now().getEpochSecond();
	}
	
	public Route getRoute()
	{
		return route;
	}

	public void setRoute(Route route)
	{
		this.route = route;
	}

	public void setRouteID(UUID routeID)
	{
		this.routeID = routeID;
	}

	public void setTicketID(UUID ticketID)
	{
		this.ticketID = ticketID;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public Date getValidFrom()
	{
		return validFrom;
	}

	public void setValidFrom(Date validFrom)
	{
		this.validFrom = validFrom;
	}

	public Date getValidUntil()
	{
		return validUntil;
	}

	public void setValidUntil(Date validUntil)
	{
		this.validUntil = validUntil;
	}

	public long getLastUpdated()
	{
		return lastUpdated;
	}

	public void setLastUpdated(long lastUpdated)
	{
		this.lastUpdated = lastUpdated;
	}

	public ComfortClass getComfortClass()
	{
		return comfortClass;
	}

	public void setComfortClass(ComfortClass comfortClass)
	{
		this.comfortClass = comfortClass;
	}

	public UUID getRouteID()
	{
		return routeID;
	}
	
	public UUID getTicketID()
	{
		return ticketID;
	}

	public void update()
	{
		this.lastUpdated = Instant.now().getEpochSecond();
	}

}
