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
import dao.TicketDAO;
import model.Ticket;

public class SyncTicketRunnable implements Runnable
{

	private TicketDAO aDAO;
	private APIController g3API;
	
	@Override
	public void run()
	{
		try {

			//check if has to update
			HashMap<String, String> params = new HashMap<String, String>();
			g3API = new APIController(APIUrl.G3, "ticket/massUpdateStatus", RequestType.GET, params);
			aDAO = new TicketDAO();

			JSONObject mainStatus = g3API.getJsonResult().getJSONObject(0);
			TreeMap<String, String> localStatus = aDAO.updateStatus();

			if (localStatus.get("Count").equals(mainStatus.getString("Count"))
					&& localStatus.get("LastUpdated").equals(mainStatus.getString("LastUpdated"))) {
				
				System.out.println("Ticket up to date");
				return;
			}
			
			//get main table values
			g3API.setUrl("ticket");
			JSONArray mainJsonList = g3API.getJsonResult();
			
			ArrayList<Ticket> localList = aDAO.selectAll();
			ArrayList<Ticket> mainList = new ArrayList<Ticket>();
			
			for(int i = 0; i < mainJsonList.length(); i++)
			{
				JSONObject obj = mainJsonList.getJSONObject(i);
				Ticket a = new Ticket();
				
				a.setTicketID(UUID.fromString(obj.getString("TicketID")));
				a.setRouteID(UUID.fromString(obj.getJSONObject("Route").getString("RouteID")));
				a.setTypeTicketID(UUID.fromString(obj.getJSONObject("TypeTicket").getString("TypeTicketID")));
				a.setDate(obj.getString("Date"));
				a.setValidFrom(obj.getString("ValidFrom"));
				a.setValidUntil(obj.getString("ValidUntil"));
				a.setLastUpdated(obj.getLong("LastUpdated"));
				
				mainList.add(a);
			}
			
			//update tables
			ArrayList<Ticket> smallerList = new ArrayList<Ticket>();
			ArrayList<Ticket> biggerList = new ArrayList<Ticket>();
			
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
				Ticket tmpA = new Ticket();
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
			System.out.println("SyncTicketError");
			System.out.println(e);
		}
		finally
		{
			System.out.println("---- Ticket ----");
		}
	}
	
	private void updateLocal(ArrayList<Ticket> ticketList)
	{
		for (int i = 0; i < ticketList.size(); i++)
		{
			aDAO.insertOrUpdate(ticketList.get(i));		
		}
	}
	
	private void updateMain(ArrayList<Ticket> ticketList)
	{
		try {
			if (ticketList.isEmpty())
				return;
			
			HashMap<String, String> params = new HashMap<String, String>();
			
			JSONArray ticketListJSON = new JSONArray(ticketList);
			
			params.put("ticketList", ticketListJSON.toString());
			
			
			g3API.setUrl("ticket/massUpdate");
			g3API.setRequestType(RequestType.MASSPUT);
			g3API.setParams(params);
			g3API.getJsonResult();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}