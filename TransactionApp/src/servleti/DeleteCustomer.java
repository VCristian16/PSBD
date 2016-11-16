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
public class DeleteCustomer extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userid = request.getParameter("id");

		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
			Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/eduarddb;");

			PreparedStatement ps1 = con.prepareStatement("select * from customers where id=?");
			PreparedStatement ps2 = con.prepareStatement("delete from customers where id=?");
			ps2.setString(1, userid);
			ps1.setString(1, userid);
			out.print("<!DOCTYPE html>");
			out.print("<html>");
			out.print("<head>");
			out.print("</head>");
			out.print("<body>");

			ResultSet rs = ps1.executeQuery();

			while (rs.next()) {
				out.print("You removed the customer " + rs.getString(1) + " with the following info<br>");
				out.print(rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5) + " "
						+ rs.getString(6) + " " + rs.getString(7) + " ");
			}

			ps2.executeUpdate();

			out.print("</body>");
			out.print("</html>");
		} catch (Exception e) {
			System.out.println(e);
		}

		out.close();
	}
}