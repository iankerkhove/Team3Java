package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import model.Address;
import model.Customer;
import model.RailCard;

public class CustomerDAO extends BaseDAO {

	public CustomerDAO() {

	}

	public int insert(Customer c) {
		PreparedStatement ps = null;

		String sql = "INSERT INTO Customer VALUES(?,?,?,?,?,?,?,?)";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			AddressDAO h = new AddressDAO();
			h.insert(c.getAddress());

			ps.setString(1, c.getCustomerID().toString());
			ps.setString(2, c.getRailCard().toString());
			ps.setString(3, c.getAddress().toString());
			ps.setString(4, c.getFirstName());
			ps.setString(5, c.getLastName());
			ps.setString(6, c.getBirthDate().toString());
			ps.setString(7, c.getEmailAddress());
			ps.setLong(8, c.getUnixTimestamp());

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

	public ArrayList<Customer> selectAllSync() {
		ArrayList<Customer> list = null;

		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM Customer";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			rs = ps.executeQuery();
			list = new ArrayList<Customer>();

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

	public ArrayList<Customer> selectAll() {
		ArrayList<Customer> list = null;

		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT c.CustomerID, r.CardID, r.LastUpdated as CardLastUpdated, a.AddressID, a.Street,"
				+ " a.Number, a.City, a.ZipCode, a.Coordinates, a.LastUpdated as AddressLastUpdated, "
				+ "c.FirstName, c.LastName, c.BirthDate, c.Email, c.LastUpdated as CustomerLastUpdated"
				+ " FROM Customer c" + "INNER JOIN Address a ON a.AddressID = c.AddressID"
				+ "INNER JOIN RailCard r ON r.CardID = c.RailCardID;";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			rs = ps.executeQuery();
			list = new ArrayList<Customer>();

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

	public Customer selectOne(String customerID) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT c.CustomerID, r.CardID, r.LastUpdated as CardLastUpdated, a.AddressID, a.Street,"
				+ " a.Number, a.City, a.ZipCode, a.Coordinates, a.LastUpdated as AddressLastUpdated, "
				+ "c.FirstName, c.LastName, c.BirthDate, c.Email, c.LastUpdated as CustomerLastUpdated"
				+ " FROM Customer c" + "INNER JOIN Address a ON a.AddressID = c.AddressID"
				+ "INNER JOIN RailCard r ON r.CardID = c.RailCardID" + "WHERE c.CustomerID = ?;";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			ps.setString(1, customerID);
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

	private Customer resultToModel(ResultSet rs) throws SQLException {
		Address a = new Address();
		Customer c = new Customer();
		RailCard r = new RailCard();

		r.setRailCardID(UUID.fromString(rs.getString("CardID")));
		r.setLastUpdated(rs.getLong("CardLastUpdated"));

		a.setAddressID(UUID.fromString(rs.getString("AddressID")));
		a.setStreet(rs.getString("Street"));
		a.setNumber(rs.getInt("Number"));
		a.setCity(rs.getString("City"));
		a.setZipCode(rs.getInt("ZipCode"));
		a.setCoordinates(rs.getString("Coordinates"));
		a.setLastUpdated(rs.getLong("AddressLastUpdated"));

		c.setCustomerID(UUID.fromString(rs.getString("CustomerID")));
		c.setAddress(a);
		c.setRailCard(r);
		c.setFirstName(rs.getString("FirstName"));
		c.setLastName(rs.getString("LastName"));
		c.setBirthDate(rs.getDate("BirthDate"));
		c.setEmailAddress(rs.getString("Email"));
		c.setLastUpdated(rs.getLong("LastUpdated"));
		return c;
	}

	public static void createTable() {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "CREATE TABLE IF NOT EXISTS `Customer` (" + "`CustomerID` varchar(36) NOT NULL DEFAULT '0',"
				+ "`RailCardID` varchar(36) NOT NULL DEFAULT '0'," + "`AddressID` varchar(36) NOT NULL DEFAULT '0',"
				+ "`FirstName` varchar(20) NOT NULL," + "`LastName` varchar(20) NOT NULL,"
				+ "`BirthDate` varchar(20) NOT NULL," + "`Email` varchar(50) NOT NULL,"
				+ "`LastUpdated` bigint(14) DEFAULT NULL," + "PRIMARY KEY (`CustomerID`),"
				+ "KEY `AddressID` (`AddressID`)," + "KEY `RailCardID` (`RailCardID`)"
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
