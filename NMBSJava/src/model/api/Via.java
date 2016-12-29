package model.api;

import org.json.JSONObject;

import controller.DateTimeConverter;

public class Via
{
	private String id;
	private ViaPoint arrival;
	private ViaPoint departure;
	private String timeBetween;
	private String station;
	private StationInfo stationinfo;
	private String vehicle;
	private String direction;
	
	public Via()
	{}
	
	public Via(String id, ViaPoint arrival, ViaPoint departure, String timeBetween, String station,
			StationInfo stationinfo, String vehicle, String direction)
	{
		this.id = id;
		this.arrival = arrival;
		this.departure = departure;
		this.timeBetween = timeBetween;
		this.station = station;
		this.stationinfo = stationinfo;
		this.vehicle = vehicle;
		this.direction = direction;
	}

	public Via(JSONObject json) {
		
		this.id = json.getString("id");
		this.arrival = new ViaPoint(json.getJSONObject("arrival"));
		this.departure = new ViaPoint(json.getJSONObject("departure"));
		this.timeBetween = json.getString("timeBetween");
		this.station = json.getString("station");
		this.stationinfo = new StationInfo(json.getJSONObject("stationinfo"));
		this.vehicle = json.getString("vehicle");
		this.direction = json.getJSONObject("direction").getString("name");
	}

	public String getId()
	{
		return id;
	}

	public ViaPoint getArrival()
	{
		return arrival;
	}

	public ViaPoint getDeparture()
	{
		return departure;
	}

	public String getTimeBetween()
	{
		return DateTimeConverter.getReadableFromEpoch(this.timeBetween);
	}

	public String getStation()
	{
		return station;
	}

	public StationInfo getStationinfo()
	{
		return stationinfo;
	}

	public String getVehicle()
	{
		return vehicle;
	}

	public String getDirection()
	{
		return direction;
	}
}
