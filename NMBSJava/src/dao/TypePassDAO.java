package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import model.TypePass;

public class TypePassDAO extends BaseDAO {

	public TypePassDAO() {
		
	}

	public int insert(TypePass t) {
		PreparedStatement ps = null;

		String sql = "INSERT INTO TypePass VALUES(?,?,?,?)";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			ps.setString(1, t.getTypePassID().toString());
			ps.setString(2, t.getName());
			ps.setDouble(3, t.getPrice());
			ps.setLong(4, t.getUnixTimestamp());

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

	public ArrayList<TypePass> selectAllSync() {
		ArrayList<TypePass> list = null;

		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM TypePass";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			rs = ps.executeQuery();
			list = new ArrayList<TypePass>();

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

	public ArrayList<TypePass> selectAll() {
		ArrayList<TypePass> list = null;

		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT t.TypePassID, t.Name, t.Price, t.LastUpdated as TypePassLastUpdated"
				+ "FROM TypePass t;";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			rs = ps.executeQuery();
			list = new ArrayList<TypePass>();

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

	public TypePass selectOne(String typePassID) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT t.TypePassID, t.Name, t.Price, t.LastUpdated as TypePassLastUpdated"
				+ "FROM TypePass t" + "WHERE TypePassID = ?;";

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

	private TypePass resultToModel(ResultSet rs) throws SQLException {
		TypePass t = new TypePass();
		
		t.setTypePassID(UUID.fromString(rs.getString("TypePassID")));
		t.setName(rs.getString("Name"));
		t.setPrice(rs.getDouble("Price"));
		t.setLastUpdated(rs.getLong("TypePassLastUpdated"));

		return t;
	}

	public static void createTable() {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "CREATE TABLE IF NOT EXISTS `TypePass` (  "
				+ "`TypePassID` varchar(36) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',  "
				+ "`Name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,  "
				+ "`Price` double NOT NULL,  `LastUpdated` bigint(14) DEFAULT NULL,  "
				+ "PRIMARY KEY (`TypePassID`)) "
				+ "ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;";

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