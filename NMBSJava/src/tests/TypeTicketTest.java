package tests;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import model.TypeTicket;

public class TypeTicketTest {
	private UUID typeTicketID = UUID.fromString("066a0147-674e-478f-b1c8-7a21e266c170");
	private String name = "standaard";
	private double price = 5;
	private int comfortClass = 2;
	private long lastUpdated;
	private TypeTicket tt;

	@Before
	public void setUp() throws Exception
	{
		tt = new TypeTicket(name,price,comfortClass);
		tt.setTypeTicketID(typeTicketID);
		tt.setLastUpdated(lastUpdated);
	}
	
	@Test
	public void ConstructorTest(){
		assertEquals(name,tt.getName());
		assertEquals(price,tt.getPrice(),price);
		assertEquals(comfortClass,tt.getComfortClass());
	}
	
	@Test
	public void TypeTicketIDTest(){
		tt.setTypeTicketID(typeTicketID);
		assertEquals(typeTicketID,tt.getTypeTicketID());
	}
	
	@Test
	public void NameTest(){
		tt.setName("standard");
		assertEquals("standard",tt.getName());
	}
	
	@Test
	public void PriceTest(){
		tt.setPrice(9.5);
		assertEquals(9.5,tt.getPrice(),9.5);
	}
	
	@Test
	public void ComfortClassTest(){
		tt.setComfortClass(1);
		assertEquals(1,tt.getComfortClass());
	}
	
	@Test
	public void LastUpdatedTest(){
		tt.setLastUpdated(1482918942);
		assertEquals(1482918942,tt.getLastUpdated());
	}
}
