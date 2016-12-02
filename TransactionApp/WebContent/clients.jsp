<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
table {
    border-collapse: collapse;
    width:40;
}

th, td {
    text-align: left;
    padding: 5px;
}

td[contenteditable=true]

tr:nth-child(even){background-color: #f2f2f2}

th {
    background-color: #4CAF50;
    color: white;
}
</style>

</head>
<body>
<% 


List<String[]> list= (ArrayList<String[]>) request.getAttribute("listObj");



%>

<table >
  <thead>
    <tr>
      <th>Client_ID</th>
      <th>Name</th>
      <th>CNP</th>
      <th>Phone</th>
      <th>Address</th>
    </tr>
  </thead>
			<tbody>
			        <%
						for (int i = 0; i < list.size(); i++) {
							String obj[]=list.get(i);
							
					%>
			<tr>
				
					<td><%=obj[0]%></td>
					<td><%=obj[1] %></td>
					<td><%=obj[2] %></td>
					<td><%=obj[3] %></td>
					<td><%=obj[4] %></td>
					
					
					
					

			</tr>
				<%}%>
				
			</tbody>
		</table>

</body>
</html>