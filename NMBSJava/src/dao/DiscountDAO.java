package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.UUID;

import model.Customer;
import model.Discount;


public class DiscountDAO extends BaseDAO
{

	public DiscountDAO()
	{

	}
	
	public int insertOrUpdate(Discount d)
	{
		Discount exists = this.selectOne(d.getDiscountID().toString());

		if (exists == null)
			return this.insert(d);
		else
			return this.update(d);
	}
	
	public int insert(Discount d)
	{
		PreparedStatement ps = null;

		String sql = "INSERT INTO Discount VALUES(?,?,?,?)";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			ps.setString(1, d.getDiscountID().toString());
			ps.setString(2, d.getName());
			ps.setDouble(3, d.getAmount());
			ps.setLong(4, d.getLastUpdated());

			// api call

			return ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				if (ps != null)
					ps.close();

			} catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new RuntimeException("error.unexpected");
			}
		}
	}
	public int update(Discount d)
	{
		PreparedStatement ps = null;

		String sql = "UPDATE `Discount` SET `DiscountID`=?,`Name`=?,"
				+ "`Amount`=?,`LastUpdated`=? WHERE DiscountID=?;";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);
			
			
			ps.setString(1, d.getDiscountID().toString());
			
			ps.setString(2, d.getName());
			ps.setDouble(3, d.getAmount());
			ps.setLong(4, d.getLastUpdated());
			ps.setString(5, d.getDiscountID().toString());

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
	
	public ArrayList<Discount> selectAll()
	{
		ArrayList<Discount> list = null;

		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM Discount";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			rs = ps.executeQuery();
			list = new ArrayList<Discount>();

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

		String sql = "SELECT COUNT(DISTINCT DiscountID) as Count, MAX(LastUpdated) as LastUpdated FROM Discount";

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

	public Discount selectOne(String discountID)
	{
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM Discount WHERE DiscountID=?";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			ps.setString(1, discountID);
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


	private Discount resultToModel(ResultSet rs) throws SQLException
	{
		Discount d = new Discount();
		d.setDiscountID(UUID.fromString(rs.getString("DiscountID")));
		d.setName(rs.getString("Name"));
		d.setAmount(rs.getDouble("Amount"));
		d.setLastUpdated(rs.getInt("LastUpdated"));

		return d;
	}


	public static void createTable(Connection con)
	{
		PreparedStatement ps = null;

		String sql = "CREATE TABLE IF NOT EXISTS `Discount` (" 
				+ "`DiscountID` varchar(36) NOT NULL DEFAULT '0',"
				+ "`Name` varchar(20) NOT NULL," 
				+ "`Amount` double NOT NULL,"
				+ "`LastUpdated` bigint(14) DEFAULT NULL," 
				+ "PRIMARY KEY (`DiscountID`)"
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
