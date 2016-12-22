package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import model.Address;
import model.Route;
import model.Station;
public class RouteDAO extends BaseDAO {
	
						public RouteDAO() {

						}

						public int insert(Route r) {
							PreparedStatement ps = null;

							String sql = "INSERT INTO Route VALUES(?,?,?,?)";

							try {

								if (getConnection().isClosed()) {
									throw new IllegalStateException("error unexpected");
								}
								ps = getConnection().prepareStatement(sql);

								ps.setString(1,r.getRouteID().toString());
								ps.setString(2,r.getDepartureStationID().toString());
								ps.setString(3, r.getArrivalStationID().toString());
								ps.setLong(4, r.getUnixTimestamp());
								
								//api call
								
								
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
						public ArrayList<Route> selectAllSync() {
							ArrayList<Route> list = null;

							PreparedStatement ps = null;
							ResultSet rs = null;

							String sql = "SELECT * FROM Route";

							try {

								if (getConnection().isClosed()) {
									throw new IllegalStateException("error unexpected");
								}
								ps = getConnection().prepareStatement(sql);

								rs = ps.executeQuery();
								list = new ArrayList<Route>();

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

						public ArrayList<Route> selectAll() {
							ArrayList<Route> list = null;

							PreparedStatement ps = null;
							ResultSet rs = null;

							String sql = "SELECT r.RouteID, s.StationID as DepartStation, s.StationID as ArrivalStation, a.AddressID, a.Street,"+
									 " a.Number, a.City, a.ZipCode, a.Coordinates, a.LastUpdated as AddressLastUpdated,"
									 + " s.Name, s.CoX,s.CoY,"+
									 "s.LastUpdated as StationLastUpdated, "+
									 "r.LastUpdated as RouteLastUpdated"+
									 " FROM Route r"+
									 "INNER JOIN Station s ON s.StationID = r.DepartureStationID"+
									 "INNER JOIN Address a ON a.AddressID = s.AddressID;";

							try {

								if (getConnection().isClosed()) {
									throw new IllegalStateException("error unexpected");
								}
								ps = getConnection().prepareStatement(sql);

								rs = ps.executeQuery();
								list = new ArrayList<Route>();

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

						public Route selectOne(String routeID) {
							PreparedStatement ps = null;
							ResultSet rs = null;

							String sql = 
									"SELECT r.RouteID, s.StationID as DepartStation, s.StationID as ArrivalStation, a.AddressID, a.Street,"+
											 " a.Number, a.City, a.ZipCode, a.Coordinates, a.LastUpdated as AddressLastUpdated,"
											 + " s.Name, s.CoX,s.CoY,"+
											 "s.LastUpdated as StationLastUpdated, "+
											 "r.LastUpdated as RouteLastUpdated"+
											 " FROM Route r"+
											 "INNER JOIN Station s ON s.StationID = r.DepartureStationID"+
											 "INNER JOIN Address a ON a.AddressID = s.AddressID"
											 + "WHERE r.RouteID=?;";
							try {

								if (getConnection().isClosed()) {
									throw new IllegalStateException("error unexpected");
								}
								ps = getConnection().prepareStatement(sql);

								ps.setString(1, routeID);
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

						static Route resultToModel(ResultSet rs) throws SQLException {
							Route r= new Route();
							Station s = new Station();
							Station s2 = new Station();
							Address a = new Address();
							Address a2 = new Address();
							
							a.setAddressID(UUID.fromString(rs.getString("AddressID")));
							a.setStreet(rs.getString("Street"));
							a.setNumber(rs.getInt("Number"));
							a.setCity(rs.getString("City"));
							a.setZipCode(rs.getInt("ZipCode"));
							a.setCoordinates(rs.getString("Coordinates"));
							a.setLastUpdated(rs.getLong("AddressLastUpdated"));
							
							s.setStationID(UUID.fromString(rs.getString("StationID")));
							s.setAddress(a);
							s.setStationName(rs.getString("Name"));
							s.setCox(rs.getString("CoX"));
							s.setCoy(rs.getString("CoY"));
							s.setLastUpdated(rs.getLong("StationLasUpdated"));
							
							a2.setAddressID(UUID.fromString(rs.getString("AddressID")));
							a2.setStreet(rs.getString("Street"));
							a2.setNumber(rs.getInt("Number"));
							a2.setCity(rs.getString("City"));
							a2.setZipCode(rs.getInt("ZipCode"));
							a2.setCoordinates(rs.getString("Coordinates"));
							a2.setLastUpdated(rs.getLong("AddressLastUpdated"));
							
							s2.setStationID(UUID.fromString(rs.getString("StationID")));
							s2.setAddress(a2);
							s2.setStationName(rs.getString("Name"));
							s2.setCox(rs.getString("CoX"));
							s2.setCoy(rs.getString("CoY"));
							s2.setLastUpdated(rs.getLong("StationLasUpdated"));
							
							r.setRouteID(UUID.fromString(rs.getString("RouteID")));
							r.setDepartureStation(s);
							r.setArrivalStation(s2);
							r.setLastUpdated(rs.getLong("RouteLastUpdated"));
						
							return r;
						}

						public static void createTable(){
							PreparedStatement ps = null;
							ResultSet rs = null;

							String sql = "CREATE TABLE IF NOT EXISTS `Route` ("+
										"`RouteID` varchar(36) NOT NULL DEFAULT '0',"+
  										"`DepartureStationID` varchar(36) NOT NULL DEFAULT '0',"+
  										"`ArrivalStationID` varchar(36) NOT NULL DEFAULT '0',"+
  										"`LastUpdated` bigint(14) DEFAULT NULL,"+
  										"PRIMARY KEY (`RouteID`),"+
  										"UNIQUE KEY `uq_route` (`DepartureStationID`,`ArrivalStationID`) USING BTREE,"+
  										"KEY `fk_arrivStat` (`ArrivalStationID`)"+
  										") ENGINE=InnoDB DEFAULT CHARSET=latin1;";

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
