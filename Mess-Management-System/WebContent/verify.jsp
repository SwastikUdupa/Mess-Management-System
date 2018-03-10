<%@ page import = "mess.client.Student"%>
<%@ page import = "mess.client.MessAdmin"%>
<html>
	<head>
		<title>Verifying...</title>
	</head>
	
	<body>
		<%
		String ID = request.getParameter("ID");
		String password = request.getParameter("pass");
		String type = request.getParameter("auth");
		
		if(type == null)
			response.sendRedirect("login.jsp");
		
		else if(type.equals("Student"))
		{
			Student student = new Student(ID);
			
			if(student.getName() == null)
			{
				session.setAttribute("Response", "Invalid ID");
				response.sendRedirect("login.jsp");
			}
			else if(!student.getPassword().equals(password))
			{
				session.setAttribute("Response", "Invalid Password");
				response.sendRedirect("login.jsp");
			}	
			else
			{
				session.setAttribute("ID", ID);
				session.setAttribute("name", student.getName());
				session.setAttribute("type", type);
				session.setAttribute("Response", "Valid");
				response.sendRedirect("home.jsp");
			}	
		}
		
		else if(type.equals("Admin"))
		{
			MessAdmin admin = new MessAdmin(ID);
			
			if(admin.getName() == null)
			{
				session.setAttribute("Response", "Invalid ID");
				response.sendRedirect("admin.jsp");
			}
			else if(!admin.getPassword().equals(password))
			{
				session.setAttribute("Response", "Invalid Password");
				response.sendRedirect("admin.jsp");
			}	
			else
			{
				session.setAttribute("ID", ID);
				session.setAttribute("name", admin.getName());
				session.setAttribute("type", type);
				session.setAttribute("Response", "Valid");
				response.sendRedirect("home.jsp");
			}	
		}
		
		
		
		
		%>
	</body>
</html>