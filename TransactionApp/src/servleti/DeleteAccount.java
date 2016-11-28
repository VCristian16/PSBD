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
public class DeleteAccount extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String iban = request.getParameter("iban");

		try {
			Class.forName ("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Cristi96vsl");

			PreparedStatement ps1 = con.prepareStatement("select * from accounts where iban=?");
			PreparedStatement ps2 = con.prepareStatement("delete from accounts where iban=?");
			ps2.setString(1, iban);
			ps1.setString(1, iban);
			out.print("<!DOCTYPE html>");
			out.print("<html>");
			out.print("<head>");
			out.print("</head>");
			out.print("<body>");

			ResultSet rs = ps1.executeQuery();

			while (rs.next()) {
				out.print("You removed the Account " + rs.getString(1) + " with the following info<br>");
				out.print(rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
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