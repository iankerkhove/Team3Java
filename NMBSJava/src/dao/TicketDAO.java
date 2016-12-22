package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import model.Route;
import model.Ticket;
import model.TypeTicket;

public class TicketDAO extends BaseDAO {

	public TicketDAO() {

	}

	public int insert(Ticket t) {
		PreparedStatement ps = null;

		String sql = "INSERT INTO Ticket VALUES(?,?,?,?,?,?,?)";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			ps.setString(1, t.getTicketID().toString());
			ps.setString(2, t.getRouteID().toString());
			ps.setString(3, t.getTicketID().toString());
			ps.setString(4, t.getDate().toString());
			ps.setString(5, t.getValidFrom().toString());
			ps.setString(6, t.getValidUntil().toString());
			ps.setLong(7, t.getUnixTimestamp());

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

	public ArrayList<Ticket> selectAllSync() {
		ArrayList<Ticket> list = null;

		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM Ticket";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			rs = ps.executeQuery();
			list = new ArrayList<Ticket>();

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

	public ArrayList<Ticket> selectAll() {
		ArrayList<Ticket> list = null;

		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT t.TicketID, r.RouteID, r.DepartureStation as DepartStation, r.ArrivalStation as ArrivalStation, a.AddressID, a.Street,"
				+ " a.Number, a.City, a.ZipCode, a.Coordinates, a.LastUpdated as AddressLastUpdated,"
				+ " s.Name, s.CoX,s.CoY," + "s.LastUpdated as StationLastUpdated, "
				+ "r.LastUpdated as RouteLastUpdated"
				+ "ty.TypeTicketID, ty.Name,ty.Price,ty.ComfortClass, ty.LastUpdated"
				+ "t.Date,t.ValidFrom,t.ValidUntil,t.LastUpdated as TicketLastUpdated" + " FROM Ticket t"
				+ "INNER JOIN Route r ON r.RouteID = t.RouteID"
				+ "INNER JOIN TypeTicket ty ON ty.TypeTicket = t.TypeTicket"
				+ "INNER JOIN Station s ON s.StationID = r.DepartureStationID"
				+ "INNER JOIN Address a ON a.AddressID = s.AddressID;";
		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			rs = ps.executeQuery();
			list = new ArrayList<Ticket>();

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

	public Ticket selectOne(String ticketID) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT t.TicketID, r.RouteID, r.DepartureStation as DepartStation, r.ArrivalStation as ArrivalStation, a.AddressID, a.Street,"
				+ " a.Number, a.City, a.ZipCode, a.Coordinates, a.LastUpdated as AddressLastUpdated,"
				+ " s.Name, s.CoX,s.CoY," + "s.LastUpdated as StationLastUpdated, "
				+ "r.LastUpdated as RouteLastUpdated"
				+ "ty.TypeTicketID, ty.Name,ty.Price,ty.ComfortClass, ty.LastUpdated"
				+ "t.Date,t.ValidFrom,t.ValidUntil,t.LastUpdated as TicketLastUpdated" + " FROM Ticket t"
				+ "INNER JOIN Route r ON r.RouteID = t.RouteID"
				+ "INNER JOIN TypeTicket ty ON ty.TypeTicket = t.TypeTicket"
				+ "INNER JOIN Station s ON s.StationID = r.DepartureStationID"
				+ "INNER JOIN Address a ON a.AddressID = s.AddressID" + "WHERE t.TicketID=?;";
		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			ps.setString(1, ticketID);
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

	static Ticket resultToModel(ResultSet rs) throws SQLException {
		Ticket t = new Ticket();
		TypeTicket ty = TypeTicketDAO.resultToModel(rs);
		Route r = RouteDAO.resultToModel(rs);

		t.setTicketID(UUID.fromString(rs.getString("TicketID")));
		t.setRouteID(r.getRouteID());
		t.setTicketID(ty.getTypeTicketID());
		t.setDate(rs.getDate("Date"));
		t.setValidFrom(rs.getDate("ValidFrom"));
		t.setValidUntil(rs.getDate("ValidUntil"));
		t.setLastUpdated(rs.getLong("TicketLastUpdated"));

		return t;
	}

	public static void createTable() {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "CREATE TABLE IF NOT EXISTS `Ticket` (" + "`TicketID` varchar(36) NOT NULL DEFAULT '0',"
				+ "`RouteID` varchar(36) NOT NULL DEFAULT '0'," + "`TypeTicketID` varchar(36) NOT NULL DEFAULT '0',"
				+ "`Date` varchar(20) NOT NULL," + "`ValidFrom` varchar(20) NOT NULL,"
				+ "`ValidUntil` varchar(20) NOT NULL," + "`LastUpdated` bigint(14) DEFAULT NULL,"
				+ "PRIMARY KEY (`TicketID`)," + "KEY `RouteID` (`RouteID`)," + "KEY `TypeTicketID` (`TypeTicketID`)"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";
		;

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