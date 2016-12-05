package api;

import org.json.JSONObject;

import controller.DateTimeConverter;

public class ViaPointAPI {
	private JSONObject json = null;
	
	private String time;
	private String platform;
	private String platforminfoName;
	private String platforminfoNormal;
	private String delay;
	private String canceled;

	public ViaPointAPI(JSONObject json) {
		this.json = json;

		this.time = this.json.getString("time");
		this.platform = this.json.getString("platform");
		this.platforminfoName = this.json.getJSONObject("platforminfo").getString("name");
		this.platforminfoNormal = this.json.getJSONObject("platforminfo").getString("normal");
		this.delay = this.json.getString("delay");
		this.canceled = this.json.getString("canceled");
	}

	public String getDate() {
		return DateTimeConverter.getDateString(this.time);
	}
	
	public String getTime() {
		return DateTimeConverter.getTimeString(this.time);
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

	public String getDelay() {
		return DateTimeConverter.getReadableFromEpoch(this.delay);
	}

	public String getCanceled() {
		return canceled;
	}
}
