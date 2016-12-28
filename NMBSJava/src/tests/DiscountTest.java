package tests;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import model.Discount;

public class DiscountTest
{
	private Discount disc;
	private long lastUpdated = 1482918942;
	@Before
	public void setUp() throws Exception
	{
		disc = new Discount("Standaardbiljet", 1.5);
		disc.setLastUpdated(lastUpdated);
	}

	@Test
	public void ConstructorTest()
	{
		assertEquals("Standaardbiljet", disc.getName());
		assertEquals(1.5, disc.getAmount(), 1.5);
		assertEquals(lastUpdated,disc.getLastUpdated());
	}

	@Test
	public void DiscountIDTest()
	{
		disc.setDiscountID(UUID.fromString("3d783771-7b4c-42ee-8557-beb6e16db331"));
		assertEquals(UUID.fromString("3d783771-7b4c-42ee-8557-beb6e16db331"), disc.getDiscountID());
	}

	@Test
	public void NameTest()
	{
		disc.setName("GoPass1");
		assertEquals("GoPass1", disc.getName());
	}

	@Test
	public void AmountTest()
	{
		disc.setAmount(2);
		assertEquals(2, disc.getAmount(), 2);
	}

	@Test
	public void LastUpdatedTest(){
		disc.setLastUpdated(1482918942);
		assertEquals(1482918942,disc.getLastUpdated());
	}
}
