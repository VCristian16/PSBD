package servleti;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class AddAccount extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String iban = request.getParameter("iban");
		Double amount = Double.parseDouble(request.getParameter("amount"));
		String currency = request.getParameter("currency");
		String customerid = request.getParameter("customerid");
	 
		try {
			Class.forName ("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			String insertClientAccount = "{call INSERT_ACCOUNTS(?,?,?,?)}";
			CallableStatement callableStatement = null;
			callableStatement = con.prepareCall(insertClientAccount);
			callableStatement.setString(1, iban);
			callableStatement.setString(2, currency);
			callableStatement.setDouble(3, amount);
			callableStatement.setString(4, customerid);
			
			callableStatement.executeUpdate();
			System.out.println("Account is inserted into DBUSER table!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("AdminPage");
			dispatcher.forward(request, response);
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
			
		out.close();
	}
}