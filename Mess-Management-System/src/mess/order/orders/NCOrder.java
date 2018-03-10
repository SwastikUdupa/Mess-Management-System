/**
	The NCOrder class reperesents the night mess order of a student
	There is a restriction of 10 items per order. This class
	connects to the database to store information such as orderID,
	status, price and so on.
	
	@author Sanket Rajan Gupte 2013A7PS078G
 */

package mess.order.orders;

import mess.order.Status;
import mess.menu.NCMenuItem;
import mess.utility.DBM;

public class NCOrder
{
	public String studentID;
	public int orderID;
	public NCMenuItem[] items = new NCMenuItem[10];
	public int[] quantity = new int[10];
	public Status status;
	public int orderPrice;
	public int index = -1;
	public String list;
	
	
	public NCOrder(String sid)
	{
		studentID = sid;
		status = Status.BEING_BUILT;	
	}
	
			
	public boolean addItem(NCMenuItem i, int q)
	{
		if(index < 9)
		{	
			index++;
			items[index] = i;
			quantity[index] = q;
			return true;
		}
		else
			return false;
	}
	
	public boolean removeItem(NCMenuItem i, int q)
	{
		if(index > -1)
		{
			items[index] = null;
			quantity[index] = 0;
			index--;
			return true;
		}
		else
			return false;
	}
	
	public void submit()
	{
		DBM db = new DBM("C:\\Java\\databases\\Orders");
		
		String order = new String();
		
		for(int i = 0; i <= index; i++)
			order = order + items[i].name + " x " + Integer.toString(quantity[i]) + "\n";
		
		String[] keys = {"STUDENT_ID", "ITEMS", "STATUS", "PRICE"};
		String[] values = {studentID, order, "BEING_BUILT", Integer.toString(this.getOrderPrice())};
		db.insertData("NC_ORDERS", keys, values);	
		db.closeConnection();	
	}
	
	public int getOrderPrice()
	{
		int sum = 0;
		
		for(int i = 0; i <= index; i++)
			sum = sum + quantity[i] * items[i].price;
			
		return sum;
	}
	
	public String getStudentID()
	{
		return studentID;
	}
	
	public Status getOrderStatus()
	{
		return status;
	}
	
	public void setStudentID(String s)
	{
		studentID = s;
	}
	
	public void setOrderStatus(Status s)
	{
		status = s;
	}
}