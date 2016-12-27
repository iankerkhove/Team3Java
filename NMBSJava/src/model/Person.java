package model;

import java.util.Date;

public class Person {
	private int perID;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private String emailAddress;
	private Address address;
	
	public Person(String firstName, String lastName, Date birthDate, String emailAddress, Address address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.emailAddress = emailAddress;
		this.setAddress(address);
	}

	public int getPerID() {
		return perID;
	}
	
	public void setPerID(int perID) {
		this.perID = perID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}