<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<style>
table {
	border-collapse: collapse;
	width: 40;
}

th, td {
	text-align: left;
	padding: 5px;
}

td[contenteditable=true]

tr:nth-child(even) {
	background-color: #f2f2f2
}

th {
	background-color: #4CAF50;
	color: white;
}
</style>

</head>
<body>

	<%
	Map<String,String> data=(HashMap<String,String>) request.getAttribute("jsonString");
	List<String[]> list= (ArrayList<String[]>) request.getAttribute("listObj");
	List<String[]> listCard= (ArrayList<String[]>) request.getAttribute("listCard");

	String Name = data.get("fname");
	String CNP = data.get("cnp");
	String Phone = data.get("phone");
	String Address = data.get("adress");
	String subsd = data.get("subs");
	String userId = data.get("userid");
	String iban = data.get("iban");
	String currency = data.get("currency");
	String amount = data.get("amount");

%>

	<h2 align="center">Client Info</h2>

	<form action="UpdateCustomer" method="post">
		<table align="center">
			<thead>
				<tr>
					<th>Name</th>
					<th>CNP</th>
					<th>Phone</th>
					<th>Address</th>
					<th>Subsidiary</th>
				</tr>
		
			</thead>
			<tbody>
				<tr>
					<input size="10" type="hidden" name="userid" value="<%=userId%>">
					<td><input size="10" type="text" name="firstname"
						value="<%=Name%>"></td>
					<td><input size="10" type="text" name="cnp" value="<%=CNP%>"></td>
					<td><input size="10" type="text" name="phone"
						value="<%=Phone%>"></td>
					<td><input size="10" type="text" name="address"
						value="<%=Address%>"></td>
					<td><input size="10" type="text" name="subsidary_id"
						value="<%=subsd%>"></td>
				</tr>
				
			</tbody>
			
			  <thead>
					<tr>
					<th>IBAN</th>
					<th>Currency</th>
					<th>Amount</th>
				</tr>	
			</thead>
			<tbody>
				<tr>
					<%
						for (int i = 0; i < list.size(); i++) {
							String obj[]=list.get(i);							
					%>

					<td><%=obj[0]%></td>
					<td><%=obj[1]%></td>
					<td><%=obj[2]%></td>

				</tr>
				<%}%>
			</tbody>
			
			
			
			 <thead>
					<tr>
					<th>CARD_NUMBER</th>
					<th>EXP_DATE</th>
					<th>CR2_CODE</th>
					<th>TYPE</th>
					<th>IBAN</th>
				</tr>	
			</thead>
			<tbody>
				<tr>
					<%
						for (int i = 0; i < listCard.size(); i++) {
							String obj[]=listCard.get(i);							
					%>

					<td><%=obj[0]%></td>
					<td><%=obj[1]%></td>
					<td><%=obj[2]%></td>
					<td><%=obj[3]%></td>
					<td><%=obj[4]%></td>
				</tr>
				<%}%>
			</tbody>
			
		</table>
		<p>Options:</p>
		<button type="submit" name="update"
			style="margin-left: 70px; opacity: 1;">Update</button>


	</form>


	<form action="AdminPage" method="post">

		<button type="submit" name="update"
			style="margin-left: 70px; opacity: 1;">Home</button>

	</form>
</body>
</html>
