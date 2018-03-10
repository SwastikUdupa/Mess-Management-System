<%@ page import = "mess.menu.*"%>

<html>
	<head>
		<% String m = request.getParameter("menu"); %>
		<title> <%= m%> </title>
	</head>
	
	<body>
		<%
			Menu menu = new Menu(m);
			
			String[] list = menu.listNames();
			boolean flag = false;
			String type = (String) session.getAttribute("type");
			
			if(type == null)
				type = "";
			
			for(int i= 0; i< list.length; i++)
			{
				if(list[i].equals(m))
					flag = true;
			}
						
			if(m.equals("NC_MENU"))
			{%>
			
			<h2>NC MENU</h2>
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
				}%>
				<br>
				<p>------------------------------------------------------------------------</p>
				<%
				
				if(type.equals("Admin"))
				{%>
					<button onclick = "location.href = 'edit.jsp?menu=<%=m%>'">Edit Menu</button>
				<%}
				
			}
			else if(flag)
			{%>
			
				<h2> <%= m%> </h2>
			
				<%MenuItem[] mi = menu.getmenulist();
				for(int i = 0; i< mi.length; i++)
				{
					if(mi[i] != null)
					{
						%>
						<p> <%= mi[i].getName()%> </p>
						<%
					}
				}%>
				<br>
				<p>------------------------------------------------------------------------</p>
				<%
				if(type.equals("Admin"))
				{%>
					<button onclick = "location.href = 'edit.jsp?menu=<%=m%>'">Edit Menu</button>
				<%}
			}		
				
			
			else
				out.println("not in list");
		%>
		<br><br>
		<button onclick = "location.href = 'home.jsp'">Home</button>
	</body>
</html>