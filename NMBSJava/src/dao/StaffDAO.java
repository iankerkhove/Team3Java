package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import model.Address;
import model.Staff;

public class StaffDAO extends BaseDAO {

	public StaffDAO() {
		// TODO Auto-generated constructor stub
	}

	public int insert(Staff s) {
		PreparedStatement ps = null;

		String sql = "INSERT INTO Staff VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			ps.setString(1, s.getStaffID().toString());
			ps.setString(2, s.getAddressID().toString());
			ps.setString(3, s.getStationID().toString());
			ps.setString(4, s.getFirstName());
			ps.setString(5, s.getLastName());
			ps.setString(6, s.getUserName());
			ps.setString(7, s.getPassword());
			ps.setInt(8, s.getRights());
			ps.setString(9, s.getBirthDate().toString());
			ps.setString(10, s.getEmail());
			ps.setString(11, s.getApiToken());
			ps.setLong(12, s.getUnixTimestamp());

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

	public ArrayList<Staff> selectAllSync() {
		ArrayList<Staff> list = null;

		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM Staff";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			rs = ps.executeQuery();
			list = new ArrayList<Staff>();

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

	public ArrayList<Staff> selectAll() {
		ArrayList<Staff> list = null;

		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT s.StaffID, s.AddressID, a.Street, a.Number, a.City,"
				+ "a.ZipCode, a.Coordinates, a.LastUpdated as AddressLastUpdated, s.StationID,"
				+ "st.Name, st.CoX, st.CoY, st.LastUpdated as StationLastUpdated"
				+ "s.FirstName, s.LastName, s.UserName, s.Password, s.Rights, s.BirthDate,"
				+ "s.Email, s.Api_token, s.LastUpdated as StaffLastUpdated FROM Staff s"
				+ "INNER JOIN Address a ON a.AddressID = s.AddressID"
				+ "INNER JOIN Station st ON st.StationID = s.StationID;";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			rs = ps.executeQuery();
			list = new ArrayList<Staff>();

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

	public Staff selectOne(String staffID) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT s.StaffID, s.AddressID, a.Street, a.Number, a.City,"
				+ "a.ZipCode, a.Coordinates, a.LastUpdated as AddressLastUpdated, s.StationID,"
				+ "st.Name, st.CoX, st.CoY, st.LastUpdated as StationLastUpdated"
				+ "s.FirstName, s.LastName, s.UserName, s.Password, s.Rights, s.BirthDate,"
				+ "s.Email, s.Api_token, s.LastUpdated as StaffLastUpdated FROM Staff s"
				+ "INNER JOIN Address a ON a.AddressID = s.AddressID"
				+ "INNER JOIN Station st ON st.StationID = s.StationID" + "WHERE StaffID = ?;";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			ps.setString(1, staffID);
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

	private Staff resultToModel(ResultSet rs) throws SQLException {
		Staff s = new Staff();
		Address a = new Address();

		a.setAddressID(UUID.fromString(rs.getString("AddressID")));
		a.setStreet(rs.getString("Street"));
		a.setNumber(rs.getInt("Number"));
		a.setCity(rs.getString("City"));
		a.setZipCode(rs.getInt("ZipCode"));
		a.setCoordinates(rs.getString("Coordinates"));
		a.setLastUpdated(rs.getLong("AddressLastUpdated"));

		s.setStaffID(UUID.fromString(rs.getString("StaffID")));
		s.setAddressID(a.getAddressID());
		s.setAddress(a);
		s.setStationID(UUID.fromString(rs.getString("StationID")));
		s.setFirstName(rs.getString("FirstName"));
		s.setLastName(rs.getString("LastName"));
		s.setUserName(rs.getString("UserName"));
		s.setPassword(rs.getString("Password"));
		s.setRights(rs.getInt("Rights"));
		s.setBirthDate(rs.getString("BirthDate"));
		s.setEmail(rs.getString("Email"));
		s.setApiToken(rs.getString("Api_token"));
		s.setLastUpdated(rs.getLong("StaffLastUpdated"));

		return s;
	}

	public static void createTable() {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "CREATE TABLE IF NOT EXISTS `Staff` (  `StaffID` varchar(36) "
				+ "NOT NULL DEFAULT '0',  `AddressID` varchar(36) NOT NULL DEFAULT '0',  "
				+ "`StationID` varchar(36) NOT NULL DEFAULT '0', "
				+ " `FirstName` varchar(20) NOT NULL,  `LastName` varchar(20) NOT NULL,  "
				+ "`UserName` varchar(512) NOT NULL,  `Password` varchar(512) NOT NULL,  "
				+ "`Rights` int(1) NOT NULL,  `BirthDate` int(11) NOT NULL,  "
				+ "`Email` varchar(50) NOT NULL,  `Api_token` varchar(60) DEFAULT NULL,  "
				+ "`LastUpdated` bigint(14) DEFAULT NULL,  PRIMARY KEY (`StaffID`),  "
				+ "UNIQUE KEY `UserName` (`UserName`), UNIQUE KEY `api_token` (`Api_token`),  "
				+ "KEY `AddressID` (`AddressID`),  KEY `StationID` (`StationID`)) "
				+ "ENGINE=InnoDB DEFAULT CHARSET=latin1;";

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