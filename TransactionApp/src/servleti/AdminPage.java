package servleti;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class AdminPage extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userid = request.getParameter("userid");
		String nume = null, prenume = null;

		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
			Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/eduarddb;");
			PreparedStatement ps = con.prepareStatement("select first_name,last_name from customers where id=?");
			ps.setString(1, userid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				prenume = rs.getString(1);
				nume = rs.getString(2);
			}
			out.print("<!DOCTYPE html>");
			out.print("<html>");
			out.print("<head>");

			out.print("</head>");
			out.print("<body>");
			out.print(
					"<div style=\"border: 2px solid  ; height: 20% ;width: 99%;	background-color: #4CAF50;float: top; \">");
			out.print("Bine ai venit " + nume + " " + prenume);
			out.print("</div>");
			out.print(
					"<div style=\"border: 2px solid  ;height: 650px ;width: 33%;	background-color: #cceeff;display: inline;float: left; \">");
			out.print("Manage Customers");
			out.print("<hr>");
			out.print("Add Customer");
			out.print("<form   action=\"AddCustomer\" method=\"post\">");
			out.print(
					"<fieldset><label for=\"user_id\">User Id</label> <input type=\"text\" name=\"userid\" id=\"userid\"></fieldset>");
			out.print(
					"<fieldset><label for=\"firstname\">First Name</label> <input type=\"text\" name=\"firstname\" id=\"firstname\"> ");
			out.print(
					" <label for=\"lastname\">Last Name</label> <input type=\"text\" name=\"lastname\" id=\"lastname\"></fieldset>");
			out.print(
					"<fieldset><label for=\"birth_date\">Birth Date</label> <input type=\"text\" name=\"birth_date\" id=\"birth_date\"></fieldset>");
			out.print(
					"<fieldset><label for=\"address\">Address</label> <input type=\"textarea\" name=\"address\" id=\"address\"> ");
			out.print(
					" <label for=\"phome\">Phone</label> <input type=\"text\" name=\"phone\" id=\"phone\"></fieldset>");
			out.print(
					"<fieldset><label for=\"password\">Pass </label> <input type=\"password\"name=\"password\" id=\"password\"> ");
			out.print(
					" <label for=\"password\">Confirm Pass</label> <input type=\"password\"name=\"password2\" id=\"password2\"></fieldset>");
			out.print("<input type=\"submit\" id=\"login\" value=\"Add\">");
			out.println("</form>");
			out.print("<hr>");

			out.print("Delete Customer");
			out.print("<form   action=\"DeleteCustomer\" method=\"post\">");
			out.print(
					"<fieldset><label for=\"password\">Insert ID</label> <input type=\"text\"name=\"id\" id=\"idtodelete\"></fieldset>");

			out.print("<input type=\"submit\" id=\"DeleteCustomer\"   value=\"Delete\">");
			out.println("</form>");

			// update customer
			out.print("<hr>");
			out.print("Update Customer");
			out.print("<form   action=\"UpdateCustomer\" method=\"post\">");
			out.print(
					"<fieldset><label for=\"user_id\">User Id</label> <input type=\"text\" name=\"userid\" id=\"userid\"></fieldset>");
			out.print(
					"<fieldset><label for=\"firstname\">First Name</label> <input type=\"text\" name=\"firstname\" id=\"firstname\"> ");
			out.print(
					" <label for=\"lastname\">Last Name</label> <input type=\"text\" name=\"lastname\" id=\"lastname\"></fieldset>");
			out.print(
					"<fieldset><label for=\"birth_date\">Birth Date</label> <input type=\"text\" name=\"birth_date\" id=\"birth_date\"></fieldset>");
			out.print(
					"<fieldset><label for=\"address\">Address</label> <input type=\"textarea\" name=\"address\" id=\"address\"> ");
			out.print(
					" <label for=\"phone\">Phone</label> <input type=\"text\" name=\"phone\" id=\"phone\"></fieldset>");
			out.print(
					"<fieldset><label for=\"password\">Pass</label> <input type=\"password\"name=\"password\" id=\"txtNewPassword\"  onChange=\"checkPasswordMatch();\"> ");
			out.print(
					" <label for=\"password\">Confirm Pass</label> <input type=\"password\"name=\"password2\" id=\"txtConfirmPassword\"></fieldset>");

			out.print("<input type=\"submit\" id=\"login\" value=\"Update\">");
			out.println("</form>");

			out.print("</div>");
			/////////////////////////////////////////////////////////////
			out.print(
					"<div style=\"height: 650px ;width: 33%;	background-color: #cceeff; display: inline; float: left;border: 2px solid   \">");
			out.print("Manage Accounts");
			out.print("<hr>");
			out.print("Add Account");
			out.print("<form   action=\"AddAccount\" method=\"post\">");
			out.print(
					"<fieldset><label for=\"iban\">IBAN</label> <input type=\"text\" name=\"iban\" id=\"iban\"></fieldset>");
			out.print(
					"<fieldset><label for=\"Amount\">Amount</label> <input type=\"text\" name=\"amount\" id=\"amount\"></fieldset> ");
			out.print(
					" <fieldset><label for=\"currency\">Currency</label> <input type=\"text\" name=\"currency\" id=\"currency\"></fieldset>");
			out.print(
					"<fieldset><label for=\"customer_id\">Customer Id</label> <input type=\"text\" name=\"customerid\" id=\"customerid\"></fieldset>");

			out.print("<input type=\"submit\" id=\"login\" value=\"Add\">");
			out.println("</form>");
			out.print("<hr>");

			out.print("Delete Account");
			out.print("<form   action=\"DeleteAccount\" method=\"post\">");
			out.print(
					"<fieldset><label for=\"password\">Insert IBAN</label> <input type=\"text\"name=\"iban\" id=\"ibantodelete\"></fieldset>");

			out.print("<input type=\"submit\" id=\"DeleteCustomer\"   value=\"Delete\">");
			out.println("</form>");

			// update account
			out.print("<hr>");
			out.print("Update Account");
			out.print("<form   action=\"UpdateAccount\" method=\"post\">");
			out.print(
					"<fieldset><label for=\"iban\">IBAN</label> <input type=\"text\" name=\"iban\" id=\"iban\"></fieldset>");
			out.print(
					"<fieldset><label for=\"Amount\">Amount</label> <input type=\"text\" name=\"amount\" id=\"amount\"></fieldset> ");
			out.print(
					" <fieldset><label for=\"currency\">Currency</label> <input type=\"text\" name=\"currency\" id=\"currency\"></fieldset>");
			out.print(
					"<fieldset><label for=\"customer_id\">Customer Id</label> <input type=\"text\" name=\"customerid\" id=\"customerid\"></fieldset>");

			out.print("<input type=\"submit\" id=\"login\" value=\"Update\">");
			out.println("</form>");

			out.print("</div>");
			///////////////////////////////////////////////////////////
			out.print(
					"<div style=\"height: 650px ;width: 33%;	background-color: #cceeff; display: inline;float: left;border: 2px solid   \">");
			out.print("Make transactions");
			out.print("<hr>");
			out.print("One account transactions");
			out.print("<form   action=\"AddTransactionOneAccount\" id=\"formtransaction\" method=\"post\">");
			out.print(
					"<fieldset><label for=\"iban\">IBAN</label> <input type=\"text\" name=\"iban\" id=\"iban\"></fieldset>");
			out.print(
					"<fieldset><label for=\"Amount\">Amount</label> <input type=\"text\" name=\"amount\" id=\"amount\"></fieldset> ");
			// out.print(" <fieldset><label for=\"type\">Type</label> <input
			// type=\"text\" name=\"type\" id=\"type\"></fieldset>");
			out.print("<fieldset><label for=\"type\">Type</label> <select name=\"type\">            "
					+ "<option value=\"DEBIT\">DEBIT</option>" + "<option value=\"CREDIT\">CREDIT</option>"
					+ " </select> </fieldset>   ");
			out.print(
					"<fieldset><textarea rows=\"4\"  cols=\"50\" name=\"details\"   form=\"formtransaction\">Enter Details</textarea></fieldset>");

			out.print("<input type=\"submit\" id=\"login\" value=\"Make Transaction\">");
			out.println("</form>");
			out.print("<hr>");
			out.print("Transactions between two accounts with the same cuurency");
			out.print("<hr>");

			out.print("<form   action=\"AddTransactionTwoAccounts\" id=\"formtransaction2\" method=\"post\">");
			out.print(
					"<fieldset><label for=\"iban\">IBAN From</label> <input type=\"text\" name=\"ibanfrom\" id=\"ibanfrom\"></fieldset>");
			out.print(
					"<fieldset><label for=\"iban\">IBAN To</label> <input type=\"text\" name=\"ibanto\" id=\"ibanto\"></fieldset>");
			out.print(
					"<fieldset><label for=\"Amount\">Amount</label> <input type=\"text\" name=\"amount\" id=\"amount\"></fieldset> ");

			out.print(
					"<fieldset><textarea rows=\"4\"  cols=\"50\" name=\"details\"   form=\"formtransaction2\">Enter Details</textarea></fieldset>");

			out.print("<input type=\"submit\" id=\"login\" value=\"Make Transaction\">");
			out.println("</form>");

			out.print("</div>");
			//////////////////////////////////////////////////////////
			out.print("List information");
			out.print("<hr>See information by customer id ");
			out.print("<form   action=\"ReadById\" id=\"ReadById\" method=\"post\">");
			out.print(
					"<fieldset><label for=\"iban\">Customer Id</label> <input type=\"text\" name=\"userid\" id=\"id\">");
			out.print("<input type=\"submit\" id=\"login\" value=\"See Results\"></fieldset>");
			out.println("</form>");
			
			out.print("<hr>See information by name ");
			out.print("<form   action=\"ReadByName\" id=\"ReadByName\" method=\"post\">");
			out.print(
					"<fieldset><label for=\"iban\">First Name</label> <input type=\"text\" name=\"firstname\" id=\"id\">");
			out.print(
					"<label for=\"iban\">Last Name</label> <input type=\"text\" name=\"lastname\" id=\"id\">");
			out.print("<input type=\"submit\" id=\"login\" value=\"See Results\"></fieldset>");
			out.println("</form>");
			out.print("</body>");
			out.print("</html>");
		} catch (Exception e) {
			System.out.println(e);
		}
		out.close();
	}
}