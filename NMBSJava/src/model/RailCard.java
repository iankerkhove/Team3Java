package model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.UUID;

public class RailCard
{
	private UUID railCardID;
	private ArrayList<Subscription> subscriptions;
	private long lastUpdated;

	public RailCard()
	{
		subscriptions = new ArrayList<Subscription>();
		railCardID = UUID.randomUUID();
		lastUpdated = Instant.now().getEpochSecond();
	}


	public UUID getRailCardID()
	{
		return railCardID;
	}

	public void setRailCardID(UUID railCardID)
	{
		this.railCardID = railCardID;
	}

	public ArrayList<Subscription> getSubscriptions()
	{
		return subscriptions;
	}

	public void setSubscriptions(ArrayList<Subscription> subscriptions)
	{
		this.subscriptions = subscriptions;
	}

	public void addSubscription(Subscription s)
	{
		if (s != null) {
			subscriptions.add(s);
		}
	}

	public void deleteSubscription(int subscriptionID)
	{
		try {
			subscriptions.remove(subscriptionID);
		}
		catch (IndexOutOfBoundsException oob) {
			System.out.println("Abonnement met id " + subscriptionID + " bestaat niet");
		}
	}

	public long getLastUpdated()
	{
		return lastUpdated;
	}
	
	public void setLastUpdated(long lastUpdated)
	{
		this.lastUpdated = lastUpdated;
	}
	
	public void update()
	{
		this.lastUpdated = Instant.now().getEpochSecond();
	}
	
}
