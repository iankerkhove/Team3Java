package model;

import controller.SettingController;

public class SettingsSingleton
{
	private static SettingsSingleton settings;
	
	private String firstTime;
	private int role;
	
	private SettingsSingleton()
	{

	}

	public static SettingsSingleton getSettings()
	{
		if (settings == null)
			settings = new SettingsSingleton();
		
		return settings;
	}
	
	public String getFirstTime()
	{
		return firstTime;
	}
	
	public void setFirstTime(String firstTime)
	{
		this.firstTime = firstTime;
		SettingController.save();
	}
	
	public void setRole(int role)
	{
		this.role = role;
	}
	
	public int getRole()
	{
		return this.role;
	}
}
