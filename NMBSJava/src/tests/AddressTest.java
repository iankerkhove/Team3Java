package tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import controller.URLCon;
import model.Address;

public class AddressTest
{
	private Address adres;
	JSONObject temp;

	@Before
	public void setUp() throws Exception
	{
		adres = new Address("Nijverheidskaai", 170, "Brussel", 1000, "50.8410136 - 4.322051299999998");

	}

	@Test
	public void constructorTest()
	{
		assertEquals("Nijverheidskaai", adres.getStreet());
		assertEquals(170, adres.getNumber());
		assertEquals("Brussel", adres.getCity());
		assertEquals(1000, adres.getZipCode());
		assertEquals("50.8410136 - 4.322051299999998", adres.getCoordinates());

	}

	@Test
	public void AddressIDTest()
	{
		// adres.setAddressID(3);
		assertEquals(3, adres.getAddressID());
	}

	@Test
	public void StreetTest()
	{
		adres.setStreet("Nijverheidskaai");
		assertEquals("Nijverheidskaai", adres.getStreet());
	}

	@Test
	public void NumberTest()
	{
		adres.setNumber(170);
		assertEquals(170, adres.getNumber());
	}

	@Test
	public void CityTest()
	{
		adres.setCity("Brussel");
		assertEquals("Brussel", adres.getCity());
	}

	@Test
	public void ZipTest()
	{
		adres.setZipCode(1000);
		assertEquals(1000, adres.getZipCode());
	}

	@Test
	public void CoordinatesTest()
	{
		adres.setCoordinates("50.8410136 - 4.322051299999998");
		assertEquals("50.8410136 - 4.322051299999998", adres.getCoordinates());
	}

	@Test
	public void ToStringTest()
	{
		// adres.setAddressID(3);
		assertEquals(
				"Address [addressID=3, street=Nijverheidskaai, number=170, city=Brussel, zipCode=1000, coordinates=50.8410136 - 4.322051299999998]",
				adres.toString());
	}

	@Test
	public void GetAPItest()
	{
		// adres.setAddressID(3);
		try {
			temp = new JSONObject(URLCon.readUrl("http://nmbs-team.tk/api/address/3", "GET"));
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(3, temp.getInt("AddressID"));
	}
}
