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
public class UpdateCustomer extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userid = request.getParameter("userid");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String birth_date = request.getParameter("birth_date");
		String address = request.getParameter("address");
		int phone = Integer.parseInt(request.getParameter("phone"));
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");

		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
			Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/eduarddb;");
			if (firstname.length() != 0) {
				PreparedStatement ps = con.prepareStatement("update customers set first_name=? where id=?");
				ps.setString(2, userid);
				ps.setString(1, firstname);
				ps.executeUpdate();
			}
			if (lastname.length() != 0) {
				PreparedStatement ps = con.prepareStatement("update customers set last_name=? where id=?");
				ps.setString(2, userid);
				ps.setString(1, lastname);
				ps.executeUpdate();
			}
			if (birth_date.length() != 0) {
				PreparedStatement ps = con.prepareStatement("update customers set birth_date=? where id=?");
				ps.setString(2, userid);
				ps.setString(1, birth_date);
				ps.executeUpdate();
			}
			if (address.length() != 0) {
				PreparedStatement ps = con.prepareStatement("update customers set address=? where id=?");
				ps.setString(2, userid);
				ps.setString(1, address);
				ps.executeUpdate();
			}
			if (phone != 0) {
				PreparedStatement ps = con.prepareStatement("update customers set phone=? where id=?");
				ps.setString(2, userid);
				ps.setInt(1, phone);
				ps.executeUpdate();
			}

			out.print("<!DOCTYPE html>");
			out.print("<html>");
			out.print("<head>");
			out.print("</head>");
			out.print("<body>");
			out.print(" You just Updated Customer " + userid + "with the following information " + firstname + " "
					+ lastname + " " + password + " " + birth_date + " " + address + " " + phone);

			out.print("</body>");
			out.print("</html>");
		} catch (Exception e) {
			System.out.println(e);
		}

		out.close();
	}
}