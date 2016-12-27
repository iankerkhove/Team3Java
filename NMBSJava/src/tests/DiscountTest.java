package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import model.Discount;
public class DiscountTest {
	private Discount disc;
	
	@Before
	public void setUp() throws Exception {
		disc = new Discount("Standaardbiljet", 1.5);

	}
	
	@Test
	public void ConstructorTest(){
		assertEquals("Standaardbiljet",disc.getName());
		assertEquals(1.5, disc.getAmount(),1.5);
	} 
	
	@Test
	public void DiscountIDTest(){
		//disc.setDiscountID(1);
		assertEquals(1,disc.getDiscountID());
	}
	
	@Test
	public void NameTest(){
		disc.setName("GoPass1");
		assertEquals("GoPass1",disc.getName());
	}
	
	@Test
	public void AmountTest(){
		disc.setAmount(2);
		assertEquals(2,disc.getAmount(),2);
	}
	
}
