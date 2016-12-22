package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import model.Discount;

public class DiscountDAO extends BaseDAO {

	public DiscountDAO() {

	}

	public int insert(Discount d) {
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
			ps.setLong(4, d.getUnixTimestamp());

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

	public ArrayList<Discount> selectAll() {
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

	public Discount selectOne(String discountID) {
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

	private Discount resultToModel(ResultSet rs) throws SQLException {
		Discount d = new Discount();
		d.setDiscountID(UUID.fromString(rs.getString("DiscountID")));
		d.setName(rs.getString("Name"));
		d.setAmount(rs.getDouble("Amount"));
		d.setLastUpdated(rs.getInt("LastUpdated"));

		return d;
	}

	public static void createTable() {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "CREATE TABLE IF NOT EXISTS `Discount` (" + "`DiscountID` varchar(36) NOT NULL DEFAULT '0',"
				+ "`Name` varchar(20) NOT NULL," + "`Amount` double NOT NULL,"
				+ "`LastUpdated` bigint(14) DEFAULT NULL," + "PRIMARY KEY (`DiscountID`)"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
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

}
