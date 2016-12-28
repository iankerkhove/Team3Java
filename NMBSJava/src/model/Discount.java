package model;

import java.time.Instant;
import java.util.UUID;

public class Discount
{
	private UUID discountID;
	private String name;
	private double amount;
	private long lastUpdated;

	public Discount()
	{}

	public Discount(String name, double amount)
	{
		this.name = name;
		this.amount = amount;
		this.discountID = UUID.randomUUID();
		this.lastUpdated = Instant.now().getEpochSecond();
	}

	public UUID getDiscountID()
	{
		return discountID;
	}

	public void setDiscountID(UUID discountID)
	{
		this.discountID = discountID;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public double getAmount()
	{
		return amount;
	}

	public void setAmount(double amount)
	{
		this.amount = amount;
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
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((discountID == null) ? 0 : discountID.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Discount other = (Discount) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (discountID == null) {
			if (other.discountID != null)
				return false;
		}
		else if (!discountID.equals(other.discountID))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		}
		else if (!name.equals(other.name))
			return false;
		return true;
	}

}
