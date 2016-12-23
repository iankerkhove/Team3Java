package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

import controller.APIController;
import controller.APIController.APIUrl;
import controller.APIController.RequestType;
import dao.StaffDAO;
import model.Staff;

public class SyncStaffRunnable implements Runnable
{
	private StaffDAO sDAO;
	private APIController g3API;
	
	@Override
	public void run()
	{
		try {

			//check if has to update
			HashMap<String, String> params = new HashMap<String, String>();
			g3API = new APIController(APIUrl.G3, "staff/massUpdateStatus", RequestType.GET, params);
			sDAO = new StaffDAO();

			JSONObject mainStatus = g3API.getJsonResult().getJSONObject(0);
			TreeMap<String, String> localStatus = sDAO.updateStatus();

			if (localStatus.get("Count").equals(mainStatus.getString("Count"))
					&& localStatus.get("LastUpdated").equals(mainStatus.getString("LastUpdated"))) {
				
				System.out.println("Staff' up to date");
				return;
			}
			
			//get main table values
			g3API.setUrl("staff");
			JSONArray mainJsonList = g3API.getJsonResult();
			
			ArrayList<Staff> localList = sDAO.selectAll();
			ArrayList<Staff> mainList = new ArrayList<Staff>();
			
			for(int i = 0; i < mainJsonList.length(); i++)
			{
				JSONObject obj = mainJsonList.getJSONObject(i);
				Staff s = new Staff();
				
				s.setStaffID(UUID.fromString(obj.getString("StaffID")));
				s.setAddressID(UUID.fromString(obj.getString("AddressID")));
				s.setStationID(UUID.fromString(obj.getString("StationID")));
				s.setFirstName(obj.getString("FirstName"));
				s.setLastName(obj.getString("LastName"));
				s.setUserName(obj.getString("UserName"));
				s.setPassword(obj.getString("Password"));
				s.setRights(obj.getInt("Rights"));
				s.setBirthDate((Date) obj.get("birthDate"));
				s.setEmail(obj.getString("Email"));
				s.setApiToken(obj.getString("Api_token"));
				s.setLastUpdated(obj.getLong("LastUpdated"));

				mainList.add(s);
			}
			
			//update tables
			ArrayList<Staff> smallerList = new ArrayList<Staff>();
			ArrayList<Staff> biggerList = new ArrayList<Staff>();
			
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
				Staff tmpS = new Staff();
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
			System.out.println("SyncStaffError");
			System.out.println(e);
		}
		finally
		{
			System.out.println("---- Staff ----");
		}
	}
	
	private void updateLocal(ArrayList<Staff> addressList)
	{
		for (int i = 0; i < addressList.size(); i++)
		{
			sDAO.insertOrUpdate(addressList.get(i));		
		}
	}
	
	private void updateMain(ArrayList<Staff> staffList)
	{
		try {
			if (staffList.isEmpty())
				return;
			
			HashMap<String, String> params = new HashMap<String, String>();
			
			JSONArray addressListJSON = new JSONArray(staffList);
			
			params.put("staffList", addressListJSON.toString());
			
			
			g3API.setUrl("staff/massUpdate");
			g3API.setRequestType(RequestType.MASSPUT);
			g3API.setParams(params);
			g3API.getJsonResult();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
