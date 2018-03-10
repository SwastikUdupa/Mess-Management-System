<%@ page import = "mess.order.Status"%>
<%@ page import = "mess.menu.*"%>
<%@ page import = "mess.order.orders.NCOrder"%>

<html>
	<head>
		<title>NC Order</title>
	</head>
	
	<body>
		<h2>Place your order</h2>
	
		<%
			String type = (String) session.getAttribute("type");
			String ID = (String) session.getAttribute("ID");
			String itemName = request.getParameter("itemName");
			String done = request.getParameter("done");
			String itemQty = request.getParameter("itemQty");
			String AR = request.getParameter("add");
			String message = new String();
			
			Menu menu = new Menu("NC_MENU");
			NCMenuItem[] ncm = menu.getNCmenulist();
			
			int bill = 0;
			
			if(type == null)
				response.sendRedirect("login.jsp");
			else if(!type.equals("Student"))
				response.sendRedirect("home.jsp");
			
			
			if(itemName == null)
				session.setAttribute("order", new NCOrder(ID));
			else
			{
				boolean mark = false;
				boolean stat = false;
				NCOrder nco = (NCOrder)session.getAttribute("order");
				if(AR.equals("ADD"))
				{
					for(int i = 0; i < ncm.length; i++)
						if(ncm[i] != null)
							if(ncm[i].getName().equals(itemName))
							{
								mark = true;
								stat = nco.addItem(ncm[i], Integer.parseInt(itemQty));
								if(stat)
									message = itemName + " added to order";
								else
									message = "Could not add " + itemName + " to order";
								break;
							}
					
			
				}
				else if(AR.equals("REMOVE"))
				{
					for(int i = 0; i < ncm.length; i++)
						if(ncm[i] != null)
							if(ncm[i].getName().equals(itemName))
							{
								mark = true;
								stat = nco.removeItem(ncm[i], Integer.parseInt(itemQty));
								if(stat)
									message = itemName + " removed from order";
								else
									message = "Could not remove " + itemName + " from order";
								break;
							}
				}
			}
			
			%><h2>NC MENU</h2>
			<h2><%=message%></h2>
			<h3>Name -------- Cost</h3><%
			
			
			for(int i = 0; i < ncm.length; i++)
			{	
				if(ncm[i] != null)
				{
					%>
					<p> <%= ncm[i].getName()+"------------"+ncm[i].getPrice() %> </p>
					<%
				}
			}
			%><p>------------------------------------------------------------------------</p>
			<h3>Your current order :</h3><%
			
			if(itemName != null)
			{
				NCOrder nco = (NCOrder)session.getAttribute("order");
				
				if(done != null)
					if(done.equals("yes"))
					{
						nco.submit();
						response.sendRedirect("home.jsp");
					}
			
				for(int i = 0; i <= nco.index; i++)
				{%>
					<p><%= nco.items[i].name + "  x  " + Integer.toString(nco.quantity[i])%></p>
			
				<%}
				
				bill = nco.getOrderPrice();
			}
			%>
			<h3>Total bill : Rs. <%= bill%> </h3>
			<p>------------------------------------------------------------------------</p>
			<form action = "ncorder.jsp" method = "POST">
					
					Enter Item Name : <input type = "text" name = "itemName">
					Enter Item Quantity : <input type = "text" name = "itemQty">
					<br><br>
					<input type="radio" name="add" value="ADD" checked>Add Item to Order
					<input type="radio" name="add" value="REMOVE">Remove Item from Order
					
					<br><br>
					Order Completed?
					<br><br>
					<input type="radio" name="done" value="yes">Yes
					<input type="radio" name="done" value="no" checked>No
					<br><br>
					<input type = "submit" value = "Update"/>
									
				</form>
			
			
		<p>------------------------------------------------------------------------</p>
		<button onclick = "location.href = 'home.jsp'">Home</button>
	</body>
</html>