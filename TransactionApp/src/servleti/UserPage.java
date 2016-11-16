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
public class UserPage extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userid = request.getParameter("userid");
		String pass = request.getParameter("password");

		String nume = null, prenume = null;

		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
			Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/eduarddb;");
			PreparedStatement ps = con.prepareStatement("select first_name,last_name from customers where id=? and password=?");
			PreparedStatement ps2 = con.prepareStatement("select * from Accounts where customer_id=?");
			PreparedStatement ps3 = con.prepareStatement("select t.iban,t.amount,a.currency,t.type,t.details from Customers c,Accounts a, transactions t where c.id=? and c.id=a.customer_id and a.iban=t.iban");

			ps.setString(1, userid);
			ps.setString(2, pass);
			ps2.setString(1, userid);
			ps3.setString(1, userid);

			ResultSet rs = ps.executeQuery();
			ResultSet rs2 = ps2.executeQuery();
			ResultSet rs3 = ps3.executeQuery();


			while (rs.next()) {
				prenume = rs.getString(1);
				nume = rs.getString(2);
			}

			out.print("<!DOCTYPE html>");
			out.print("<html>");
			out.print("<head>");
	     	out.print("<style>table {    border-collapse: collapse;    width: 100%;}th, td {    text-align: left;    padding: 8px;}tr:nth-child(even){background-color: #f2f2f2}th {    background-color: #4CAF50;    color: white;}</style>");
			out.print("</head>");
			out.print("<body>");
			out.print(" <div style=\"height: 20% ;width: 100%;	background-color: #cceeff;float: top; \">");
			out.print("	Bine ai venit " + nume + " " + prenume);
			out.print("	</div>");
			out.print("<div style=\"height: 80% ;width: 50%;	background-color: #cceeff;float: left; \">");

			out.print("<table border=2>");

			out.print("<tr>");
			out.print("<th>IBAN</th>");
			out.print("<th>SOLD</th>");
			out.print("<th>VALUTA</th>");
			out.print("</tr>");
			while (rs2.next()) {
				out.print("<tbody class=\"table-hover\">");
				out.print("<tr>");

				out.print("<td>" + rs2.getString(1) + "</td>");
				out.print("<td>" + rs2.getString(2) + "</td>");
				out.print("<td>" + rs2.getString(3) + "</td>");

				out.print("</tr>");

				 
			}
			out.print("</tbody>");
			out.print("</table>");

			out.print("	</div>");
			out.print(
					"	<div style=\"height: 80% ;width: 50%;	background-color: #cceeff; display: block;float: right; \">");
			out.print("<table border=2>");

			out.print("<tr>");
			out.print("<th>IBAN</th>");
			out.print("<th>VALOARE</th>");
			out.print("<th>VALUTA</th>");
			out.print("<th>TIP</th>");
			out.print("<th>DETALII</th>");
			out.print("</tr>");
			while (rs3.next()) {
				out.print("<tbody class=\"table-hover\">");
				out.print("<tr>");

				out.print("<td>" + rs3.getString(1) + "</td>");
				out.print("<td>" + rs3.getString(2) + "</td>");
				out.print("<td>" + rs3.getString(3) + "</td>");
				out.print("<td>" + rs3.getString(4) + "</td>");
				out.print("<td>" + rs3.getString(5) + "</td>");
				out.print("</tr>");

 			}
			out.print("</tbody>");
			out.print("</table>");			
			out.print("	</div>");
			out.print("</body>");
			out.print("</html>");
		} catch (Exception e) {
			System.out.println(e);
		}

		out.close();
	}
}