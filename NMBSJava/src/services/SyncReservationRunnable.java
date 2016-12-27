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
import dao.ReservationDAO;
import model.Reservation;

public class SyncReservationRunnable implements Runnable
{
	private ReservationDAO rDAO;
	private APIController g3API;

	@Override
	public void run()
	{
		try {

			// check if has to update
			HashMap<String, String> params = new HashMap<String, String>();
			g3API = new APIController(APIUrl.G3, "customer/massUpdateStatus", RequestType.GET, params);
			rDAO = new ReservationDAO();

			JSONObject mainStatus = g3API.getJsonResult().getJSONObject(0);
			TreeMap<String, String> localStatus = rDAO.updateStatus();

			if (localStatus.get("Count").equals(mainStatus.getString("Count"))
					&& localStatus.get("LastUpdated").equals(mainStatus.getString("LastUpdated"))) {

				System.out.println("Reservation up to date");
				return;
			}

			// get main table values
			g3API.setUrl("reservation");
			JSONArray mainJsonList = g3API.getJsonResult();

			ArrayList<Reservation> localList = rDAO.selectAll();
			ArrayList<Reservation> mainList = new ArrayList<Reservation>();

			for (int i = 0; i < mainJsonList.length(); i++) {
				JSONObject obj = mainJsonList.getJSONObject(i);
				Reservation r = new Reservation();

				r.setReservationID(UUID.fromString(obj.getString("ReservationID")));
				r.setPassengerCount(obj.getInt("PassengerCount"));
				r.setTrainID(obj.getString("TrainID"));
				r.setPrice(obj.getDouble("Price"));
				r.setReservationDate(obj.getString("ReservationDate"));
				r.setRouteID(UUID.fromString(obj.getJSONObject("Route").getString("RouteID")));
				r.setLastUpdated(obj.getLong("LastUpdated"));

				mainList.add(r);
			}

			// update tables
			ArrayList<Reservation> smallerList = new ArrayList<Reservation>();
			ArrayList<Reservation> biggerList = new ArrayList<Reservation>();

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
				Reservation tmpS = new Reservation();
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

		}
		catch (Exception e) {
			System.out.println("SyncReservationError");
			System.out.println(e);
		}
		finally {
			System.out.println("---- Reservation ----");
		}
	}

	private void updateLocal(ArrayList<Reservation> reservationList)
	{
		for (int i = 0; i < reservationList.size(); i++) {
			rDAO.insertOrUpdate(reservationList.get(i));
		}
	}

	private void updateMain(ArrayList<Reservation> reservationList)
	{
		try {
			if (reservationList.isEmpty())
				return;

			HashMap<String, String> params = new HashMap<String, String>();

			JSONArray reservationListJSON = new JSONArray(reservationList);

			params.put("reservationList", reservationListJSON.toString());

			g3API.setUrl("reservation/massUpdate");
			g3API.setRequestType(RequestType.MASSPUT);
			g3API.setParams(params);
			g3API.getJsonResult();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}