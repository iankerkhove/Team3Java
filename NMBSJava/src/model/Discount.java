package model;

import java.time.Instant;
import java.util.UUID;

public class Discount {
	private UUID discountID;
	private String name;
	private double amount;
	private long unixTimestamp;
	public Discount(String name, double amount) {
		this.name = name;
		this.amount = amount;
		this.discountID = UUID.randomUUID();
		unixTimestamp = Instant.now().getEpochSecond();
	}
	
	public Discount() {
		
	}
	public void setUnixTimestamp(long unixTimestamp) {
		this.unixTimestamp = unixTimestamp;
	}
	public void setLastUpdated(long unixTimestamp){
		this.unixTimestamp = unixTimestamp;
	}
	public long getUnixTimestamp() {
		return unixTimestamp;
	}

	public void update() {
		this.unixTimestamp = Instant.now().getEpochSecond();
	}

	public UUID getDiscountID() {
		return discountID;
	}
	public void setDiscountID(UUID discountID) {
		this.discountID = discountID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
}
