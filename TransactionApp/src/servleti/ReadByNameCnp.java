package servleti;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class ReadByNameCnp extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		String cnp = request.getParameter("cnp");
		try {
			Class.forName ("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			CallableStatement callableStatement = null;
			String getDBUSERByUserIdSql = "{call QUERRY_CLIENTS(?,?,?,?,?,?,?,?,?)}";
			
			
				
				callableStatement = con.prepareCall(getDBUSERByUserIdSql);

				callableStatement.setString(1, name);
				callableStatement.setString(2,cnp);
				callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
				callableStatement.registerOutParameter(4, java.sql.Types.NUMERIC);
				callableStatement.registerOutParameter(5, java.sql.Types.VARCHAR);
				callableStatement.registerOutParameter(6, java.sql.Types.VARCHAR);
				callableStatement.registerOutParameter(7, java.sql.Types.VARCHAR);
				callableStatement.registerOutParameter(8, java.sql.Types.VARCHAR);
				callableStatement.registerOutParameter(9, java.sql.Types.NUMERIC);
				

				// execute getDBUSERByUserId store procedure
				callableStatement.executeUpdate();
             
				String userid = callableStatement.getString(3);
				String phone = callableStatement.getString(4);
				String address = callableStatement.getString(5);
				String subsidaryCity = callableStatement.getString(6);
				String iban =callableStatement.getString(7);
				String currency=callableStatement.getString(8);
				String amount=callableStatement.getString(9);
				
				Map<String,String> map = new HashMap<String,String>();
					map.put("userid",userid);
					map.put("cnp",cnp);
					map.put("fname",name);				
					map.put("phone",phone);
					map.put("adress", address);
					map.put("subs",subsidaryCity);
					map.put("iban", iban);
					map.put("currency", currency);
					map.put("amount",amount);


				request.setAttribute("jsonString",map);
				
			
				RequestDispatcher dispatcher = request.getRequestDispatcher("/admin.jsp");
				dispatcher.forward(request, response);
				
				
				

				
				
		} catch (Exception e) {
			System.out.println(e);
		}

		

		out.close();
		
	}
}