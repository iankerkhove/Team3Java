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
import model.Route;
import model.Ticket;
import model.TypeTicket;

public class TicketDAO extends BaseDAO
{
	public final static String BASE_URL = "ticket/";

	public TicketDAO()
	{}
	
	public int insertOrUpdate(Ticket a) {
		Ticket exists = this.selectOne(a.getTicketID().toString());

		if (exists == null)
			return this.insert(a);
		else
			return this.update(a);
	}

	public int insert(Ticket t)
	{
		PreparedStatement ps = null;

		String sql = "INSERT INTO Ticket VALUES(?,?,?,?,?,?,?)";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			ps.setString(1, t.getTicketID().toString());
			ps.setString(2, t.getRouteID().toString());
			ps.setString(3, t.getTypeTicketID().toString());
			ps.setString(4, t.getDate().toString());
			ps.setString(5, t.getValidFrom().toString());
			ps.setString(6, t.getValidUntil().toString());
			ps.setLong(7, t.getLastUpdated());

			if (!isSyncFunction)
			{
				params = new HashMap<String, String>();
				params.put("ticketID", t.getTicketID().toString());
				params.put("routeID", t.getRouteID().toString());
				params.put("typeTicketID", t.getTypeTicketID().toString());
				params.put("date", t.getDate());
				params.put("validFrom", t.getValidFrom());
				params.put("validUntil", t.getValidUntil());
				params.put("lastUpdated", Long.toString(t.getLastUpdated()));
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
	
	public int update(Ticket t) {
		PreparedStatement ps = null;

		String sql = "UPDATE Ticket SET `RouteID`=?, `TypeTicketID`=?, `Date`=?, `ValidFrom`=?, `ValidUntil`=?, `LastUpdated`=? WHERE TicketID = ?";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			t.update();
			ps.setString(1, t.getRouteID().toString());
			ps.setString(2, t.getTypeTicketID().toString());
			ps.setString(3, t.getDate());
			ps.setString(4, t.getValidFrom());
			ps.setString(5, t.getValidUntil());
			ps.setLong(6, t.getLastUpdated());
			ps.setString(7, t.getTicketID().toString());

			if (!isSyncFunction)
			{
				params = new HashMap<String, String>();
				params.put("ticketID", t.getTicketID().toString());
				params.put("routeID", t.getRouteID().toString());
				params.put("typeTicketID", t.getTypeTicketID().toString());
				params.put("date", t.getDate());
				params.put("validFrom", t.getValidFrom());
				params.put("validUntil", t.getValidUntil());
				params.put("lastUpdated", Long.toString(t.getLastUpdated()));
			}

			return ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (!isSyncFunction)
					syncMainDB(BASE_URL + "update/" + params.get("ticketID"), RequestType.PUT, params);
				
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new RuntimeException("error.unexpected");
			}
		}
	}
	
