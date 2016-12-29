package model.api;

import java.util.ArrayList;

import org.json.JSONObject;

public class Train
{
	private int number;
	private int traintype;
	private String fullId;
	private String departureStation;
	private String terminusStation;
	private ArrayList<Station> stops;
	private int stopCount;
	private boolean cancelled;
	private TrainTime time;
	private String Jid;
	private String Cid;
	
	public Train()
	{}
	
	public Train(int number, int traintype, String fullId, String departureStation, String terminusStation,
			ArrayList<Station> stops, int stopCount, boolean cancelled, TrainTime time, String jid, String cid)
	{
		this.number = number;
		this.traintype = traintype;
		this.fullId = fullId;
		this.departureStation = departureStation;
		this.terminusStation = terminusStation;
		this.stops = stops;
		this.stopCount = stopCount;
		this.cancelled = cancelled;
		this.time = time;
		Jid = jid;
		Cid = cid;
	}
	
	public Train(JSONObject json)
	{
		if (!json.get("Number").equals(null))
			this.number = json.getInt("Number");
		else
			this.number = 0;
		
		this.traintype = json.getInt("TrainType");
		this.fullId = json.getString("FullId");
		
		if (!json.get("DepartureStation").equals(null)) 
			this.departureStation = json.getString("DepartureStation");
		else
			this.departureStation = "";
		
		this.terminusStation = json.getString("TerminusStation");
		this.stops = stopsFromJson(json.getJSONObject("Stops"));
		this.cancelled = json.getBoolean("Cancelled");
		this.time = new TrainTime(json.getJSONObject("Time"));
		
		if (!json.get("Jid").equals(null))
			this.Jid = (String) json.get("Jid");
		else
			this.Jid = "";
		
		if (!json.get("Cid").equals(null)) 
			this.Cid = (String) json.get("Cid");
		else
			this.Cid = "";
	}
	
	public ArrayList<Station> stopsFromJson(JSONObject json)
	{
		ArrayList<Station> returnArray = new ArrayList<Station>();
		
		for (int i = 0; i < json.getJSONArray("Stations").length(); i++) 
		{
			Station s = new Station(json.getJSONArray("Stations").getJSONObject(i));
			returnArray.add(s);
		}
		return returnArray;
	}
	
	public String toString()
	{
		String ss = "";
		ss += getFullId() + "\n\nVan: " + getStops().get(0).getName() + " ("
				+ getStops().get(0).getTime().getDeparture().substring(0, 10) + " om "
				+ getStops().get(0).getTime().getDeparture().substring(11, 16) + ")" + "\nNaar: "
				+ getStops().get(getStops().size() - 1).getName() + " ("
				+ getStops().get(getStops().size() - 1).getTime().getArrival().substring(0,
						10)
				+ " om " + getStops().get(getStops().size() - 1).getTime().getArrival()
						.substring(11, 16)
				+ ")";

		return ss;
	}

	public int getNumber()
	{
		return number;
	}

	public void setNumber(int number)
	{
		this.number = number;
	}

	public int getTraintype()
	{
		return traintype;
	}

	public void setTraintype(int traintype)
	{
		this.traintype = traintype;
	}

	public String getFullId()
	{
		return fullId;
	}

	public void setFullId(String fullId)
	{
		this.fullId = fullId;
	}

	public String getDepartureStation()
	{
		return departureStation;
	}

	public void setDepartureStation(String departureStation)
	{
		this.departureStation = departureStation;
	}

	public String getTerminusStation()
	{
		return terminusStation;
	}

	public void setTerminusStation(String terminusStation)
	{
		this.terminusStation = terminusStation;
	}

	public ArrayList<Station> getStops()
	{
		return stops;
	}

	public void setStops(ArrayList<Station> stops)
	{
		this.stops = stops;
	}

	public int getStopCount()
	{
		return stopCount;
	}

	public void setStopCount(int stopCount)
	{
		this.stopCount = stopCount;
	}

	public boolean isCancelled()
	{
		return cancelled;
	}

	public void setCancelled(boolean cancelled)
	{
		this.cancelled = cancelled;
	}

	public TrainTime getTime()
	{
		return time;
	}

	public void setTime(TrainTime time)
	{
		this.time = time;
	}

	public String getJid()
	{
		return Jid;
	}

	public void setJid(String jid)
	{
		Jid = jid;
	}

	public String getCid()
	{
		return Cid;
	}

	public void setCid(String cid)
	{
		Cid = cid;
	}
	
}
