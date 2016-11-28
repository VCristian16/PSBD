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
public class ReadById extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userid = request.getParameter("userid");

		try {
			Class.forName ("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			CallableStatement callableStatement = null;
			String getDBUSERByUserIdSql = "{call QUERRY_CLIENTS(?,?,?,?,?,?)}";
			
			
				
				callableStatement = con.prepareCall(getDBUSERByUserIdSql);

				callableStatement.setString(1, userid);
				callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
				callableStatement.registerOutParameter(3, java.sql.Types.NUMERIC);
				callableStatement.registerOutParameter(4, java.sql.Types.NUMERIC);
				callableStatement.registerOutParameter(5, java.sql.Types.VARCHAR);
				callableStatement.registerOutParameter(6, java.sql.Types.VARCHAR);

				// execute getDBUSERByUserId store procedure
				callableStatement.executeUpdate();
                
				String Name = callableStatement.getString(2);
				String CNP = callableStatement.getString(3);
				String phone = callableStatement.getString(4);
				String address = callableStatement.getString(5);
				String subsidaryCity = callableStatement.getString(6);
				
				Map<String,String> map = new HashMap<String,String>();
					map.put("userid",userid);
					map.put("fname",Name);
					map.put("cnp",CNP);
					map.put("phone",phone);
					map.put("adress", address);
					map.put("subs",subsidaryCity);


				request.setAttribute("jsonString",map);
				
			
				RequestDispatcher dispatcher = request.getRequestDispatcher("/admin.jsp");
				dispatcher.forward(request, response);
				
				
				/*	

				System.out.println("UserName : " + Name);
				System.out.println("CreatedBy : " + CNP);
				System.out.println("CreatedDate : " + phone);

	

			
			out.print("<!DOCTYPE html>");
			out.print("<html>");
			out.print("<head>");
	     	out.print("<style>table {    border-collapse: collapse;    width: 100%;}th, td {    text-align: left;    padding: 8px;}tr:nth-child(even){background-color: #f2f2f2}th {    background-color: #4CAF50;    color: white;}</style>");

			out.print("</head>");
			out.print("<body>");
			out.print(" <div style=\"height: 20% ;width: 100%;	background-color: #cceeff;float: top; \">");
			out.print("	Bine ai venit " + Name );
			out.print("<form   action=\"AdminPage\" method=\"post\">");
			out.print("<input type=\"submit\" id=\"AdminPage\"   value=\"Home\">");
			out.print("	</div>");
			out.print("<div style=\"height: 80% ;width: 50%;	background-color: #cceeff;float: left; \">");

			out.print("<table border=2>");

	

			out.print("	</div>");
			out.print(
					"	<div style=\"height: 80% ;width: 50%;	background-color: #cceeff; display: block;float: right; \">");
			out.print("<table border=2>");

			out.print("<tr>");
			out.print("<th>CNP</th>");
			out.print("<th>Telefon</th>");
			out.print("<th>Adresa</th>");
			out.print("<th>Filiala</th>");
			out.print("</tr>");
			
				out.print("<tbody class=\"table-hover\">");
				out.print("<tr>");

				out.print("<td>" + CNP + "</td>");
				out.print("<td>" + phone + "</td>");
				out.print("<td>" + address + "</td>");
				out.print("<td>" + subsidaryCity + "</td>");
				;
				out.print("</tr>");

			
			out.print("</tbody>");
			out.print("</table>");
			out.print("	</div>");
			out.print("</body>");
			out.print("</html>");
		*/
				
				
		} catch (Exception e) {
			System.out.println(e);
		}

		

		out.close();
		
	}
}