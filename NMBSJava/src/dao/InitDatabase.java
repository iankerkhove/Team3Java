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
			RailCardDAO.createTable(con);
			CustomerDAO.createTable(con);
			DiscountDAO.createTable(con);
			RouteDAO.createTable(con);
			LineDAO.createTable(con);
			LostObjectDAO.createTable(con);
			TypePassDAO.createTable(con);
			PassDAO.createTable(con);
			ReservationDAO.createTable(con);
			StaffDAO.createTable(con);
			SubscriptionDAO.createTable(con);
			
			//setup constraints
			AddConstraints.add(con);
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
