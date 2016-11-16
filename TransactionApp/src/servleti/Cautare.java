package servleti;

import java.sql.*;

import static java.lang.System.out;


public class Cautare {
	
	public static int validate(String id,String password){
		int status = 0;
			try{
				System.out.println("servlet login apelat");
				Class.forName("org.apache.derby.jdbc.ClientDriver");
				Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/eduarddb;");
			    out.println("connection established successfully...!!");     
				PreparedStatement ps=con.prepareStatement("select * from customers where id=? and password=?");
		
				ps.setString(1,id);
				ps.setString(2,password);
				
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
				String selected_id=rs.getString(1);	
			    out.println("am intrat in while");     

				if(selected_id.startsWith("ADMIN")==true)status=1;
				else status=2;
				}
				
				
				 
			}catch(Exception e){System.out.println(e);}
		return status;
	}

	
}
