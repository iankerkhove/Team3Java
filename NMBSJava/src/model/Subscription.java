package model;

import java.util.Date;

public class Subscription {
	// datamembers
	private int subscriptionID;
	private int railID;
	private int routeID;
	private int discountID;
	private Date validFrom;
	private Date validUntil;
	
	//getters en setters
	public int getSubscriptionID() {
		return subscriptionID;
	}
	public void setSubscriptionID(int subscriptionID) {
		this.subscriptionID = subscriptionID;
	}
	public int getRailID() {
		return railID;
	}
	public void setRailID(int railID) {
		this.railID = railID;
	}
	public int getRouteID() {
		return routeID;
	}
	public void setRouteID(int routeID) {
		this.routeID = routeID;
	}
	public int getDiscountID() {
		return discountID;
	}
	public void setDiscountID(int discountID) {
		this.discountID = discountID;
	}
	public Date getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}
	public Date getValidUntil() {
		return validUntil;
	}
	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}
	
	//constructor
	public Subscription(int subscriptionID, int railID, int routeID, int discountID, Date validFrom, Date validUntil) {
		super();
		this.subscriptionID = subscriptionID;
		this.railID = railID;
		this.routeID = routeID;
		this.discountID = discountID;
		this.validFrom = validFrom;
		this.validUntil = validUntil;
	}
	public Subscription(int subscriptionID, int railID, int routeID, Date validFrom, Date validUntil) {
		super();
		this.subscriptionID = subscriptionID;
		this.railID = railID;
		this.routeID = routeID;
		this.validFrom = validFrom;
		this.validUntil = validUntil;
	}
	
	//functies en methoden
}
