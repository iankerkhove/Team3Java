package model;

import java.util.UUID;

public class Customer extends Person
{
	private UUID customerID;
	private UUID railCardID;
	private RailCard railCard;

	public Customer()
	{}

	public Customer(String firstName, String lastName, String birthDate, String emailAddress, Address address,
			RailCard railCard)
	{
		super(firstName, lastName, birthDate, emailAddress, address);
		this.railCard = railCard;
		this.customerID = UUID.randomUUID();
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
	
	public UUID getRailCardID()
	{
		return railCardID;
	}
	
	public void setRailCardID(UUID railCardID)
	{
		this.railCardID = railCardID;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((customerID == null) ? 0 : customerID.hashCode());
		result = prime * result + ((railCardID == null) ? 0 : railCardID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (customerID == null) {
			if (other.customerID != null)
				return false;
		}
		else if (!customerID.equals(other.customerID))
			return false;
		if (railCardID == null) {
			if (other.railCardID != null)
				return false;
		}
		else if (!railCardID.equals(other.railCardID))
			return false;
		return true;
	}
	
}