package model;

import java.util.Date;

public class User extends Person {
	private int userID;
	private String username;
	private String password;
	private int rights;
	
	public User(String firstName, String lastName, Date birthDate, String emailAddress, Address address,
			String username, String password, int rights) {
		super(firstName, lastName, birthDate, emailAddress, address);
		this.username = username;
		this.password = password;
		this.rights = rights;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getRights() {
		return rights;
	}

	public void setRights(int rights) {
		this.rights = rights;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
