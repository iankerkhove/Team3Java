
//package tests;
//
//import static org.junit.Assert.*;
//
//import java.util.UUID;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import model.Address;
//import model.Station;
//
//public class StationTest {
//	private Station station;
//	private Address adres;
//	private UUID stationID = UUID.randomUUID();
//	@Before
//	public void setUp() throws Exception {
//		adres = new Address("Nijverheidskaai", 170, "Brussel", 1000, "50.8410136 - 4.322051299999998");
//		station = new Station(adres,"Brussel-Zuid");
//	}
//	
//	@Test
//	public void ConstructorTest() {
//		assertEquals(adres,station.getAddress());
//		assertEquals("Brussel-Zuid",station.getStationName());
//	}
//	
//	@Test
//	public void AdresTest(){
//		station.setAddress(adres);
//		assertEquals(adres,station.getAddress());
//	}
//	
//	@Test
//	public void StationNameTest(){
//		station.setStationName("Brussel-Centraal");
//		assertEquals("Brussel-Centraal",station.getStationName());
//	}
//	
//	@Test
//	public void StationIDTest(){
//		station.setStationID(stationID);
//		assertEquals(stationID,station.getStationID());
//	}
//}
