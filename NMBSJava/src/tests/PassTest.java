package tests;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import model.Pass;
import model.TypePass;

public class PassTest {
	private UUID passID = UUID.fromString("9f2c9a2d-4312-4db8-a91b-073e3695971e");
	private UUID typePassID = UUID.fromString("81a3968d-ac45-435d-9760-61c299fee7e6");
	private String date = "2016,12,28";
	private String startDate = "2016,12,30";
	private int comfortClass = 2;
	private TypePass typePass;
	private long lastUpdated;
	private Pass pas;
	
	@Before
	public void setUp() throws Exception
	{
		pas = new Pass(typePassID, date, startDate,  comfortClass);
		pas.setLastUpdated(lastUpdated);
		pas.setPassID(passID);
	}
	
	@Test
	public void ConstructorTest(){
		assertEquals(typePassID,pas.getTypePassID());
		assertEquals(date,pas.getDate());
		assertEquals(startDate,pas.getStartDate());
		assertEquals(comfortClass, pas.getComfortClass());
	}
	
	@Test
	public void PassIDTest(){
		pas.setPassID(UUID.fromString("9f2c9a2d-4312-4db8-a91b-073e3695971e"));
		assertEquals(UUID.fromString("9f2c9a2d-4312-4db8-a91b-073e3695971e"),pas.getPassID());
	}
	
	@Test
	public void TypePassID(){
		pas.setTypePassID(UUID.fromString("81a3968d-ac45-435d-9760-61c299fee7e6"));
		assertEquals(UUID.fromString("81a3968d-ac45-435d-9760-61c299fee7e6"),pas.getTypePassID());
	}
	
	@Test
	public void DateTest(){
		pas.setDate("2016,12,8");
		assertEquals("2016,12,8",pas.getDate());
	}
	
	@Test
	public void StartDateTest(){
		pas.setStartDate("2016,12,20");
		assertEquals("2016,12,20",pas.getStartDate());
	}
	
	@Test
	public void ComfortClassTest(){
		pas.setComfortClass(1);
		assertEquals(1,pas.getComfortClass());
	}
	
	@Test
	public void TypePassTest(){
		pas.setTypePass(typePass);
		assertEquals(typePass,pas.getTypePass());
	}
	
	@Test
	public void LastUpdatedTest(){
		pas.setLastUpdated(1482918942);
		assertEquals(1482918942,pas.getLastUpdated());
	}
}
