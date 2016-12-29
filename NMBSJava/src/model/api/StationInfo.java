package model.api;

import org.json.JSONObject;

public class StationInfo
{
	private String id;
	private String locationX;
	private String locationY;
	private String name;
	private String standardName;

	public StationInfo()
	{}
	
	public StationInfo(String id, String locationX, String locationY, String name, String standardName)
	{
		this.id = id;
		this.locationX = locationX;
		this.locationY = locationY;
		this.name = name;
		this.standardName = standardName;
	}

	public StationInfo(JSONObject json) {
		
		this.id = json.getString("id");
		this.locationX = json.getString("locationX");
		this.locationY = json.getString("locationY");
		this.name = json.getString("name");
		this.standardName = json.getString("standardname");
		
	}

	public String getId()
	{
		return id;
	}

	public String getLocationX()
	{
		return locationX;
	}

	public String getLocationY()
	{
		return locationY;
	}

	public String getName()
	{
		return name;
	}

	public String getStandardName()
	{
		return standardName;
	}

}
