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
import dao.CustomerDAO;
import model.Customer;
public class SyncCustomerRunnable implements Runnable  {

		private CustomerDAO cDAO;
		private APIController g3API;
		
		@Override
		public void run()
		{
			try {

				//check if has to update
				HashMap<String, String> params = new HashMap<String, String>();
				g3API = new APIController(APIUrl.G3, "customer/massUpdateStatus", RequestType.GET, params);
				cDAO = new CustomerDAO();

				JSONObject mainStatus = g3API.getJsonResult().getJSONObject(0);
				TreeMap<String, String> localStatus = cDAO.updateStatus();

				if (localStatus.get("Count").equals(mainStatus.getString("Count"))
						&& localStatus.get("LastUpdated").equals(mainStatus.getString("LastUpdated"))) {
					
					System.out.println("Staff' up to date");
					return;
				}
				
				//get main table values
				g3API.setUrl("customer");
				JSONArray mainJsonList = g3API.getJsonResult();
				
				ArrayList<Customer> localList = cDAO.selectAll();
				ArrayList<Customer> mainList = new ArrayList<Customer>();
				
				for(int i = 0; i < mainJsonList.length(); i++)
				{
					JSONObject obj = mainJsonList.getJSONObject(i);
					Customer c = new Customer();

					c.setCustomerID(UUID.fromString(obj.getString("CustomerID")));
					c.setAddressID(UUID.fromString(obj.getJSONObject("Address").getString("AddressID")));
					c.setRailCardID(UUID.fromString(obj.getJSONObject("RailCard").getString("CardID")));					
					c.setFirstName(obj.getString("FirstName"));
					c.setLastName(obj.getString("LastName"));
					c.setBirthDate(obj.getString("BirthDate"));
					c.setEmail(obj.getString("Email"));
					c.setLastUpdated(obj.getLong("LastUpdated"));

					mainList.add(c);
				}
				
				//update tables
				ArrayList<Customer> smallerList = new ArrayList<Customer>();
				ArrayList<Customer> biggerList = new ArrayList<Customer>();
				
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
					Customer tmpS = new Customer();
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
				System.out.println("SyncCustomerError");
				System.out.println(e);
			}
			finally
			{
				System.out.println("---- Customer ----");
			}
		}
		
		private void updateLocal(ArrayList<Customer> customerList)
		{
			for (int i = 0; i < customerList.size(); i++)
			{
				cDAO.insertOrUpdate(customerList.get(i));		
			}
		}
		
		private void updateMain(ArrayList<Customer> customerList)
		{
			try {
				if (customerList.isEmpty())
					return;
				
				HashMap<String, String> params = new HashMap<String, String>();
				
				JSONArray customerListJSON = new JSONArray(customerList);
				
				params.put("customerList", customerListJSON.toString());
				
				
				g3API.setUrl("customer/massUpdate");
				g3API.setRequestType(RequestType.MASSPUT);
				g3API.setParams(params);
				g3API.getJsonResult();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
