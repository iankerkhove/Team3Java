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

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((typePassID == null) ? 0 : typePassID.hashCode());
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
		TypePass other = (TypePass) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		}
		else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (typePassID == null) {
			if (other.typePassID != null)
				return false;
		}
		else if (!typePassID.equals(other.typePassID))
			return false;
		return true;
	}
	
}
