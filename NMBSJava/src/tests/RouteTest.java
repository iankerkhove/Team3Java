package tests;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import model.Route;
import model.Station;

public class RouteTest {
	private UUID routeID = UUID.fromString("6f7dad92-ecc1-4f68-8a60-62d0a0ae0dfc");
	private UUID departureStationID = UUID.fromString("6f7dad92-ecc1-4f68-8a60-62d0a0ae0dfc");
	private UUID arrivalStationID = UUID.fromString("1e9c7f87-f9bb-4108-be0d-f42a2bac81e9");
	private Station departureStation;
	private Station arrivalStation;
	private long lastUpdated = 1482922383;
	private Route route;
	
	@Before
	public void setUp() throws Exception
	{
		route = new Route(departureStationID, arrivalStationID);
		route.setRouteID(routeID);
		route.setLastUpdated(lastUpdated);
		departureStation = new Station();
		arrivalStation = new Station();
	}
	
	@Test
	public void ConstructorTest(){
		assertEquals(routeID,route.getRouteID());
		assertEquals(departureStationID,route.getDepartureStationID());
		assertEquals(arrivalStationID,route.getArrivalStationID());
	}
	
	@Test
	public void RouteIDTest(){
		route.setRouteID(UUID.fromString("6f7dad92-ecc1-4f68-8a60-62d0a0ae0dfc"));
		assertEquals(UUID.fromString("6f7dad92-ecc1-4f68-8a60-62d0a0ae0dfc"),route.getRouteID());
	}
	
	@Test
	public void DepartureStationIDTest(){
		route.setDepartureStationID(departureStationID);
		assertEquals(departureStationID,route.getDepartureStationID());
	}
	
	@Test
	public void ArrivalStationIDTest(){
		route.setArrivalStationID(arrivalStationID);
		assertEquals(arrivalStationID,route.getArrivalStationID());
	}
	
	@Test
	public void DepartureStationTest(){
		route.setDepartureStation(departureStation);
		assertEquals(departureStation,route.getDepartureStation());
	}
	
	@Test
	public void ArrivalStationTest(){
		route.setArrivalStation(arrivalStation);
		assertEquals(arrivalStation,route.getArrivalStation());
	}
	@Test
	public void LastUpdatedTest(){
		route.setLastUpdated(1482918942);
		assertEquals(1482918942,route.getLastUpdated());
	}
}
