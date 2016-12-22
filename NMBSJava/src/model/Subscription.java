package model;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class Subscription {
	private UUID subscriptionID;
	private UUID railID;
	private UUID routeID;
	private UUID discountID;
	private Date validFrom;
	private Date validUntil;
	private long unixTimestamp;
	public Subscription(UUID railID, UUID routeID, UUID discountID, Date validFrom, Date validUntil) {
		super();
		this.railID = railID;
		this.routeID = routeID;
		this.discountID = discountID;
		this.validFrom = validFrom;
		this.validUntil = validUntil;
		this.subscriptionID = UUID.randomUUID();
		this.unixTimestamp = Instant.now().getEpochSecond();
	}
	public Subscription(UUID railID, UUID routeID, Date validFrom, Date validUntil) {
		super();
		this.railID = railID;
		this.routeID = routeID;
		this.validFrom = validFrom;
		this.validUntil = validUntil;
		this.subscriptionID = UUID.randomUUID();
		this.unixTimestamp = Instant.now().getEpochSecond();
	}
	public Subscription() {

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
	//getters en setters
	public UUID getSubscriptionID() {
		return subscriptionID;
	}
	public void setSubscriptionID(UUID subscriptionID) {
		this.subscriptionID = subscriptionID;
	}
	public UUID getRailID() {
		return railID;
	}
	public void setRailID(UUID railID) {
		this.railID = railID;
	}
	public UUID getRouteID() {
		return routeID;
	}
	public void setRouteID(UUID routeID) {
		this.routeID = routeID;
	}
	public UUID getDiscountID() {
		return discountID;
	}
	public void setDiscountID(UUID discountID) {
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
}
