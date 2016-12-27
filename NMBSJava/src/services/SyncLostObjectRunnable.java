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
	import dao.LostObjectDAO;
	import model.LostObject;

public class SyncLostObjectRunnable implements Runnable {
			private LostObjectDAO lDAO;
			private APIController g3API;
			
			@Override
			public void run()
			{
				try {

					//check if has to update
					HashMap<String, String> params = new HashMap<String, String>();
					g3API = new APIController(APIUrl.G3, "lostObject/massUpdateStatus", RequestType.GET, params);
					lDAO = new LostObjectDAO();

					JSONObject mainStatus = g3API.getJsonResult().getJSONObject(0);
					TreeMap<String, String> localStatus = lDAO.updateStatus();

					if (localStatus.get("Count").equals(mainStatus.getString("Count"))
							&& localStatus.get("LastUpdated").equals(mainStatus.getString("LastUpdated"))) {
						
						System.out.println("Lost Object up to date");
						return;
					}
					
					//get main table values
					g3API.setUrl("lostObject");
					JSONArray mainJsonList = g3API.getJsonResult();
					
					ArrayList<LostObject> localList = lDAO.selectAll();
					ArrayList<LostObject> mainList = new ArrayList<LostObject>();
					
					for(int i = 0; i < mainJsonList.length(); i++)
					{
						JSONObject obj = mainJsonList.getJSONObject(i);
						LostObject l = new LostObject();
						
						l.setObjectID(UUID.fromString(obj.getString("ObjectID")));
						l.setStationID(UUID.fromString(obj.getJSONObject("Station").getString("StationID")));
						l.setDescription(obj.getString("Description"));
						l.setDate(obj.getString("Date"));
						l.setTrainID(obj.getString("TrainID"));
						l.setFound( (obj.getString("Found").equals("1")) ? true : false);
						l.setLastUpdated(obj.getLong("LastUpdated"));
						mainList.add(l);
						
						
					}
					
					//update tables
					ArrayList<LostObject> smallerList = new ArrayList<LostObject>();
					ArrayList<LostObject> biggerList = new ArrayList<LostObject>();
					
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
						LostObject tmpS = new LostObject();
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
					System.out.println("SyncLostObjectError");
					System.out.println(e);
				}
				finally
				{
					System.out.println("---- LostObject ----");
				}
			}
			
			private void updateLocal(ArrayList<LostObject> lostObjectList)
			{
				for (int i = 0; i < lostObjectList.size(); i++)
				{
					lDAO.insertOrUpdate(lostObjectList.get(i));		
				}
			}
			
			private void updateMain(ArrayList<LostObject> lostObjectList)
			{
				try {
					if (lostObjectList.isEmpty())
						return;
					
					HashMap<String, String> params = new HashMap<String, String>();
					
					JSONArray lostObjectListJSON = new JSONArray(lostObjectList);
					
					params.put("lostObjectList", lostObjectListJSON.toString());
					
					
					g3API.setUrl("lostObject/massUpdate");
					g3API.setRequestType(RequestType.MASSPUT);
					g3API.setParams(params);
					g3API.getJsonResult();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
