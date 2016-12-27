package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import controller.APIController.APIUrl;
import controller.APIController.RequestType;
import services.APIThread;

public abstract class BaseDAO
{
	private Connection connection;
	
	private APIThread g3API;
	protected HashMap<String, String> params;
	protected Boolean isSyncFunction;

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
		this.isSyncFunction = false;
		try {
			setConnection(DatabaseSingleton.getDatabaseSingleton().getConnection(true));
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void syncMainDB(String url, RequestType requestType, HashMap<String, String> params)
	{
		g3API = new APIThread(APIUrl.G3, url, requestType, params, false);
		g3API.start();
	}
	
	public void setSyncFunction()
	{
		this.isSyncFunction = true;
	}
}

