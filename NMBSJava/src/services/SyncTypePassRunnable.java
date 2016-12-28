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
import dao.TypePassDAO;
import model.TypePass;

public class SyncTypePassRunnable implements Runnable  {

		private TypePassDAO ttDAO;
		private APIController g3API;
		
		@Override
		public void run()
		{
			try {

				//check if has to update
				HashMap<String, String> params = new HashMap<String, String>();
				g3API = new APIController(APIUrl.G3, "typePass/massUpdateStatus", RequestType.GET, params);
				ttDAO = new TypePassDAO();

				JSONObject mainStatus = g3API.getJsonResult().getJSONObject(0);
				TreeMap<String, String> localStatus = ttDAO.updateStatus();

				if (localStatus.get("Count").equals(mainStatus.getString("Count"))
						&& localStatus.get("LastUpdated").equals(mainStatus.getString("LastUpdated"))) {
					
					System.out.println("typePass up to date");
					return;
				}
				
				//get main table values
				g3API.setUrl("typePass");
				JSONArray mainJsonList = g3API.getJsonResult();
				
				ArrayList<TypePass> localList = ttDAO.selectAll();
				ArrayList<TypePass> mainList = new ArrayList<TypePass>();
				
				for(int i = 0; i < mainJsonList.length(); i++)
				{
					JSONObject obj = mainJsonList.getJSONObject(i);
					TypePass tt = new TypePass();

					tt.setTypePassID(UUID.fromString(obj.getString("TypePassID")));
					tt.setName(obj.getString("Name"));
					tt.setPrice(obj.getDouble("Price"));
					tt.setLastUpdated(obj.getLong("LastUpdated"));

					mainList.add(tt);
				}
				
				//update tables
				ArrayList<TypePass> smallerList = new ArrayList<TypePass>();
				ArrayList<TypePass> biggerList = new ArrayList<TypePass>();
				
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
					TypePass tmpS = new TypePass();
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
				System.out.println("SyncTypePassError");
				System.out.println(e);
			}
			finally
			{
				System.out.println("---- TypePass ----");
			}
		}
		
		private void updateLocal(ArrayList<TypePass> typePassList)
		{
			ttDAO.setSyncFunction();
			
			for (int i = 0; i < typePassList.size(); i++)
			{
				ttDAO.insertOrUpdate(typePassList.get(i));		
			}
		}
		
		private void updateMain(ArrayList<TypePass> typePassList)
		{
			try {
				if (typePassList.isEmpty())
					return;
				
				HashMap<String, String> params = new HashMap<String, String>();
				
				JSONArray typePassListJSON = new JSONArray(typePassList);
				
				params.put("typePassList", typePassListJSON.toString());
				
				
				g3API.setUrl("typePass/massUpdate");
				g3API.setRequestType(RequestType.MASSPUT);
				g3API.setParams(params);
				g3API.getJsonResult();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
