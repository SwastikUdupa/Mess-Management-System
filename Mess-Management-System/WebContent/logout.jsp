<html>
	<head>
		<title>Logging out...</title>
	</head>
	
	<body>
		<%
			String type = (String) session.getAttribute("type");
			
			session.setAttribute("ID", null);
			session.setAttribute("name", null);
			session.setAttribute("type", null);
			session.setAttribute("Response", "Logout");
			
			if(type == null)
			{
				session.setAttribute("Response", null);
				response.sendRedirect("login.jsp");
			}
			else if(type.equals("Student"))
				response.sendRedirect("login.jsp");
			else if(type.equals("Admin"))
				response.sendRedirect("admin.jsp");
			else
			{
				session.setAttribute("Response", null);
				response.sendRedirect("home.jsp");
			}
		%>
	</body>
</html>