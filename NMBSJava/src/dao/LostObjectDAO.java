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
import model.LostObject;
import model.Station;

public class LostObjectDAO extends BaseDAO
{
	public final static String BASE_URL = "lostObject/";

	public LostObjectDAO()
	{}
	
	public int insertOrUpdate(LostObject l)
	{
		LostObject exists = this.selectOne(l.getObjectID().toString());

		if (exists == null)
			return this.insert(l);
		else
			return this.update(l);
	}
	
	public int insert(LostObject l)
	{
		PreparedStatement ps = null;

		String sql = "INSERT INTO LostObject VALUES(?,?,?,?,?,?,?)";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			ps.setString(1, l.getObjectID().toString());
			ps.setString(2, l.getStationID().toString());
			ps.setString(3, l.getDescription());
			ps.setString(4, l.getDate());
			ps.setString(5, l.getTrainID());
			ps.setBoolean(6, l.getFound());
			ps.setLong(7, l.getLastUpdated());

			if (!isSyncFunction)
			{
				params = new HashMap<String, String>();
				params.put("objectID", l.getObjectID().toString());
				params.put("stationID", l.getStationID().toString());
				params.put("description", l.getDescription());
				params.put("date", l.getDate());
				params.put("trainID", l.getTrainID());
				params.put("found", (l.getFound())? "1": "0");
				params.put("lastUpdated", Long.toString(l.getLastUpdated()));
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
	
	public int update(LostObject l)
	{
		PreparedStatement ps = null;
		
		String sql = "UPDATE `LostObject` SET `StationID`=?,`Description`=?,"
				+ "`Date`=?,`TrainID`=?,`Found`=?,"
				+ "`LastUpdated`=? WHERE ObjectID = ?";
		
		try {
			
			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			ps.setString(1, l.getStationID().toString());
			ps.setString(2, l.getDescription());
			ps.setString(3, l.getDate().toString());
			ps.setString(4, l.getTrainID());
			ps.setBoolean(5, l.getFound());
			ps.setLong(6, l.getLastUpdated());
			ps.setString(7, l.getObjectID().toString());
			
			if (!isSyncFunction)
			{
				params = new HashMap<String, String>();
				params.put("objectID", l.getObjectID().toString());
				params.put("stationID", l.getStationID().toString());
				params.put("description", l.getDescription());
				params.put("date", l.getDate());
				params.put("trainID", l.getTrainID());
				params.put("found", (l.getFound())? "1": "0");
				params.put("lastUpdated", Long.toString(l.getLastUpdated()));
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
					syncMainDB(BASE_URL + "update/" + params.get("objectID"), RequestType.PUT, params);
				
			}
			catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new RuntimeException("error.unexpected");
			}
		}
		
	}

	public ArrayList<LostObject> selectAllSync()
	{
		ArrayList<LostObject> list = null;

		PreparedStatement ps = null;
		ResultSet rs = null;


		String sql = "SELECT l.ObjectID, l.Description, l.Date, l.TrainID, l.Found, l.LastUpdated as LostObjectLastUpdated " 
				+ " FROM LostObject l ;";
		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			rs = ps.executeQuery();
			list = new ArrayList<LostObject>();

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

	public ArrayList<LostObject> selectAll()
	{
		ArrayList<LostObject> list = null;

		PreparedStatement ps = null;
		ResultSet rs = null;


		String sql = "SELECT l.ObjectID, s.StationID, "
				+ "s.Name, s.CoX,s.CoY," + "s.LastUpdated as StationLastUpdated, "
				+ "l.Description,l.Date,l.TrainID,l.Found,l.LastUpdated as LostObjectLastUpdated " 
				+ " FROM LostObject l "
				+ "INNER JOIN Station s ON s.StationID = l.StationID;";
		
		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			rs = ps.executeQuery();
			list = new ArrayList<LostObject>();

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

		String sql = "SELECT COUNT(DISTINCT ObjectID) as Count, MAX(LastUpdated) as LastUpdated FROM LostObject";

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
	public LostObject selectOne(String objectID)
	{
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT l.ObjectID, s.StationID,"
				+ " s.Name, s.CoX,s.CoY," + "s.LastUpdated as StationLastUpdated, "
				+ "l.Description,l.Date,l.TrainID,l.Found,l.LastUpdated as LostObjectLastUpdated" 
				+ " FROM LostObject l "
				+ "INNER JOIN Station s ON s.StationID = l.StationID" + " WHERE l.ObjectID=?;";
		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			ps.setString(1, objectID);
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

	private LostObject resultToModel(ResultSet rs) throws SQLException
	{
		LostObject l = new LostObject();
		Station s = new Station();

		s.setStationID(UUID.fromString(rs.getString("StationID")));
		s.setStationName(rs.getString("Name"));
		s.setCoX(rs.getString("CoX"));
		s.setCoY(rs.getString("CoY"));
		s.setLastUpdated(rs.getLong("StationLastUpdated"));

		l.setObjectID(UUID.fromString(rs.getString("ObjectID")));
		l.setStationID(s.getStationID());
		l.setDescription(rs.getString("Description"));
		l.setDate(rs.getString("Date"));
		l.setTrainID(rs.getString("TrainID"));
		l.setFound(rs.getBoolean("Found"));
		l.setLastUpdated(rs.getLong("LostObjectLastUpdated"));
		return l;
	}

	public static void createTable(Connection con)
	{
		PreparedStatement ps = null;

		String sql = "CREATE TABLE IF NOT EXISTS `LostObject` (" 
				+ "`ObjectID` varchar(36) NOT NULL DEFAULT '0',"
				+ "`StationID` varchar(36) NOT NULL DEFAULT '0'," 
				+ "`Description` varchar(100) NOT NULL,"
				+ "`Date` varchar(11) DEFAULT NULL," 
				+ "`TrainID` varchar(36) NOT NULL DEFAULT '0',"
				+ "`Found` tinyint(1) DEFAULT '0',"
				+ "`LastUpdated` bigint(14) NOT NULL, " 
				+ "PRIMARY KEY (`ObjectID`), "
				+ "FOREIGN KEY (`StationID`) REFERENCES `Station`(`StationID`)"
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