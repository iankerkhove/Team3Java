package tests;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import model.Address;
import model.Person;

public class PersonTest {
	private Date datum = new Date(1977,03,02);
	private Address adres;
	private Person persoon;
	@Before
	public void setUp() throws Exception {
		adres = new Address("Nijverheidskaai", 170, "Brussel", 1000, "50.8410136 - 4.322051299999998");
		persoon = new Person("Chris", "Martin", datum, "chris.martin@coldplay.uk", adres);

	}

	@Test
	public void ConstructorTest(){
		assertEquals("Chris",persoon.getFirstName());
		assertEquals("Martin",persoon.getLastName());
		assertEquals(datum,persoon.getBirthDate());
		assertEquals("chris.martin@coldplay.uk",persoon.getEmailAddress());
		assertEquals(adres,persoon.getAddress());
	}

	@Test
	public void PerIDTest(){
		persoon.setPerID(1);
		assertEquals(1,persoon.getPerID());
	}
	
	@Test
	public void FirstNameTest(){
		persoon.setFirstName("Chris");
		assertEquals("Chris",persoon.getFirstName());
	}
	
	@Test
	public void LastNameTest(){
		persoon.setLastName("Martin");
		assertEquals("Martin",persoon.getLastName());
	}
	
	@Test
	public void BirthDateTest(){
		persoon.setBirthDate(datum);
		assertEquals(datum,persoon.getBirthDate());
	}
	
	@Test
	public void EmailTest(){
		persoon.setEmailAddress("chris.martin@coldplay.uk");
		assertEquals("chris.martin@coldplay.uk",persoon.getEmailAddress());
	}
	
	@Test
	public void AddressTest(){
		persoon.setAddress(adres);
		assertEquals(adres,persoon.getAddress());
	}
	
	//api test
}

