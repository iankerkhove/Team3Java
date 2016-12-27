package tests;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import model.LostObject;
import model.Station;

public class LostObjectTest {
	private Date datum = new Date(2016,12,05);
	private Station station;
	private LostObject lobj;
	private String trainID = "3635";
	@Before
	public void setUp() throws Exception {
		lobj = new LostObject(station, "blauwe pull", datum, trainID);
		
	}
	
	@Test
	public void ConstructorTest(){
		assertEquals(station,lobj.getStation());
		assertEquals("blauwe pull",lobj.getDescription());
		assertEquals(datum,lobj.getDate());
		assertEquals(3635,lobj.getTrainID());
	}
	
	@Test 
	public void ObjectIDTest(){
		//lobj.setObjectID(1);
		assertEquals(1,lobj.getObjectID());
	}
	
	@Test
	public void StationTest(){
		lobj.setStation(station);
		assertEquals(station,lobj.getStation());
	}
	
	@Test
	public void DescriptionTest(){
		lobj.setDescription("grijze pull");
		assertEquals("grijze pull",lobj.getDescription());
	}
	
	@Test
	public void DateTest(){
		lobj.setDate(datum);
		assertEquals(datum,lobj.getDate());
	}
	
	@Test
	public void TrainIDTest(){
		lobj.setTrainID("3635");
		assertEquals(3635,lobj.getTrainID());
	}
	
	@Test
	public void ToStringTest(){
		//assertEquals("LostObject [objectID=1, station=null, description=grijze pull, date=2016,12,05, trainID=3635]",lobj.toString());
		
	}
	
	//api test
}
