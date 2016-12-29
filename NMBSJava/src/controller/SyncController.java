package controller;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import services.InitDatabaseRunnable;
import services.SyncAddressRunnable;
import services.SyncAllRunnable;
import services.SyncCustomerRunnable;
import services.SyncDiscountRunnable;
import services.SyncLineRunnable;
import services.SyncLostObjectRunnable;
import services.SyncPassRunnable;
import services.SyncRailCardRunnable;
import services.SyncReservationRunnable;
import services.SyncRouteRunnable;
import services.SyncStaffRunnable;
import services.SyncStationRunnable;
import services.SyncSubscriptionRunnable;
import services.SyncTicketRunnable;
import services.SyncTypePassRunnable;
import services.SyncTypeTicketRunnable;

public class SyncController
{
	
	public static void Start()
	{
		final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		
		//scheduler.scheduleWithFixedDelay(new SyncAllRunnable(), 0, 60*60, TimeUnit.SECONDS);
	}
	
	public static void initDatabase()
	{
		Thread t = new Thread(new InitDatabaseRunnable());
		t.run();
	}

	public static void SyncAddress()
	{
		Thread t = new Thread(new SyncAddressRunnable());
		t.run();
	}
	
	public static void SyncStation()
	{
		Thread t = new Thread(new SyncStationRunnable());
		t.run();
	}
	
	public static void SyncRoute()
	{
		Thread t = new Thread(new SyncRouteRunnable());
		t.run();
	}
	
	public static void SyncDiscount()
	{
		Thread t = new Thread(new SyncDiscountRunnable());
		t.run();
	}
	
	public static void SyncSubscription()
	{
		Thread t = new Thread(new SyncSubscriptionRunnable());
		t.run();
	}
	
	public static void SyncRailCard()
	{
		Thread t = new Thread(new SyncRailCardRunnable());
		t.run();
	}
	
	public static void SyncTypeTicekt()
	{
		Thread t = new Thread(new SyncTypeTicketRunnable());
		t.run();
	}
	
	public static void SyncTicket()
	{
		Thread t = new Thread(new SyncTicketRunnable());
		t.run();
	}
	
	public static void SyncTypePass()
	{
		Thread t = new Thread(new SyncTypePassRunnable());
		t.run();
	}
	
	public static void SyncPass()
	{
		Thread t = new Thread(new SyncPassRunnable());
		t.run();
	}
	
	public static void SyncLine()
	{
		Thread t = new Thread(new SyncLineRunnable());
		t.run();
	}
	
	public static void SyncLostObject()
	{
		Thread t = new Thread(new SyncLostObjectRunnable());
		t.run();
	}
	
	public static void SyncReservation()
	{
		Thread t = new Thread(new SyncReservationRunnable());
		t.run();
	}
	
	public static void SyncCustomer()
	{
		Thread t = new Thread(new SyncCustomerRunnable());
		t.run();
	}
	
	public static void SyncStaff()
	{
		Thread t = new Thread(new SyncStaffRunnable());
		t.run();
	}
}
