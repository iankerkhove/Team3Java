package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import model.Route;
import model.Station;

public class RouteDAO extends BaseDAO
{

	public RouteDAO()
	{}

	public int insert(Route r)
	{
		PreparedStatement ps = null;

		String sql = "INSERT INTO Route VALUES(?,?,?,?)";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			ps.setString(1, r.getRouteID().toString());
			ps.setString(2, r.getDepartureStationID().toString());
			ps.setString(3, r.getArrivalStationID().toString());
			ps.setLong(4, r.getLastUpdated());


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


	public ArrayList<Route> selectAllSync()
	{
		ArrayList<Route> list = null;

		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM Route";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			rs = ps.executeQuery();
			list = new ArrayList<Route>();

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


	public ArrayList<Route> selectAll()
	{
		ArrayList<Route> list = null;

		PreparedStatement ps = null;
		ResultSet rs = null;


		String sql = "SELECT "
				+ "r.RouteID, s1.StationID as DepartureStationID, s1.Name as DepartureName, "
				+ "s1.CoX as DepartureCoX, s1.CoY as DepartureCoY, s1.LastUpdated as DepartureLastUpdated,"
				+ "s2.StationID as ArrivalStationID, s2.Name as ArrivalName, "
				+ "s2.CoX as ArrivalCoX, s2.CoY as ArrivalCoY, s2.LastUpdated as ArrivalLastUpdated, r.LastUpdated as RouteLastUpdated"
				+ "FROM Route r"
				+ "INNER JOIN Station s1 on s1.StationID = r.DepartureStationID "
				+ "INNER JOIN Station s2 on s2.StationID = r.ArrivalStationID;";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			rs = ps.executeQuery();
			list = new ArrayList<Route>();

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

	public Route selectOne(String routeID)
	{
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT "
				+ "r.RouteID, s1.StationID as DepartureStationID, s1.Name as DepartureName, "
				+ "s1.CoX as DepartureCoX, s1.CoY as DepartureCoY, s1.LastUpdated as DepartureLastUpdated,"
				+ "s2.StationID as ArrivalStationID, s2.Name as ArrivalName, "
				+ "s2.CoX as ArrivalCoX, s2.CoY as ArrivalCoY, s2.LastUpdated as ArrivalLastUpdated, r.LastUpdated as RouteLastUpdated"
				+ "FROM Route r"
				+ "INNER JOIN Station s1 on s1.StationID = r.DepartureStationID "
				+ "INNER JOIN Station s2 on s2.StationID = r.ArrivalStationID"
				+ "WHERE r.RouteID=?;";
		
		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			ps.setString(1, routeID);
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


	public static Route resultToModel(ResultSet rs) throws SQLException
	{
		Route r = new Route();
		Station s = new Station();
		Station s2 = new Station();


		s.setStationID(UUID.fromString(rs.getString("DepartureStationID")));
		s.setStationName(rs.getString("DepartureName"));
		s.setCox(rs.getString("DepartureCoX"));
		s.setCoy(rs.getString("DepartureCoY"));
		s.setLastUpdated(rs.getLong("DepartureLastUpdated"));

		s2.setStationID(UUID.fromString(rs.getString("ArrivalStationID")));
		s2.setStationName(rs.getString("ArrivalName"));
		s2.setCox(rs.getString("ArrivalCoX"));
		s2.setCoy(rs.getString("ArrivalCoY"));
		s2.setLastUpdated(rs.getLong("ArrivalStationLastUpdated"));

		r.setRouteID(UUID.fromString(rs.getString("RouteID")));
		r.setDepartureStation(s);
		r.setArrivalStation(s2);
		r.setLastUpdated(rs.getLong("RouteLastUpdated"));

		return r;
	}

	public static void createTable(Connection con)
	{
		PreparedStatement ps = null;

		String sql = "CREATE TABLE IF NOT EXISTS `Route` (" 
				+ "`RouteID` varchar(36) NOT NULL DEFAULT '0',"
				+ "`DepartureStationID` varchar(36) NOT NULL DEFAULT '0',"
				+ "`ArrivalStationID` varchar(36) NOT NULL DEFAULT '0'," 
				+ "`LastUpdated` bigint(14) DEFAULT NULL,"
				+ "UNIQUE(`DepartureStationID`,`ArrivalStationID`),"
				+ "PRIMARY KEY (`RouteID`),"
				+ "FOREIGN KEY (`DepartureStationID`) REFERENCES `Station`(`StationID`),"
				+ "FOREIGN KEY (`ArrivalStationID`) REFERENCES `Station`(`StationID`)"
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

