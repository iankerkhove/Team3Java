package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import model.Address;
import model.User;

public class UserTest {
	private User user;
	private Date datum = new Date(2017,03,02);
	private Address adres;
	private ArrayList users;
	@Before
	public void setUp() throws Exception {
		adres = new Address("Nijverheidskaai", 170, "Brussel", 1000, "50.8410136 - 4.322051299999998");
		user = new User("Jonas", "De Frère",datum , "jonas.de.frere@gmail.com", adres,"jonasdf", "jonaskn", 0);
		users = new ArrayList ();
	}
	@Test
	public void constructorTest() {
		assertEquals("Jonas",user.getFirstName());
		assertEquals("De Frère", user.getLastName());
		assertEquals(datum, user.getBirthDate());
		assertEquals("jonas.de.frere@gmail.com", user.getEmailAddress());
		assertEquals(adres,user.getAddress());
		assertEquals("jonasdf",user.getUsername());
		assertEquals("jonaskn",user.getPassword());
		assertEquals(0,user.getRights());
	}
	
	@Test
	public void UserIDTest(){
		user.setUserID(1);
		assertEquals(1,user.getUserID());
	}
	
	@Test
	public void UserNameTest(){
		user.setUsername("ian");
		assertEquals("ian",user.getUsername());
	}
	
	@Test
	public void RightsTest(){
		user.setRights(1);
		assertEquals(1,user.getRights());
	}
	
	@Test
	public void PasswordTest(){
		user.setPassword("jonas");
		assertEquals("jonas",user.getPassword());
	}
	
	@Test
	public void UserArrayTest(){
		users.add(user);
		assertEquals(1,users.size());
		assertEquals(user,users.get(0));
	}
}
