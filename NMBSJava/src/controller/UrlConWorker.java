package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.SwingWorker;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

public class UrlConWorker extends SwingWorker<JSONObject, Object> {

	private final static String BASE_URL = "http://nmbs-team.tk/api/";
	private final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.29 Safari/537.36";
	
	private String url;
	private HashMap<String, String> params;
	private RequestType requestType;
	
	public enum RequestType {
		GET, POST, PUT, DELETE
	}
	
	
	
	public UrlConWorker(String url, RequestType requestType, HashMap<String, String> params) {
		this.url = url;
		this.params = params;
		this.requestType = requestType;
	}
	
	
	@Override
	protected JSONObject doInBackground() throws Exception {
		HttpResponse response = null;
		
		switch(this.requestType) {
		case GET:
			response = this.getRequest();
			break;
		
		case POST:
			response = this.postRequest();
			break;
		
		case PUT:
			response = this.putRequest();
			break;
		
		case DELETE:
			break;
		}
		
		
		return this.httpToJson(response);
	}

	private JSONObject httpToJson(HttpResponse response) throws UnsupportedOperationException, IOException {
		
		BufferedReader rd = new BufferedReader(
		        new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		
		return new JSONObject(result.toString());
	}
	
	private HttpResponse getRequest() throws ClientProtocolException, IOException
	{
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet get = new HttpGet(BASE_URL + url);

		
		// add header
		get.setHeader("User-Agent", USER_AGENT);
		get.setHeader("Authorization", "Bearer " + LoginController.getToken());
		
		for (Map.Entry<String, String> entry : this.params.entrySet())
		{
			url += "&" + entry.getKey() + "=" + entry.getValue();
		}
		
		return client.execute(get);
	}
	
	private HttpResponse postRequest() throws ClientProtocolException, IOException 
	{
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(BASE_URL + url);

		
		// add header
		post.setHeader("User-Agent", USER_AGENT);
		post.setHeader("Authorization", "Bearer " + LoginController.getToken());

		
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		
		for (Map.Entry<String, String> entry : this.params.entrySet())
		{
			urlParameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		

		post.setEntity(new UrlEncodedFormEntity(urlParameters));
		
		return client.execute(post);
	}
	
	private HttpResponse putRequest() throws ClientProtocolException, IOException 
	{
		HttpClient client = HttpClientBuilder.create().build();
		HttpPut put = new HttpPut(BASE_URL + url);

		
		// add header
		put.setHeader("User-Agent", USER_AGENT);
		put.setHeader("Authorization", "Bearer " + LoginController.getToken());

		
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		
		for (Map.Entry<String, String> entry : this.params.entrySet())
		{
			urlParameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		

		put.setEntity(new UrlEncodedFormEntity(urlParameters));
		
		return client.execute(put);
	}
	
	

}
