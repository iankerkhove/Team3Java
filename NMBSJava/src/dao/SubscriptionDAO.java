package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import model.Discount;
import model.RailCard;
import model.Route;
import model.Station;
import model.Subscription;
public class SubscriptionDAO extends BaseDAO {
	
						public SubscriptionDAO() {

						}

						public int insert(Subscription s) {
							PreparedStatement ps = null;

							String sql = "INSERT INTO Subscription VALUES(?,?,?,?,?,?,?)";

							try {

								if (getConnection().isClosed()) {
									throw new IllegalStateException("error unexpected");
								}
								ps = getConnection().prepareStatement(sql);

								ps.setString(1, s.getSubscriptionID().toString());
								ps.setString(2,s.getRailID().toString());
								ps.setString(3, s.getRouteID().toString());
								ps.setString(4, s.getDiscountID().toString());
								ps.setString(5,s.getValidFrom().toString());
								ps.setString(6, s.getValidUntil().toString());
								ps.setLong(7, s.getUnixTimestamp());
								
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
						public ArrayList<Subscription> selectAllSync() {
							ArrayList<Subscription> list = null;

							PreparedStatement ps = null;
							ResultSet rs = null;

							String sql = "SELECT * FROM LostObject";

							try {

								if (getConnection().isClosed()) {
									throw new IllegalStateException("error unexpected");
								}
								ps = getConnection().prepareStatement(sql);

								rs = ps.executeQuery();
								list = new ArrayList<Subscription>();

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

						public ArrayList<Subscription> selectAll() {
							ArrayList<Subscription> list = null;

							PreparedStatement ps = null;
							ResultSet rs = null;

							String sql = "SELECT s.SubscriptionID, c.CardID,c.LastUpdated as RailCardLastUpdated"+
									 "r.RouteID,r.DepartureStationID,r.ArrivalStationID,r.LastUpdated as RouteLastUpdated,"
									 + "d.DiscountID, d.Name,d.Amount,d.LastUpdated as DiscountLastUpdated,"+
									 "s.ValidFrom,s.ValidUntil,s.LastUpdated as SubscriptionLastUpdated"+
									 " FROM Subscription s"+
									 "INNER JOIN RailCard c ON c.CardID = s.RailCardID"+
									 "INNER JOIN Route r ON r.RouteID = s.RouteID"+
									 "INNER JOIN Discount d ON d.DiscountID = s.DiscountID;";

							try {

								if (getConnection().isClosed()) {
									throw new IllegalStateException("error unexpected");
								}
								ps = getConnection().prepareStatement(sql);

								rs = ps.executeQuery();
								list = new ArrayList<Subscription>();

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

						public Subscription selectOne(String discountID) {
							PreparedStatement ps = null;
							ResultSet rs = null;

							String sql = 
									"SELECT s.SubscriptionID, c.CardID,c.LastUpdated as RailCardLastUpdated"+
											 "r.RouteID,r.DepartureStationID,r.ArrivalStationID,r.LastUpdated as RouteLastUpdated,"
											 + "d.DiscountID, d.Name,d.Amount,d.LastUpdated as DiscountLastUpdated,"+
											 "s.ValidFrom,s.ValidUntil,s.LastUpdated as SubscriptionLastUpdated"+
											 " FROM Subscription s"+
											 "INNER JOIN RailCard c ON c.CardID = s.RailCardID"+
											 "INNER JOIN Route r ON r.RouteID = s.RouteID"+
											 "INNER JOIN Discount d ON d.DiscountID = s.DiscountID;";
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

						private Subscription resultToModel(ResultSet rs) throws SQLException {
							Subscription s= new Subscription();
							RailCard c = new RailCard();
							Route r = new Route();
							Discount d = new Discount();
							
							r.RouteDAO.resultToModel(rs);
							c.setRailCardID(UUID.fromString(rs.getString("CardID")));
							c.setLastUpdated(rs.getLong("RailCardLastUpdated"));
							
							r.setRouteID(UUID.fromString(rs.getString("RouteID")));
							r.setDepartureStationID(UUID.fromString(rs.getString("DepartureStationID")));
							r.setArrivalStationID(UUID.fromString(rs.getString("ArrivalStationID")));
							r.setLastUpdated(rs.getLong("RouteLastUpdated"));
							
							d.setDiscountID(UUID.fromString(rs.getString("DiscountID")));
							d.setName(rs.getString("Name"));
							d.setAmount(rs.getDouble("Amount"));
							d.setLastUpdated(rs.getLong("LastUpdated"));
							
							//rail route en discount moeten veranderen naar c,r,d
							// + static functie maken zodat er geen dubbele code is
						
							s.setSubscriptionID(UUID.fromString(rs.getString("SubscriptionID")));
							s.setRailID(UUID.fromString(rs.getString("CardID")));
							s.setRouteID(UUID.fromString(rs.getString("RouteID")));
							s.setDiscountID(UUID.fromString(rs.getString("DiscountID")));
							s.setValidFrom(rs.getDate("ValidFrom"));
							s.setValidUntil(rs.getDate("ValidUntil"));
							s.setLastUpdated(rs.getLong("SubscriptionLastUpdated"));
							
							return s;
						}

						public static void createTable(){
							PreparedStatement ps = null;
							ResultSet rs = null;

							String sql = "CREATE TABLE IF NOT EXISTS `Subscription` ("+
										"`SubscriptionID` varchar(36) NOT NULL DEFAULT '0',"+
										"`RailCardID` varchar(36) NOT NULL DEFAULT '0',"+
										"`RouteID` varchar(36) NOT NULL DEFAULT '0',"+
										"`DiscountID` varchar(36) NOT NULL DEFAULT '0',"+
										"`ValidFrom` varchar(20) NOT NULL,"+
										"`ValidUntil` varchar(20) NOT NULL,"+
										"`LastUpdated` bigint(14) DEFAULT NULL,"+
										"PRIMARY KEY (`SubscriptionID`),"+
										"KEY `RailCardID` (`RailCardID`),"+
										"KEY `RouteID` (`RouteID`),"+
										"KEY `DiscountID` (`DiscountID`)"+
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
