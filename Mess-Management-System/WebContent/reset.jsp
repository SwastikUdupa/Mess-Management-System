<%@ page import = "mess.client.MessAdmin"%>

<html>
	<head>
		<title>Reset System</title>
	<head>
	
	<body>
	
	<%
		String ID = request.getParameter("IDcon");
		String password = request.getParameter("passcon");
		String type = (String) session.getAttribute("type");
		String message = "";
		String color = "#000000";
	
		if(type.equals("Admin"))
		{
			MessAdmin admin = new MessAdmin(ID);
		
			if(admin.getName()!= null && admin.getPassword().equals(password))
			{
				admin.resetSystem();
				message = "System successfully reset";
				color = "#008000";
			}
			else if(password == null)
			{
				message = "";
				color = "#FF0000";
			}
			else
			{
				message = "Invalid Login Credentials";
				color = "#FF0000";
			}
		}
		else
			response.sendRedirect("login.jsp");
	%>
	
		
		<h2><font color = "<%= color %>" > <%= message %> </font></h2>
		
		<h2>Confirm Reset</h2>
		
		<form action = "reset.jsp" method = "POST">
			Email ID : <input type = "text" name = "IDcon">
			<br>
			Password : <input type = "password" name = "passcon"/>
			<br>
			<input type = "submit" value = "Reset"/>
		</form>
		<br>
		<button onclick = "location.href = 'home.jsp'">Home</button>

	</body>
</html>
			