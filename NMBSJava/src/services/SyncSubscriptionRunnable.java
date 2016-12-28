package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONObject;
import controller.APIController;
import controller.APIController.APIUrl;
import controller.APIController.RequestType;
import dao.SubscriptionDAO;
import model.Subscription;

public class SyncSubscriptionRunnable implements Runnable
{

	private SubscriptionDAO aDAO;
	private APIController g3API;

	@Override
	public void run()
	{
		try {

			// check if has to update
			HashMap<String, String> params = new HashMap<String, String>();
			g3API = new APIController(APIUrl.G3, "subscription/massUpdateStatus", RequestType.GET, params);
			aDAO = new SubscriptionDAO();

			JSONObject mainStatus = g3API.getJsonResult().getJSONObject(0);
			TreeMap<String, String> localStatus = aDAO.updateStatus();

			if (localStatus.get("Count").equals(mainStatus.getString("Count"))
					&& localStatus.get("LastUpdated").equals(mainStatus.getString("LastUpdated"))) {

				System.out.println("Subscriptions up to date");
				return;
			}

			// get main table values
			g3API.setUrl("subscription");
			JSONArray mainJsonList = g3API.getJsonResult();

			ArrayList<Subscription> localList = aDAO.selectAll();
			ArrayList<Subscription> mainList = new ArrayList<Subscription>();

			for (int i = 0; i < mainJsonList.length(); i++) {
				JSONObject obj = mainJsonList.getJSONObject(i);
				Subscription a = new Subscription();

				a.setSubscriptionID(UUID.fromString(obj.getString("SubscriptionID")));
				a.setRailCardID(UUID.fromString(obj.getString("RailCardID")));
				a.setRouteID(UUID.fromString(obj.getJSONObject("Route").getString("RouteID")));
				a.setDiscountID(UUID.fromString(obj.getJSONObject("Discount").getString("DiscountID")));
				a.setValidFrom(obj.getString("ValidFrom"));
				a.setValidUntil(obj.getString("ValidUntil"));
				a.setLastUpdated(obj.getLong("LastUpdated"));

				mainList.add(a);
			}

			// update tables
			ArrayList<Subscription> smallerList = new ArrayList<Subscription>();
			ArrayList<Subscription> biggerList = new ArrayList<Subscription>();

			boolean localIsBigger = false;

			if (localList.size() < mainList.size()) {
				smallerList = localList;
				biggerList = mainList;
			}
			else {
				smallerList = mainList;
				biggerList = localList;
				localIsBigger = true;
			}

			for (int i = 0; i < smallerList.size(); i++) {
				Subscription tmpA = new Subscription();
				tmpA = smallerList.get(i);

				if (biggerList.contains(tmpA)) {
					smallerList.remove(tmpA);
					biggerList.remove(tmpA);
				}

			}

			// update local
			if (localIsBigger)
				updateLocal(smallerList);
			else
				updateLocal(biggerList);

			// update main
			if (localIsBigger)
				updateMain(biggerList);
			else
				updateMain(smallerList);

		}
		catch (Exception e) {
			System.out.println("SyncSubscriptionError");
			System.out.println(e);
		}
		finally {
			System.out.println("---- Subscription ----");
		}
	}

	private void updateLocal(ArrayList<Subscription> subscriptionList)
	{
		aDAO.setSyncFunction();
		
		for (int i = 0; i < subscriptionList.size(); i++) {
			aDAO.insertOrUpdate(subscriptionList.get(i));
		}
	}

	private void updateMain(ArrayList<Subscription> subscriptionList)
	{
		try {
			if (subscriptionList.isEmpty())
				return;

			HashMap<String, String> params = new HashMap<String, String>();

			JSONArray subscriptionListJSON = new JSONArray(subscriptionList);

			params.put("subscriptionList", subscriptionListJSON.toString());

			g3API.setUrl("subscription/massUpdate");
			g3API.setRequestType(RequestType.MASSPUT);
			g3API.setParams(params);
			g3API.getJsonResult();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}