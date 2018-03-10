/**
	The Leave class allows the Admin to view number of people 
	who will be on leave by counting the number of database entries for that day
	Methods are also provided to submit the leave and delete it from the table 
	once the end date has been reached
 */
 
package mess.utility; 
 
import java.sql.*;
 
public class Leave
{
	public String startDate;
	public String endDate;
	
	public Leave(String s, String e)
	{
		startDate = s;
		endDate = e;
	}
	
	public void submit()
	{
		DBM db = new DBM("Students.db");
	
		String[] keys = {"START", "END"};
		String[] values = {startDate, endDate};
		db.insertData("STATS", keys, values);
		
		db.closeConnection();
	}

	public void delete(String date)
	{
		DBM db = new DBM("Students.db");
	
		String sql = "DELETE * FROM STATS WHERE END = '" + endDate + "'";
		db.modifyData(sql);
		db.closeConnection();
	}
	
	public int calcAbsent()throws SQLException
	{
		DBM db = new DBM("Students.db");
		String sql = "SELECT * FROM STATS";
		ResultSet rs = db.selectData(sql);
		rs.last();
		
		return rs.getRow();
	}
	
	public void setStartDate(String s)
	{
		startDate = s;
	}
		
	public void setEndDate(String s)
	{
		endDate = s;
	}
	
	public String getStartDate()
	{
		return startDate;
	}
	
	public String getEndDate()
	{
		return endDate;
	}	
}