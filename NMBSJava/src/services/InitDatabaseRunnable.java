package services;

import java.util.HashMap;

import controller.APIController;
import controller.APIController.APIUrl;
import controller.APIController.RequestType;
import dao.InitDatabase;
import model.SettingsSingleton;

public class InitDatabaseRunnable implements Runnable
{

	@Override
	public void run()
	{

		try {
			HashMap<String, String> params = new HashMap<String, String>();
			
			params.put("TargetFilePath", "localDB.db");
			
			APIController g3API = new APIController(APIUrl.G3, "mixed/getDatabase", RequestType.GETFILE, params);
			g3API.getJsonResult();
			
		}
		catch (Exception e) {
			
			System.out.println("Download DB failed, initializing localDB");
			
			InitDatabase.init();
			
			SettingsSingleton settings = SettingsSingleton.getSettings();
			settings.setFirstTime(true);
			
			System.out.println("Databases-setted");
		}

	}

}
