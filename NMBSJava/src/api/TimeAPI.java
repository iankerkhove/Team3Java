package api;

import org.json.JSONObject;

public class TimeAPI {
	private String date;
	private String duration;
	private String arrival;
	private String actualArrival;
	private String departure;
	private String actualDeparture;

	public TimeAPI(JSONObject json) {
		this.date = json.getString("Date");
		this.duration = json.getString("Duration");
		this.arrival = json.getString("Arrival");
		this.actualArrival = json.getString("ActualArrival");
		this.departure = json.getString("Departure");
		this.actualDeparture = json.getString("ActualDeparture");
	}

	public TimeAPI() {
		super();
		this.date = "1970-01-01T00:00:00";
		this.duration = "0";
		this.arrival = "1970-01-01T00:00:00";
		this.actualArrival = "1970-01-01T00:00:00";
		this.departure = "1970-01-01T00:00:00";
		this.actualDeparture = "1970-01-01T00:00:00";
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public String getActualArrival() {
		return actualArrival;
	}

	public void setActualArrival(String actualArrival) {
		this.actualArrival = actualArrival;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getActualDeparture() {
		return actualDeparture;
	}

	public void setActualDeparture(String actualDeparture) {
		this.actualDeparture = actualDeparture;
	}
}
