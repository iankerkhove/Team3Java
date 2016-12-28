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
import dao.DiscountDAO;
import model.Discount;

public class SyncDiscountRunnable implements Runnable
{

	private DiscountDAO dDAO;
	private APIController g3API;

	@Override
	public void run()
	{
		try {

			// check if has to update
			HashMap<String, String> params = new HashMap<String, String>();
			g3API = new APIController(APIUrl.G3, "discount/massUpdateStatus", RequestType.GET, params);
			dDAO = new DiscountDAO();

			JSONObject mainStatus = g3API.getJsonResult().getJSONObject(0);
			TreeMap<String, String> localStatus = dDAO.updateStatus();

			if (localStatus.get("Count").equals(mainStatus.getString("Count"))
					&& localStatus.get("LastUpdated").equals(mainStatus.getString("LastUpdated"))) {

				System.out.println("Discount up to date");
				return;
			}

			// get main table values
			g3API.setUrl("discount");
			JSONArray mainJsonList = g3API.getJsonResult();

			ArrayList<Discount> localList = dDAO.selectAll();
			ArrayList<Discount> mainList = new ArrayList<Discount>();

			for (int i = 0; i < mainJsonList.length(); i++) {
				JSONObject obj = mainJsonList.getJSONObject(i);
				Discount d = new Discount();

				d.setDiscountID(UUID.fromString(obj.getString("DiscountID")));
				d.setName(obj.getString("Name"));
				d.setAmount(obj.getDouble("Amount"));
				d.setLastUpdated(obj.getLong("LastUpdated"));

				mainList.add(d);
			}

			// update tables
			ArrayList<Discount> smallerList = new ArrayList<Discount>();
			ArrayList<Discount> biggerList = new ArrayList<Discount>();

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
				Discount tmpS = new Discount();
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
			System.out.println("SyncDiscountError");
			System.out.println(e);
		}
		finally {
			System.out.println("---- Discount ----");
		}
	}

	private void updateLocal(ArrayList<Discount> discountList)
	{
		dDAO.setSyncFunction();
		
		for (int i = 0; i < discountList.size(); i++) {
			dDAO.insertOrUpdate(discountList.get(i));
		}
	}

	private void updateMain(ArrayList<Discount> discountList)
	{
		try {
			if (discountList.isEmpty())
				return;

			HashMap<String, String> params = new HashMap<String, String>();

			JSONArray discountListJSON = new JSONArray(discountList);

			params.put("discountList", discountListJSON.toString());

			g3API.setUrl("discount/massUpdate");
			g3API.setRequestType(RequestType.MASSPUT);
			g3API.setParams(params);
			g3API.getJsonResult();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
