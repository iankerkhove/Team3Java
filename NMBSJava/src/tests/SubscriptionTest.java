package tests;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import model.Subscription;




public class SubscriptionTest {
	private Subscription sub;
	private Subscription sub2;
	private UUID id = UUID.randomUUID();
	private UUID id2= UUID.randomUUID();
	private UUID id3= UUID.randomUUID();
	private Date datumvan = new Date(2017,03,02);
	private Date datumtot = new Date(2018,03,02);
	@Before
	public void setUp() throws Exception {
		sub = new Subscription(id, id2, id3, datumvan, datumtot);
		sub2 = new Subscription(id, id2, datumvan, datumtot);
	}
	
	@Test
	public void ConstructorTest1(){
		assertEquals(1,sub.getRailID());
		assertEquals(12,sub.getRouteID());
		assertEquals(1,sub.getDiscountID());
		assertEquals(datumvan,sub.getValidFrom());
		assertEquals(datumtot,sub.getValidUntil());
	}
	
	@Test
	public void ConstructorTest2(){
		assertEquals(1,sub2.getRailID());
		assertEquals(2,sub2.getRouteID());
		assertEquals(datumvan,sub2.getValidFrom());
		assertEquals(datumtot,sub2.getValidUntil());
	}
	
	@Test
	public void subIDtest(){
		//sub.setSubscriptionID(10);
		assertEquals(10,sub.getSubscriptionID());
	}
	
	@Test
	public void RailIDTest(){
		//sub.setRailID(2);
		assertEquals(2,sub.getRailID());
	}
	
	@Test
	public void RouteIDTest(){
		//sub.setRouteID(2);
		assertEquals(2,sub.getRouteID());
	}
	
	@Test
	public void DiscountTest(){
		sub.setDiscountID(id3);
		assertEquals(id3,sub.getDiscountID());
	}
	
	@Test
	public void ValidFromTest(){
		sub.setValidFrom(datumvan);
		assertEquals(datumvan,sub.getValidFrom());
	}
	
	@Test
	public void ValidUntilTest(){
		sub.setValidUntil(datumtot);
		assertEquals(datumtot,sub.getValidUntil());
	}
}

