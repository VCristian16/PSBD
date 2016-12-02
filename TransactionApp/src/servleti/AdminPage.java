package servleti;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class AdminPage extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		String userid = request.getParameter("userid");
		List<String> lst=new ArrayList<>();
		String nume = null;
		try {
			Class.forName ("oracle.jdbc.driver.OracleDriver");		
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			PreparedStatement ps = con.prepareStatement("select name from clients where client_id=?");
			PreparedStatement ps2 =con.prepareStatement("select * from subsidaries");
			ps.setString(1, userid);
			ResultSet rs = ps.executeQuery();
			ResultSet rs2=ps2.executeQuery();
			while(rs2.next()){
				lst.add(rs2.getString(1));
			}
			
			while (rs.next()) {				
				nume = rs.getString(1);
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
				
		request.setAttribute("nume",nume);
		request.setAttribute("lista",lst);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/interface.jsp");
		dispatcher.forward(request, response);
		
		
	}
}