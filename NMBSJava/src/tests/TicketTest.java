package tests;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import model.Ticket;
import model.Ticket.ComfortClass;
public class TicketTest {
	private Ticket ticket;
	private UUID id;
	private Date datum = new Date(2017,03,02);
	private Date datumVan = new Date(2017,03,02);
	private Date datumTot = new Date(2017,04,02);
	
	@Before
	public void setUp() throws Exception {
		ticket = new Ticket(id, datum, 11.5,datumVan, datumTot, ComfortClass.Second);
	}
	
	@Test
	public void ConstructorTest(){
		assertEquals(1,ticket.getRouteID());
		assertEquals(datum,ticket.getDate());
		assertEquals(11.5,ticket.getPrice(),11.5);
		assertEquals(datumVan, ticket.getValidFrom());
		assertEquals(datumTot, ticket.getValidUntil());
		assertEquals(ComfortClass.Second, ticket.getComfortClass());
	}
	
	@Test
	public void TicketIDTest(){
		//ticket.setTicketID(1);
		assertEquals(1,ticket.getTicketID());
	}
	
	@Test
	public void RouteIDTest(){
		//ticket.setRouteID(2);
		assertEquals(2,ticket.getRouteID());
	}
	
	@Test
	public void DatumTest(){
		ticket.setDate(datum);
		assertEquals(datum,ticket.getDate());
	}
	
	@Test
	public void PrijsTest(){
		ticket.setPrice(12);
		assertEquals(12,ticket.getPrice(),12);
	}
	
	@Test
	public void ValidFromTest(){
		ticket.setValidFrom(datumVan);
		assertEquals(datumVan, ticket.getValidFrom());
	}
	
	@Test
	public void ValidUntilTest(){
		ticket.setValidUntil(datumTot);
		assertEquals(datumTot,ticket.getValidUntil());
	}
	
	@Test
	public void ComfortTest(){
		ticket.setComfortClass(ComfortClass.First);
		assertEquals(ComfortClass.First,ticket.getComfortClass());
	}
	
	

}
