package tests;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import model.TypePass;

public class TypePassTest {
	private UUID typePassID = UUID.fromString("066a0147-674e-478f-b1c8-7a21e266c170");
	private String name = "kaart";
	private double price = 12;
	private long lastUpdated = 1482933437;
	private TypePass tp;
	
	@Before
	public void setUp() throws Exception
	{
		tp = new TypePass(name,price);
		tp.setTypePassID(typePassID);
		tp.setLastUpdated(lastUpdated);
	}
	
	@Test
	public void ConstructorTest(){
		assertEquals(name,tp.getName());
		assertEquals(price,tp.getPrice(),12);
	}
	
	@Test
	public void TypePasIDTest(){
		tp.setTypePassID(typePassID);
		assertEquals(typePassID,tp.getTypePassID());
	}
	
	@Test
	public void NameTest(){
		tp.setName("abo");
		assertEquals("abo",tp.getName());
	}
	
	@Test
	public void PriceTest(){
		tp.setPrice(price);
		assertEquals(price,tp.getPrice(),12);
	}
	
	@Test
	public void LastUpdatedTest(){
		tp.setLastUpdated(1482918942);
		assertEquals(1482918942,tp.getLastUpdated());
	}
	
}

