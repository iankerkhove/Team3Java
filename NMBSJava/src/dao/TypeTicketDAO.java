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
import model.TypeTicket;

public class TypeTicketDAO extends BaseDAO
{
	public final static String BASE_URL = "typeTicket/";

	public TypeTicketDAO()
	{}
	
	public int insertOrUpdate(TypeTicket tt)
	{
		TypeTicket exists = this.selectOne(tt.getTypeTicketID().toString());

		if (exists == null)
			return this.insert(tt);
		else
			return this.update(tt);
	}

	public int insert(TypeTicket t)
	{
		PreparedStatement ps = null;

		String sql = "INSERT INTO TypeTicket VALUES(?,?,?,?,?)";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			ps.setString(1, t.getTypeTicketID().toString());
			ps.setString(2, t.getName());
			ps.setDouble(3, t.getPrice());
			ps.setInt(4, t.getComfortClass());
			ps.setLong(5, t.getLastUpdated());

			if (!isSyncFunction)
			{
				params = new HashMap<String, String>();
				params.put("typeTicketID", t.getTypeTicketID().toString());
				params.put("name", t.getName());
				params.put("price", Double.toString(t.getPrice()));
				params.put("comfortClass", Integer.toString(t.getComfortClass()));
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
	
	public int update(TypeTicket t)
	{
		PreparedStatement ps = null;

		String sql = "UPDATE TypeTicket SET `Name`=?, `Price`=?, `ComfortClass`=?, `LastUpdated`=? WHERE TypeTicketID=?";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			t.update();
			ps.setString(1, t.getName());
			ps.setDouble(2, t.getPrice());
			ps.setInt(3, t.getComfortClass());
			ps.setLong(4, t.getLastUpdated());
			ps.setString(5, t.getTypeTicketID().toString());

			if (!isSyncFunction)
			{
				params = new HashMap<String, String>();
				params.put("typeTicketID", t.getTypeTicketID().toString());
				params.put("name", t.getName());
				params.put("price", Double.toString(t.getPrice()));
				params.put("comfortClass", Integer.toString(t.getComfortClass()));
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
					syncMainDB(BASE_URL + "update/" + params.get("typeTicketID"), RequestType.PUT, params);
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

		String sql = "SELECT COUNT(DISTINCT TypeTicketID) as Count, MAX(LastUpdated) as LastUpdated FROM TypeTicket";

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

	public ArrayList<TypeTicket> selectAllSync()
	{
		ArrayList<TypeTicket> list = null;

		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT t.TypeTicketID, t.Name as TypeTicketName, t.Price, t.ComfortClass, t.LastUpdated as TypeTicketLastUpdated";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			rs = ps.executeQuery();
			list = new ArrayList<TypeTicket>();

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

	public ArrayList<TypeTicket> selectAll()
	{
		ArrayList<TypeTicket> list = null;

		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT t.TypeTicketID, t.Name as TypeTicketName, t.Price, t.ComfortClass, t.LastUpdated as TypeTicketLastUpdated FROM TypeTicket t ORDER BY t.Name;";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			rs = ps.executeQuery();
			list = new ArrayList<TypeTicket>();

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

	public TypeTicket selectOne(String typePassID)
	{
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT t.TypeTicketID, t.Name, t.Price, t.ComfortClass, "
				+ "t.LastUpdated as TypeTikcetLastUpdated FROM TypeTicket t WHERE TypeTicketID = ?;";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			ps.setString(1, typePassID);
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
	
	public TypeTicket selectOneOnName(String typeTicketName, int comfortClass)
	{
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT t.TypeTicketID, t.Name, t.Price, t.ComfortClass, "
				+ "t.LastUpdated as TypeTikcetLastUpdated FROM TypeTicket t WHERE Name = ? AND ComfortClass = ?;";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			ps.setString(1, typeTicketName);
			ps.setInt(2, comfortClass);
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

	public static TypeTicket resultToModel(ResultSet rs) throws SQLException
	{
		TypeTicket t = new TypeTicket();

		t.setTypeTicketID(UUID.fromString(rs.getString("TypeTicketID")));
		t.setName(rs.getString("TypeTicketName"));
		t.setPrice(rs.getDouble("Price"));
		t.setComfortClass(rs.getInt("ComfortClass"));
		t.setLastUpdated(rs.getLong("TypeTicketLastUpdated"));

		return t;
	}

	public static void createTable(Connection con)
	{
		PreparedStatement ps = null;

		String sql = "CREATE TABLE IF NOT EXISTS `TypeTicket` ("
				+ "`TypeTicketID` varchar(36) NOT NULL DEFAULT '0',"
				+ "`Name` varchar(50) NOT NULL,"
				+ "`Price` double NOT NULL,"
				+ "`ComfortClass` int(11) NOT NULL,  "
				+ "`LastUpdated` bigint(14) NOT NULL,  "
				+ "PRIMARY KEY (`TypeTicketID`)"
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