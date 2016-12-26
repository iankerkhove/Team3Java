package model;

import java.time.Instant;
import java.util.UUID;

public class Person
{
	private String firstName;
	private String lastName;
	private String birthDate;
	private String email;
	private Address address;
	private UUID addressID;
	private long lastUpdated;

	public Person()
	{}
	
	public Person(String firstName, String lastName, String birthDate, String email, Address address)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.email = email;
		this.address = address;
		this.lastUpdated = Instant.now().getEpochSecond();
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

	public UUID getAddressID()
	{
		return addressID;
	}

	public void setAddressID(UUID addressID)
	{
		this.addressID = addressID;
	}

	public Address getAddress()
	{
		return address;
	}

	public void setAddress(Address address)
	{
		this.address = address;
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