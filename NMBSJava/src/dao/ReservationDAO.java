package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.UUID;

import controller.APIController.RequestType;
import model.Reservation;
import model.Route;

public class ReservationDAO extends BaseDAO
{
	public final static String BASE_URL = "reservation/";

	public ReservationDAO()
	{}
	
	public int insertOrUpdate(Reservation r)
	{
		Reservation exists = this.selectOne(r.getReservationID().toString());

		if (exists == null)
			return this.insert(r);
		else
			return this.update(r);
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
			ps.setString(3, r.getTrainID());
			ps.setDouble(4, r.getPrice());
			ps.setString(5, r.getReservationDate());
			ps.setString(6, r.getRouteID().toString());
			ps.setLong(7, r.getLastUpdated());

			if (!isSyncFunction)
			{
				params = new HashMap<String, String>();
				params.put("reservationID", r.getReservationID().toString());
				params.put("passengerCount", Integer.toString(r.getPassengerCount()));
				params.put("trainID", r.getTrainID());
				params.put("price", Double.toString(r.getPrice()));
				params.put("reservationDate", r.getReservationDate());
				params.put("routeID", r.getRouteID().toString());
				params.put("lastUpdated", Long.toString(r.getLastUpdated()));
			}

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
				if (!isSyncFunction)
					syncMainDB(BASE_URL + "create", RequestType.POST, params);

			}
			catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new RuntimeException("error.unexpected");
			}
		}

	}
	public int update(Reservation r)
	{
		PreparedStatement ps = null;

		String sql = "UPDATE `Reservation` SET `PassengerCount`=?,"
				+ "`TrainID`=?,`Price`=?,`ReservationDate`=?,"
				+ "`RouteID`=?,`LastUpdated`=? WHERE ReservationID = ?;";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);
			
			ps.setInt(1, r.getPassengerCount());
			ps.setString(2, r.getTrainID());
			ps.setDouble(3, r.getPrice());
			ps.setString(4, r.getReservationDate());
			ps.setString(5,r.getRouteID().toString());
			ps.setLong(6, r.getLastUpdated());
			ps.setString(7, r.getReservationID().toString());

			if (!isSyncFunction)
			{
				params = new HashMap<String, String>();
				params.put("reservationID", r.getReservationID().toString());
				params.put("passengerCount", Integer.toString(r.getPassengerCount()));
				params.put("trainID", r.getTrainID());
				params.put("price", Double.toString(r.getPrice()));
				params.put("reservationDate", r.getReservationDate());
				params.put("routeID", r.getRouteID().toString());
				params.put("lastUpdated", Long.toString(r.getLastUpdated()));
			}

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
				if (!isSyncFunction)
					syncMainDB(BASE_URL + "update/" + params.get("reservationID"), RequestType.PUT, params);

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

		String sql ="SELECT re.ReservationID, re.PassengerCount, re.TrainID, re.Price, re.ReservationDate, re.LastUpdated as ReservationLastUpdated "
				+ "FROM Reservation re;";

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

		String sql = "SELECT r.RouteID, r.DepartureStationID as DepartStation, r.ArrivalStationID as ArrivalStation,"
				+ " s.Name, s.CoX,s.CoY, s.LastUpdated as StationLastUpdated, "
				+ "r.LastUpdated as RouteLastUpdated, re.ReservationID, re.PassengerCount, re.TrainID, "
				+ "re.Price, re.ReservationDate, re.LastUpdated as ReservationLastUpdated FROM Reservation re "
				+ "INNER JOIN Route r ON r.RouteID = re.RouteID "
				+ "INNER JOIN Station s ON s.StationID = r.DepartureStationID;";

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
	public TreeMap<String, String> updateStatus()
	{
		TreeMap<String, String> map = null;

		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT COUNT(DISTINCT ReservationID) as Count, MAX(LastUpdated) as LastUpdated FROM Reservation";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			rs = ps.executeQuery();
			map = new TreeMap<String, String>();

			while (rs.next()) {
				map.put("Count", rs.getString("Count"));
				map.put("LastUpdated", rs.getString("LastUpdated"));
			}

			return map;
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

		String sql = "SELECT r.RouteID, r.DepartureStationID as DepartStation, r.ArrivalStationID as ArrivalStation,"
				+ " s.Name, s.CoX,s.CoY, s.LastUpdated as StationLastUpdated, "
				+ "r.LastUpdated as RouteLastUpdated, re.ReservationID, re.PassengerCount, re.TrainID, "
				+ "re.Price, re.ReservationDate, re.LastUpdated as ReservationLastUpdated FROM Reservation re "
				+ "INNER JOIN Route r ON r.RouteID = re.RouteID "
				+ "INNER JOIN Station s ON s.StationID = r.DepartureStationID " 
				+ "WHERE re.ReservationID = ?;";

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
		re.setReservationDate(rs.getString("ReservationDate"));
		re.setRoute(r);
		re.setLastUpdated(rs.getLong("ReservationLastUpdated"));

		return re;
	}

	public static void createTable(Connection con)
	{
		PreparedStatement ps = null;

		String sql = "CREATE TABLE IF NOT EXISTS `Reservation` (  "
				+ "`ReservationID` varchar(36) NOT NULL DEFAULT '0', " 
				+ "`PassengerCount` int(11) NOT NULL, "
				+ "`TrainID` varchar(36) NOT NULL DEFAULT '0', "
				+ "`Price` double NOT NULL, "
				+ "`ReservationDate` varchar(40) NOT NULL, "
				+ "`RouteID` varchar(36) NOT NULL DEFAULT '0', "
				+ "`LastUpdated` bigint(14) NOT NULL, "
				+ "PRIMARY KEY (`ReservationID`), "
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