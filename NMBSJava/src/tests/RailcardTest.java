package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import model.RailCard;
public class RailcardTest {
	private RailCard railcard;
	private ArrayList subs;
	@Before
	public void setUp() throws Exception {
		railcard = new RailCard();
		subs = new ArrayList();
	}
	
	@Test
	public void RailcardIDTest() {
		//railcard.setRailCardID(3);
		assertEquals(3,railcard.getRailCardID());
	}
	
	@Test
	public void SubscripionTest(){
		railcard.setSubscriptions(subs);
		assertEquals(subs,railcard.getSubscriptions());
	}
	
	@Test
	public void ArrayTest(){
		subs.add(railcard);
		assertEquals(1, subs.size());
		assertEquals(railcard,subs.get(0));
	}

}
