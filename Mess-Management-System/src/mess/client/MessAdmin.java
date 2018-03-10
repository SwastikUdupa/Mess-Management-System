/**
	The MessAdmin class is one of the core classes. It represents
	the system admin who can reset the system, swipe cards
	and reset his password
 */

package mess.client;
 
import java.sql.*;
import mess.utility.DBM;

public class MessAdmin
{
	public String name;
	public String emailID;
	public String password;
	public int ID;
	public char mess;
	
	public MessAdmin(String email)throws SQLException
	{
		emailID = email;
		DBM db = new DBM("C:\\Java\\databases\\Admins");
		
		String sql = "SELECT * FROM ADMINS WHERE EMAIL = '" + emailID + "'";
		ResultSet rs = db.selectData(sql);
		
		try	
		{
			name = rs.getString("NAME");
			ID = rs.getInt("ID");
			password = rs.getString("PASSWORD");
			mess = rs.getString("MESS").charAt(0);
		}
		catch(SQLException e)
		{
			emailID = null;
		}
		finally
		{
			rs.close();
		}
		
		db.closeConnection();
	}
	
	public void resetSystem()
	{
		DBM db = new DBM("C:\\Java\\databases\\Students");
		
		String sql = "UPDATE STUDENTS SET EATEN = '0' WHERE MESS = '" + mess + "'";
		db.modifyData(sql);
		db.closeConnection();
	}

	public int swipe(String ID)throws SQLException
	{
		DBM db = new DBM("C:\\Java\\databases\\Students");
		
		String sql = "SELECT * FROM STUDENTS WHERE ID = '" + ID + "'";
		ResultSet rs = db.selectData(sql);
		int eat = 0;
		try
		{
			eat = rs.getInt("EATEN");
		}
		catch(SQLException e)
		{
			eat = -1;
		}
		finally
		{
			rs.close();
		}
		if(eat == 0)
		{
			sql = "UPDATE STUDENTS SET EATEN = '1' WHERE ID = '" + ID + "'";
			db.modifyData(sql);
		}
		
		db.closeConnection();
		return eat;
			
	}
	
	public void changePassword(String s)
	{
		DBM db = new DBM("C:\\Java\\databases\\Admins");
		
		String sql = "UPDATE ADMINS SET PASSWORD = '" + s + "' WHERE ID = '" + Integer.toString(ID) + "'";
		db.modifyData(sql);
		db.closeConnection();
		this.setPassword(s);
	}

	public void setPassword(String s)
	{
		password = s;
	}
	
	public void setEmail(String s)
	{
		emailID = s;
	}
	
	public void setName(String s)
	{
		name = s;
	}
	
	public void setMess(char m)
	{
		mess = m;
	}
	
	public String getEmail()
	{
		return emailID;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public char getMess()
	{
		return mess;
	}
}
	