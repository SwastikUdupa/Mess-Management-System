<%@ page import = "mess.utility.*"%>

<html>
	<head>
		<title>Feedback / Complaints</title>
	<head>
	
	<body>
	
		<%
			String type = (String) session.getAttribute("type");
			String ID = (String) session.getAttribute("ID");
			String comment = request.getParameter("comment");
			String ftype = request.getParameter("ftype");
			String message = new String();
						
			if(comment == null)
				comment = "";
				
			if(type == null)
				response.sendRedirect("login.jsp");
				
			else if(type.equals("Student"))
			{
				if(!comment.equals(""))
				{
					Feedback feedback = null;
					Type t = null;
				
					if(ftype.equals("Feedback"))
						t = Type.FEEDBACK; 
					else if(ftype.equals("Complaint"))
						t = Type.COMPLAINT; 
										
					feedback = new Feedback(t);
					feedback.setStudentID(ID);
					feedback.setDescription(comment);
					int code = feedback.submit();
					
					message = "Feedback Submitted";	
				}
				else
					message = "Please enter some Feedback!!!";
		%>
		
		<h2>Feedback</h2>
		
		<h2> <%= message%> </h2>
		
		<form action = "feedback.jsp" method = "POST">
			<textarea cols = "50" rows = "5" name = "comment"></textarea>
			<br><br>
			<input type="radio" name="ftype" value="Feedback" checked>Feedback
			<input type="radio" name="ftype" value="Complaint">Complaint
			<br><br>
			<input type = "submit" value = "Submit"/>
		</form>
		
		<%	}
			else if(type.equals("Admin"))
			{%>
			<h2>Feedback</h2>
			
			<%	 
				for(int i = 10; i > 0; i--)
				{
					Feedback feedback = new Feedback(i, Type.FEEDBACK);
					
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