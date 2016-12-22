package model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.UUID;

public class RailCard {
	private UUID railCardID;
	private ArrayList<Subscription> subscriptions;
	private long unixTimestamp;
	public RailCard() {
		subscriptions = new ArrayList ();
		railCardID = UUID.randomUUID();
		unixTimestamp = Instant.now().getEpochSecond();
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
	public UUID getRailCardID() {
		return railCardID;
	}

	public void setRailCardID(UUID railCardID) {
		this.railCardID = railCardID;
	}

	public ArrayList<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(ArrayList<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}
	
	public void addSubscription (Subscription s) {
		 if (s != null) {
			 subscriptions.add(s);
		 }
	}
	public void deleteSubscription (int subscriptionID) {
		try {
		subscriptions.remove(subscriptionID);
		}
		catch (IndexOutOfBoundsException oob) {
			System.out.println("Abonnement met id " + subscriptionID + " bestaat niet");
		}
	}
}
