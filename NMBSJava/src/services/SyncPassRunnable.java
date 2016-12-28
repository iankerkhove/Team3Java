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
import dao.PassDAO;
import model.Pass;

public class SyncPassRunnable implements Runnable {
	private PassDAO pDAO;
	private APIController g3API;

	@Override
	public void run() {
		try {

			// check if has to update
			HashMap<String, String> params = new HashMap<String, String>();
			g3API = new APIController(APIUrl.G3, "pass/massUpdateStatus", RequestType.GET, params);
			pDAO = new PassDAO();

			JSONObject mainStatus = g3API.getJsonResult().getJSONObject(0);
			TreeMap<String, String> localStatus = pDAO.updateStatus();

			if (localStatus.get("Count").equals(mainStatus.getString("Count"))
					&& localStatus.get("LastUpdated").equals(mainStatus.getString("LastUpdated"))) {

				System.out.println("Pass up to date");
				return;
			}

			// get main table values
			g3API.setUrl("pass");
			JSONArray mainJsonList = g3API.getJsonResult();

			ArrayList<Pass> localList = pDAO.selectAll();
			ArrayList<Pass> mainList = new ArrayList<Pass>();

			for (int i = 0; i < mainJsonList.length(); i++) {
				JSONObject obj = mainJsonList.getJSONObject(i);
				Pass p = new Pass();

				p.setPassID(UUID.fromString(obj.getString("PassID")));
				p.setTypePassID(UUID.fromString(obj.getJSONObject("TypePass").getString("TypePassID")));
				p.setDate(obj.getString("Date"));
				p.setStartDate(obj.getString("StartDate"));
				p.setComfortClass(obj.getInt("ComfortClass"));
				p.setLastUpdated(obj.getLong("LastUpdated"));

				mainList.add(p);
			}

			// update tables
			ArrayList<Pass> smallerList = new ArrayList<Pass>();
			ArrayList<Pass> biggerList = new ArrayList<Pass>();

			boolean localIsBigger = false;

			if (localList.size() < mainList.size()) {
				smallerList = localList;
				biggerList = mainList;
			} else {
				smallerList = mainList;
				biggerList = localList;
				localIsBigger = true;
			}

			for (int i = 0; i < smallerList.size(); i++) {
				Pass tmpS = new Pass();
				tmpS = smallerList.get(i);

				if (biggerList.contains(tmpS)) {
					smallerList.remove(tmpS);
					biggerList.remove(tmpS);
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

		} catch (Exception e) {
			System.out.println("SyncPassError");
			System.out.println(e);
		} finally {
			System.out.println("---- Pass ----");
		}
	}

	private void updateLocal(ArrayList<Pass> customerList) 
	{
		pDAO.setSyncFunction();
		
		for (int i = 0; i < customerList.size(); i++) {
			pDAO.insertOrUpdate(customerList.get(i));
		}
	}

	private void updateMain(ArrayList<Pass> passList) {
		try {
			if (passList.isEmpty())
				return;

			HashMap<String, String> params = new HashMap<String, String>();

			JSONArray passListJSON = new JSONArray(passList);

			params.put("passList", passListJSON.toString());

			g3API.setUrl("pass/massUpdate");
			g3API.setRequestType(RequestType.MASSPUT);
			g3API.setParams(params);
			g3API.getJsonResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
