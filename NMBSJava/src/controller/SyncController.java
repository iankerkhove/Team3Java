package controller;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import services.SyncAddressRunnable;
import services.SyncAllRunnable;

public class SyncController
{
	
	public static void Start()
	{
		final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		
//		final ScheduledFuture<?> syncHandle = scheduler.scheduleWithFixedDelay(new SyncAllRunnable(), 0, 60*60, TimeUnit.SECONDS);
		scheduler.scheduleWithFixedDelay(new SyncAllRunnable(), 0, 60*60, TimeUnit.SECONDS);
	}
	
	
	
	
	public static void SyncAddress()
	{
		Thread t = new Thread(new SyncAddressRunnable());
	}
	
	
	
}
