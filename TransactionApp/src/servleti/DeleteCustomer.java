package servleti;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class DeleteCustomer extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String client_id = request.getParameter("id");
		CallableStatement callableStatement = null;

		try {
			Class.forName ("oracle.jdbc.driver.OracleDriver");
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			String deleteClientProc = "{call DELETE_CLIENTS(?)}";
			
			callableStatement = conn.prepareCall(deleteClientProc);
			callableStatement.setString(1, client_id);
			callableStatement.execute();
			System.out.println("Client Deleted!");

			
			out.print("<!DOCTYPE html>");
			out.print("<html>");
			out.print("<head>");
			out.print("</head>");
			out.print("<body>");

				out.print("You removed the customer " + client_id + " with the following info<br>");
			
				out.print("<form   action=\"AdminPage\" method=\"post\">");
				out.print("<input type=\"submit\" id=\"AdminPage\"   value=\"Home\">");
			out.print("</body>");
			out.print("</html>");

			
		} catch (Exception e) {
			System.out.println(e);
		}

		out.close();
	}
}