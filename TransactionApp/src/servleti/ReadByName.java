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
public class ReadByName extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");

 
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
			Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/eduarddb;");
 			PreparedStatement ps2 = con.prepareStatement("select * from Customers where first_name=? and last_name=?");
			 
 			ps2.setString(1, firstname);
 			ps2.setString(2, lastname);
		 
 			ResultSet rs2 = ps2.executeQuery();
		 

			out.print("<!DOCTYPE html>");
			out.print("<html>");
			out.print("<head>");
	     	out.print("<style>table {    border-collapse: collapse;    width: 100%;}th, td {    text-align: left;    padding: 8px;}tr:nth-child(even){background-color: #f2f2f2}th {    background-color: #4CAF50;    color: white;}</style>");

			out.print("</head>");
			out.print("<body>");
			out.print("Search Results<hr>");
			out.print("<div style=\"height: 80% ;width: 50%;	background-color: #cceeff;float: left; \">");

			out.print("<table border=2>");

			out.print("<tr>");
			out.print("<th>ID</th>");
			out.print("<th>FIRST NAME</th>");
			out.print("<th>LAST NAME</th>");
			out.print("<th>PASSWORD</th>");
			out.print("<th>BIRTH DATE</th>");
			out.print("<th>ADDRESS</th>");
			out.print("<th>PHONE</th>");

			out.print("</tr>");
			while (rs2.next()) {
				out.print("<tbody class=\"table-hover\">");
				out.print("<tr>");

				out.print("<td>" + rs2.getString(1) + "</td>");
				out.print("<td>" + rs2.getString(2) + "</td>");
				out.print("<td>" + rs2.getString(3) + "</td>");
				out.print("<td>" + rs2.getString(4) + "</td>");
				out.print("<td>" + rs2.getString(5) + "</td>");
				out.print("<td>" + rs2.getString(6) + "</td>");
				out.print("<td>0" + rs2.getString(7) + "</td>");

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