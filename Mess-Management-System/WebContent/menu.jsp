<%@ page import = "mess.menu.Menu"%>

<html>
	<head>
		<title>Menus</title>
	</head>
	
	<body>
	
		<h2>LIST OF MENUS</h2>
		<button onclick = "location.href = 'home.jsp'">Home</button>
		<br>
		<p>------------------------------------------------------------------------</p>
		
		<%
			Menu menu = new Menu("SUNDAY_LUNCH_MENU");
			String[] list = menu.listNames();
	
			for(int i = 0; i < list.length; i++)
			{%>
				<a href = "view.jsp?menu=<%=list[i]%>"> <%= list[i] %> </a>
				<br>
			<%}
			
		%>
	</body>
</html>