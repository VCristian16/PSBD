package servleti;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class AddCustomer extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String subsidary_id = request.getParameter("item");
		String name = request.getParameter("firstname");
		String cnp = request.getParameter("cnp");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		Map<String,String> map = new HashMap<String,String>();
		map.put("cnp",cnp);
		map.put("fname",name);				
		map.put("phone",phone);
		map.put("adress", address);
		map.put("subsd",subsidary_id);
		

		

		try {
			Class.forName ("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			String insertClientProc = "{call INSERT_CLIENTS(?,?,?,?,?)}";
			CallableStatement callableStatement = null;
			callableStatement = con.prepareCall(insertClientProc);
			callableStatement.setString(1, name);
			callableStatement.setString(2, cnp);
			callableStatement.setString(3, phone);
			callableStatement.setString(4, address);
			callableStatement.setString(5, subsidary_id);
			callableStatement.executeUpdate();
			System.out.println("Record is inserted into DBUSER table!");


			
		} catch (Exception e) {
			System.out.println(e);
		}

		request.setAttribute("jsonString",map);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/addCust.jsp");
		dispatcher.forward(request, response);
		out.close();
	}
}