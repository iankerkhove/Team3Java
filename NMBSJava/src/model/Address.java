package model;

import java.time.Instant;
import java.util.UUID;

public class Address
{

	private UUID addressID;
	private String street;
	private int number;
	private String city;
	private int zipCode;
	private String coordinates;
	private long lastUpdated;

	public Address()
	{}

	public Address(String street, int number, String city, int zipCode, String coordinates)
	{
		this.street = street;
		this.number = number;
		this.city = city;
		this.zipCode = zipCode;
		this.coordinates = coordinates;
		this.addressID = UUID.randomUUID();
		this.lastUpdated = Instant.now().getEpochSecond();
	}

	public UUID getAddressID()
	{
		return addressID;
	}

	public void setAddressID(UUID addressID)
	{
		this.addressID = addressID;
	}

	public String getStreet()
	{
		return street;
	}

	public void setStreet(String street)
	{
		this.street = street;
	}

	public int getNumber()
	{
		return number;
	}

	public void setNumber(int number)
	{
		this.number = number;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public int getZipCode()
	{
		return zipCode;
	}

	public void setZipCode(int zipCode)
	{
		this.zipCode = zipCode;
	}

	public String getCoordinates()
	{
		return coordinates;
	}

	public void setCoordinates(String coordinates)
	{
		this.coordinates = coordinates;
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

	@Override
	public String toString()
	{
		return "Address [addressID=" + addressID + ", street=" + street + ", number=" + number + ", city=" + city
				+ ", zipCode=" + zipCode + ", coordinates=" + coordinates + "]";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addressID == null) ? 0 : addressID.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((coordinates == null) ? 0 : coordinates.hashCode());
		result = prime * result + number;
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		result = prime * result + zipCode;
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (addressID == null) {
			if (other.addressID != null)
				return false;
		}
		else if (!addressID.equals(other.addressID))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		}
		else if (!city.equals(other.city))
			return false;
		if (coordinates == null) {
			if (other.coordinates != null)
				return false;
		}
		else if (!coordinates.equals(other.coordinates))
			return false;
		if (number != other.number)
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		}
		else if (!street.equals(other.street))
			return false;
		if (zipCode != other.zipCode)
			return false;
		return true;
	}
	
}
