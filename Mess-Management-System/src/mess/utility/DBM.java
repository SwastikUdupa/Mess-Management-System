/**
	This Class is the core database manager class and is used by every other class
	in the mess management system. It provides methods to connect to the databases
	and perform CRUD operations.

	@author Sanket Rajan Gupte 2013A7PS078G
 */
 
package mess.utility; 
 
import java.sql.*;

public class DBM
{
	String DBname = new String();
	Connection connection = null;
	Statement statement = null;
	
	public DBM(String name)
	{
		DBname = name + ".db";
		this.connectToDB(DBname);
	}
	
	public void connectToDB(String DBname)
	{
		try
		{
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + DBname);
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			System.out.println("Opened database " + DBname + " successfully");
		}
		
		catch(Exception e)
		{
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
			System.out.println("No driver bro");
		}
	}
	
	public void closeConnection()
	{
		if(connection == null)
			System.out.println("Connect to to database first");
		
		else
		{
			try
			{
				if(statement != null)
					statement.close();
			
				connection.close();
				System.out.println("connection closed");
			}
			
			catch(Exception e)
			{
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
			}
		}
	}

	public void insertData(String Table, String[] keys, String[] values)
	{
		String key = new String();
		String value = new String();
		String sql = new String();
		
		for(int i = 0; i < keys.length; i++)
		{
			key = key + keys[i] + ",";
			value = value + "'" + values[i] + "'" + ",";
		}
		
		key = "(" + key.substring(0,key.length()-1) + ")";
		value = "(" + value.substring(0,value.length()-1) + ")";
		
		sql = "INSERT INTO " + Table + " " + key + " VALUES " + value;
		
		System.out.println(sql);
		this.modifyData(sql);
	}
	
	public ResultSet selectData(String query)
	{
		ResultSet resultset = null;
		
		try
		{
			resultset = statement.executeQuery(query);
			return resultset;
		}
		
		catch(Exception e)
		{
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
			return resultset;		
		}
	}
	
	public void modifyData(String update)
	{
		try
		{
			statement.executeUpdate(update);
			connection.commit();
		}
		
		catch(Exception e)
		{
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
}