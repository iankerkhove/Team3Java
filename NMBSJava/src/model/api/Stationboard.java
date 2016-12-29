package model.api;

import java.util.ArrayList;

import org.json.JSONArray;

public class Stationboard
{
	private String station;
	private ArrayList<Train> trains;
	
	public Stationboard()
	{}

	public Stationboard(String station, ArrayList<Train> trains)
	{
		this.station = station;
		this.trains = trains;
	}
	
	public Stationboard(JSONArray json)
	{
		trains = new ArrayList<Train>();
		for (int i = 0; i < json.length(); i++) {
			Train t = new Train(json.getJSONObject(i));
			trains.add(t);
		}
	}
	
	public String toString() {
		String ss = "";
		ss += "Treinen vanuit " + station + "\n\n";
		for (int i = 0; i < trains.size() && i < 10; i++) {
			ss += trains.get(i).getTime().getDeparture().substring(11, 16) + " " + trains.get(i).getTerminusStation();
			ss += "\n";
		}
		return ss;
	}
	
	public String getStation()
	{
		return station;
	}

	public void setStation(String station)
	{
		this.station = station;
	}

	public ArrayList<Train> getTrains()
	{
		return trains;
	}

	public void setTrains(ArrayList<Train> trains)
	{
		this.trains = trains;
	}

}
