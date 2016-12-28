package model;

import java.time.Instant;
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
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((apiToken == null) ? 0 : apiToken.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + rights;
		result = prime * result + ((staffID == null) ? 0 : staffID.hashCode());
		result = prime * result + ((stationID == null) ? 0 : stationID.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Staff other = (Staff) obj;
		if (apiToken == null) {
			if (other.apiToken != null)
				return false;
		}
		else if (!apiToken.equals(other.apiToken))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		}
		else if (!password.equals(other.password))
			return false;
		if (rights != other.rights)
			return false;
		if (staffID == null) {
			if (other.staffID != null)
				return false;
		}
		else if (!staffID.equals(other.staffID))
			return false;
		if (stationID == null) {
			if (other.stationID != null)
				return false;
		}
		else if (!stationID.equals(other.stationID))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		}
		else if (!userName.equals(other.userName))
			return false;
		return true;
	}

}