package api;

import java.util.ArrayList;
import org.json.JSONObject;

import controller.DateTimeConverter;

public class ConnectionAPI {
	private JSONObject json = null;

	private String id;
	private StationIrailAPI departure;
	private StationIrailAPI arrival;
	private String duration;
	private String numberOfVias;
	private ArrayList<ViaAPI> vias;

	public ConnectionAPI(JSONObject json) {
		this.json = json;
		this.id = this.json.getString("id");
		this.departure = new StationIrailAPI(this.json.getJSONObject("departure"));
		this.arrival = new StationIrailAPI(this.json.getJSONObject("arrival"));
		this.duration = this.json.getString("duration");
		this.vias = new ArrayList<ViaAPI>();
		if (this.json.has("vias")) {
			this.numberOfVias = this.json.getJSONObject("vias").getString("number");
			for (int i = 0; i < this.json.getJSONObject("vias").getJSONArray("via").length(); i++) {
				ViaAPI e = new ViaAPI(this.json.getJSONObject("vias").getJSONArray("via").getJSONObject(i));
				this.vias.add(e);
			}
		} else {
			this.numberOfVias = "0";
		}
	}

	public String getId() {
		return id;
	}

	public StationIrailAPI getDeparture() {
		return departure;
	}

	public StationIrailAPI getArrival() {
		return arrival;
	}

	public String getDuration() {
		return DateTimeConverter.getReadableFromEpoch(this.duration);
	}

	public ArrayList<ViaAPI> getVias() {
		return vias;
	}

	public String getNumberOfVias() {
		return numberOfVias;
	}
}
