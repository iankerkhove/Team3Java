package tests;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import model.Reservation;

public class ReservationTest {
	private UUID reservationID = UUID.fromString("066a0147-674e-478f-b1c8-7a21e266c170");
	private int passengerCount = 20;
	private String trainID = "IC3635";
	private String reservationDate = "2016,10,23";
	private double price = 65.2;
	private UUID routeID = UUID.fromString("610deb80-1af3-4e45-a985-729aa7db352d");
	private long lastUpdated = 1482920974;
	private Reservation res;
	
	@Before
	public void setUp() throws Exception
	{
		res = new Reservation(passengerCount,trainID, price, reservationDate, routeID);
		res.setReservationID(reservationID); 
		res.setLastUpdated(lastUpdated);
	}
	
	@Test
	public void ConstrucorTest(){
		assertEquals(passengerCount,res.getPassengerCount());
		assertEquals(trainID,res.getTrainID());
		assertEquals(65.2,res.getPrice(),65.2);
		assertEquals(reservationDate,res.getReservationDate());
		assertEquals(routeID,res.getRouteID());
	}
	
	@Test
	public void ReservationIDTest(){
		res.setReservationID(UUID.fromString("066a0147-674e-478f-b1c8-7a21e266c170"));
		assertEquals(UUID.fromString("066a0147-674e-478f-b1c8-7a21e266c170"),res.getReservationID());
	}
	
	@Test
	public void PassengerCountTest(){
		res.setPassengerCount(62);
		assertEquals(62,res.getPassengerCount());
	}
	
	@Test
	public void TrainIDTest(){
		res.setTrainID("Ps08");
		assertEquals("Ps08",res.getTrainID());
	}
	
	@Test
	public void PriceTest(){
		res.setPrice(62.5);
		assertEquals(62.5,res.getPrice(),62.5);
	}
	
	@Test
	public void RouteIDTest(){
		res.setRouteID(UUID.fromString("610deb80-1af3-4e45-a985-729aa7db352d"));
		assertEquals(UUID.fromString("610deb80-1af3-4e45-a985-729aa7db352d"),res.getRouteID());
	}
	
	@Test
	public void LastUpdatedTest(){
		res.setLastUpdated(1482918942);
		assertEquals(1482918942,res.getLastUpdated());
	}
	
}
