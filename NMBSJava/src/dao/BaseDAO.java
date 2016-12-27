package dao;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class BaseDAO
{
	private Connection connection;

	protected Connection getConnection()
	{
		return connection;
	}

	protected void setConnection(Connection connection)
	{
		this.connection = connection;
	}

	public BaseDAO()
	{
		try {
			setConnection(DatabaseSingleton.getDatabaseSingleton().getConnection(true));

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

