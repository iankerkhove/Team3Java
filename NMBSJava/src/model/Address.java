package model;

import java.time.Instant;
import java.util.UUID;

public class Address {
	
private UUID addressID;
private String street;
private int number;
private String city;
private int zipCode;
private String coordinates;
private long unixTimestamp;

public Address(String street, int number, String city, int zipCode, String coordinates ) {
	this.street = street;
	this.number = number;
	this.city = city;
	this.zipCode = zipCode;
	this.coordinates = coordinates;
	this.addressID = UUID.randomUUID();
	unixTimestamp = Instant.now().getEpochSecond();
}
public Address() {
	
}
public void setAddressID(UUID addressID) {
	this.addressID = addressID;
}
public void setUnixTimestamp(long unixTimestamp) {
	this.unixTimestamp = unixTimestamp;
}
public long getUnixTimestamp() {
	return unixTimestamp;
}

public void update() {
	this.unixTimestamp = Instant.now().getEpochSecond();
}
public void setLastUpdated(long unixTimestamp){
	this.unixTimestamp = unixTimestamp;
}
public UUID getAddressID() {
	return addressID;
}

public String getStreet() {
	return street;
}

public void setStreet(String street) {
	this.street = street;
}

public int getNumber() {
	return number;
}

public void setNumber(int number) {
	this.number = number;
}

public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}

public int getZipCode() {
	return zipCode;
}

public void setZipCode(int zipCode) {
	this.zipCode = zipCode;
}

public String getCoordinates() {
	return coordinates;
}

public void setCoordinates(String coordinates) {
	this.coordinates = coordinates;
}

@Override
public String toString() {
	return "Address [addressID=" + addressID + ", street=" + street + ", number=" + number + ", city=" + city
			+ ", zipCode=" + zipCode + ", coordinates=" + coordinates + "]";
}


}
