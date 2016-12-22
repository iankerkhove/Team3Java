package dao;

	import java.sql.Connection;
	import java.sql.SQLException;

	public class BaseDAO {

		private static Connection connection;

		public static Connection getConnection() {
			return connection;
		}

		public void setConnection(Connection connection) {
			this.connection = connection;
		}
		
		public BaseDAO(){
			try {
				setConnection(DatabaseSingleton.getDatabaseSingleton().getConnection(true));
				
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}

