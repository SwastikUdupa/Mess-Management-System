<%@ page import = "mess.order.orders.NCOrder"%>
<h1>NC Orders</h1>
<h3>
		<% out.println("Name : " + session.getAttribute("name")); %>
		<br>
		<% out.println("ID : " + session.getAttribute("ID")); %>
		<br>
		<% out.println("Type : " + session.getAttribute("type")); %>
		<br>
		<br>---------------------------------------------------<br>
</h3>
<%
	NCOrder nco = (NCOrder)session.getAttribute("order");
	for(int i = 0; i <= nco.index; i++)
	{%>
		Status:<%= nco.getOrderStatus() %>
		<br>
		<p><%= nco.items[i].name + "  x  " + Integer.toString(nco.quantity[i])%></p>
	<%}
%>
<br>---------------------------------------------------<br>
<b> Current Order </b>
<br>---------------------------------------------------<br>
<button onclick = "location.href = 'home.jsp'">Home</button>

