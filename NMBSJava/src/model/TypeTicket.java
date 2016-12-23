package model;

import java.time.Instant;
import java.util.UUID;

public class TypeTicket
{
	private UUID typeTicketID;
	private String name;
	private double price;
	private int comfortClass;
	private long lastUpdated;
	
	public TypeTicket()
	{}

	public TypeTicket(String name, double price, int comfortClass)
	{
		this.typeTicketID = UUID.randomUUID();
		this.name = name;
		this.price = price;
		this.comfortClass = comfortClass;
		this.lastUpdated = Instant.now().getEpochSecond();
	}

	public UUID getTypeTicketID()
	{
		return typeTicketID;
	}

	public void setTypeTicketID(UUID typeTicketID)
	{
		this.typeTicketID = typeTicketID;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public int getComfortClass()
	{
		return comfortClass;
	}

	public void setComfortClass(int comfortClass)
	{
		this.comfortClass = comfortClass;
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
