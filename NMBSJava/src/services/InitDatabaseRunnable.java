package services;

import dao.InitDatabase;
import model.SettingsSingleton;

public class InitDatabaseRunnable implements Runnable
{

	@Override
	public void run()
	{
		InitDatabase.init();
		
		SettingsSingleton settings = SettingsSingleton.getSettings();
		settings.setFirstTime("True");
		
		System.out.println("Databases-setted");
	}

}
