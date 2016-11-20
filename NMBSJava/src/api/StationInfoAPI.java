package api;

import org.json.JSONObject;

public class StationInfoAPI {
	private JSONObject json = null;

	private String id;
	private String locationX;
	private String locationY;
	private String name;
	private String standardName;

	public StationInfoAPI(JSONObject json) {
		this.json = json;
		
		this.id = this.json.getString("id");
		this.locationX = this.json.getString("locationX");
		this.locationY = this.json.getString("locationY");
		this.name = this.json.getString("name");
		this.standardName = this.json.getString("standardname");
		
	}

	public String getId() {
		return id;
	}

	public String getLocationX() {
		return locationX;
	}

	public String getLocationY() {
		return locationY;
	}

	public String getName() {
		return name;
	}

	public String getStandardName() {
		return standardName;
	}
}
