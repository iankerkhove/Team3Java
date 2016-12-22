package model;

import java.time.Instant;
import java.util.UUID;

public class TypePass {
	private UUID typePassID;
	private String name;
	private double price;
	private long unixTimestamp;
	
	public TypePass(String name, double price) {
		super();
		this.typePassID = UUID.randomUUID();
		this.name = name;
		this.price = price;
		this.unixTimestamp = Instant.now().getEpochSecond();
	}
	public TypePass(){
		
	}
	public UUID getTypePassID() {
		return typePassID;
	}
	public void setTypePassID(UUID typePassID) {
		this.typePassID = typePassID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setUnixTimestamp(long unixTimestamp) {
		this.unixTimestamp = unixTimestamp;
	}
	public long getUnixTimestamp() {
		return unixTimestamp;
	}

	public void update() {
		this.unixTimestamp = Instant.now().getEpochSecond();
	}
	public void setLastUpdated(long unixTimestamp){
		this.unixTimestamp = unixTimestamp;
	}
	
	
}
