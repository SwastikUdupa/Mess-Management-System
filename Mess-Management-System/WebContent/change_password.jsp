<%@ page import = "mess.client.MessAdmin"%>
<%@ page import = "mess.client.Student"%>

<html>
	<head>
		<title>Reset System</title>
	<head>
	
	<body>
	
	<%
		String ID = request.getParameter("IDcon");
		String password = request.getParameter("passcon");
		String newpass = request.getParameter("newpass");
		String repass = request.getParameter("repass");
		String type = (String) session.getAttribute("type");
		String message = "";
		String color = "#000000";
	
		if(type.equals("Admin"))
		{
			MessAdmin admin = new MessAdmin(ID);
		
			if(admin.getName()!= null && admin.getPassword().equals(password))
			{
				if(newpass.equals(repass) && newpass != "")
				{
					admin.changePassword(newpass);
					message = "Password successfully reset";
					color = "#008000";
				}
				else
				{
					message = "New passwords aren't matching. Retype!!!";
					color = "#FF0000";
				}
				
			}
			else if(password == null)
			{
				message = "";
				color = "#FF0000";
			}
			else
			{
				message = "Invalid Credentials";
				color = "#FF0000";
			}
		}
		else if(type.equals("Student"))
		{
			Student student = new Student(ID);
		
			if(student.getName()!= null && student.getPassword().equals(password))
			{
				if(newpass.equals(repass))
				{
					student.changePassword(newpass);
					message = "Password successfully reset";
					color = "#008000";
				}
				else
				{
					message = "New passwords aren't matching. Retype!!!";
					color = "#FF0000";
				}
				
			}
			else if(password == null)
			{
				message = "";
				color = "#FF0000";
			}
			else
			{
				message = "Invalid Credentials";
				color = "#FF0000";
			}
		}
		
		else
			response.sendRedirect("login.jsp");
	%>
	
		
		<h2><font color = "<%= color %>" > <%= message %> </font></h2>
		
		<h2>Confirm Password Reset</h2>
		
		<form action = "change_password.jsp" method = "POST">
			ID : <input type = "text" name = "IDcon">
			<br>
			Password : <input type = "password" name = "passcon"/>
			<br>
			New Password : <input type = "password" name = "newpass">
			<br>
			Retype New Password : <input type = "password" name = "repass"/>
			<br>
			<input type = "submit" value = "Reset"/>
		</form>
		<br>
		<button onclick = "location.href = 'home.jsp'">Home</button>

	</body>
</html>
			