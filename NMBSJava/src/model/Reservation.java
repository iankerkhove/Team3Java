package model;

import java.time.Instant;
import java.util.UUID;

public class Reservation {
	private UUID reservationID;
	private int passengerCount;
	private int trainID;
	private double price;
	private UUID routeID;
	private long unixTimestamp;
	public Reservation(int passengerCount, int trainID, double price, UUID routeID) {
		super();
		this.reservationID = UUID.randomUUID();
		this.passengerCount = passengerCount;
		this.trainID = trainID;
		this.price = price;
		this.routeID = routeID;
		this.unixTimestamp =  Instant.now().getEpochSecond(); 
	}
	public UUID getReservationID() {
		return reservationID;
	}
	public void setReservationID(UUID reservationID) {
		this.reservationID = reservationID;
	}
	public int getPassengerCount() {
		return passengerCount;
	}
	public void setPassengerCount(int passengerCount) {
		this.passengerCount = passengerCount;
	}
	public int getTrainID() {
		return trainID;
	}
	public void setTrainID(int trainID) {
		this.trainID = trainID;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public UUID getRouteID() {
		return routeID;
	}
	public void setRouteID(UUID routeID) {
		this.routeID = routeID;
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
