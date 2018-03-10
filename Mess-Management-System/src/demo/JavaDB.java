package demo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
public class JavaDB 
{
//	public String name;
//	public boolean hasEaten;
//	public JavaDB(String id)
//	{
//		try
//		{
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mess_management", "root", "");
//			Statement st = con.createStatement();
//			ResultSet rs = st.executeQuery("select * from students where ID="+id+";");
//			while(rs.next())
//				name = (rs.getString("NAME"));
//				if(rs.getInt("EATEN") == 1)
//					hasEaten = true;
//				else
//					hasEaten = false;
//			con.close();
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//			
//		}
//	}
//	public static void main(String args[])
//	{
//		JavaDB db = new JavaDB("1");
//		String name = db.getName();
//		System.out.println(name);
//	}
//	public String getName()
//	{
//		System.out.println(hasEaten);
//		return name;
//	}
	public static void main(String args[]) throws ClassNotFoundException, SQLException, IOException 
	{
		
//		Class.forName("com.mysql.jdbc.Driver");
//		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mess_management", "root", "");
//		Statement st = con.createStatement();
//		ResultSet rs = st.executeQuery("select * from students;");
//		while(rs.next())
//			System.out.print(rs.getString(5));
//		String q = "update students set mess='C' where mess='B';";
//		System.out.println(q);
//		st.executeUpdate(q);
//		ResultSet rs2 = st.executeQuery("select mess from students;");
//		while (rs2.next())
//		{
//			System.out.print(rs2.getString(1));
//		}
		Class.forName("org.sqlite.JDBC");
		System.out.println("lel");	
	}
}
