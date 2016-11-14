package model;

import java.util.ArrayList;

public class RailCard {
	private int railCardID;
	private ArrayList<Subscription> subscriptions;
	
	public RailCard() {
		subscriptions = new ArrayList ();
	}

	public int getRailCardID() {
		return railCardID;
	}

	public void setRailCardID(int railCardID) {
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
