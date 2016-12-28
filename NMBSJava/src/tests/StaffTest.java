package tests;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import model.Address;
import model.Staff;
import model.Station;

public class StaffTest{
	private UUID staffID = UUID.fromString("8d137eee-a4a3-45ab-8582-7405f3fd0dcb") ;
	private UUID stationID = UUID.fromString("45a8f7c8-25cf-4fac-8d80-57dc6491c7ac");
	private Station station = new Station();
	private String userName;
	private String password;
	private int rights;
	private String apiToken = "$2y$10$/olWC6/ld3COCS7Kp3AXrOFFZBfz3mxAfH/Keoj5Cw/...";
	private Staff staf;
	private long lastUpdated;
	private Address address;
	private String firstName,lastName,birthDate,email;
	
	@Before
	public void setUp() throws Exception
	{
		staf = new Staff(address,station, firstName, lastName, userName,  password, rights, birthDate,email);
		staf.setStaffID(staffID);
		staf.setLastUpdated(lastUpdated);
		station.setStationName("Geraardsbergen");
		station.setStationID(stationID);
	}
	
	@Test
	public void ConstructorTest(){
		assertEquals(address,staf.getAddress());
		assertEquals(station,staf.getStation());
		assertEquals(firstName,staf.getFirstName());
		assertEquals(lastName,staf.getLastName());
		assertEquals(password,staf.getPassword());
		assertEquals(rights,staf.getRights());
		assertEquals(birthDate,staf.getBirthDate());
		assertEquals(email,staf.getEmail());
	}
	
	@Test
	public void StaffIDTest(){
		staf.setStaffID(UUID.fromString("8d137eee-a4a3-45ab-8582-7405f3fd0dcb"));
		assertEquals(UUID.fromString("8d137eee-a4a3-45ab-8582-7405f3fd0dcb"),staf.getStaffID());
	}
	
	@Test
	public void StationTest(){
		staf.setStation(station);
		assertEquals(station,staf.getStation());
	}
	
	@Test
	public void StationIDTest(){
		staf.setStationID(stationID);
		assertEquals(stationID,staf.getStationID());
	}
	
	@Test
	public void UserNameTest(){
		staf.setUserName("Jonas");
		assertEquals("Jonas",staf.getUserName());
	}
	
	@Test
	public void PasswordTest(){
		staf.setPassword("1234");
		assertEquals("1234",staf.getPassword());
	}
	
	@Test
	public void RightsTest(){
		staf.setRights(1);
		assertEquals(1,staf.getRights());
	}
	
	@Test
	public void ApiTokenTest(){
		staf.setApiToken("$2y$10$/olWC6/ld3COCS7Kp3AXrOFFZBfz3mxAfH/Keoj5Cw/...");
		assertEquals(apiToken,staf.getApiToken());
	}
	
	@Test
	public void LastUpdatedTest(){
		staf.setLastUpdated(1482918942);
		assertEquals(1482918942,staf.getLastUpdated());
	}
}
