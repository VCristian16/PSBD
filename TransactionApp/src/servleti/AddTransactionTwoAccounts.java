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
public class AddTransactionTwoAccounts extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String ibanfrom = request.getParameter("ibanfrom");
		Double amount = Double.parseDouble(request.getParameter("amount"));
		String ibanto = request.getParameter("ibanto");
		String details = request.getParameter("details");
	 
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
			Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/eduarddb;");
			PreparedStatement ps = con.prepareStatement("insert into transactions (amount,type,details,iban) values (?,?,?,?)");
			ps.setDouble(1, amount);
			ps.setString(2, "DEBIT");
			ps.setString(3, details);
			ps.setString(4, ibanfrom);
			ps.executeUpdate();
			
			PreparedStatement ps2 = con.prepareStatement("insert into transactions (amount,type,details,iban) values (?,?,?,?)");
			ps2.setDouble(1, amount);
			ps2.setString(2, "CREDIT");
			ps2.setString(3, details);
			ps2.setString(4, ibanto);
			ps2.executeUpdate();
		
			//am inserat in tranzactii acum actualizam sumele
			PreparedStatement getAmountFrom = con.prepareStatement	("select amount from accounts where iban=?");
			PreparedStatement getAmountTo = con.prepareStatement	("select amount from accounts where iban=?");

			getAmountFrom.setString(1, ibanfrom);
			getAmountTo.setString(1, ibanto);

			ResultSet x=getAmountFrom.executeQuery();
			ResultSet y=getAmountTo.executeQuery();

			double currentAmountFrom = 0;
			double currentAmountTo = 0;

			if(x.next())
			{
				 currentAmountFrom=Double.parseDouble(x.getString(1));
			}
			if(y.next())
			{
				 currentAmountTo=Double.parseDouble(y.getString(1));
			}
 
				PreparedStatement ps3 = con.prepareStatement("update accounts set amount=? where iban=?");
				ps3.setDouble(1, currentAmountFrom-amount);
 				ps3.setString(2, ibanfrom);
				ps3.executeUpdate();
			 
 				PreparedStatement ps4 = con.prepareStatement("update accounts set amount=? where iban=?");
				ps4.setDouble(1, currentAmountTo+amount);
 				ps4.setString(2, ibanto);
				ps4.executeUpdate();
			 
			//
			
			out.print("<!DOCTYPE html>");
			out.print("<html>");
			out.print("<head>");
			out.print("</head>");
			out.print("<body>");
			out.print(" You just made the following transaction From "+ibanfrom+"<hr> to "+ibanto+"<hr>"
					+ "amount "+amount +" details "+details);
					 

			out.print("</body>");
			out.print("</html>");
		} catch (Exception e) {
			System.out.println(e);
		}

		out.close();
	}
}