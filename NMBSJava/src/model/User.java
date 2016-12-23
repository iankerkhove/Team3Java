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
	private long lastUpdated;
	
	public User(String firstName, String lastName, Date birthDate, String emailAddress, Address address,
			String username, String password, int rights) {
		super(firstName, lastName, birthDate, emailAddress, address);
		this.username = username;
		this.password = password;
		this.rights = rights;
		users = new ArrayList<User>();
		userID = UUID.randomUUID();
		this.lastUpdated = Instant.now().getEpochSecond();
	}

	public UUID getUserID()
	{
		return userID;
	}

	public void setUserID(UUID userID)
	{
		this.userID = userID;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public int getRights()
	{
		return rights;
	}

	public void setRights(int rights)
	{
		this.rights = rights;
	}

	public ArrayList<User> getUsers()
	{
		return users;
	}

	public void setUsers(ArrayList<User> users)
	{
		this.users = users;
	}

	public long getLastUpdated()
	{
		return lastUpdated;
	}

	public void setLastUpdated(long lastUpdated)
	{
		this.lastUpdated = lastUpdated;
	}

	public void update() {
		this.lastUpdated = Instant.now().getEpochSecond();
	}

	public void addUser(User u) {
		users.add(u);
	}
}
