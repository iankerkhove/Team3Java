package model;

import java.time.Instant;
import java.util.UUID;

public class Customer extends Person
{
	private UUID customerID;
	private UUID railCardID;
	private RailCard railCard;
	private long lastUpdated;

	public Customer()
	{}

	public Customer(String firstName, String lastName, String birthDate, String emailAddress, Address address,
			RailCard railCard)
	{
		super(firstName, lastName, birthDate, emailAddress, address);
		this.railCard = railCard;
		this.customerID = UUID.randomUUID();
		this.lastUpdated = Instant.now().getEpochSecond();
	}

	public UUID getCustomerID()
	{
		return customerID;
	}

	public void setCustomerID(UUID customerID)
	{
		this.customerID = customerID;
	}

	public RailCard getRailCard()
	{
		return railCard;
	}

	public void setRailCard(RailCard railCard)
	{
		this.railCard = railCard;
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
		lastUpdated = Instant.now().getEpochSecond();
	}
	
	public UUID getRailCardID()
	{
		return railCardID;
	}
	
	public void setRailCardID(UUID railCardID)
	{
		this.railCardID = railCardID;
	}
	
}