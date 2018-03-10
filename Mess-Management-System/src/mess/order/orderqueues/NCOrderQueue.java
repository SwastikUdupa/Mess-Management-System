/**
	The NCOrderQueue class contains an array of NCOrder objects.
	These objects are loaded from the database and can be used to keep track of
	the orders. The status of each order can also be changed
	
 */

package mess.order.orderqueues;

import java.sql.*;
import mess.order.Status;
import mess.order.orders.NCOrder;
import mess.utility.DBM;

public class NCOrderQueue {
	public NCOrder[] order = new NCOrder[1000];
	int index = -1;

	public NCOrderQueue() throws SQLException {
		DBM db = new DBM("Orders.db");
		int i = 0;
		String sql = "SELECT * FROM NC ORDERS";
		ResultSet rs = db.selectData(sql);

		while (rs.next()) {
			String sid = rs.getString("STUDENT_ID");
			int oid = rs.getInt("ORDER_ID");
			int p = rs.getInt("PRICE");
			String status = rs.getString("STATUS");
			String list = rs.getString("ITEMS");

			if (index < 999) {
				index++;
				order[index] = new NCOrder(sid);
				order[index].orderID = oid;
				order[index].orderPrice = p;
				order[index].list = list;

				switch (status) {
				case "BEING_BUILT":
					order[index].status = Status.BEING_BUILT;
				case "READY":
					order[index].status = Status.READY;
				case "DELIVERED":
					order[index].status = Status.DELIVERED;
				}
			}
		}

		db.closeConnection();
	}

	public void removeOrder(int ID) {
		DBM db = new DBM("Orders.db");

		String sql = "DELETE * FROM NC ORDERS WHERE ORDER_ID = '" + Integer.toString(ID) + "'";
		db.modifyData(sql);
		db.closeConnection();
	}

	public void setStatus(int ID, Status st) 
	{
		String s = new String();

		switch (st) {
		case BEING_BUILT:
			s = "BEING BUILT";
			break;
		case DELIVERED:
			s = "DELIVERED";
			break;
		case READY:
			s = "READY";
			break;
		}

		DBM db = new DBM("Orders.db");

		String sql = "UPDATE NC ORDERS SET STATUS = '" + s + "' WHERE ORDER_ID = '" + Integer.toString(ID) + "'";
		db.modifyData(sql);
		db.closeConnection();
	}
}