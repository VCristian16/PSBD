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
public class AddCustomer extends HttpServlet {
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
		//String password2 = request.getParameter("password2");

		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
			Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/eduarddb;");
			PreparedStatement ps = con.prepareStatement("insert into customers values (?,?,?,?,?,?,?)");
			ps.setString(1, userid);
			ps.setString(2, firstname);
			ps.setString(3, lastname);
			ps.setString(4, password);
			ps.setString(5, birth_date);
			ps.setString(6, address);
			ps.setInt(7, phone);
			ps.executeUpdate();
			out.print("<!DOCTYPE html>");
			out.print("<html>");
			out.print("<head>");
			out.print("</head>");
			out.print("<body>");
			out.print(" You just added Customer "+userid
					+ "with the following information "+firstname+" "+lastname+ " " + password+ " " + birth_date+ " "+ address+ " "+ phone);
					 

			out.print("</body>");
			out.print("</html>");
		} catch (Exception e) {
			System.out.println(e);
		}

		out.close();
	}
}