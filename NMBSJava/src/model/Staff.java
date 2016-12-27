package model;

import java.util.UUID;

public class Staff extends Person
{
	private UUID staffID;
	private UUID stationID;
	private Station station;
	private String userName;
	private String password;
	private int rights;
	private String apiToken;

	public Staff()
	{}
	public Staff(Address address, Station station, String firstName, String lastName, String userName, String password,
			int rights, String birthDate, String email)
	{
		super(firstName, lastName, birthDate, email, address);
		this.staffID = UUID.randomUUID();
		this.station = station;
		this.userName = userName;
		this.password = password;
		this.rights = rights;
		this.apiToken = null;
	}

	public UUID getStaffID()
	{
		return staffID;
	}

	public void setStaffID(UUID staffID)
	{
		this.staffID = staffID;
	}

	public Station getStation()
	{
		return station;
	}

	public void setStation(Station station)
	{
		this.station = station;
	}

	public UUID getStationID()
	{
		return stationID;
	}

	public void setStationID(UUID stationID)
	{
		this.stationID = stationID;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public int getRights()
	{
		return rights;
	}

	public void setRights(int rights)
	{
		this.rights = rights;
	}
	public String getApiToken()
	{
		return apiToken;
	}

	public void setApiToken(String apiToken)
	{
		this.apiToken = apiToken;
	}

}