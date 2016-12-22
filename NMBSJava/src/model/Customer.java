package model;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class Customer extends Person {
	private UUID customerID;
	private RailCard railCard;
	private long unixTimestamp;
	public Customer() {
		
	}
	public Customer(String firstName, String lastName, Date birthDate, String emailAddress, Address address,
			RailCard railCard) {
		super(firstName, lastName, birthDate, emailAddress, address);
		this.railCard = railCard;
		this.customerID = UUID.randomUUID();
		unixTimestamp = Instant.now().getEpochSecond();
	}
	public void update(){
		unixTimestamp = Instant.now().getEpochSecond();
	}
	public void setLastUpdated(long unixTimestamp){
		this.unixTimestamp = unixTimestamp;
	}
	public long getUnixTimestamp() {
		return unixTimestamp;
	}
	public UUID getCustomerID() {
		return customerID;
	}

	public void setCustomerID(UUID customerID) {
		this.customerID = customerID;
	}

	public RailCard getRailCard() {
		return railCard;
	}

	public void setRailCard(RailCard railCard) {
		this.railCard = railCard;
	}
	
}