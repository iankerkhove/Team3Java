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

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + comfortClass;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((typeTicketID == null) ? 0 : typeTicketID.hashCode());
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
		TypeTicket other = (TypeTicket) obj;
		if (comfortClass != other.comfortClass)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		}
		else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (typeTicketID == null) {
			if (other.typeTicketID != null)
				return false;
		}
		else if (!typeTicketID.equals(other.typeTicketID))
			return false;
		return true;
	}

}
