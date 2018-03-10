<%@ page import = "mess.client.Student"%>
<%@ page import = "mess.client.MessAdmin"%>

<html>
	<head>
		<title>Scan Card</title>
	</head>
	
	<body>
		<h2>Enter ID Number</h2>
		
		<%
			String type = (String) session.getAttribute("type");
			String message = "";
			String name = "";
			String id = "";
			String color = "#000000";
			
			if(type.equals("Admin"))
			{
				String ID = request.getParameter("ID");
				
				if(ID != null)
				{
					MessAdmin admin = new MessAdmin((String) session.getAttribute("ID"));
					Student student = new Student(ID);
					
					if(student.getMessOption() == admin.getMess())
					{
						int code = admin.swipe(ID);
					
						if(code == 0)
						{
							message = "Student details successfully updated";
							name = student.getName();
							id = ID;
							color = "#008000";
						}
						else if(code == 1)
						{
							message = "Student has already eaten";
							name = student.getName();
							id = ID;
							color = "#FF0000";
						}
					}
					else
					{
						if(student.getID() == null)
						{
							message = "Invalid ID number";
							name = "Unkown";
							id = "Invalid";
							color = "#FF0000";
						}
						else
						{
							message = "Sorry Wrong Mess!!!";
							name = student.getName();
							id = ID;
							color = "#FF0000";
						}
					}
				}
			}
			else
				response.sendRedirect("login.jsp");
		%>
		
		<h2><font color = "<%= color %>" > <%= message %> </font></h2>
		<p>Name : <%= name%><p>
		<p>ID : <%= id%><p>
		<form action = "scancard.jsp" method = "POST">
			ID Number : <input type = "text" name = "ID"><br><br>
			<input type = "submit" value = "Submit"/>
		</form>
		<br>
		<button onclick = "location.href = 'home.jsp'">Home</button>
	</body>
</html>