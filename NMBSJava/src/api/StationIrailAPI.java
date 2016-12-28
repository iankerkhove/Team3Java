package api;

import org.json.JSONObject;

import controller.DateTimeConverter;

public class StationIrailAPI {
	JSONObject json = null;
	
	private String delay;
	private String station;
	private StationInfoAPI stationinfo;
	private String time;
	private String vehicle;
	private String platform;
	private String platforminfoName;
	private String platforminfoNormal;
	private String cancelled;
	private String direction;

	public StationIrailAPI(JSONObject json) {
		this.json = json;
		
		this.delay = this.json.getString("delay");
		this.station = this.json.getString("station");
		this.stationinfo = new StationInfoAPI(this.json.getJSONObject("stationinfo"));
		this.time = this.json.getString("time");
		this.vehicle = this.json.getString("vehicle");
		this.platform = this.json.getString("platform");
		this.platforminfoName = this.json.getJSONObject("platforminfo").getString("name");
		this.platforminfoNormal = this.json.getJSONObject("platforminfo").getString("normal");
		this.cancelled = this.json.getString("canceled");
		this.direction = this.json.getJSONObject("direction").getString("name");
	}

	public String getDelay() {
		return DateTimeConverter.getReadableFromEpoch(this.delay);
	}

	public String getStation() {
		return station;
	}

	public StationInfoAPI getStationinfo() {
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
