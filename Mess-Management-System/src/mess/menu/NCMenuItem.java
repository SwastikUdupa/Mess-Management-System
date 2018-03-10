/**
	The NCMenuItem class is an extension of the MenuItem class.
	It contains additional methods for setting the price and availibility.
	It also accesses the database to store the NCMenuItem objects
	allowing them to be stored or modified on the database
 */
 
package mess.menu; 
 
import java.sql.*;
import mess.utility.DBM;

public class NCMenuItem extends MenuItem
{
	public int price;
	public boolean availibility;
	

	public NCMenuItem(String n, int p, boolean b)
	{
		super(n);
		price = p;
		availibility = b	;	
	}
		
	public void createNCMenuItem()
	{
		DBM db = new DBM("C:\\Java\\databases\\Menu Items");
		
		String[] keys = {"NAME", "PRICE", "AVAILABLE"};
		String[] values = {name, Integer.toString(price), Boolean.toString(availibility)};
		db.insertData("NC_ITEMS", keys, values);	
		db.closeConnection();	
	}
	
	public static NCMenuItem loadNCMenuItem(String n)throws SQLException
	{
		DBM db = new DBM("C:\\Java\\databases\\Menu Items");
		
		String sql = "SELECT * FROM NC_ITEMS WHERE NAME = '" + n + "'";
		ResultSet rs = db.selectData(sql);
		String nm = null;
		int p = 0;
		boolean b = false;
		
		try
		{
			if(rs != null)
			{
				nm = rs.getString("NAME");
				p = rs.getInt("PRICE");
				b = rs.getBoolean("AVAILABLE");
				db.closeConnection();
			}
		}
		catch(SQLException e)
		{
			nm = "";
			p = 0;
			b = false;
		}
		finally
		{
			db.closeConnection();
		}
		
		return new NCMenuItem(nm,p,b);
	}

	public void deleteNCMenuItem(String n)
	{
		DBM db = new DBM("C:\\Java\\databases\\Menu Items");
		
		String sql = "DELETE * FROM NC_ITEMS WHERE NAME = '" + n + "'";
		db.modifyData(sql);
		db.closeConnection();	
	}

	public void changePrice(String n, int p)
	{
		DBM db = new DBM("C:\\Java\\databases\\Menu Items");
		
		String sql = "UPDATE NC_ITEMS SET PRICE = '" + Integer.toString(p) + "' WHERE NAME = '" + n + "'";
		db.modifyData(sql);
		db.closeConnection();	
	}
	
	public void changeAvailibility(String n, boolean s)
	{
		DBM db = new DBM("C:\\Java\\databases\\Menu Items");
		
		String sql = "UPDATE NC_ITEMS SET AVAILABLE = '" + Boolean.toString(s) + "' WHERE NAME = '" + n + "'";
		db.modifyData(sql);
		db.closeConnection();	
	}
	
	public int getPrice()
	{
		return price;
	}
	
	public void setPrice(int p)
	{
		price = p;
	}
	
	public boolean getAvailibility()
	{
		return availibility;
	}
	
	public void setAvailibility(boolean b)
	{
		availibility = b;
	}
}