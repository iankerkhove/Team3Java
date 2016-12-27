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
import dao.RailCardDAO;
import model.RailCard;

public class SyncRailCardRunnable implements Runnable {

	private RailCardDAO rDAO;
	private APIController g3API;

	@Override
	public void run() {
		try {

			// check if has to update
			HashMap<String, String> params = new HashMap<String, String>();
			g3API = new APIController(APIUrl.G3, "railCard/massUpdateStatus", RequestType.GET, params);
			rDAO = new RailCardDAO();

			JSONObject mainStatus = g3API.getJsonResult().getJSONObject(0);
			TreeMap<String, String> localStatus = rDAO.updateStatus();

			if (localStatus.get("Count").equals(mainStatus.getString("Count"))
					&& localStatus.get("LastUpdated").equals(mainStatus.getString("LastUpdated"))) {

				System.out.println("RailCard up to date");
				return;
			}

			// get main table values
			g3API.setUrl("railCard");
			JSONArray mainJsonList = g3API.getJsonResult();

			ArrayList<RailCard> localList = rDAO.selectAll();
			ArrayList<RailCard> mainList = new ArrayList<RailCard>();

			for (int i = 0; i < mainJsonList.length(); i++) {
				JSONObject obj = mainJsonList.getJSONObject(i);
				RailCard r = new RailCard();

				r.setRailCardID(UUID.fromString(obj.getString("CardID")));
				r.setLastUpdated(obj.getLong("LastUpdated"));

				mainList.add(r);
			}

			// update tables
			ArrayList<RailCard> smallerList = new ArrayList<RailCard>();
			ArrayList<RailCard> biggerList = new ArrayList<RailCard>();

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
				RailCard tmpS = new RailCard();
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
			System.out.println("SyncRailCardError");
			System.out.println(e);
		} finally {
			System.out.println("---- RailCard ----");
		}
	}

	private void updateLocal(ArrayList<RailCard> railCardList) {
		for (int i = 0; i < railCardList.size(); i++) {
			rDAO.insertOrUpdate(railCardList.get(i));
		}
	}

	private void updateMain(ArrayList<RailCard> railCardList) {
		try {
			if (railCardList.isEmpty())
				return;

			HashMap<String, String> params = new HashMap<String, String>();

			JSONArray railCardListJSON = new JSONArray(railCardList);

			params.put("railCardList", railCardListJSON.toString());

			g3API.setUrl("railCard/massUpdate");
			g3API.setRequestType(RequestType.MASSPUT);
			g3API.setParams(params);
			g3API.getJsonResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
