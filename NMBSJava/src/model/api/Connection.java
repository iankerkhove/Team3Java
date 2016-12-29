package model.api;

import java.util.ArrayList;

import org.json.JSONObject;

import controller.DateTimeConverter;

public class Connection
{
	private String id;
	private StationIrail departure;
	private StationIrail arrival;
	private String duration;
	private String numberOfVias;
	private ArrayList<Via> vias;

	public Connection()
	{}
	
	public Connection(String id, StationIrail departure, StationIrail arrival, String duration,
			String numberOfVias, ArrayList<Via> vias)
	{
		this.id = id;
		this.departure = departure;
		this.arrival = arrival;
		this.duration = duration;
		this.numberOfVias = numberOfVias;
		this.vias = vias;
	}

	public Connection(JSONObject json) {
		
		this.id = json.getString("id");
		this.departure = new StationIrail(json.getJSONObject("departure"));
		this.arrival = new StationIrail(json.getJSONObject("arrival"));
		this.duration = json.getString("duration");
		
		this.vias = new ArrayList<Via>();
		
		if (json.has("vias"))
		{
			this.numberOfVias = json.getJSONObject("vias").getString("number");
			
			for (int i = 0; i < json.getJSONObject("vias").getJSONArray("via").length(); i++) 
			{
				Via e = new Via(json.getJSONObject("vias").getJSONArray("via").getJSONObject(i));
				this.vias.add(e);
			}
		}
		else 
		{
			this.numberOfVias = "0";
		}
	}

	public String getId()
	{
		return id;
	}

	public StationIrail getDeparture()
	{
		return departure;
	}

	public StationIrail getArrival()
	{
		return arrival;
	}

	public String getDuration()
	{
		return DateTimeConverter.getReadableFromEpoch(this.duration);
	}

	public String getNumberOfVias()
	{
		return numberOfVias;
	}

	public ArrayList<Via> getVias()
	{
		return vias;
	}
}
