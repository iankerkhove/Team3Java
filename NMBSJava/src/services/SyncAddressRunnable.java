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
import dao.AddressDAO;
import model.Address;

public class SyncAddressRunnable implements Runnable
{

	private AddressDAO aDAO;
	private APIController g3API;
	
	@Override
	public void run()
	{
		try {

			//check if has to update
			HashMap<String, String> params = new HashMap<String, String>();
			g3API = new APIController(APIUrl.G3, "address/massUpdateStatus", RequestType.GET, params);
			aDAO = new AddressDAO();

			JSONObject mainStatus = g3API.getJsonResult().getJSONObject(0);
			TreeMap<String, String> localStatus = aDAO.updateStatus();

			if (localStatus.get("Count").equals(mainStatus.getString("Count"))
					&& localStatus.get("LastUpdated").equals(mainStatus.getString("LastUpdated"))) {
				
				System.out.println("Address' op to date");
				return;
			}
			
			//get main table values
			g3API.setUrl("address");
			JSONArray mainJsonList = g3API.getJsonResult();
			
			ArrayList<Address> localList = aDAO.selectAll();
			ArrayList<Address> mainList = new ArrayList<Address>();
			
			for(int i = 0; i < mainJsonList.length(); i++)
			{
				JSONObject obj = mainJsonList.getJSONObject(i);
				Address a = new Address();
				
				a.setAddressID(UUID.fromString(obj.getString("AddressID")));
				a.setStreet(obj.getString("Street"));
				a.setNumber(obj.getInt("Number"));
				a.setCity(obj.getString("City"));
				a.setZipCode(obj.getInt("ZipCode"));
				a.setCoordinates(obj.getString("Coordinates"));
				a.setLastUpdated(obj.getLong("LastUpdated"));
				
				mainList.add(a);
			}
			
			//update tables
			ArrayList<Address> smallerList = new ArrayList<Address>();
			ArrayList<Address> biggerList = new ArrayList<Address>();
			
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
				Address tmpA = new Address();
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
			System.out.println("SyncAddressError");
			System.out.println(e);
		}
		finally
		{
			System.out.println("Address' synced");
		}
	}
	
	private void updateLocal(ArrayList<Address> addressList)
	{
		for (int i = 0; i < addressList.size(); i++)
		{
			aDAO.insertOrUpdate(addressList.get(i));		
		}
	}
	
	private void updateMain(ArrayList<Address> addressList)
	{
		try {
			HashMap<String, String> params = new HashMap<String, String>();
			
			JSONArray addressListJSON = new JSONArray(addressList);
			
			params.put("AddressList", addressListJSON.toString());
			
			System.out.println(params);
			
			
			g3API.setUrl("address/massUpdate");
			g3API.setParams(params);
			g3API.getJsonResult();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
