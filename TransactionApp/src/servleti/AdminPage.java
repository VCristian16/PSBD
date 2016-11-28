package servleti;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
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
		String nume = null;
		try {
			Class.forName ("oracle.jdbc.driver.OracleDriver");		
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			PreparedStatement ps = con.prepareStatement("select name from clients where client_id=?");
			ps.setString(1, userid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {				
				nume = rs.getString(1);
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		request.setAttribute("nume",nume);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin.jsp");
		dispatcher.forward(request, response);
		*/
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.print("<head>");
		out.print("</head>");
		
		
		out.print("<body>");
		
		out.print(
				"<div style=\"border: 2px solid  ; height: 20% ;width: 99%;	background-color: #4CAF50;float: top; \">");
		out.print("Bine ai venit " + nume );
		out.print("</div>");
		
		
		out.print(
				"<div style=\"border: 2px solid  ;height: 770px ;width: 33%;	background-color: #cceeff;display: inline;float: left; \">");
		out.print("Manage Customers");
		out.print("<hr>");
		out.print("Add Customer");		
		out.print("<form   action=\"AddCustomer\" method=\"post\">");
		out.print(
				"<fieldset><label for=\"firstname\"></label> <input type=\"text\" name=\"firstname\" id=\"firstname\">Name</fieldset>");
		out.print(
				"<fieldset><label for=\"cnp\"></label> <input type=\"text\" name=\"cnp\" id=\"cnp\">CNP</fieldset>");
		out.print(
				"<fieldset><label for=\"address\"></label> <input type=\"textarea\" name=\"address\" id=\"address\"> Address</fieldset> ");
		out.print(
				"<fieldset> <label for=\"phome\"></label> <input type=\"text\" name=\"phone\" id=\"phone\">Phone</fieldset>");
		out.print(
				"<fieldset><label for=\"password\"></label> <input type=\"text\"name=\"subsidary_id\" id=\"subsidary_id\">Subsidary_Id</fieldset> ");
		
		out.print("<input type=\"submit\" id=\"login\" value=\"Add\">");
		
		out.println("</form>");
		
		
		
		
		out.print("<hr>");
		out.print("Delete Customer");
		out.print("<form   action=\"DeleteCustomer\" method=\"post\">");
		out.print(
				"<fieldset><label for=\"password\"></label> <input type=\"text\"name=\"id\" id=\"idtodelete\">Insert ID</fieldset>");

		out.print("<input type=\"submit\" id=\"DeleteCustomer\"   value=\"Delete\">");
		out.println("</form>");
		
		
		
		
		out.print("<hr>");
		out.print("Update Customer");
		out.print("<form   action=\"UpdateCustomer\" method=\"post\">");
		out.print(
				"<fieldset><label for=\"user_id\"</label> <input type=\"text\" name=\"userid\" id=\"userid\">User Id</fieldset>");
		out.print(
				"<fieldset><label for=\"firstname\"></label> <input type=\"text\" name=\"firstname\" id=\"firstname\">Name</fieldset>");
		out.print(
				"<fieldset><label for=\"cnp\"></label> <input type=\"text\" name=\"cnp\" id=\"cnp\">CNP</fieldset>");
		out.print(
				"<fieldset><label for=\"address\"></label> <input type=\"textarea\" name=\"address\" id=\"address\">Address</fieldset> ");
		out.print(
				"<fieldset> <label for=\"phome\"></label> <input type=\"text\" name=\"phone\" id=\"phone\">Phone</fieldset>");
		out.print(
				"<fieldset><label for=\"password\"></label> <input type=\"text\"name=\"subsidary_id\" id=\"subsidary_id\">Subsidary_Id</fieldset> ");
		
		out.print("<input type=\"submit\" id=\"login\" value=\"Update\">");
		out.println("</form>");		
		out.print("</div>");
		
		
		

		out.print(
				"<div style=\"height: 770px ;width: 33%;	background-color: #cceeff; display: inline;float: left;border: 2px solid   \">");	
		out.print("List information");
		out.print("<hr>See information by customer id ");
		out.print("<form   action=\"ReadById\" id=\"ReadById\" method=\"post\">");
		out.print(
				"<fieldset><label for=\"iban\"></label> <input type=\"text\" name=\"userid\" id=\"id\">Customer Id</fieldset>");
		out.print("<input type=\"submit\" id=\"login\" value=\"See Results\"></fieldset>");
		out.println("</form>");
		
		out.print("<hr>See information by name ");
		out.print("<form   action=\"ReadByName\" id=\"ReadByName\" method=\"post\">");
		out.print(
				"<fieldset><label for=\"iban\"></label> <input type=\"text\" name=\"firstname\" id=\"id\">Name</fieldset>");
		out.print("<input type=\"submit\" id=\"login\" value=\"See Results\"></fieldset>");
		out.println("</form>");
		
		out.print("<fieldset>Manage Accounts</fieldset>");
		out.print("<br>");		
		out.print("Add Account");
		out.print("<form   action=\"AddAccount\" method=\"post\">");
		out.print(
				"<fieldset><label for=\"iban\"></label> <input type=\"text\" name=\"iban\" id=\"iban\">IBAN</fieldset>");
		out.print(
				"<fieldset><label for=\"Amount\"></label> <input type=\"text\" name=\"amount\" id=\"amount\">Amount</fieldset> ");
		out.print(
				" <fieldset><label for=\"currency\"></label> <input type=\"text\" name=\"currency\" id=\"currency\">Currency</fieldset>");
		out.print(
				"<fieldset><label for=\"customer_id\"></label> <input type=\"text\" name=\"customerid\" id=\"customerid\">Customer Id</fieldset>");

		out.print("<input type=\"submit\" id=\"login\" value=\"Add\">");
		out.println("</form>");
		out.print("<hr>");

		out.print("Delete Account");
		out.print("<form   action=\"DeleteAccount\" method=\"post\">");
		out.print(
				"<fieldset><label for=\"password\"></label> <input type=\"text\"name=\"iban\" id=\"ibantodelete\">Insert IBAN</fieldset>");

		out.print("<input type=\"submit\" id=\"DeleteCustomer\"   value=\"Delete\">");
		out.println("</form>");

		// update account
		out.print("<hr>");
		out.print("Update Account");
		out.print("<form   action=\"UpdateAccount\" method=\"post\">");
		out.print(
				"<fieldset><label for=\"iban\"></label> <input type=\"text\" name=\"iban\" id=\"iban\">IBAN</fieldset>");
		out.print(
				"<fieldset><label for=\"Amount\"></label> <input type=\"text\" name=\"amount\" id=\"amount\">Amount</fieldset> ");
		out.print(
				" <fieldset><label for=\"currency\"></label> <input type=\"text\" name=\"currency\" id=\"currency\">Currency</fieldset>");
		out.print(
				"<fieldset><label for=\"customer_id\"></label> <input type=\"text\" name=\"customerid\" id=\"customerid\">Customer Id</fieldset>");

		out.print("<input type=\"submit\" id=\"login\" value=\"Update\">");
		out.println("</form>");

	
		out.print("</div>");
		
		
		out.print(
				"<div style=\"height: 770px ;width: 33%;	background-color: #cceeff; display: inline;float: left;border: 2px solid   \">");
		out.print("Make transactions");
		out.print("<hr>");
		out.print("One account transactions");
		out.print("<form   action=\"AddTransactionOneAccount\" id=\"formtransaction\" method=\"post\">");
		out.print(
				"<fieldset><label for=\"iban\"></label> <input type=\"text\" name=\"iban\" id=\"iban\">IBAN</fieldset>");
		out.print(
				"<fieldset><label for=\"Amount\"></label> <input type=\"text\" name=\"amount\" id=\"amount\">Amount</fieldset> ");
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
				"<fieldset><label for=\"iban\"></label> <input type=\"text\" name=\"ibanfrom\" id=\"ibanfrom\">IBAN From</fieldset>");
		out.print(
				"<fieldset><label for=\"iban\"></label> <input type=\"text\" name=\"ibanto\" id=\"ibanto\">IBAN To</fieldset>");
		out.print(
				"<fieldset><label for=\"Amount\"></label> <input type=\"text\" name=\"amount\" id=\"amount\">Amount</fieldset> ");

		out.print(
				"<fieldset><textarea rows=\"4\"  cols=\"50\" name=\"details\"   form=\"formtransaction2\">Enter Details</textarea></fieldset>");

		out.print("<input type=\"submit\" id=\"login\" value=\"Make Transaction\">");
		out.println("</form>");

		out.print("</div>");
		
		out.print("</body>");
		out.print("</html>");
	
		 
		
		out.close();
	}
}