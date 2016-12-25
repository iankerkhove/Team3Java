package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.UUID;

import model.Station;

public class StationDAO extends BaseDAO
{

	public StationDAO()
	{}

	public int insertOrUpdate(Station s)
	{
		Station exists = this.selectOne(s.getStationID().toString());

		if (exists == null)
			return this.insert(s);
		else
			return this.update(s);
	}
	
	public int insert(Station s)
	{
		PreparedStatement ps = null;

		String sql = "INSERT INTO Station VALUES(?,?,?,?,?)";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			ps.setString(1, s.getStationID().toString());
			ps.setString(2, s.getStationName());
			ps.setString(3, s.getCoX());
			ps.setString(4, s.getCoY());
			ps.setLong(5, s.getLastUpdated());

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

	public int update(Station s)
	{
		PreparedStatement ps = null;

		String sql = "UPDATE `Station` SET `Name`=?,`CoX`=?,`CoY`=?,`LastUpdated`=? WHERE `StationID`=?";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			ps.setString(1, s.getStationName());
			ps.setString(2, s.getCoX());
			ps.setString(3, s.getCoY());
			ps.setLong(4, s.getLastUpdated());
			ps.setString(5, s.getStationID().toString());

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
	
	public ArrayList<Station> selectAll()
	{
		ArrayList<Station> list = null;

		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT s.StationID, s.Name, s.CoX, s.CoY, s.LastUpdated as StationLastUpdated FROM Station s ORDER BY s.Name";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			rs = ps.executeQuery();
			list = new ArrayList<Station>();

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

		String sql = "SELECT COUNT(DISTINCT StationID) as Count, MAX(LastUpdated) as LastUpdated FROM Station";

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

	public Station selectOne(String stationID)
	{
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT s.StationID, s.Name, s.CoX, s.CoY, s.LastUpdated as StationLastUpdated FROM Station s WHERE s.StationID=?";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			ps.setString(1, stationID);
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
	
	public Station selectOneOnName(String stationName)
	{
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT s.StationID, s.Name, s.CoX, s.CoY, s.LastUpdated as StationLastUpdated FROM Station s WHERE s.Name=?";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			ps.setString(1, stationName);
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

	public static Station resultToModel(ResultSet rs) throws SQLException
	{
		Station s = new Station();
		
		s.setStationID(UUID.fromString(rs.getString("StationID")));
		s.setStationName(rs.getString("Name"));
		s.setCoX(rs.getString("CoX"));
		s.setCoY(rs.getString("CoY"));
		s.setLastUpdated(rs.getLong("StationLastUpdated"));
		
		return s;
	}

	public static void createTable(Connection con)
	{
		PreparedStatement ps = null;

		String sql = "CREATE TABLE IF NOT EXISTS `Station` ("
				+ "`StationID` VARCHAR(36) NOT NULL DEFAULT 0,"
				+ "`Name` varchar(100) DEFAULT NULL UNIQUE,"
				+ "`CoX` varchar(30) DEFAULT NULL,"
				+ "`CoY` varchar(30) DEFAULT NULL,"
				+ "`LastUpdated` bigint(14) DEFAULT NULL,"
				+ "PRIMARY KEY (`StationID`)"
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