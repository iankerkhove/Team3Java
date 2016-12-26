package model;

import java.time.Instant;
import java.util.UUID;

public class Staff
{
	private UUID staffID;
	private UUID addressID;
	private Address address;
	private UUID stationID;
	private Station station;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private int rights;
	private String birthDate;
	private String email;
	private String apiToken;
	private long lastUpdated;
	
	public Staff()
	{}

	public Staff(UUID addressID, UUID stationID, String firstName, String lastName, String userName, String password,
			int rights, String birthDate, String email)
	{
		this.staffID = UUID.randomUUID();
		this.addressID = addressID;
		this.stationID = stationID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.rights = rights;
		this.birthDate = birthDate;
		this.email = email;
		this.apiToken = null;
		this.lastUpdated = Instant.now().getEpochSecond();
	}

	public UUID getStaffID()
	{
		return staffID;
	}

	public void setStaffID(UUID staffID)
	{
		this.staffID = staffID;
	}

	public Address getAddress()
	{
		return address;
	}

	public void setAddress(Address address)
	{
		this.address = address;
	}

	public Station getStation()
	{
		return station;
	}

	public void setStation(Station station)
	{
		this.station = station;
	}

	public UUID getAddressID()
	{
		return addressID;
	}

	public void setAddressID(UUID addressID)
	{
		this.addressID = addressID;
	}

	public UUID getStationID()
	{
		return stationID;
	}

	public void setStationID(UUID stationID)
	{
		this.stationID = stationID;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
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

	public String getBirthDate()
	{
		return birthDate;
	}

	public void setBirthDate(String birthDate)
	{
		this.birthDate = birthDate;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getApiToken()
	{
		return apiToken;
	}

	public void setApiToken(String apiToken)
	{
		this.apiToken = apiToken;
	}

	public long getLastUpdated()
	{
		return lastUpdated;
	}

	public void setLastUpdated(long lastUpdated)
	{
		this.lastUpdated = lastUpdated;
	}

	public void update()
	{
		this.lastUpdated = Instant.now().getEpochSecond();
	}

}