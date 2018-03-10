/**
	The MenuItem class allows the user to create objects
	representing individual menu items which are stored in the database.
	These objects are used to populate the Menu objects.
 */
 
package mess.menu;
 
import java.sql.*;
import mess.utility.DBM;

public class MenuItem
{
	public String name;
	public int ID;
	
	public MenuItem(String n)
	{
		name = n;
	}
		
	public void createMenuItem()
	{
		DBM db = new DBM("C:\\Java\\databases\\Menu Items");
		
		String[] keys = {"NAME"};
		String[] values = {name};
		db.insertData("REGULAR_ITEMS", keys, values);	
		db.closeConnection();	
	}
	
	public static MenuItem loadMenuItem(String n)throws SQLException
	{
		DBM db = new DBM("C:\\Java\\databases\\Menu Items");
		
		String sql = "SELECT * FROM REGULAR_ITEMS WHERE NAME = '" + n + "'";
		ResultSet rs = db.selectData(sql);
		String nm = null;
		
		try
		{
			if(rs != null)
				nm = rs.getString("NAME");
		}
		catch(SQLException e)
		{
			nm = "";
		}
		finally
		{
			db.closeConnection();
		}
		
		return new MenuItem(nm);
	}
	
	public void deleteMenuItem(String n)
	{
		DBM db = new DBM("C:\\Java\\databases\\Menu Items");
		
		String sql = "DELETE FROM REGULAR_ITEMS WHERE NAME = '" + n + "'";
		db.modifyData(sql);
		db.closeConnection();	
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setname(String s)
	{
		name = s;
	}
}