	public TreeMap<String, String> updateStatus() {
		TreeMap<String, String> map = null;

		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT COUNT(DISTINCT TicketID) as Count, MAX(LastUpdated) as LastUpdated FROM Ticket";

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
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new RuntimeException("error.unexpected");
			}
		}
	}

	public ArrayList<Ticket> selectAllSync()
	{
		ArrayList<Ticket> list = null;

		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM Ticket";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			rs = ps.executeQuery();
			list = new ArrayList<Ticket>();

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

	public ArrayList<Ticket> selectAll()
	{
		ArrayList<Ticket> list = null;

		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT "
                + "r.RouteID, s1.StationID as DepartureStationID, s1.Name as DepartureName, "
				+ "s1.CoX as DepartureCoX, s1.CoY as DepartureCoY, s1.LastUpdated as DepartureLastUpdated, "
				+ "s2.StationID as ArrivalStationID, s2.Name as ArrivalName, "
				+ "s2.CoX as ArrivalCoX, s2.CoY as ArrivalCoY, s2.LastUpdated as ArrivalLastUpdated, r.LastUpdated as RouteLastUpdated, "
				+ "ty.TypeTicketID, ty.Name as TypeTicketName, ty.Price, ty.ComfortClass, ty.LastUpdated, "
				+ "t.Date, t.ValidFrom, t.ValidUntil, t.LastUpdated as TicketLastUpdated " 
                + "FROM Ticket t "
                + "INNER JOIN Route r ON r.RouteID = t.RouteID "
				+ "INNER JOIN TypeTicket ty ON ty.TypeTicketID = t.TypeTicketID "
				+ "INNER JOIN Station s1 on s1.StationID = r.DepartureStationID "
				+ "INNER JOIN Station s2 on s2.StationID = r.ArrivalStationID;";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			rs = ps.executeQuery();
			list = new ArrayList<Ticket>();

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

	public Ticket selectOne(String ticketID)
	{
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT "
                + "r.RouteID, s1.StationID as DepartureStationID, s1.Name as DepartureName, "
				+ "s1.CoX as DepartureCoX, s1.CoY as DepartureCoY, s1.LastUpdated as DepartureLastUpdated, "
				+ "s2.StationID as ArrivalStationID, s2.Name as ArrivalName, "
				+ "s2.CoX as ArrivalCoX, s2.CoY as ArrivalCoY, s2.LastUpdated as ArrivalLastUpdated, r.LastUpdated as RouteLastUpdated, "
				+ "ty.TypeTicketID, ty.Name as TypeTicketName, ty.Price, ty.ComfortClass, ty.LastUpdated, "
				+ "t.Date, t.ValidFrom, t.ValidUntil, t.LastUpdated as TicketLastUpdated " 
                + "FROM Ticket t "
                + "INNER JOIN Route r ON r.RouteID = t.RouteID "
				+ "INNER JOIN TypeTicket ty ON ty.TypeTicketID = t.TypeTicketID "
				+ "INNER JOIN Station s1 on s1.StationID = r.DepartureStationID "
				+ "INNER JOIN Station s2 on s2.StationID = r.ArrivalStationID "
				+ "WHERE t.TicketID=?;";
		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			ps.setString(1, ticketID);
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

	static Ticket resultToModel(ResultSet rs) throws SQLException
	{
		Ticket t = new Ticket();
		TypeTicket ty = TypeTicketDAO.resultToModel(rs);
		Route r = RouteDAO.resultToModel(rs);

		t.setTicketID(UUID.fromString(rs.getString("TicketID")));
		t.setRoute(r);
		t.setTicketID(ty.getTypeTicketID());
		t.setDate(rs.getString("Date"));
		t.setValidFrom(rs.getString("ValidFrom"));
		t.setValidUntil(rs.getString("ValidUntil"));
		t.setLastUpdated(rs.getLong("TicketLastUpdated"));

		return t;
	}

	public static void createTable(Connection con)
	{
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "CREATE TABLE IF NOT EXISTS `Ticket` (" 
				+ "`TicketID` varchar(36) NOT NULL DEFAULT '0',"
				+ "`RouteID` varchar(36) NOT NULL DEFAULT '0'," 
				+ "`TypeTicketID` varchar(36) NOT NULL DEFAULT '0',"
				+ "`Date` varchar(20) NOT NULL," 
				+ "`ValidFrom` varchar(20) NOT NULL,"
				+ "`ValidUntil` varchar(20) NOT NULL," 
				+ "`LastUpdated` bigint(14) DEFAULT NULL,"
				+ "PRIMARY KEY (`TicketID`),"
				+ "FOREIGN KEY (`RouteID`) REFERENCES `Route`(`RouteID`)"
				+ "FOREIGN KEY (`TypeTicketID`) REFERENCES `TypeTicket`(`TypeTicketID`)"
				+ ");";
		;

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
				if (rs != null)
					rs.close();
			}
			catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new RuntimeException("error.unexpected");
			}
		}
	}
}