z<%@ page import = "mess.utility.*"%>

<html>
	<head>
		<title>Complaints</title>
	<head>
	
	<body>
	
		<%
			String type = (String) session.getAttribute("type");
			String ID = (String) session.getAttribute("ID");
			
			if(type.equals("Admin"))
			{%>
			<h2>Complaints</h2>
			
			<%	 
				for(int i = 10; i > 0; i--)
				{
					Feedback feedback = new Feedback(i, Type.COMPLAINT);
					
					if(feedback.getDescription() != null)
					{
						%>
						<br>
						<p>-----------------------------------------------------------------------------</p>
						<p>Student ID : <%= feedback.getStudentID() %> | Feedback ID : <%= feedback.getFeedbackID() %> | Date : <%= feedback.getDate() %></p> 
						<p><%= feedback.getDescription() %></p>
						<%
					}
				}
			}
		%>
		<br>
		<button onclick = "location.href = 'home.jsp'">Home</button>
		
	</body>
</html>