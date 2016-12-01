<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<% String nume = (String) request.getAttribute("nume"); 
   List<String> list =(ArrayList<String>) request.getAttribute("lista");

%>

<body>
	<div style= "border: 2px solid	;height: 20% ;width: 99%	;background-color: #4CAF50;float:top ">
	<p> Bine ai venit <%= nume %> <p>
		
	</div>



	<div
		style="border: 2px solid; height: 900px; width: 33%; background-color: #cceeff; display: inline; float: left">
		<p>Manage Customers</p>
		<hr>
		<p>Add Customer</p>
		<form action=AddCustomer method=post>
			<fieldset>
				<input type=text name=firstname id=firstname> Name
			</fieldset>
			<fieldset>
				<input type=text name=cnp id=cnp> CNP
			</fieldset>
			<fieldset>
				<input type=text name=address id=address> Address
			</fieldset>
			<fieldset>
				<input type=text name=phone id=phone> Phone
			</fieldset>
			<fieldset>
				<select>
					<%
						for (int i = 0; i < list.size(); i++) {
					%>
					<option>
						<%=list.get(i)%>
					</option>
					<%
						}
					%>
				</select>

			</fieldset>

			<input type=submit id=login value=Add>
		</form>
		<hr>

		<p>Delete Customer</p>
		<form action=DeleteCustomer method=post>

			<fieldset>
				<input type=text name=id id=idtodelete>Insert ID
			</fieldset>

			<input type=submit id=DeleteCustomer value=Delete>
		</form>




		<hr>
		Update Customer
		<form action=UpdateCustomer method=post>

			<fieldset>
				<input type=text name=firstname id=firstname> Name
			</fieldset>
			<fieldset>
				<input type=text name=cnp id=cnp> CNP
			</fieldset>
			<fieldset>
				<input type=text name=address id=address> Address
			</fieldset>
			<fieldset>
				<input type=text name=phone id=phone> Phone
			</fieldset>
			<fieldset>
				<select>
					<%
						for (int i = 0; i < list.size(); i++) {
					%>
					<option>
						<%=list.get(i)%>
					</option>
					<%
						}
					%>
				</select>

			</fieldset>

			<input type=submit id=login value=Update>
		</form>
	</div>


	<div
		style="border: 2px solid; height: 900px; width: 33%; background-color: #cceeff; display: inline; float: left">
		<p>List information</p>
        <hr>
		<p>See information by customer id</p>
		<form action=ReadById id=ReadById method=post>

			<fieldset>
				<input type=text name=userid id=id>Customer Id
			</fieldset>
			<input type=submit id=login value=SeeResults>
		</form>

		<hr>
		See information by name
		<form action=ReadByName id=ReadByName method=post>

			<fieldset>
				<input type=text name=firstname id=id>Name
			</fieldset>
			<input type=submit id=login value=SeeResults>
		</form>

		<fieldset>Manage Accounts</fieldset>
		<br> Add Account
		<form action=AddAccount method=post>

			<fieldset>
				<input type=text name=iban id=iban>IBAN
			</fieldset>

			<fieldset>
				<input type=text name=amount id=amount>Amount
			</fieldset>

			<fieldset>
				<input type=text name=currency id=currency>Currency
			</fieldset>

			<fieldset>
				<input type=text name=customerid id=customerid>Customer Id
			</fieldset>

			<input type=submit id=login value=Add>
		</form>
		<hr>

		Delete Account
		<form action=DeleteAccount method=post>
			<fieldset>
				<input type=text name=iban id=ibantodelete>Insert IBAN
			</fieldset>
			<input type=submit id=DeleteCustomer value=Delete>
		</form>

		<hr>
		Update Account
		<form action=UpdateAccount method=post>

			<fieldset>
				<input type=text name=iban id=iban>IBAN
			</fieldset>

			<fieldset>
				<input type=text name=amount id=amount>Amount
			</fieldset>

			<fieldset>
				<label for=currency></label> <input type=text name=currency
					id=currency>Currency
			</fieldset>

			<fieldset>
				<input type=text name=customerid id=customerid>Customer Id
			</fieldset>

			<input type=submit id=login value=Update>
		</form>


	</div>



	<div
		style="border: 2px solid; height: 900px; width: 33%; background-color: #cceeff; display: inline; float: left">
		<p>Make transactions</p>
		<hr>
		<p>One account transactions</p>
		<form action=AddTransactionOneAccount id=formtransaction method=post>
			<fieldset>
				 <input type=text name=iban id=iban>IBAN
			</fieldset>

			<fieldset>
				 <input type=text name=amount id=amount>Amount
			</fieldset>
			<fieldset>
				<input  type=text name=type id=type>
			</fieldset>
			<fieldset>
				 <select name=type> 
					<option value=DEBIT>DEBIT</option> 
					<option value=CREDIT>CREDIT</option> 
				</select>
			</fieldset>

			<fieldset>
				<textarea rows=4 cols=50 name=details form=formtransaction>Enter Details</textarea>
			</fieldset>

			<input type=submit id=login value=MakeTransaction>
		</form>
		<hr>
		Transactions between two accounts with the same cuurency
		<hr>

		<form action=AddTransactionTwoAccounts id=formtransaction2 method=post>
			<fieldset>
				<input type=text name=ibanfrom
					id=ibanfrom>IBAN From
			</fieldset>

			<fieldset>
				<input type=text name=ibanto	id=ibanto>IBAN To
			</fieldset>

			<fieldset>
				 <input type=text name=amount id=amount>Amount
			</fieldset>

			<fieldset>
				<textarea rows=4 cols=50 name=details form=formtransaction2>Enter Details</textarea>
			</fieldset>

			<input type=submit id=login value=MakeTransaction>
		</form>
		</div>
</body>
</html>