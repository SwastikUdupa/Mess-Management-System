/**
	This class provides a template for the Student object.
	It provides a container to store student details after loading them from
	the database. It also has functionality to change the password and mess option.	
 */
 
package mess.client;
 
import java.sql.*;
import mess.utility.DBM;

public class Student
{
	public char messOption;
	public String ID;
	public String name;
	public String emailID;
	public boolean hasEaten;
	public String password;
	
	public Student(String id)throws SQLException
	{
		ID = id;
		DBM db = new DBM("C:\\Java\\databases\\Students");
		
		String sql = "SELECT * FROM STUDENTS WHERE ID = '" + id + "'";
		ResultSet rs = db.selectData(sql);
		
		try
		{
			name = rs.getString("NAME");
			emailID = rs.getString("EMAIL");
			password = rs.getString("PASSWORD");
			messOption = rs.getString("MESS").charAt(0);
		
			if(rs.getInt("EATEN") == 1)
				hasEaten = true;
			else
				hasEaten = false;
		}
		catch(SQLException e)
		{
			ID = null;
		}
		finally
		{
			rs.close();
		}
		
		db.closeConnection();
	}
	
	public void changePassword(String s)
	{
		DBM db = new DBM("C:\\Java\\databases\\Students");
		
		String sql = "UPDATE STUDENTS SET PASSWORD = '" + s + "' WHERE ID = '" + ID + "'";
		db.modifyData(sql);
		db.closeConnection();
	}
	
	public void changeMessOption(String c)
	{
		DBM db = new DBM("C:\\Java\\databases\\Students");
		
		String sql = "UPDATE STUDENTS SET MESS = '" + c + "' WHERE ID = '" + ID + "'";
		db.modifyData(sql);
		db.closeConnection();
	}
	
	public String getID()
	{
		return ID;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getEmail()
	{
		return emailID;
	}
	
	public char getMessOption()
	{
		return messOption;
	}
	public boolean getHasEaten()
	{
		return hasEaten;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setMessOption(char c)
	{
		messOption = c;
	}
	
	public void setID(String s)
	{
		ID = s;
	}
	
	public void setName(String s)
	{
		name = s;
	}
	
	public void setEmail(String s)
	{
		emailID = s;	
	}	
	
	public void setHasEaten(boolean b)
	{
		hasEaten = b;
	}
	
	public void setPassword(String s)
	{
		password = s;
	}
}