package model;

public class Customer {
	//datamembers
	private int cumstomerID;
	private int railCardID;
	private int addressID;
	private String lastName;
	private String firstName;
	private String emailAddress;
	
	//getters en setters
	public int getCumstomerID() {
		return cumstomerID;
	}
	public void setCumstomerID(int cumstomerID) {
		this.cumstomerID = cumstomerID;
	}
	public int getRailCardID() {
		return railCardID;
	}
	public void setRailCardID(int railCardID) {
		this.railCardID = railCardID;
	}
	public int getAddressID() {
		return addressID;
	}
	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	//constructor
	public Customer(int cumstomerID, int railCardID, int addressID, String lastName, String firstName,
			String emailAddress) {
		super();
		this.cumstomerID = cumstomerID;
		this.railCardID = railCardID;
		this.addressID = addressID;
		this.lastName = lastName;
		this.firstName = firstName;
		this.emailAddress = emailAddress;
	}
	public Customer(int cumstomerID, int railCardID, int addressID, String lastName, String firstName) {
		super();
		this.cumstomerID = cumstomerID;
		this.railCardID = railCardID;
		this.addressID = addressID;
		this.lastName = lastName;
		this.firstName = firstName;
	}
	
	//functies en methoden
	
	
}
