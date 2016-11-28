package servleti;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class UpdateCustomer extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String user_id=request.getParameter("userid");
		String name = request.getParameter("firstname");
		String cnp = request.getParameter("cnp");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String subsidary_id = request.getParameter("subsidary_id");
		

		try {
			Class.forName ("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			String insertClientProc = "{call UPDATE_CLIENTS(?,?,?,?,?,?)}";
			CallableStatement callableStatement = null;
			callableStatement = con.prepareCall(insertClientProc);
			callableStatement.setString(1, user_id);
			callableStatement.setString(2, name);
			callableStatement.setString(3, cnp);
			callableStatement.setString(4, phone);
			callableStatement.setString(5, address);
			callableStatement.setString(6, subsidary_id);			
			callableStatement.executeUpdate();
			System.out.println("User Update!!");

			RequestDispatcher dispatcher = request.getRequestDispatcher("AdminPage");
			dispatcher.forward(request, response);
			
			
		} catch (Exception e) {
			System.out.println(e);
		}

		out.close();
	}
}