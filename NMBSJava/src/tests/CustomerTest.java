package tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import controller.URLCon;
import model.Address;
import model.Customer;
import model.RailCard;
public class CustomerTest {
	private Customer customer;
	private Address adres;
	private RailCard railcard;
	
	JSONObject temp;
	@Before
	public void setUp() throws Exception {
		//c.setTime(datum);
		adres = new Address("Nijverheidskaai", 170, "Brussel", 1000, "50.8410136 - 4.322051299999998");
		customer = new Customer("Jan", "Paternoster", "12101989", "jan.paternoster@bbr.be", adres, railcard);

	}
	
	@Test
	public void ConstructorTest(){
		assertEquals("Jan",customer.getFirstName());
		assertEquals("Paternoster",customer.getLastName());
		assertEquals("12101989",customer.getBirthDate());
		assertEquals("jan.paternoster@bbr.be",customer.getEmail());
		assertEquals(adres,customer.getAddress());
		assertEquals(railcard, customer.getRailCard());
	}
	
	@Test
	public void FirstNameTest(){
		customer.setFirstName("Dries");
		assertEquals("Dries",customer.getFirstName());
	}
	
	@Test
	public void LastNameTest(){
		customer.setLastName("Van Dijck");
		assertEquals("Van Dijck",customer.getLastName());
	}
	
	@Test
	public void BirthTest(){
		customer.setBirthDate("12101989");
		assertEquals("12101989",customer.getBirthDate());
	}
	
	@Test
	public void MailTest(){
		customer.setEmail("dries.van.dijck@bbr.be");
		assertEquals("dries.van.dijck@bbr.be",customer.getEmail());
	}
	
	@Test
	public void AdresTest(){
		customer.setAddress(adres);
		assertEquals(adres,customer.getAddress());
	}
	
	
	@Test
	public void CustomerIDTest(){
		//customer.setCustomerID(1);
		assertEquals(1,customer.getCustomerID());
	}
	
	@Test
	public void RailCardTest(){
		customer.setRailCard(railcard);
		assertEquals(railcard,customer.getRailCard());
	}
	
	@Test
	public void GetAPItest() {
		//customer.setCustomerID(1);
		try {
			temp = new JSONObject(URLCon.readUrl("http://nmbs-team.tk/api/staff/login?username=nmbsteam&password=nmbsteamrocks", "POST"));
			System.out.println(temp);
			temp = new JSONObject(URLCon.readUrl("http://nmbs-team.tk/api/customer/1", "GET"));
			System.out.println(temp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(1, temp.getInt("AddressID"));
	}
}
