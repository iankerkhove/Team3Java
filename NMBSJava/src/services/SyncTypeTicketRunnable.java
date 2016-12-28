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
import dao.TypeTicketDAO;
import model.TypeTicket;

public class SyncTypeTicketRunnable implements Runnable {

	private TypeTicketDAO ttDAO;
	private APIController g3API;

	@Override
	public void run() {
		try {

			// check if has to update
			HashMap<String, String> params = new HashMap<String, String>();
			g3API = new APIController(APIUrl.G3, "typeTicket/massUpdateStatus", RequestType.GET, params);
			ttDAO = new TypeTicketDAO();

			JSONObject mainStatus = g3API.getJsonResult().getJSONObject(0);
			TreeMap<String, String> localStatus = ttDAO.updateStatus();

			if (localStatus.get("Count").equals(mainStatus.getString("Count"))
					&& localStatus.get("LastUpdated").equals(mainStatus.getString("LastUpdated"))) {

				System.out.println("typeTicket up to date");
				return;
			}

			// get main table values
			g3API.setUrl("typeTicket");
			JSONArray mainJsonList = g3API.getJsonResult();

			ArrayList<TypeTicket> localList = ttDAO.selectAll();
			ArrayList<TypeTicket> mainList = new ArrayList<TypeTicket>();

			for (int i = 0; i < mainJsonList.length(); i++) {
				JSONObject obj = mainJsonList.getJSONObject(i);
				TypeTicket tt = new TypeTicket();

				tt.setTypeTicketID(UUID.fromString(obj.getString("TypeTicketID")));
				tt.setName(obj.getString("Name"));
				tt.setPrice(obj.getDouble("Price"));
				tt.setComfortClass(obj.getInt("ComfortClass"));
				tt.setLastUpdated(obj.getLong("LastUpdated"));

				mainList.add(tt);
			}

			// update tables
			ArrayList<TypeTicket> smallerList = new ArrayList<TypeTicket>();
			ArrayList<TypeTicket> biggerList = new ArrayList<TypeTicket>();

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
				TypeTicket tmpS = new TypeTicket();
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
			System.out.println("SyncTypeTicketError");
			System.out.println(e);
		} finally {
			System.out.println("---- TypeTicket ----");
		}
	}

	private void updateLocal(ArrayList<TypeTicket> typeTicketList) 
	{
		ttDAO.setSyncFunction();
		
		for (int i = 0; i < typeTicketList.size(); i++) {
			ttDAO.insertOrUpdate(typeTicketList.get(i));
		}
	}

	private void updateMain(ArrayList<TypeTicket> typeTicketList) {
		try {
			if (typeTicketList.isEmpty())
				return;

			HashMap<String, String> params = new HashMap<String, String>();

			JSONArray typeTicketListJSON = new JSONArray(typeTicketList);

			params.put("typeTicketList", typeTicketListJSON.toString());

			g3API.setUrl("typeTicket/massUpdate");
			g3API.setRequestType(RequestType.MASSPUT);
			g3API.setParams(params);
			g3API.getJsonResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
