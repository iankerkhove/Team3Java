package tests;

import static org.junit.Assert.assertEquals;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import model.LostObject;

public class LostObjectTest
{
	private String datum = "2016,12,05";
	private UUID stationID;
	private LostObject lobj;
	private String trainID = "3635";
	private boolean found=true;
	private long lastUpdated = 1482918942;
	@Before
	public void setUp() throws Exception
	{
		lobj = new LostObject(stationID, "blauwe pull", datum, trainID,found);
		 lobj.setLastUpdated(lastUpdated);
	}

	@Test
	public void ConstructorTest()
	{
		assertEquals(stationID, lobj.getStationID());
		assertEquals("blauwe pull", lobj.getDescription());
		assertEquals(datum, lobj.getDate());
		assertEquals("3635", lobj.getTrainID());
		assertEquals(true,lobj.getFound());
		assertEquals(lastUpdated,lobj.getLastUpdated());
	}

	@Test
	public void ObjectIDTest()
	{
		lobj.setObjectID(UUID.fromString("79cbbd0d-7ea2-4366-a5a8-2ad2ff2f95a8"));
		assertEquals(UUID.fromString("79cbbd0d-7ea2-4366-a5a8-2ad2ff2f95a8"), lobj.getObjectID());
	}

	@Test
	public void StationTest()
	{
		lobj.setStationID(stationID);
		assertEquals(stationID, lobj.getStationID());
	}

	@Test
	public void DescriptionTest()
	{
		lobj.setDescription("grijze pull");
		assertEquals("grijze pull", lobj.getDescription());
	}

	@Test
	public void DateTest()
	{
		lobj.setDate(datum);
		assertEquals(datum, lobj.getDate());
	}

	@Test
	public void TrainIDTest()
	{
		lobj.setTrainID("3635");
		assertEquals("3635", lobj.getTrainID());
	}

	@Test
	public void LastUpdatedTest(){
		lobj.setLastUpdated(1482918942);
		assertEquals(1482918942,lobj.getLastUpdated());
	}
	@Test
	public void ToStringTest()
	{
		// assertEquals("LostObject [objectID=1, station=null,
		// description=grijze pull, date=2016,12,05,
		// trainID=3635]",lobj.toString());

	}

	// api test
}
