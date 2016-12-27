package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseSingleton {
	private final static String DATABASENAME = "localDB";
	private static DatabaseSingleton ref;

	private Connection connection;

	private DatabaseSingleton() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static DatabaseSingleton getDatabaseSingleton() {
		if (ref == null)
			ref = new DatabaseSingleton();
		return ref;
	}

	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();

	}

	public Connection getConnection(boolean autoCommit) throws SQLException {
		if (connection == null || connection.isClosed()) {

			try {
				connection = DriverManager.getConnection(
						"jdbc:sqlite:"+DATABASENAME+".db");

			} catch (SQLException ex) {
				System.out.println("SQLException: " + ex.getMessage());
				System.out.println("SQLState: " + ex.getSQLState());
				System.out.println("VendorError: " + ex.getErrorCode());
			}
		}
		return connection;
	}

}
