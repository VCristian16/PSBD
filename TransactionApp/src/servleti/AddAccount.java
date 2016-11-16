package servleti;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
			Class.forName("org.apache.derby.jdbc.ClientDriver");
			Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/eduarddb;");
			PreparedStatement ps = con.prepareStatement("insert into accounts values (?,?,?,?)");
			ps.setString(1, iban);
			ps.setDouble(2, amount);
			ps.setString(3, currency);
			ps.setString(4, customerid);
			 
			ps.executeUpdate();
			out.print("<!DOCTYPE html>");
			out.print("<html>");
			out.print("<head>");
			out.print("</head>");
			out.print("<body>");
			out.print(" You just added Account "+iban
					+ "with the following information "+amount+" "+currency+" "+currency);
					 

			out.print("</body>");
			out.print("</html>");
		} catch (Exception e) {
			System.out.println(e);
		}

		out.close();
	}
}