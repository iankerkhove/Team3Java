package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import model.Reservation;
import model.Route;

public class ReservationDAO extends BaseDAO
{

	public ReservationDAO()
	{

	}

	public int insert(Reservation r)
	{
		PreparedStatement ps = null;

		String sql = "INSERT INTO Reservation VALUES(?,?,?,?,?,?,?)";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			ps.setString(1, r.getReservationID().toString());
			ps.setInt(2, r.getPassengerCount());
			ps.setString(3, r.getTrainID().toString());
			ps.setDouble(4, r.getPrice());
			ps.setString(5, r.getReservationDate().toString());
			ps.setString(5, r.getRouteID().toString());
			ps.setLong(6, r.getLastUpdated());


			// api call

			return ps.executeUpdate();

		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		finally {
			try {
				if (ps != null)
					ps.close();

			}
			catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new RuntimeException("error.unexpected");
			}
		}

	}

	public ArrayList<Reservation> selectAllSync()
	{
		ArrayList<Reservation> list = null;

		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM Reservation";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			rs = ps.executeQuery();
			list = new ArrayList<Reservation>();

			while (rs.next()) {
				list.add(resultToModel(rs));
			}

			return list;
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			}
			catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new RuntimeException("error.unexpected");
			}
		}

	}

	public ArrayList<Reservation> selectAll()
	{
		ArrayList<Reservation> list = null;

		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT r.RouteID, s.StationID as DepartStation, s.StationID as ArrivalStation, a.AddressID, a.Street,"
				+ " a.Number, a.City, a.ZipCode, a.Coordinates, a.LastUpdated as AddressLastUpdated,"
				+ " s.Name, s.CoX,s.CoY, s.LastUpdated as StationLastUpdated, "
				+ "r.LastUpdated as RouteLastUpdated, re.ReservationID, re.PassengerCount, re.TrainID, "
				+ "re.Price, re.ReservationDate, re.LastUpdated as ReservationLastUpdated FROM Reservation re"
				+ "INNER JOIN Route r ON r.RouteID = re.RouteID"
				+ "INNER JOIN Station s ON s.StationID = r.DepartureStationID"
				+ "INNER JOIN Address a ON a.AddressID = s.AddressID;";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			rs = ps.executeQuery();
			list = new ArrayList<Reservation>();

			while (rs.next()) {
				list.add(resultToModel(rs));
			}

			return list;
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			}
			catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new RuntimeException("error.unexpected");
			}
		}

	}

	public Reservation selectOne(String reservationID)
	{
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT r.RouteID, r.DepartureStationID as DepartStation, r.ArrivalStationID as ArrivalStation, a.AddressID, a.Street,"
				+ " a.Number, a.City, a.ZipCode, a.Coordinates, a.LastUpdated as AddressLastUpdated,"
				+ " s.Name, s.CoX,s.CoY, s.LastUpdated as StationLastUpdated, "
				+ "r.LastUpdated as RouteLastUpdated, re.ReservationID, re.PassengerCount, re.TrainID, re.Price, "
				+ "re.ReservationDate, re.LastUpdated as ReservationLastUpdated FROM Reservation re"
				+ "INNER JOIN Route r ON r.RouteID = re.RouteID"
				+ "INNER JOIN Station s ON s.StationID = r.DepartureStationID"
				+ "INNER JOIN Address a ON a.AddressID = s.AddressID" + "WHERE re.ReservationID = ?;";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			ps.setString(1, reservationID);
			rs = ps.executeQuery();
			if (rs.next())
				return resultToModel(rs);
			else
				return null;
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			}
			catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new RuntimeException("error.unexpected");
			}
		}
	}

	private Reservation resultToModel(ResultSet rs) throws SQLException
	{
		Reservation re = new Reservation();

		Route r = RouteDAO.resultToModel(rs);
		re.setReservationID(UUID.fromString(rs.getString("ReservationID")));
		re.setPassengerCount(rs.getInt("PassengerCount"));
		re.setTrainID(rs.getString("TrainID"));
		re.setPrice(rs.getDouble("Price"));
		re.setReservationDate(rs.getDate("ReservationDate"));
		re.setRoute(r);
		re.setLastUpdated(rs.getLong("ReservationLastUpdated"));

		return re;
	}

	public static void createTable(Connection con)
	{
		PreparedStatement ps = null;

		String sql = "CREATE TABLE IF NOT EXISTS `Reservation` (  "
				+ "`ReservationID` varchar(36) NOT NULL DEFAULT '0', " 
				+ "`PassengerCount` int(11) NOT NULL,  "
				+ "`TrainID` varchar(36) NOT NULL DEFAULT '0',  "
				+ "`Price` double NOT NULL, "
				+ "`RouteID` varchar(36) NOT NULL DEFAULT '0',"
				+ "`LastUpdated` bigint(14) DEFAULT NULL, "
				+ "PRIMARY KEY (`ReservationID`),"
				+ "FOREIGN KEY (`RouteID`) REFERENCES `Route`(`RouteID`)"
				+ ");";


		try {

			if (con.isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		finally {
			try {
				if (ps != null)
					ps.close();
			}
			catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new RuntimeException("error.unexpected");
			}
		}
	}
}