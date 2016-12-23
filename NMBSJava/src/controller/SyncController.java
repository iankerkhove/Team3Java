package controller;

import services.SyncAddressRunnable;
import services.SyncAllRunnable;

public class SyncController
{
	public static void SyncAll()
	{
		Thread t = new Thread(new SyncAllRunnable());
	}
	
	public static void SyncAddress()
	{
		Thread t = new Thread(new SyncAddressRunnable());
	}

}
