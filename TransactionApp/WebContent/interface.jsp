<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<% 
//String nume = (String) request.getAttribute("nume"); 

List<String> list =(ArrayList<String>) request.getAttribute("lista");


%>

<body>
	<div
		style="border: 2px solid; height: 20%; width: 99%; background-color: #4CAF50; float: top">
		<a href="CustomerMenu.jsp">
			Info Employees
		</a>
	</div>



	<div
		style="border: 2px solid; height: 1000px; width: 33%; background-color: #cceeff; display: inline; float: left">
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
				<select name="item" id="item">
					<%
						for (int i = 0; i < list.size(); i++) {
					%>
					<option value="<%=list.get(i)%>">

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

		<!-- 
		<p>Delete Customer</p>
		<form action=DeleteCustomer method=post>

			<fieldset>
				<input type=text name=id id=idtodelete>Insert ID
			</fieldset>

			<input type=submit id=DeleteCustomer value=Delete>
		</form>
		

        -->
        
        <p>   See and Update client information </p>
		<form action=ReadByNameCnp method=post>

			<fieldset>
				<input type=text name=cnp >CNP
			</fieldset>
			<input type=submit id=login value=Ok>
		</form>
		
		<hr>
		
		<p> See subsidary  clients </p>
		<form action=ReadBySubsidary method=post>
		<fieldset>
				<select name="itemClients" id="itemClients">
					<%
						for (int i = 0; i < list.size(); i++) {
					%>
					<option value="<%=list.get(i)%>">
						<%=list.get(i)%>
					</option>
					<%
						}
					%>
				</select>
		</fieldset>
		
		<input type=submit id=users value=Ok>
		</form>
		
		

		<hr>
		<!--  
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
				<select name="itemUpdate" id="itemUpdate">
					<%
						for (int i = 0; i < list.size(); i++) {
					%>
					<option value="<%=list.get(i)%>">
						<%=list.get(i)%>
					</option>
					<%
						}
					%>
				</select>
			</fieldset>

			<input type=submit id=login value=Update>
		</form>
		
		-->
	</div>


	<div
		style="border: 2px solid; height: 1000px; width: 33%; background-color: #cceeff; display: inline; float: left">
		<p>Manage Accounts</p>
		<hr>
			<p> Add Account<p>
		<form action=AddAccount method=post>

	
			<fieldset>
				<input type=text name=amount id=amount>Amount
			</fieldset>

			<fieldset>
				
				<select name=currency id=currency>
					<option value=EUR>EUR</option>
					<option value=USD>USD</option>
					<option value=RON>RON</option>
				</select>
				Currency
			</fieldset>

			<fieldset>
				<input type=text name=cnp id=cnp>CNP
			</fieldset>

			<input type=submit id=login value=Add>
		</form>
		
		

		<hr>
		Update Account
		<form action=UpdateAccount method=post>

			
			<fieldset>
				<input type=text name=iban id=iban>IBAN
			</fieldset>
			<fieldset>
				<input type=text name=amount id=amount> Amount
			</fieldset>

			<input type=submit id=login value=Update>
		</form>

		<hr>
		<p>Manage Cards</p>
		<hr>
		
			<p> Add Card<p>
		<form action=AddCard method=post>

			<fieldset>
				<input type=text name=cardn id=card>Card Number
			</fieldset>

			<fieldset>
				<input type=text name=cardexp id=cardexp>Expire
			</fieldset>

			<fieldset>
				<input type=text name=cardcrcode id=cardcrcode>Cr Code
			</fieldset>

			<fieldset>
				<select name=type>
					<option value=DEBIT>DEBIT</option>
					<option value=CREDIT>CREDIT</option>
				</select>Type
			</fieldset>
			
			<fieldset>
				<input type=text name=cardiban id=cardiban>Card IBAN
			</fieldset>

			<input type=submit id=card value=Add>
		</form>
		
		<hr>
		
			<p> Update Card<p>
		<form action=UpdateCard method=post>
		
			<fieldset>
				<input type=text name=cardnmb id=cardnmb>Card Number
			</fieldset>
			
			<fieldset>
				<input type=text name=cardexp id=cardexp>Expire
			</fieldset>
			
			<fieldset>
				<input type=text name=cardcrcode id=cardcrcode>Cr Code
			</fieldset>
			
			


			<input type=submit id=card value=Ok>
		</form>
		


	</div>



	<div
		style="border: 2px solid; height: 1000px; width: 33%; background-color: #cceeff; display: inline; float: left">
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
				<input type=text name=type id=type>
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
				<input type=text name=ibanfrom id=ibanfrom>IBAN From
			</fieldset>

			<fieldset>
				<input type=text name=ibanto id=ibanto>IBAN To
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