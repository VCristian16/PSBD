package servleti;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
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

import oracle.jdbc.OracleTypes;

@SuppressWarnings("serial")
public class ReadBySubsidary extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String subsidary_id = request.getParameter("itemClients");
		List<String[]> list=new ArrayList<String[]>();
	   
		

 
		try {
			Class.forName ("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			String cursorClientProc = "{call RET_ClientsCursor(?,?)}";
			CallableStatement callableStatement = null;
			callableStatement = con.prepareCall(cursorClientProc);
			callableStatement.setString(1, subsidary_id);
			callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			// execute getDBUSERCursor store procedure
			callableStatement.executeUpdate();

			// get cursor and cast it to ResultSet
			ResultSet rs = (ResultSet) callableStatement.getObject(2);
			while(rs.next()){
				String name = rs.getString("NAME");
				String client_id = rs.getString("CLIENT_ID");
				String phone = rs.getString("PHONE");
				String address = rs.getString("ADDRESS");
				String cnp=rs.getString("CNP");
				/*
				String iban=rs.getString("IBAN");
				String amount=rs.getString("AMOUNT");
				String currency=rs.getString("CURRENCY");
				*/
				String[]  line={client_id,name,cnp,phone,address};
				list.add(line);
				
				
				
			}
			
			request.setAttribute("listObj",list);
			request.setAttribute("subsidary", subsidary_id);
			System.out.println("Stop");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/clients.jsp");
			dispatcher.forward(request, response);
			
 			
		 

			
		} catch (Exception e) {
			System.out.println(e);
		}

		out.close();
	}
}