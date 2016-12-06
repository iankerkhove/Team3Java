package tests;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import model.Subscription;




public class SubscriptionTest {
	private Subscription sub;
	private Date datumvan = new Date(2017,03,02);
	private Date datumtot = new Date(2018,03,02);
	@Before
	public void setUp() throws Exception {
		sub = new Subscription(1, 12, 1, datumvan, datumtot);

	}
	
	@Test
	public void ConstructorTest(){
		assertEquals(1,sub.getRailID());
		assertEquals(12,sub.getRouteID());
		assertEquals(1,sub.getDiscountID());
		assertEquals(datumvan,sub.getValidFrom());
		assertEquals(datumtot,sub.getValidUntil());
	}
	
	@Test
	public void subIDtest(){
		sub.setSubscriptionID(10);
		assertEquals(10,sub.getSubscriptionID());
	}
	
	@Test
	public void RailIDTest(){
		sub.setRailID(2);
		assertEquals(2,sub.getRailID());
	}
	
	@Test
	public void RouteIDTest(){
		sub.setRouteID(2);
		assertEquals(2,sub.getRouteID());
	}
	
	@Test
	public void DiscountTest(){
		sub.setDiscountID(2);
		assertEquals(2,sub.getDiscountID());
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

