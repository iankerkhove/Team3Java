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
import dao.LineDAO;
import model.Line;

public class SyncLineRunnable implements Runnable
{

	private LineDAO aDAO;
	private APIController g3API;
	
	@Override
	public void run()
	{
		try {

			//check if has to update
			HashMap<String, String> params = new HashMap<String, String>();
			g3API = new APIController(APIUrl.G3, "line/massUpdateStatus", RequestType.GET, params);
			aDAO = new LineDAO();

			JSONObject mainStatus = g3API.getJsonResult().getJSONObject(0);
			TreeMap<String, String> localStatus = aDAO.updateStatus();

			if (localStatus.get("Count").equals(mainStatus.getString("Count"))
					&& localStatus.get("LastUpdated").equals(mainStatus.getString("LastUpdated"))) {
				
				System.out.println("Lines up to date");
				return;
			}
			
			//get main table values
			g3API.setUrl("line");
			JSONArray mainJsonList = g3API.getJsonResult();
			
			ArrayList<Line> localList = aDAO.selectAll();
			ArrayList<Line> mainList = new ArrayList<Line>();
			
			for(int i = 0; i < mainJsonList.length(); i++)
			{
				JSONObject obj = mainJsonList.getJSONObject(i);
				Line a = new Line();
				
				a.setLineID(UUID.fromString(obj.getString("LineID")));
				a.setRouteID(UUID.fromString(obj.getString("RouteID")));
				a.setTrainType(obj.getString("TrainType"));
				a.setLastUpdated(obj.getLong("LastUpdated"));
				
				mainList.add(a);
			}
			
			//update tables
			ArrayList<Line> smallerList = new ArrayList<Line>();
			ArrayList<Line> biggerList = new ArrayList<Line>();
			
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
				Line tmpA = new Line();
				tmpA = smallerList.get(i);
				
				if (biggerList.contains(tmpA))
				{
					smallerList.remove(tmpA);
					biggerList.remove(tmpA);
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
			System.out.println("SyncLineError");
			System.out.println(e);
		}
		finally
		{
			System.out.println("---- Line ----");
		}
	}
	
	private void updateLocal(ArrayList<Line> lineList)
	{
		aDAO.setSyncFunction();
		
		for (int i = 0; i < lineList.size(); i++)
		{
			aDAO.insertOrUpdate(lineList.get(i));		
		}
	}
	
	private void updateMain(ArrayList<Line> lineList)
	{
		try {
			if (lineList.isEmpty())
				return;
			
			HashMap<String, String> params = new HashMap<String, String>();
			
			JSONArray lineListJSON = new JSONArray(lineList);
			
			params.put("lineList", lineListJSON.toString());
			
			
			g3API.setUrl("line/massUpdate");
			g3API.setRequestType(RequestType.MASSPUT);
			g3API.setParams(params);
			g3API.getJsonResult();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}