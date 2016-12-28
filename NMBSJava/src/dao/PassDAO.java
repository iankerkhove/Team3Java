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
import model.Pass;
import model.TypePass;

public class PassDAO extends BaseDAO
{
	public final static String BASE_URL = "pass/";

	public PassDAO()
	{}
	
	public int insertOrUpdate(Pass p)
	{
		Pass exists = this.selectOne(p.getPassID().toString());

		if (exists == null)
			return this.insert(p);
		else
			return this.update(p);
	}
	
	public int insert(Pass p)
	{
		PreparedStatement ps = null;

		String sql = "INSERT INTO Pass VALUES(?,?,?,?,?,?)";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			ps.setString(1, p.getPassID().toString());
			ps.setString(2, p.getTypePassID().toString());
			ps.setString(3, p.getDate());
			ps.setString(4, p.getStartDate());
			ps.setInt(5, p.getComfortClass());
			ps.setLong(6, p.getLastUpdated());

			if (!isSyncFunction)
			{
				params = new HashMap<String, String>();
				params.put("passID", p.getPassID().toString());
				params.put("typePassID", p.getTypePassID().toString());
				params.put("date", p.getDate());
				params.put("startDate", p.getStartDate());
				params.put("comfortClass", Integer.toString(p.getComfortClass()));
				params.put("lastUpdated", Long.toString(p.getLastUpdated()));
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
	public int update(Pass p)
	{
		PreparedStatement ps = null;

		String sql = "UPDATE `Pass` SET `PassID`=?,`TypePassID`=?,"
				+ "`Date`=?,`StartDate`=?,`ComfortClass`=?,"
				+ "`LastUpdated`=? WHERE PassID = ?;";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);
			
			
			ps.setString(1, p.getPassID().toString());
			ps.setString(2, p.getTypePassID().toString());
			ps.setString(3, p.getDate());
			ps.setString(4, p.getStartDate().toString());
			ps.setInt(5,p.getComfortClass());
			ps.setLong(6, p.getLastUpdated());
			ps.setString(7, p.getPassID().toString());

			if (!isSyncFunction)
			{
				params = new HashMap<String, String>();
				params.put("passID", p.getPassID().toString());
				params.put("typePassID", p.getTypePassID().toString());
				params.put("date", p.getDate());
				params.put("startDate", p.getStartDate());
				params.put("comfortClass", Integer.toString(p.getComfortClass()));
				params.put("lastUpdated", Long.toString(p.getLastUpdated()));
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
					syncMainDB(BASE_URL + "update/" + params.get("passID"), RequestType.PUT, params);

			}
			catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new RuntimeException("error.unexpected");
			}
		}

	}
	public ArrayList<Pass> selectAllSync()
	{
		ArrayList<Pass> list = null;

		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT p.PassID, t.TypePassID, t.Name, t.Price,t.LastUpdated as TypePassLastUpdated,"
				+ " p.Date,p.StartDate,p.ComfortClass, p.LastUpdated as PassLastUpdated" + " FROM Pass p "
				+ "INNER JOIN TypePass t ON p.TypePassID = t.TypePassID;";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			rs = ps.executeQuery();
			list = new ArrayList<Pass>();

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

		String sql = "SELECT COUNT(DISTINCT PassID) as Count, MAX(LastUpdated) as LastUpdated FROM Pass";

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

	public ArrayList<Pass> selectAll()
	{
		ArrayList<Pass> list = null;

		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT p.PassID, t.TypePassID, t.Name, t.Price,t.LastUpdated as TypePassLastUpdated,"
				+ " p.Date,p.StartDate,p.ComfortClass, p.LastUpdated as PassLastUpdated" + " FROM Pass p "
				+ "INNER JOIN TypePass t ON p.TypePassID = t.TypePassID;";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			rs = ps.executeQuery();
			list = new ArrayList<Pass>();

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

	public Pass selectOne(String passID)
	{
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT p.PassID, t.TypePassID, t.Name, t.Price,t.LastUpdated as TypePassLastUpdated,"
				+ " p.Date,p.StartDate,p.ComfortClass, p.LastUpdated as PassLastUpdated" + " FROM Pass p "
				+ "INNER JOIN TypePass t ON p.TypePassID = t.TypePassID" + " WHERE p.PassID=?;";
		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			ps.setString(1, passID);
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

	static Pass resultToModel(ResultSet rs) throws SQLException
	{
		Pass p = new Pass();
		TypePass t = new TypePass();

		t.setTypePassID(UUID.fromString(rs.getString("TypePassID")));
		t.setName(rs.getString("Name"));
		t.setPrice(rs.getDouble("Price"));
		t.setLastUpdated(rs.getLong("TypePassLastUpdated"));

		p.setPassID(UUID.fromString(rs.getString("PassID")));
		p.setTypePass(t);
		p.setDate(rs.getString("Date"));
		p.setStartDate(rs.getString("StartDate"));
		p.setComfortClass(rs.getInt("ComfortClass"));
		p.setLastUpdated(rs.getLong("PassLastUpdated"));

		return p;
	}

	public static void createTable(Connection con)
	{
		PreparedStatement ps = null;

		String sql = "CREATE TABLE IF NOT EXISTS `Pass` ("
				+ "`PassID` varchar(36) NOT NULL DEFAULT '0',"
				+ "`TypePassID` varchar(36) NOT NULL DEFAULT '0',"
				+ "`Date` varchar(11) NOT NULL,"
				+ "`StartDate` varchar(11) NOT NULL," 
				+ "`ComfortClass` int(11) NOT NULL,"
				+ "`LastUpdated` bigint(14) NOT NULL," 
				+ "PRIMARY KEY (`PassID`) "
				+ "FOREIGN KEY (`TypePassID`) REFERENCES `TypePass`(`TypePassID`)"
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