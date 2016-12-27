package model;

import java.time.Instant;
import java.util.UUID;

public class TypePass
{
	private UUID typePassID;
	private String name;
	private double price;
	private long lastUpdated;
	
	public TypePass()
	{}

	public TypePass(String name, double price)
	{
		this.typePassID = UUID.randomUUID();
		this.name = name;
		this.price = price;
		this.lastUpdated = Instant.now().getEpochSecond();
	}

	public UUID getTypePassID()
	{
		return typePassID;
	}

	public void setTypePassID(UUID typePassID)
	{
		this.typePassID = typePassID;
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
