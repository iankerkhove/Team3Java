package model.api;

import org.json.JSONObject;

import controller.DateTimeConverter;

public class ViaPoint
{
	private String time;
	private String platform;
	private String platforminfoName;
	private String platforminfoNormal;
	private String delay;
	private String canceled;

	public ViaPoint()
	{}
	
	public ViaPoint(String time, String platform, String platforminfoName, String platforminfoNormal, String delay,
			String canceled)
	{
		this.time = time;
		this.platform = platform;
		this.platforminfoName = platforminfoName;
		this.platforminfoNormal = platforminfoNormal;
		this.delay = delay;
		this.canceled = canceled;
	}

	public ViaPoint(JSONObject json) {

		this.time = json.getString("time");
		this.platform = json.getString("platform");
		this.platforminfoName = json.getJSONObject("platforminfo").getString("name");
		this.platforminfoNormal = json.getJSONObject("platforminfo").getString("normal");
		this.delay = json.getString("delay");
		this.canceled = json.getString("canceled");
	}

	public String getDate() {
		return DateTimeConverter.getDateString(this.time);
	}
	
	public String getTime()
	{
		return DateTimeConverter.getTimeString(this.time);
	}

	public String getPlatform()
	{
		return platform;
	}

	public String getPlatforminfoName()
	{
		return platforminfoName;
	}

	public String getPlatforminfoNormal()
	{
		return platforminfoNormal;
	}

	public String getDelay()
	{
		return DateTimeConverter.getReadableFromEpoch(this.delay);
	}

	public String getCanceled()
	{
		return canceled;
	}
}
