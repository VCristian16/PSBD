package servleti;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.jdbc.OracleTypes;

@SuppressWarnings("serial")
public class ReadByNameCnp extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String cnp = request.getParameter("cnp");
		List<String[]> list=new ArrayList<String[]>();
		List<String[]> listCard=new ArrayList<String[]>();   
		try {
			Class.forName ("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			CallableStatement callableStatement = null;
			String getDBUSERByUserIdSql = "{call QUERRY_CLIENTS(?,?,?,?,?,?,?,?)}";
			Map<String,String> map = new HashMap<String,String>();

			try{
				callableStatement = con.prepareCall(getDBUSERByUserIdSql);
				callableStatement.setString(1, cnp);
				callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
				callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
				callableStatement.registerOutParameter(4, java.sql.Types.NUMERIC);
				callableStatement.registerOutParameter(5, java.sql.Types.VARCHAR);
				callableStatement.registerOutParameter(6, java.sql.Types.VARCHAR);
				callableStatement.registerOutParameter(7, OracleTypes.CURSOR);	
				callableStatement.registerOutParameter(8, OracleTypes.CURSOR);	

				// execute getDBUSERByUserId store procedure
				callableStatement.executeUpdate();
				String name=callableStatement.getString(2);
				String userid = callableStatement.getString(3);
				String phone = callableStatement.getString(4);
				String address = callableStatement.getString(5);
				String subsidaryCity = callableStatement.getString(6);
				ResultSet rs = (ResultSet) callableStatement.getObject(7);
				ResultSet rs1 = (ResultSet) callableStatement.getObject(8);
				while(rs.next()){
					String iban = rs.getString("IBAN");
					String currency = rs.getString("CURRENCY");
					String amount = rs.getString("AMOUNT");

					String[]  line={iban,currency,amount};
					list.add(line);



				}
				rs.close();
				while(rs1.next()){
					String card_n = rs1.getString("CARD_NUMBER");
					String exp_date = rs1.getString("EXP_DATE");
					String cr2_code = rs1.getString("CR2_CODE");
					String type = rs1.getString("TYPE");
					String iban= rs1.getString("IBAN");
					String[]  line={card_n,exp_date,cr2_code,type,iban};
					listCard.add(line);



				}
				rs1.close();

				map.put("userid",userid);
				map.put("cnp",cnp);
				map.put("fname",name);				
				map.put("phone",phone);
				map.put("adress", address);
				map.put("subs",subsidaryCity);



			}catch(Exception e){
				System.out.println("Data not found!");
				map.put("cnp","not found");
				map.put("fname","not found");				
				map.put("phone","not found");
				map.put("adress","not found");
				map.put("subs","not found");
				map.put("iban", "not found");
				map.put("currency", "not found");
				map.put("amount","not found");
				map.put("userid","not found");
			}


			request.setAttribute("jsonString",map);
			request.setAttribute("listObj",list);
			request.setAttribute("listCard",listCard);	




			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin.jsp");
			dispatcher.forward(request, response);






		} catch (Exception e) {
			System.out.println(e);
		}



		out.close();

	}
}