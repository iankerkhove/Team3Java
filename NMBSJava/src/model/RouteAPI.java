package model;

import java.util.ArrayList;

import org.json.JSONObject;

public class RouteAPI {
	private String departure;
	private String arrival;
	private String line;
	private boolean cancelled;
	private ArrayList<TrainAPI> trains = new ArrayList<TrainAPI>();
	private ArrayList<TransferstationAPI> transferstations = new ArrayList<TransferstationAPI>();

	public RouteAPI(JSONObject json) {
		this.departure = json.getString("Departure");
		this.arrival = json.getString("Arrival");
		if (!json.get("Line").equals(null)) {
			this.line = json.getString("Line");
		} else
			this.line = "";
		this.cancelled = json.getBoolean("Cancelled");
		for (int i = 0; i < json.getJSONArray("Trains").length(); i++) {
			TrainAPI t = new TrainAPI(json.getJSONArray("Trains").getJSONObject(i));
			trains.add(t);
		}
		for (int i = 0; i < json.getJSONArray("TransferStations").length(); i++) {
			TransferstationAPI t = new TransferstationAPI(json.getJSONArray("TransferStations").getJSONObject(i));
			transferstations.add(t);
		}
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	public ArrayList<TrainAPI> getTrains() {
		return trains;
	}

	public void setTrains(ArrayList<TrainAPI> trains) {
		this.trains = trains;
	}

	public ArrayList<TransferstationAPI> getTransferstations() {
		return transferstations;
	}

	public void setTransferstations(ArrayList<TransferstationAPI> transferstations) {
		this.transferstations = transferstations;
	}
}
