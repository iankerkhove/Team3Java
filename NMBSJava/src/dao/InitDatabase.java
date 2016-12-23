package dao;

import java.sql.Connection;
import java.sql.SQLException;
public class InitDatabase {

	public static void init()
	{
		Connection con;
		try {
			con = DatabaseSingleton.getDatabaseSingleton().getConnection(true);
		
			//setup tables
			AddressDAO.createTable(con);
			StationDAO.createTable(con);
			
			RouteDAO.createTable(con);
			DiscountDAO.createTable(con);
			SubscriptionDAO.createTable(con);
			RailCardDAO.createTable(con);
			
			TypeTicketDAO.createTable(con);
			TicketDAO.createTable(con);
			
			TypePassDAO.createTable(con);
			PassDAO.createTable(con);
			
			LineDAO.createTable(con);
			LostObjectDAO.createTable(con);
			
			ReservationDAO.createTable(con);
			
			CustomerDAO.createTable(con);
			StaffDAO.createTable(con);
						
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
