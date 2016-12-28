package tests;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import model.Address;
import model.Person;

public class PersonTest {
	private String firstName = "jonas";
	private String lastName = "df";
	private String birthDate = "3/3/1997";
	private String email ="email@email.com" ;
	private Address address;
	private UUID addressID = UUID.fromString("29046c16-ee31-4103-af24-d168dfb808e4");
	private long lastUpdated = 1482934355;
	private Person p;
	
	@Before
	public void setUp() throws Exception
	{
		p = new Person( firstName, lastName, birthDate, email, address);
		p.setLastUpdated(lastUpdated);
		address.setAddressID(addressID);
	}
	
	@Test
	public void ConstructorTest(){
		assertEquals(firstName,p.getFirstName());
		assertEquals(lastName,p.getLastName());
		assertEquals(birthDate,p.getBirthDate());
		assertEquals(email,p.getEmail());
		assertEquals(address, p.getAddress());
	}
	
	@Test
	public void FirstNameTest(){
		p.setFirstName(firstName);
		assertEquals(firstName,p.getFirstName());
	}
	
	@Test
	public void LastNameTest(){
		p.setLastName(lastName);
		assertEquals(lastName,p.getLastName());
	}
	
	@Test
	public void BirthDateTest(){
		p.setBirthDate("1997,02,5");
		assertEquals("1997,02,5",p.getBirthDate());
	}
	
	@Test
	public void MailTest(){
		p.setEmail(email);
		assertEquals(email,p.getEmail());
	}
	
	@Test
	public void AddressIDTest(){
		p.setAddressID(addressID);
		assertEquals(addressID,p.getAddressID());
	}
	
	@Test
	public void AddresTest(){
		p.setAddress(address);
		assertEquals(address,p.getAddress());
	}
	
	@Test
	public void LastUpdatedTest(){
		p.setLastUpdated(1482918942);
		assertEquals(1482918942,p.getLastUpdated());
	}
}
