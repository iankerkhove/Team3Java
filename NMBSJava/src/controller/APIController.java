package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class APIController
{
	private final static String BASE_URL_IR = "https://api.irail.be/connections/";
	private final static String BASE_URL_TT = "https://traintracks.online/api/";
	private final static String BASE_URL_G3 = "http://nmbs-team.tk/api/";

	private final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.29 Safari/537.36";

	private String url;
	private HashMap<String, String> params;
	private RequestType requestType;
	private String base_url;

	public enum RequestType
	{
		GET, POST, PUT, DELETE
	}

	public enum APIUrl
	{
		TRAINTRACKS, IRAILS, G3
	}

	public APIController(APIUrl apiUrl, String url, RequestType requestType, HashMap<String, String> params)
	{
		this.url = url;
		this.params = params;
		this.requestType = requestType;

		switch (apiUrl) {
		case TRAINTRACKS:
			this.base_url = BASE_URL_TT;
			break;
		case IRAILS:
			this.base_url = BASE_URL_IR;
			break;
		default:
			this.base_url = BASE_URL_G3;
			break;
		}
	}
	
	public JSONObject getJsonResult() throws UnsupportedOperationException, IOException
	{
		HttpResponse response = null;

		switch (this.requestType) {
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


	private JSONObject httpToJson(HttpResponse response) throws UnsupportedOperationException, IOException
	{

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

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
		HttpGet get = new HttpGet(this.base_url + "?" + url);

		// add header
		get.setHeader("User-Agent", USER_AGENT);
		get.setHeader("Authorization", "Bearer " + LoginController.getToken());

		if (this.base_url.equals(BASE_URL_IR)) {
			this.params.putIfAbsent("format", "json");
			this.params.putIfAbsent("timeSel", "depart");
			this.params.putIfAbsent("lang", "NL");
		}

		boolean firstItt = true;

		for (Map.Entry<String, String> entry : this.params.entrySet()) {
			if (firstItt) {
				url += entry.getKey() + "=" + entry.getValue();
				firstItt = false;
			}
			else {
				url += "&" + entry.getKey() + "=" + entry.getValue();
			}
		}

		return client.execute(get);
	}

	private HttpResponse postRequest() throws ClientProtocolException, IOException
	{
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(this.base_url + url);

		// add header
		post.setHeader("User-Agent", USER_AGENT);
		post.setHeader("Authorization", "Bearer " + LoginController.getToken());

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();

		for (Map.Entry<String, String> entry : this.params.entrySet()) {
			urlParameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}

		post.setEntity(new UrlEncodedFormEntity(urlParameters));

		return client.execute(post);
	}

	private HttpResponse putRequest() throws ClientProtocolException, IOException
	{
		HttpClient client = HttpClientBuilder.create().build();
		HttpPut put = new HttpPut(this.base_url + url);

		// add header
		put.setHeader("User-Agent", USER_AGENT);
		put.setHeader("Authorization", "Bearer " + LoginController.getToken());

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();

		for (Map.Entry<String, String> entry : this.params.entrySet()) {
			urlParameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}

		put.setEntity(new UrlEncodedFormEntity(urlParameters));

		return client.execute(put);
	}

	
	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public HashMap<String, String> getParams()
	{
		return params;
	}

	public void setParams(HashMap<String, String> params)
	{
		this.params = params;
	}

	public RequestType getRequestType()
	{
		return requestType;
	}

	public void setRequestType(RequestType requestType)
	{
		this.requestType = requestType;
	}
}
