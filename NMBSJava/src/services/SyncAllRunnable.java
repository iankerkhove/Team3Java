package services;

import java.util.Queue;
import java.util.concurrent.LinkedTransferQueue;

import dao.AddressDAO;
import dao.CustomerDAO;
import dao.DiscountDAO;
import dao.LineDAO;
import dao.LostObjectDAO;
import dao.PassDAO;
import dao.RailCardDAO;
import dao.ReservationDAO;
import dao.RouteDAO;
import dao.StaffDAO;
import dao.StationDAO;
import dao.SubscriptionDAO;
import dao.TicketDAO;
import dao.TypePassDAO;
import dao.TypeTicketDAO;

public class SyncAllRunnable implements Runnable
{

	private final Object _lock = new Object();
    private Queue<Runnable> workQueue;

    public SyncAllRunnable()
    {
    	workQueue = new LinkedTransferQueue<Runnable>();
        
    	workQueue.add(new SyncAddressRunnable());
    	workQueue.add(new SyncStationRunnable());

    	workQueue.add(new SyncRouteRunnable());
    	workQueue.add(new SyncDiscountRunnable());
    	workQueue.add(new SyncSubscriptionRunnable());
    	workQueue.add(new SyncRailCardRunnable());
    	
    	workQueue.add(new SyncTypeTicketRunnable());
    	workQueue.add(new SyncTicketRunnable());

    	workQueue.add(new SyncTypePassRunnable());
    	workQueue.add(new SyncPassRunnable());
    	
    	workQueue.add(new SyncLineRunnable());
    	workQueue.add(new SyncLostObjectRunnable());

    	workQueue.add(new SyncReservationRunnable());
    	    	
    	workQueue.add(new SyncCustomerRunnable());
    	workQueue.add(new SyncStaffRunnable());
        
    }
    
	@Override
	public void run()
	{
		System.out.println("synced");
		synchronized(_lock)
        {
			while(true)
            {
                Runnable r = workQueue.poll();
                
                if (r == null)
                	return;
                
                r.run();
                
            }
        }
	}

}
