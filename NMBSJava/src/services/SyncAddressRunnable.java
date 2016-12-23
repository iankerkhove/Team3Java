package services;

import java.util.Collection;
import java.util.TreeMap;

import org.json.JSONObject;

import controller.APIController;
import controller.APIController.APIUrl;
import controller.APIController.RequestType;
import dao.AddressDAO;

public class SyncAddressRunnable implements Runnable
{

	@Override
	public void run()
	{
		try {

			// (APIUrl apiUrl, String url, RequestType requestType,
			// HashMap<String, String> params)
			APIController g3API = new APIController(APIUrl.G3, "address/massUpdateStatus", RequestType.GET, null);
			AddressDAO aDao = new AddressDAO();

			TreeMap<String, String> localStatus = aDao.updateStatus();
			JSONObject mainStatus = g3API.getJsonResult();

			if (localStatus.get("Count") == mainStatus.getString("Count")
					&& localStatus.get("LastUpdated") == mainStatus.getString("LastUpdated")) {

			}

		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

}
