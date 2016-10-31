package model;

import org.json.JSONObject;

public class StationAPI {
	private String name;
	private String coordinates;
	private String arrivalPlatform;
	private String departurePlatform;
	private TimeAPI time;
	private String platform;

	public StationAPI(JSONObject json) {
		this.name = json.getString("Name");
		this.coordinates = json.getString("Coordinates");
		if (!json.get("ArrivalPlatform").equals(null)) {
			this.arrivalPlatform = json.getString("ArrivalPlatform");
		} else
			this.arrivalPlatform = "Niet gekend";
		if (!json.get("DeparturePlatform").equals(null)) {
			this.departurePlatform = json.getString("DeparturePlatform");
		} else
			this.departurePlatform = "Niet gekend";
		this.time = new TimeAPI(json.getJSONObject("Time"));
		if (!json.get("Platform").equals(null)) {
			this.platform = json.getString("Platform");
		} else
			this.platform = "Niet Gekend";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	public String getArrivalPlatform() {
		return arrivalPlatform;
	}

	public void setArrivalPlatform(String arrivalPlatform) {
		this.arrivalPlatform = arrivalPlatform;
	}

	public String getDeparturePlatform() {
		return departurePlatform;
	}

	public void setDeparturePlatform(String departurePlatform) {
		this.departurePlatform = departurePlatform;
	}

	public TimeAPI getTime() {
		return time;
	}

	public void setTime(TimeAPI time) {
		this.time = time;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}
}
