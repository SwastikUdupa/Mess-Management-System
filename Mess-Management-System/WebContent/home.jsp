<html>
	<head>
		<title>Welcome <%= session.getAttribute("name")%></title>
	<head>
	
	<body>
	
		<h3>
		<% out.println("Name : " + session.getAttribute("name")); %>
		<br>
		<% out.println("ID : " + session.getAttribute("ID")); %>
		<br>
		<% out.println("Type : " + session.getAttribute("type")); %>
		<br>
		</h3>
		
		<%
		String type = (String) session.getAttribute("type");
		if(type.equals("Admin")){%>
			<%@ include file = "admin_home.jsp" %>
		<%}
		else if(type.equals("Student")){%>
			<%@ include file = "student_home.jsp" %>
		<%}
		else
			response.sendRedirect("login.jsp");
		%>
		
	
	<br><br>
	<button onclick = "location.href = 'logout.jsp'">Logout</button>
	
	</body>
</html>