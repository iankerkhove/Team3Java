package controller;

import org.json.JSONObject;

public interface ApiRequest {
	
	public void afterUrlConWorker(JSONObject response, Object panel);

}
