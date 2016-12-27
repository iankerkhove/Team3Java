package model;

import java.util.Date;

public class Customer extends Person {
	private int customerID;
	private RailCard railCard;

	public Customer(String firstName, String lastName, Date birthDate, String emailAddress, Address address,
			RailCard railCard) {
		super(firstName, lastName, birthDate, emailAddress, address);
		this.railCard = railCard;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public RailCard getRailCard() {
		return railCard;
	}

	public void setRailCard(RailCard railCard) {
		this.railCard = railCard;
	}
	
}