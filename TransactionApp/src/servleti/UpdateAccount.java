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
public class UpdateAccount extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String iban = request.getParameter("iban");
		Double amount = Double.parseDouble(request.getParameter("amount"));
		String currency = request.getParameter("currency");
		String customerid = request.getParameter("customerid");

		try {
			Class.forName ("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Cristi96vsl");
			if(amount!=0){
			PreparedStatement ps = con.prepareStatement("update accounts set amount=? where iban=?");
 			ps.setString(2,iban);
			ps.setDouble(1,amount);
			ps.executeUpdate();
			}
			if(currency.length()!=0){
				PreparedStatement ps = con.prepareStatement("update accounts set currency=? where iban=?");
	 			ps.setString(2,iban);
				ps.setString(1,currency);
				ps.executeUpdate();
				}
			if(customerid.length()!=0){
				PreparedStatement ps = con.prepareStatement("update accounts set customer_id=? where iban=?");
	 			ps.setString(2,iban);
				ps.setString(1,customerid);
				ps.executeUpdate();
				}
			 
			out.print("<!DOCTYPE html>");
			out.print("<html>");
			out.print("<head>");
			out.print("</head>");
			out.print("<body>");
			out.print(" You just Updated Account "+iban
					+ "with the following information "+amount+" "+currency+ " " + customerid);
					 

			out.print("</body>");
			out.print("</html>");
		} catch (Exception e) {
			System.out.println(e);
		}

		out.close();
	}
}