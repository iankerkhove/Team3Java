package model.api;

import org.json.JSONObject;

import controller.DateTimeConverter;

public class StationIrail
{
	private String delay;
	private String station;
	private StationInfo stationinfo;
	private String time;
	private String vehicle;
	private String platform;
	private String platforminfoName;
	private String platforminfoNormal;
	private String cancelled;
	private String direction;
	
	public StationIrail()
	{}

	public StationIrail(String delay, String station, StationInfo stationinfo, String time, String vehicle,
			String platform, String platforminfoName, String platforminfoNormal, String cancelled, String direction)
	{
		this.delay = delay;
		this.station = station;
		this.stationinfo = stationinfo;
		this.time = time;
		this.vehicle = vehicle;
		this.platform = platform;
		this.platforminfoName = platforminfoName;
		this.platforminfoNormal = platforminfoNormal;
		this.cancelled = cancelled;
		this.direction = direction;
	}

	public StationIrail(JSONObject json) {
		
		this.delay = json.getString("delay");
		this.station = json.getString("station");
		this.stationinfo = new StationInfo(json.getJSONObject("stationinfo"));
		this.time = json.getString("time");
		this.vehicle = json.getString("vehicle");
		this.platform = json.getString("platform");
		this.platforminfoName = json.getJSONObject("platforminfo").getString("name");
		this.platforminfoNormal = json.getJSONObject("platforminfo").getString("normal");
		this.cancelled = json.getString("canceled");
		this.direction = json.getJSONObject("direction").getString("name");
	}

	public String getDelay() {
		return DateTimeConverter.getReadableFromEpoch(this.delay);
	}

	public String getStation() {
		return station;
	}

	public StationInfo getStationinfo() {
		return stationinfo;
	}

	public String getDate() {
		return DateTimeConverter.getDateString(this.time);
	}
	
	public String getTime() {
		return DateTimeConverter.getTimeString(this.time);
	}

	public String getVehicle() {
		return vehicle;
	}

	public String getPlatform() {
		return platform;
	}

	public String getPlatforminfoName() {
		return platforminfoName;
	}

	public String getPlatforminfoNormal() {
		return platforminfoNormal;
	}

	public String getCancelled() {
		return cancelled;
	}

	public String getDirection() {
		return direction;
	}

}
