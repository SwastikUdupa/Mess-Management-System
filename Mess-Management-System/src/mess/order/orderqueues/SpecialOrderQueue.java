/**
	The SpecialOrderQueue class contains an array of special orders
	which the admin can view and approve. The array is populated by reading
	entries from the database. Methods are provided to update the status
	of the orders being processed.
	
	@author Sanket Rajan Gupte 2013A7PS078G
 */
 
package mess.order.orderqueues; 
 
import java.sql.*;
import mess.order.Status;
import mess.order.orders.SpecialOrder;
import mess.utility.DBM;

public class SpecialOrderQueue
{
	public SpecialOrder[] order = new SpecialOrder[1000];
	int index = -1;
	
	public SpecialOrderQueue()throws SQLException
	{
		DBM db = new DBM("Orders.db");
		int i = 0;
		String sql = "SELECT * FROM `SPECIAL ORDERS`";
		ResultSet rs = db.selectData(sql);
			
		while(rs.next())
		{
			String sid = rs.getString("STUDENT_ID");
			String bdate = rs.getString("BOOKING DATE");
			String odate = rs.getString("ORDER DATE");
			int oid = rs.getInt("ORDER_ID");
			int p = rs.getInt("PRICE");
			int num = rs.getInt("NUM_PERSONS");
			String status = rs.getString("STATUS");
			String list = rs.getString("MEAL");
			
			if(index < 999)
			{
				index++;
				order[index] = new SpecialOrder(sid);
				order[index].orderID = oid;
				order[index].price = p;
				order[index].mealPreferences = list;
				order[index].bookingDate = bdate;
				order[index].orderDate = odate;
				order[index].numPersons = num;
				
				switch(status)
				{
					case "APPROVED" : order[index].approvalStatus = Status.APPROVED;
										break;
					case "DECLINED" : order[index].approvalStatus = Status.DECLINED;
										break;
					case "PENDING" : order[index].approvalStatus = Status.PENDING;
										break;
				}
			}
		}
			
		db.closeConnection();
	}
	
	public void removeOrder(int ID)
	{
		DBM db = new DBM("Orders.db");
		
		String sql = "DELETE * FROM `SPECIAL ORDERS` WHERE ORDER_ID = '" + Integer.toString(ID) + "'";
		db.modifyData(sql);
		db.closeConnection();	
	}
	
	public void setStatus(int ID, Status st)
	{
		String s = new String();
		
		switch(st)
		{
			case APPROVED : s = "APPROVED";
			case DECLINED : s = "DECLINED";
			case PENDING : s = "PENDING";
		}	
	
		DBM db = new DBM("Orders.db");
		
		String sql = "UPDATE `SPECIAL ORDERS` SET STATUS = '" + s + "' WHERE ORDER_ID = '" + Integer.toString(ID) + "'";
		db.modifyData(sql);
		db.closeConnection();
	}
	
	public void setPrice(int ID, int p)
	{
		
		DBM db = new DBM("Orders.db");
		
		String sql = "UPDATE `SPECIAL ORDERS` SET PRICE = '" + Integer.toString(p) + "' WHERE ORDER_ID = '" + Integer.toString(ID) + "'";
		db.modifyData(sql);
		db.closeConnection();
	}
}