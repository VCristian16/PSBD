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
public class AddTransactionOneAccount extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String iban = request.getParameter("iban");
		String type = request.getParameter("type");
		Double amount = Double.parseDouble(request.getParameter("amount"));
		
		String details = request.getParameter("details");
	 
		try {
			Class.forName ("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Cristi96vsl");
			PreparedStatement ps = con.prepareStatement("insert into transactions (amount,type,details,iban) values (?,?,?,?)");
			ps.setDouble(1, amount);
			ps.setString(2, type);
			ps.setString(3, details);
			ps.setString(4, iban);
			ps.executeUpdate();
		
			//
			PreparedStatement getAmount = con.prepareStatement	("select amount from accounts where iban=?");
			getAmount.setString(1, iban);
			ResultSet x=getAmount.executeQuery();
			double currentAmount = 0;
			if(x.next())
			{
				 currentAmount=Double.parseDouble(x.getString(1));
			}
			if(type.equals("DEBIT"))
			{
				System.out.println("Am intrat in if");
				PreparedStatement ps2 = con.prepareStatement("update accounts set amount=? where iban=?");
				ps2.setDouble(1, currentAmount-amount);
 				ps2.setString(2, iban);
				ps2.executeUpdate();
			}
			if(type.equals("CREDIT"))
			{
				System.out.println("Am intrat in if");
				PreparedStatement ps2 = con.prepareStatement("update accounts set amount=? where iban=?");
				ps2.setDouble(1, currentAmount+amount);
 				ps2.setString(2, iban);
				ps2.executeUpdate();
			}
			//
			
			out.print("<!DOCTYPE html>");
			out.print("<html>");
			out.print("<head>");
			out.print("</head>");
			out.print("<body>");
			out.print(" You just made the following transaction "+type+"<hr>"
					+ "iban "+iban +" amount "+amount+" details "+details);
					 

			out.print("</body>");
			out.print("</html>");
		} catch (Exception e) {
			System.out.println(e);
		}

		out.close();
	}
}