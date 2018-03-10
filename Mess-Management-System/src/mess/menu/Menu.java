/**
	The Menu class provides methods to modify the menuItems
	stored in the database. It also allows the admin to add
	NCMenuItems to the NCMenu and save them in the database
	
 */

package mess.menu;
 
import java.sql.*;
import mess.utility.DBM;

public class Menu
{
	public String name;
	public NCMenuItem[] NCitems = new NCMenuItem[100];
	public MenuItem[] menuitems = new MenuItem[20];

	public Menu(String n)
	{
		name = n;
	}
	
	public String[] listNames()throws SQLException
	{
		DBM db = new DBM("C:\\Java\\databases\\Menus");
		String sql = "SELECT tbl_name FROM sqlite_master WHERE type = 'table'";
		int i = 0;
		String names[] = new String[30];
		ResultSet rs = db.selectData(sql);
		
		while(rs.next())
		{
			names[i] = rs.getString("tbl_name");
			i++;
		}
		db.closeConnection();
		return names;
		
	}
	
	
	public NCMenuItem[] getNCmenulist()throws SQLException
	{
		DBM db = new DBM("C:\\Java\\databases\\Menus");
		int i = 0;
		String sql = "SELECT * FROM NC_MENU";
		ResultSet rs = db.selectData(sql);
			
		try
		{
			while(rs.next())
			{
				String itemName = rs.getString("NAME");
				int p = rs.getInt("PRICE");
				boolean b = rs.getBoolean("AVAILABLE");
			
				NCitems[i] = new NCMenuItem(itemName,p,b);
			
				i++;
			}
		}
		catch(SQLException e)
		{
			NCitems = null;
		}
			
		db.closeConnection();
		
		return NCitems;
	}
	
	public MenuItem[] getmenulist()throws SQLException
	{
		DBM db = new DBM("C:\\Java\\databases\\Menus");
		int i = 0;
		String sql = "SELECT * FROM " + name;
		ResultSet rs = db.selectData(sql);
			
		while(rs.next())
		{
			String itemName = rs.getString("NAME");
					
			menuitems[i] = new MenuItem(itemName);
			
			i++;
		}
			
		db.closeConnection();
		
		return menuitems;
	}
	
	public void addMessItem(MenuItem m)
	{
		String itemName = m.name;
		
		DBM db = new DBM("C:\\Java\\databases\\Menus");
		
		String[] keys = {"NAME"};
		String[] values = {itemName};
		db.insertData(name, keys, values);
		db.closeConnection();	
	}
	
	
	public void removeMessItem(MenuItem m)
	{
		String itemName = m.name;
	
		DBM db = new DBM("C:\\Java\\databases\\Menus");
		
		String sql = "DELETE FROM " + name + " WHERE NAME = '" + itemName + "'";
		db.modifyData(sql);
		db.closeConnection();
	}
		
	public void addNCItem(NCMenuItem m)
	{
		String itemName = m.name;
		int price = m.price;
		boolean availibility = m.getAvailibility();

		DBM db = new DBM("C:\\Java\\databases\\Menus");
		
		String[] keys = {"NAME", "PRICE", "AVAILABLE"};
		String[] values = {itemName, Integer.toString(price), Boolean.toString(availibility)};
		db.insertData("NC_MENU", keys, values);	
		db.closeConnection();
	}
	public void removeNCItem(NCMenuItem m)
	{
		String itemName = m.name;
	
		DBM db = new DBM("C:\\Java\\databases\\Menus");
		
		String sql = "DELETE FROM NC_MENU WHERE NAME = '" + itemName + "'";
		db.modifyData(sql);
		db.closeConnection();
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String n)
	{
		name = n;
	}
}	