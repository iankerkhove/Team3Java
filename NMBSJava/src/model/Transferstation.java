package model;

import org.json.JSONObject;

public class Transferstation {
	private String fullId;
	private String terminusstation;
	private String transferAt;
	private String arrivalPlatform;
	private String arrivalTime;
	private String departurePlatform;
	private String departureTime;
	private String stepOverTime;

	public Transferstation(JSONObject json) {
		this.fullId = json.getString("FullId");
		this.terminusstation = json.getString("TerminusStation");
		if (!json.get("TransferAt").equals(null)) {
			this.transferAt = json.getString("TransferAt");
		} else
			this.transferAt = "";
		if (!json.get("ArrivalPlatform").equals(null)) {
			this.arrivalPlatform = json.getString("ArrivalPlatform");
		} else
			this.arrivalPlatform = "";
		this.arrivalTime = json.getString("ArrivalTime");
		if (!json.get("DeparturePlatform").equals(null)) {
			this.departurePlatform = json.getString("DeparturePlatform");
		} else
			this.departurePlatform = "";
		this.departureTime = json.getString("DepartureTime");
		this.stepOverTime = json.getString("StepOverTime");
	}

	public String getFullId() {
		return fullId;
	}

	public void setFullId(String fullId) {
		this.fullId = fullId;
	}

	public String getTerminusstation() {
		return terminusstation;
	}

	public void setTerminusstation(String terminusstation) {
		this.terminusstation = terminusstation;
	}

	public String getTransferAt() {
		return transferAt;
	}

	public void setTransferAt(String transferAt) {
		this.transferAt = transferAt;
	}

	public String getArrivalPlatform() {
		return arrivalPlatform;
	}

	public void setArrivalPlatform(String arrivalPlatform) {
		this.arrivalPlatform = arrivalPlatform;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getDeparturePlatform() {
		return departurePlatform;
	}

	public void setDeparturePlatform(String departurePlatform) {
		this.departurePlatform = departurePlatform;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getStepOverTime() {
		return stepOverTime;
	}

	public void setStepOverTime(String stepOverTime) {
		this.stepOverTime = stepOverTime;
	}

}
