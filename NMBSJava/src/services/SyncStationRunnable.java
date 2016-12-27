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
import dao.StationDAO;
import model.Station;

public class SyncStationRunnable implements Runnable
{
	private StationDAO sDAO;
	private APIController g3API;
	
	@Override
	public void run()
	{
		try {

			//check if has to update
			HashMap<String, String> params = new HashMap<String, String>();
			g3API = new APIController(APIUrl.G3, "station/massUpdateStatus", RequestType.GET, params);
			sDAO = new StationDAO();

			JSONObject mainStatus = g3API.getJsonResult().getJSONObject(0);
			TreeMap<String, String> localStatus = sDAO.updateStatus();

			if (localStatus.get("Count").equals(mainStatus.getString("Count"))
					&& localStatus.get("LastUpdated").equals(mainStatus.getString("LastUpdated"))) {
				
				System.out.println("Stations up to date");
				return;
			}
			
			//get main table values
			g3API.setUrl("station");
			JSONArray mainJsonList = g3API.getJsonResult();
			
			ArrayList<Station> localList = sDAO.selectAll();
			ArrayList<Station> mainList = new ArrayList<Station>();
			
			for(int i = 0; i < mainJsonList.length(); i++)
			{
				JSONObject obj = mainJsonList.getJSONObject(i);
				Station s = new Station();
				
				s.setStationID(UUID.fromString(obj.getString("StationID")));
				s.setStationName(obj.getString("Name"));
				s.setCoX(obj.getString("CoX"));
				s.setCoY(obj.getString("CoY"));
				s.setLastUpdated(obj.getLong("LastUpdated"));
				
				mainList.add(s);
			}
			
			//update tables
			ArrayList<Station> smallerList = new ArrayList<Station>();
			ArrayList<Station> biggerList = new ArrayList<Station>();
			
			boolean localIsBigger = false;
			
			if (localList.size() < mainList.size())
			{
				smallerList = localList;
				biggerList = mainList;
			}
			else
			{
				smallerList = mainList;
				biggerList = localList;
				localIsBigger = true;
			}
			
			
			for (int i = 0; i < smallerList.size(); i++)
			{
				Station tmpS = new Station();
				tmpS = smallerList.get(i);
				
				if (biggerList.contains(tmpS))
				{
					smallerList.remove(tmpS);
					biggerList.remove(tmpS);
				}
					
			}
			
			
			//update local
			if (localIsBigger)
				updateLocal(smallerList);
			else
				updateLocal(biggerList);
			
			//update main
			if (localIsBigger)
				updateMain(biggerList);
			else
				updateMain(smallerList);

		}
		catch (Exception e) {
			System.out.println("SyncStationError");
			System.out.println(e);
			e.printStackTrace();
		}
		finally
		{
			System.out.println("---- Stations ----");
		}
	}
	
	private void updateLocal(ArrayList<Station> stationList)
	{
		sDAO.setSyncFunction();
		
		for (int i = 0; i < stationList.size(); i++)
		{
			sDAO.insertOrUpdate(stationList.get(i));
		}
	}
	
	private void updateMain(ArrayList<Station> stationList)
	{
		try {
			if (stationList.isEmpty())
				return;
			
			HashMap<String, String> params = new HashMap<String, String>();
			
			JSONArray stationListJSON = new JSONArray(stationList);
			
			params.put("stationList", stationListJSON.toString());
			
			System.out.println(params);
			
			
			g3API.setUrl("station/massUpdate");
			g3API.setRequestType(RequestType.MASSPUT);
			g3API.setParams(params);
			g3API.getJsonResult();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
