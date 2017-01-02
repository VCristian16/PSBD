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
public class UpdateAccount extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String iban = request.getParameter("iban");
		String amount = request.getParameter("amount");


		try {
			Class.forName ("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			String updateAccountProc = "{call UPDATE_ACCOUNTS(?,?)}";
			CallableStatement callableStatement = null;
			callableStatement = con.prepareCall(updateAccountProc);
			callableStatement.setString(1, iban);
			callableStatement.setString(2, amount);
			callableStatement.executeUpdate();
			System.out.println("Acount Updated!!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("AdminPage");
			dispatcher.forward(request, response);
					
	
		
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		out.close();
		
	}
}