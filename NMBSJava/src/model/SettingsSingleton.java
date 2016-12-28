package model;

import java.util.UUID;

import controller.SettingController;

public class SettingsSingleton
{
	private static SettingsSingleton settings;
	
	private String firstTime;
	private UUID StaffID;
	private int rights;
	private String apiToken;
	
	private SettingsSingleton()
	{}

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
	
	public UUID getStaffID()
	{
		return StaffID;
	}

	public void setStaffID(UUID staffID)
	{
		StaffID = staffID;
	}

	public int getRights()
	{
		return rights;
	}

	public void setRights(int rights)
	{
		this.rights = rights;
	}

	public String getApiToken()
	{
		return apiToken;
	}

	public void setApiToken(String apiToken)
	{
		this.apiToken = apiToken;
	}
	
	public void clearCreds()
	{
		this.apiToken = "";
		this.StaffID = UUID.randomUUID();
	}
}
