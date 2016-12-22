package model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class User extends Person {
	private UUID userID;
	private String username;
	private String password;
	private int rights;
	private ArrayList<User> users = null;
	private long unixTimestamp;
	
	public User(String firstName, String lastName, Date birthDate, String emailAddress, Address address,
			String username, String password, int rights) {
		super(firstName, lastName, birthDate, emailAddress, address);
		this.username = username;
		this.password = password;
		this.rights = rights;
		users = new ArrayList();
		userID = UUID.randomUUID();
		this.unixTimestamp = Instant.now().getEpochSecond();
	}

	public UUID getUserID() {
		return userID;
	}

	/*public void setUserID(int userID) {
		this.userID = userID;
	}*/

	public long getUnixTimestamp() {
		return unixTimestamp;
	}

	public void update() {
		this.unixTimestamp = Instant.now().getEpochSecond();
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
	public void addUser(User u) {
		users.add(u);
	}
}
