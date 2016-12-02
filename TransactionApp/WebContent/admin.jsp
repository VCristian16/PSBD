<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


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
Map<String,String> data=(HashMap<String,String>) request.getAttribute("jsonString");
//out.print(data);
String Name=data.get("fname");
String CNP =data.get("cnp");
String Phone=data.get("phone");
String Address=data.get("adress");
String subsd=data.get("subs");
String userId=data.get("userid");
String iban=data.get("iban");
String currency=data.get("currency");
String amount=data.get("amount");

List<Object> list= (ArrayList<Object>) request.getAttribute("listObj");



%>

<h2>Client Info</h2>

<form   action="UpdateCustomer" method="post">

<table >
  <thead>
    <tr>
      <th>User ID</th>
      <th>Name</th>
      <th>CNP</th>
      <th>Phone</th>
      <th>Address</th>
      <th>Subsidiary</th>
      <th>IBAN</th>
      <th>Currency</th>
      <th>Amount</th>
    </tr>
  </thead>
			<tbody>
				<tr>
					<td><%=userId%></td>
					<td><input size="10" type="text" name="firstname"
						value="<%=Name%>"></td>
					<td><input size="10" type="text" name="cnp" 
						value="<%=CNP%>"></td>
					<td><input size="10" type="text" name="phone"
						value="<%=Phone%>"></td>
					<td><input size="10" type="text" name="address"
						value="<%=Address%>"></td>
					<td><input size="10" type="text" name="subsidary_id"
						value="<%=subsd%>"></td>
					<td><input size="10" type="text" name="iban" value="<%=iban%>"></td>
					<td><input size="10" type="text" name="currency"
						value="<%=currency%>"></td>
					<td><input size="10" type="text" name="amount"
						value="<%=amount%>"></td>

				</tr>
			</tbody>
		</table>
 <button type="submit" name="update"  style=" margin-left: 800px; opacity: 1;">Update</button>
</form>


<form   action="AdminPage" method="post">
<button type="submit" name="update"  style=" margin-left: 800px; opacity: 1;">Home</button>
</form>

</body>
</html>
