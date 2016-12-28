package api;

import org.json.JSONObject;

import controller.DateTimeConverter;

public class ViaAPI {

	private JSONObject json = null;
	
	private String id;
	private ViaPointAPI arrival;
	private ViaPointAPI departure;
	private String timeBetween;
	private String station;
	private StationInfoAPI stationinfo;
	private String vehicle;
	private String direction;
	
	public ViaAPI(JSONObject json) {
		this.json = json;
		
		this.id = this.json.getString("id");
		this.arrival = new ViaPointAPI(this.json.getJSONObject("arrival"));
		this.departure = new ViaPointAPI(this.json.getJSONObject("departure"));
		this.timeBetween = this.json.getString("timeBetween");
		this.station = this.json.getString("station");
		this.stationinfo = new StationInfoAPI(json.getJSONObject("stationinfo"));
		this.vehicle = this.json.getString("vehicle");
		this.direction = this.json.getJSONObject("direction").getString("name");
	}

	public String getId() {
		return id;
	}

	public ViaPointAPI getArrival() {
		return arrival;
	}

	public ViaPointAPI getDeparture() {
		return departure;
	}

	public String getTimeBetween() {
		return DateTimeConverter.getReadableFromEpoch(this.timeBetween);
	}

	public String getStation() {
		return station;
	}

	public StationInfoAPI getStationinfo() {
		return stationinfo;
	}

	public String getVehicle() {
		return vehicle;
	}

	public String getDirection() {
		return direction;
	}

}
