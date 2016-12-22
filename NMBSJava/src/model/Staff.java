package model;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class Staff {
	private UUID staffID;
	private UUID addressID;
	private Address address;
	private UUID stationID;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private int rights;
	private String birthDate;
	private String email;
	private String apiToken;
	private long unixTimestamp;

	public Staff(UUID addressID, UUID stationID, String firstName, String lastName, String userName, String password,
			int rights, String birthDate, String email) {
		super();
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
		this.unixTimestamp = Instant.now().getEpochSecond();
	}

	public Staff() {
	}

	public UUID getStaffID() {
		return staffID;
	}

	public void setStaffID(UUID staffID) {
		this.staffID = staffID;
	}

	public UUID getAddressID() {
		return addressID;
	}

	public void setAddressID(UUID addressID) {
		this.addressID = addressID;
	}

	public UUID getStationID() {
		return stationID;
	}

	public void setStationID(UUID stationID) {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRights() {
		return rights;
	}

	public void setRights(int rights) {
		this.rights = rights;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getApiToken() {
		return apiToken;
	}

	public void setApiToken(String apiToken) {
		this.apiToken = apiToken;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public void setLastUpdated(long unixTimestamp) {
		this.unixTimestamp = unixTimestamp;
	}

	public void setAddress(Address a) {
		this.address = a;
	}

	public Address getAddress() {
		return address;
	}
}