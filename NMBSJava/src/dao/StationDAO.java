package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import model.Station;

public class StationDAO extends BaseDAO {

	public StationDAO() {

	}

	public int insert(Station s) {
		PreparedStatement ps = null;

		String sql = "INSERT INTO station VALUES(?,?,?,?,?)";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			ps.setString(1, s.getStationID().toString());
			ps.setString(2, s.getStationName());
			ps.setString(3, s.getCox());
			ps.setString(4, s.getCoy());
			ps.setLong(5, s.getUnixTimestamp());

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

	public ArrayList<Station> selectAll() {
		ArrayList<Station> list = null;

		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM Station";

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

	public Station selectOne(String stationID) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM Station WHERE StationID = ?";

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

	private Station resultToModel(ResultSet rs) throws SQLException {
		Station s = new Station();

		s.setStationID(UUID.fromString(rs.getString("StationID")));
		s.setStationName(rs.getString("Name"));
		s.setCox(rs.getString("CoX"));
		s.setCoy(rs.getString("CoY"));
		s.setLastUpdated(rs.getLong("LastUpdated"));

		return s;
	}

	public static void createTable() {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "CREATE TABLE IF NOT EXISTS `Station` (  `StationID` "
				+ "varchar(36) NOT NULL DEFAULT '0',  `AddressID` varchar(36) "
				+ "NOT NULL DEFAULT '0',  `Name` varchar(100) CHARACTER SET utf8 "
				+ "COLLATE utf8_unicode_ci DEFAULT NULL,  `CoX` varchar(30) "
				+ "DEFAULT NULL,  `CoY` varchar(30) DEFAULT NULL,  "
				+ "`LastUpdated` bigint(14) DEFAULT NULL,  PRIMARY KEY (`StationID`),  "
				+ "UNIQUE KEY `Name` (`Name`),  KEY `AddressID` (`AddressID`)) "
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