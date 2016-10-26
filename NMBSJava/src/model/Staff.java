package model;

public class Staff {
	//datamembers
	private int staffID;
	private int adresID;
	private int stationID;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String /*int?*/ rights;
	
	//getters en setters
	public int getStaffID() {
		return staffID;
	}
	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}
	public int getAdresID() {
		return adresID;
	}
	public void setAdresID(int adresID) {
		this.adresID = adresID;
	}
	public int getStationID() {
		return stationID;
	}
	public void setStationID(int stationID) {
		this.stationID = stationID;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRights() {
		return rights;
	}
	public void setRights(String rights) {
		this.rights = rights;
	}
	
	//constructor
	public Staff(int staffID, int adresID, int stationID, String firstName, String lastName, String userName,
			String password, String rights) {
		super();
		this.staffID = staffID;
		this.adresID = adresID;
		this.stationID = stationID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.rights = rights;
	}
	
	//functies en methoden
}
