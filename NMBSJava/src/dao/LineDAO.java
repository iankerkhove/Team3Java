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
import model.Line;
import model.Route;

public class LineDAO extends BaseDAO 
{
	public final static String BASE_URL = "line/";
	
	public LineDAO() {}

	public int insertOrUpdate(Line a) {
		Line exists = this.selectOne(a.getLineID().toString());

		if (exists == null)
			return this.insert(a);
		else
			return this.update(a);
	}

	public int update(Line l) {
		PreparedStatement ps = null;

		String sql = "UPDATE Line SET RouteID = ?, TrainType = ?, LastUpdated = ? WHERE LineID = ?";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			ps.setString(1, l.getRouteID().toString());
			ps.setString(2, l.getTrainType());
			ps.setLong(3, l.getLastUpdated());
			ps.setString(4, l.getLineID().toString());

			if (!isSyncFunction)
			{
				params = new HashMap<String, String>();
				params.put("lineID", l.getLineID().toString());
				params.put("routeID", l.getRouteID().toString());
				params.put("trainType", l.getTrainType());
				params.put("lastUpdated", Long.toString(l.getLastUpdated()));
			}

			return ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (!isSyncFunction)
					syncMainDB(BASE_URL + "update/" + params.get("lineID"), RequestType.PUT, params);
				
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new RuntimeException("error.unexpected");
			}
		}
	}

	public int insert(Line l) {
		PreparedStatement ps = null;

		String sql = "INSERT INTO Line VALUES(?,?,?,?)";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			ps.setString(1, l.getLineID().toString());
			ps.setString(2, l.getRouteID().toString());
			ps.setString(3, l.getTrainType());
			ps.setLong(4, l.getLastUpdated());

			if (!isSyncFunction)
			{
				params = new HashMap<String, String>();
				params.put("lineID", l.getLineID().toString());
				params.put("routeID", l.getRouteID().toString());
				params.put("trainType", l.getTrainType());
				params.put("lastUpdated", Long.toString(l.getLastUpdated()));
			}

			return ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (!isSyncFunction)
					syncMainDB(BASE_URL + "create", RequestType.POST, params);

			} catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new RuntimeException("error.unexpected");
			}
		}

	}

	public ArrayList<Line> selectAllSync() {
		ArrayList<Line> list = null;

		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM Line";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			rs = ps.executeQuery();
			list = new ArrayList<Line>();

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

	public ArrayList<Line> selectAll() {
		ArrayList<Line> list = null;

		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT l.LineID,  r.RouteID, s.StationID as DepartStation, s.StationID as ArrivalStation,"
				+ " s.Name, s.CoX, s.CoY, s.LastUpdated as StationLastUpdated, "
				+ "r.LastUpdated as RouteLastUpdated, l.TrainType, l.LastUpdated as LineLastUpdated FROM Line l "
				+ "INNER JOIN Route r ON l.RouteID = r.RouteID "
				+ "INNER JOIN Station s ON s.StationID = r.DepartureStationID;";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			rs = ps.executeQuery();
			list = new ArrayList<Line>();

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

	public Line selectOne(String lineID) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT l.LineID,  r.RouteID, s.StationID as DepartStation, s.StationID as ArrivalStation,"
				+ " s.Name, s.CoX, s.CoY, s.LastUpdated as StationLastUpdated, "
				+ "r.LastUpdated as RouteLastUpdated, l.TrainType, l.LastUpdated as LineLastUpdated FROM Line l "
				+ "INNER JOIN Route r ON l.RouteID = r.RouteID "
				+ "INNER JOIN Station s ON s.StationID = r.DepartureStationID "
				+ "WHERE l.LineID = ?;";
		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			ps.setString(1, lineID);
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

	private Line resultToModel(ResultSet rs) throws SQLException {
		Line l = new Line();

		Route r = RouteDAO.resultToModel(rs);

		l.setLineID(UUID.fromString(rs.getString("LineID")));
		l.setRoute(r);
		l.setTrainType(rs.getString("TrainType"));
		l.setLastUpdated(rs.getLong("LastUpdated"));

		return l;
	}

	public TreeMap<String, String> updateStatus() {
		TreeMap<String, String> map = null;

		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT COUNT(DISTINCT LineID) as Count, MAX(LastUpdated) as LastUpdated FROM Line";

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

	public static void createTable(Connection con) {
		PreparedStatement ps = null;

		String sql = "CREATE TABLE IF NOT EXISTS `Line` (" + "`LineID` varchar(36) NOT NULL DEFAULT '0',"
				+ "`RouteID` varchar(36) NOT NULL DEFAULT '0'," + "`TrainType` varchar(10) NOT NULL,"
				+ "`LastUpdated` bigint(14) NOT NULL," + "PRIMARY KEY (`LineID`),"
				+ "FOREIGN KEY (`RouteID`) REFERENCES `Route`(`RouteID`)" + ");";

		try {

			if (con.isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
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

}
