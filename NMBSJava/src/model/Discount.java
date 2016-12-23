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

}
