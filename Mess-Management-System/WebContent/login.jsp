<html>
	<head>
		<title>Mess Login Portal</title>
	</head>
	
	<body>
			
		<% 
		String message = new String();
		String reply = (String) session.getAttribute("Response");
		
		if(reply == null)
			message = "Welcome to NITK Mess Management Portal";
		else if(reply.equals("Invalid ID")) 
			message = "Invalid ID. Please enter a valid ID";
		else if(reply.equals("Invalid Password"))
			message = "Invalid Password. Please enter correct password";
		else if(reply.equals("Valid"))
			response.sendRedirect("home.jsp");
		else if(reply.equals("Logout"))
		{
			message = "You have successfully logged out";
			session.setAttribute("Response", null);
		}
		%>
		
		<h2><%= message%></h2>
		
		<form action = "verify.jsp" method = "POST">
			ID Number : <input type = "text" name = "ID">
			<br>
			Password : <input type = "password" name = "pass"/>
			<br>
			<input type = "submit" value = "Login"/>
			<input type = "hidden" name = "auth"  value = "Student" />
		</form>
	</body>
</html>