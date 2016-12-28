package tests;

import static org.junit.Assert.assertEquals;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import model.Ticket;
public class TicketTest {
	private Ticket ticket;
	private UUID ticketID = UUID.fromString("832d0467-691e-4b04-b774-88d86998b915");
	private UUID routeID = UUID.fromString("8deafb9f-9cdc-4398-8106-7f21a296058a");
	private UUID typeTicketID = UUID.fromString("1b6d42b2-cee0-444f-896e-0f37646edac9");
	private String datum = "2016,5,12";
	private String datumVan = "2016,5,12";
	private String datumTot = "2017,5,12";
	private  long lastUpdated = 1482932255;
	
	@Before
	public void setUp() throws Exception {
		ticket = new Ticket(routeID,typeTicketID, datum,datumVan, datumTot);
		ticket.setRouteID(routeID);
		ticket.setTypeTicketID(typeTicketID);
		ticket.setTicketID(ticketID);
		ticket.setLastUpdated(lastUpdated);
	}
	
	@Test
	public void ConstructorTest(){
		assertEquals(routeID,ticket.getRouteID());
		assertEquals(typeTicketID,ticket.getTypeTicketID());
		assertEquals(datum,ticket.getDate());
		assertEquals(datumVan, ticket.getValidFrom());
		assertEquals(datumTot, ticket.getValidUntil());
	}
	
	@Test
	public void TicketIDTest(){
		ticket.setTicketID(ticketID);
		assertEquals(ticketID,ticket.getTicketID());
	}
	
	@Test
	public void RouteIDTest(){
		ticket.setRouteID(routeID);
		assertEquals(routeID,ticket.getRouteID());
	}
	
	@Test
	public void TypeTicketTest(){
		ticket.setTypeTicketID(typeTicketID);
		assertEquals(typeTicketID,ticket.getTypeTicketID());
	}
	@Test
	public void DatumTest(){
		ticket.setDate(datum);
		assertEquals(datum,ticket.getDate());
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
	public void LastUpdatedTest(){
		ticket.setLastUpdated(1482918942);
		assertEquals(1482918942,ticket.getLastUpdated());
	}
	
	

}
