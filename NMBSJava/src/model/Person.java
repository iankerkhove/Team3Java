package model;

import java.time.Instant;
import java.util.Date;

public class Person
{
	private String firstName;
	private String lastName;
	private Date birthDate;
	private String emailAddress;
	private Address address;
	private long lastUpdated;

	public Person()
	{}
	
	public Person(String firstName, String lastName, Date birthDate, String emailAddress, Address address)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.emailAddress = emailAddress;
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

	public Date getBirthDate()
	{
		return birthDate;
	}

	public void setBirthDate(Date birthDate)
	{
		this.birthDate = birthDate;
	}

	public String getEmailAddress()
	{
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress)
	{
		this.emailAddress = emailAddress;
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