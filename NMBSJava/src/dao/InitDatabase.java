package dao;

public class InitDatabase {
	public InitDatabase() {
		AddressDAO.createTable();
		CustomerDAO.createTable();
		DiscountDAO.createTable();
		LineDAO.createTable();
		LostObjectDAO.createTable();
		PassDAO.createTable();
		RailCardDAO.createTable();
		ReservationDAO.createTable();
		RouteDAO.createTable();
		StaffDAO.createTable();
		StationDAO.createTable();
		SubscriptionDAO.createTable();
		TicketDAO.createTable();
		TypePassDAO.createTable();
		TypeTicketDAO.createTable();
	}
}
