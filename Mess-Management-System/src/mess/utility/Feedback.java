/**
	This Class contains the blueprint of Feedback objects
	and their associated methods.
	Feedback objects are used by the students to send feedback
	or complaints to the server and by the admin to read
	feedback from the server
	
	@author Sanket Rajan Gupte 2013A7PS078G
 */

package mess.utility; 

import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.*;

public class Feedback
{
	public String description;
	public String studentID;
	public int feedbackID;
	public Type type;
	public String date;// = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	
	public Feedback(Type t)
	{
		type = t;
		date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	}
	
	public Feedback(int id, Type t)throws SQLException
	{
		type = t;
		feedbackID = id;
		DBM db = new DBM("C:\\Java\\databases\\Feedback");
		
		if(type == Type.FEEDBACK)
		{
			String sql = "SELECT * FROM FEEDBACK WHERE FEEDBACK_ID = '" + Integer.toString(id) + "'";
			ResultSet rs = db.selectData(sql);
		
			try
			{
				studentID = rs.getString("STUDENT_ID");
				description = rs.getString("DATA");
				date = rs.getString("DATE");
			}
			catch(SQLException e)
			{
				description = null;
			}
			finally
			{
				rs.close();
			}
			
			db.closeConnection();
		}
		else if(type == Type.COMPLAINT)
		{
			String sql = "SELECT * FROM COMPLAINTS WHERE COMPLAINT_ID = '" + Integer.toString(id) + "'";
			ResultSet rs = db.selectData(sql);
			
			try
			{
				studentID = rs.getString("STUDENT_ID");
				description = rs.getString("DESCRIPTION");
				date = rs.getString("DATE");
			}
			catch(SQLException e)
			{
				description = null;
			}
			finally
			{
				rs.close();
			}
						
			db.closeConnection();
		}
	}
	public int submit()
	{
		DBM db = new DBM("C:\\Java\\databases\\Feedback");
		
		if(type == Type.FEEDBACK)
		{
			String[] keys = {"STUDENT_ID", "DATA", "DATE"};
			String[] values = {studentID, description, date};
			db.insertData("FEEDBACK", keys, values);
			db.closeConnection();
			return 1;
		}
		else if(type == Type.COMPLAINT)
		{
			String[] keys = {"STUDENT_ID", "DESCRIPTION", "DATE"};
			String[] values = {studentID, description, date};
			db.insertData("COMPLAINTS", keys, values);
			db.closeConnection();
			return 1;
		}
		else
			db.closeConnection();
			return 0;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public String getStudentID()
	{
		return studentID;
	}
	
	public int getFeedbackID()
	{
		return feedbackID;
	}
		
	public String getDate()
	{
		return date;
	}
	
	public Type getType()
	{
		return type;
	}
	
	public void setDescription(String s)
	{
		description = s;
	}

	public void setType(Type t)
	{
		type = t;
	}
	
	public void setStudentID(String s)
	{
		studentID = s;
	}
	
	public void setFeedbackID(int i)
	{
		feedbackID = i;
	}
}	
	