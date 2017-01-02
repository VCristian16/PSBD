package servleti;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.iban4j.CountryCode;
import org.iban4j.Iban;

@SuppressWarnings("serial")
public class AddAccount extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Iban iban =Iban.random(CountryCode.RO);
		String amount = request.getParameter("amount");
		String currency = request.getParameter("currency");
		String cnp = request.getParameter("cnp");
		Map<String,String> map = new HashMap<String,String>();
		map.put("iban",iban.toString());;
		map.put("amount",amount);				
		map.put("currency",currency);
		map.put("userid", cnp);
	 
		try {
			Class.forName ("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			String insertClientAccount = "{call INSERT_ACCOUNTS(?,?,?,?)}";
			CallableStatement callableStatement = null;
			callableStatement = con.prepareCall(insertClientAccount);
			callableStatement.setString(1, iban.toString());
			callableStatement.setString(2, currency);
			callableStatement.setString(3, amount);
			callableStatement.setString(4, cnp);		
			callableStatement.executeUpdate();
			
			System.out.println("Account is inserted into DBUSER table!");
			request.setAttribute("jsonAccount",map);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/addAccount.jsp");
			dispatcher.forward(request, response);
	
			
			
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Account not inserted into DBUSER table!");
			out.close();
		}	
	
		
		
	}
}