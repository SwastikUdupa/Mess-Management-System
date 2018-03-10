<%@ page import = "mess.menu.*"%>

<html>
	<head>
		<title>Edit Menu</title>
	</head>
	
	<body>
		<%
			String m = request.getParameter("menu");
			String itemName = request.getParameter("itemName");
			String itemPrice = request.getParameter("itemPrice");
			String AR = request.getParameter("add");
			String message = new String();
				
			Menu menu = new Menu(m);
			String[] list = menu.listNames();
			boolean flag = false;
			String type = (String) session.getAttribute("type");
			
			if(type == null)
				response.sendRedirect("login.jsp");
				
			else if(!type.equals("Admin"))
				response.sendRedirect("login.jsp");
			
			for(int i= 0; i< list.length; i++)
			{
				if(list[i].equals(m))
					flag = true;
			}
			
			if(itemName != null)
			{
				if(m.equals("NC_MENU"))
				{
					if(AR.equals("ADD"))
					{
						NCMenuItem ncm1 = NCMenuItem.loadNCMenuItem(itemName);
						if(ncm1.getName().equals(""))
						{
							NCMenuItem ncm2 = new NCMenuItem(itemName, Integer.parseInt(itemPrice), true);
							ncm2.createNCMenuItem();
							menu.addNCItem(ncm2);
						}
						else
							menu.addNCItem(ncm1);
						message = itemName + " added to NC Menu";
						
					}
					else if(AR.equals("REMOVE"))
					{
						NCMenuItem ncm1 = NCMenuItem.loadNCMenuItem(itemName);
						menu.removeNCItem(ncm1);
						message = itemName + " removed from NC Menu";
					}
				}
				else if(flag)
				{
					if(AR.equals("ADD"))
					{
						MenuItem m1 = MenuItem.loadMenuItem(itemName);
						if(m1.getName().equals(""))
						{
							MenuItem m2 = new MenuItem(itemName);
							m2.createMenuItem();
							menu.addMessItem(m2);
						}
						else
							menu.addMessItem(m1);
						message = itemName + " added to " + menu.getName();
						
					}
					else if(AR.equals("REMOVE"))
					{
						MenuItem m1 = MenuItem.loadMenuItem(itemName);
						menu.removeMessItem(m1);
						message = itemName + " removed from " + menu.getName();
					}
				
				}	
			}
			
			if(m.equals("NC_MENU"))
			{%>
			
			<h2>NC MENU</h2>
			<h2><%=message%></h2>
			<h3>Name -------- Cost</h3>
			
				<%NCMenuItem[] ncm = menu.getNCmenulist();
				for(int i = 0; i < ncm.length; i++)
				{
					if(ncm[i] != null)
					{
						%>
						<p> <%= ncm[i].getName()+"------------"+ncm[i].getPrice() %> </p>
						<%
					}
				}
				
				%>
				<br>
				<p>-----------------------------------------------------------------------</p>
				<form action = "edit.jsp" method = "POST">
					
					Enter Item Name : <input type = "text" name = "itemName">
					Enter Item Price : <input type = "text" name = "itemPrice">
					<br><br>
					<input type="radio" name="add" value="ADD" checked>Add Item to Menu
					<input type="radio" name="add" value="REMOVE">Remove Item from Menu
					<input type="hidden" name="menu"  value = "<%=m%>" />
					<br><br>
					<input type = "submit" value = "Change Menu"/>
									
				</form>
				<p>------------------------------------------------------------------------</p>
				<%
				
			}
			else if(flag)
			{%>
			
				<h2> <%= m%> </h2>
				<h2><%=message%></h2>
				<%MenuItem[] mi = menu.getmenulist();
				for(int i = 0; i< mi.length; i++)
				{
					if(mi[i] != null)
					{
						%>
						<p> <%= mi[i].getName()%> </p>
						<%
					}
				}
				
				%>
				<br>
				<p>------------------------------------------------------------------------</p>
				<form action = "edit.jsp" method = "POST">
					
					Enter Item Name : <input type = "text" name = "itemName">
					<br><br>
					<input type="radio" name="add" value="ADD" checked>Add Item to Menu
					<input type="radio" name="add" value="REMOVE">Remove Item from Menu
					<input type="hidden" name="menu"  value = "<%=m%>" />
					<br><br>
					<input type = "submit" value = "Change Menu"/>
									
				</form>
				<p>------------------------------------------------------------------------</p>
				<%
				
			}
		%>
		<button onclick = "location.href = 'home.jsp'">Home</button>
	</body>
</html>