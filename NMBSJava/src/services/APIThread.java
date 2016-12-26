package services;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONArray;

import controller.APIController;
import controller.APIController.APIUrl;
import controller.APIController.RequestType;

public class APIThread extends Thread
{
	private JSONArray apiResult;
	private APIController api;
	private ThreadListener listener;
	
	
	public APIThread(APIUrl apiUrl, String url, RequestType requestType, HashMap<String, String> params)
	{
		super();
		this.api = new APIController(apiUrl, url, requestType, params);
	}
	
	public void settListener(ThreadListener listener)
	{
		this.listener = listener;
	}

	@Override
	public void run()
	{
		try {
			apiResult = api.getJsonResult();
			listener.setResult(apiResult);

		}
		catch (UnsupportedOperationException | IOException e) {
			e.printStackTrace();
		}

	}
	
	public APIController getAPIController()
	{
		return this.api;
	}
}
