/**
	The SpecialOrder class represents orders for special occasions
	which can be booked by various clubs and departments.
	It contains information about the nature of the order including the dates,
	number of people, meal preferences and approval status
	
	@author Sanket Rajan Gupte 2013A7PS078G
 */

package mess.order.orders;

import mess.order.Status;
import mess.utility.DBM;
 
public class SpecialOrder
{
	public int orderID;
	public String studentID;
	public String bookingDate;
	public String orderDate;
	public String mealPreferences;
	public int numPersons;
	public Status approvalStatus;
	public int price;
	
	public SpecialOrder(String sid)
	{
		studentID = sid;
		approvalStatus = Status.PENDING;
	}
	
	public void submit()
	{	
		DBM db = new DBM("Orders.db");
				
		String[] keys = {"ORDER_ID", "STUDENT_ID", "BOOKING_DATE", "ORDER_DATE", "MEAL","NUM_PERSONS", "STATUS", "PRICE"};
		String[] values = {Integer.toString(orderID), studentID, bookingDate, orderDate, mealPreferences, Integer.toString(numPersons),"PENDING","0"};
		db.insertData("SPECIAL ORDERS", keys, values);	
		db.closeConnection();	
	
	}
		
	public void setOrderID(int i)
	{
		orderID = i;
	}
	
	public void setStudentID(String s)
	{
		studentID = s;
	}
	
	public void setBookingDate(String s)
	{
		bookingDate = s;
	}
	
	public void setOrderDate(String s)
	{
		orderDate = s;
	}
	
	public void setMealPreferences(String s)
	{
		mealPreferences = s;
	}
	
	public void setNumPersons(int n)
	{
		numPersons = n;
	}
	
	public void setApprovalStatus(Status s)
	{
		approvalStatus = s;
	}
	
	public int getOrderID()
	{
		return orderID;
	}
	
	public String getStudentID()
	{
		return studentID;
	}
	
	public String getOrderDate()
	{
		return orderDate;
	}
	
	public String getBookingDate()
	{
		return bookingDate;
	}
	
	public String getMealPreferences()
	{
		return mealPreferences;
	}
	
	public int getNumPersons()
	{
		return numPersons;
	}
	
	public Status getApprovalStatus()
	{
		return approvalStatus;
	}
	
}