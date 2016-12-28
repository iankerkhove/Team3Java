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
import dao.RouteDAO;
import model.Route;

public class SyncRouteRunnable implements Runnable
{

	private RouteDAO aDAO;
	private APIController g3API;

	@Override
	public void run()
	{
		try {

			// check if has to update
			HashMap<String, String> params = new HashMap<String, String>();
			g3API = new APIController(APIUrl.G3, "route/massUpdateStatus", RequestType.GET, params);
			aDAO = new RouteDAO();

			JSONObject mainStatus = g3API.getJsonResult().getJSONObject(0);
			TreeMap<String, String> localStatus = aDAO.updateStatus();

			if (localStatus.get("Count").equals(mainStatus.getString("Count"))
					&& localStatus.get("LastUpdated").equals(mainStatus.getString("LastUpdated"))) {

				System.out.println("Routes up to date");
				return;
			}

			// get main table values
			g3API.setUrl("route");
			JSONArray mainJsonList = g3API.getJsonResult();

			ArrayList<Route> localList = aDAO.selectAll();
			ArrayList<Route> mainList = new ArrayList<Route>();

			for (int i = 0; i < mainJsonList.length(); i++) {
				JSONObject obj = mainJsonList.getJSONObject(i);
				Route a = new Route();

				a.setRouteID(UUID.fromString(obj.getString("RouteID")));
				a.setDepartureStationID(UUID.fromString(obj.getJSONObject("DepartureStation").getString("StationID")));
				a.setArrivalStationID(UUID.fromString(obj.getJSONObject("ArrivalStation").getString("StationID")));
				a.setLastUpdated(obj.getLong("LastUpdated"));

				mainList.add(a);
			}

			// update tables
			ArrayList<Route> smallerList = new ArrayList<Route>();
			ArrayList<Route> biggerList = new ArrayList<Route>();

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
				Route tmpA = new Route();
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
			System.out.println("SyncRouteError");
			System.out.println(e);
		}
		finally {
			System.out.println("---- Route ----");
		}
	}

	private void updateLocal(ArrayList<Route> routeList)
	{
		aDAO.setSyncFunction();
		
		for (int i = 0; i < routeList.size(); i++) {
			aDAO.insertOrUpdate(routeList.get(i));
		}
	}

	private void updateMain(ArrayList<Route> routeList)
	{
		try {
			if (routeList.isEmpty())
				return;

			HashMap<String, String> params = new HashMap<String, String>();

			JSONArray routeListJSON = new JSONArray(routeList);

			params.put("routeList", routeListJSON.toString());

			g3API.setUrl("route/massUpdate");
			g3API.setRequestType(RequestType.MASSPUT);
			g3API.setParams(params);
			g3API.getJsonResult();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}