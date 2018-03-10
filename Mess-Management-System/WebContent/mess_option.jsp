<%@ page import = "java.util.Date"%>
<%@ page import = "java.util.Calendar"%>
<%@ page import = "java.util.TimeZone"%>
<%@ page import = "mess.client.Student"%>

<html>
	<head>
		<title>Mess Option</title>
	</head>
		
	<body>
		<%
			Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
			int day = calendar.get(Calendar.DATE);
			boolean check = false;
			String message = new String();
			String choice = request.getParameter("choice");
			String ID = (String) session.getAttribute("ID");
			String type = (String) session.getAttribute("type");
			
			if(day >= 20 && day <=25)
				check = true;
				
			if(choice != null)
				message = "You have chosen " + choice + " Mess for the coming month";
			
			if(type == null)
				response.sendRedirect("login.jsp");
			
			else if(!type.equals("Student"))
				response.sendRedirect("home.jsp");
			
			else if(check && type.equals("Student"))
			{
				Student s = new Student(ID);
				
				if(choice != null)
					s.changeMessOption(choice);			
			
				%>
					<h2>Mess Option is Open</h2>
					<h2>Vote for your favourite Mess</h2>
					<h2><%=message%></h2>
					
					<form action = "mess_option.jsp" method = "POST">
						
						<input type="radio" name="choice" value="A" checked>A Mess
						<br><br>
						<input type="radio" name="choice" value="C">C Mess
						<br><br>
						<input type = "submit" value = "Submit Choice"/>
					
					</form>
				<%
			}
			else
			{	%>
					<h2>Sorry Mess Option is Closed</h2>
				<%
			}
		%>
		<p>-----------------------------------------------------------------------------</p>
		<button onclick = "location.href = 'home.jsp'">Home</button>
	</body>
</html>	