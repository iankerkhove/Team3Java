package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;


import model.Pass;
import model.TypePass;
public class PassDAO extends BaseDAO {
	
			public PassDAO() {

							}

							public int insert(Pass p) {
								PreparedStatement ps = null;

								String sql = "INSERT INTO Pass VALUES(?,?,?,?,?,?)";

								try {

									if (getConnection().isClosed()) {
										throw new IllegalStateException("error unexpected");
									}
									ps = getConnection().prepareStatement(sql);

									ps.setString(1,p.getPassID().toString());
									ps.setString(2,p.getTypePassID().toString());
									ps.setString(3, p.getDate().toString());
									ps.setString(4, p.getStartDate().toString());
									ps.setInt(5, p.getComfortClass());
									ps.setLong(6, p.getUnixTimestamp());
									
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
							public ArrayList<Pass> selectAllSync() {
								ArrayList<Pass> list = null;

								PreparedStatement ps = null;
								ResultSet rs = null;

								String sql = "SELECT * FROM Pass";

								try {

									if (getConnection().isClosed()) {
										throw new IllegalStateException("error unexpected");
									}
									ps = getConnection().prepareStatement(sql);

									rs = ps.executeQuery();
									list = new ArrayList<Pass>();

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

							public ArrayList<Pass> selectAll() {
								ArrayList<Pass> list = null;

								PreparedStatement ps = null;
								ResultSet rs = null;

								String sql = "SELECT p.PassID, t.TypePassID, t.Name, t.Price,t.LastUpdated as TypePassLastUpdated,"+
										 " p.Date,p.StartDate,p.ComfortClass, p.LastUpdated as PassLastUpdated"+
										 " FROM Pass p"+
										 "INNER JOIN TypePass t ON p.TypePassID = t.TypePassID;";

								try {

									if (getConnection().isClosed()) {
										throw new IllegalStateException("error unexpected");
									}
									ps = getConnection().prepareStatement(sql);

									rs = ps.executeQuery();
									list = new ArrayList<Pass>();

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

							public Pass selectOne(String passID) {
								PreparedStatement ps = null;
								ResultSet rs = null;

								String sql = 
										"SELECT p.PassID, t.TypePassID, t.Name, t.Price,t.LastUpdated as TypePassLastUpdated,"+
												 " p.Date,p.StartDate,p.ComfortClass, p.LastUpdated as PassLastUpdated"+
												 " FROM Pass p"+
												 "INNER JOIN TypePass t ON p.TypePassID = t.TypePassID"+
												  "WHERE p.PassID=?;";
								try {

									if (getConnection().isClosed()) {
										throw new IllegalStateException("error unexpected");
									}
									ps = getConnection().prepareStatement(sql);

									ps.setString(1, passID);
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

							static Pass resultToModel(ResultSet rs) throws SQLException {
								Pass p= new Pass();
								TypePass t = new TypePass();
								
								t.setTypePassID(UUID.fromString(rs.getString("TypePassID")));
								t.setName(rs.getString("Name"));
								t.setPrice(rs.getDouble("Price"));
								t.setLastUpdated(rs.getLong("TypePassLastUpdated"));
								
								p.setPassID(UUID.fromString(rs.getString("PassID")));
								p.setTypePass(t);
								p.setDate(rs.getDate("Date"));
								p.setStartDate(rs.getDate("StartDate"));
								p.setComfortClass(rs.getInt("ComfortClass"));
								p.setLastUpdated(rs.getLong("PassLastUpdated"));
								
								return p;
							}

							public static void createTable(){
								PreparedStatement ps = null;
								ResultSet rs = null;

								String sql = "CREATE TABLE IF NOT EXISTS `Pass` ("+
											"`PassID` varchar(36) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',"+
											"`TypePassID` varchar(36) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',"+
											"`Date` varchar(11) COLLATE utf8_unicode_ci NOT NULL,"+
											"`StartDate` varchar(11) COLLATE utf8_unicode_ci NOT NULL,"+
											"`ComfortClass` int(11) NOT NULL,"+
											"`LastUpdated` bigint(14) DEFAULT NULL,"+
											"PRIMARY KEY (`PassID`),"+
											"KEY `TypePassID` (`TypePassID`)"+
											") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;";

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