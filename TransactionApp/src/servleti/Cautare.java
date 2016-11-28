package servleti;

import java.sql.*;

import static java.lang.System.out;


public class Cautare {
	
	public static int validate(String id,String password){
		int status = 0;
			try{
				
				System.out.println("servlet login apelat");			
		
				Class.forName ("oracle.jdbc.driver.OracleDriver");
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			    out.println("connection established successfully...!!");     
				PreparedStatement ps=con.prepareStatement("select name from clients where client_id=? ");
		
				ps.setString(1,id);
				
				
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{					
					String user=rs.getString(1);	
					out.println("am intrat in while");     
					if(user.equals("Popescu119")==true)
					{
						status=1;
					}
					else 
					{
						status=1;
					}
				}
				
				
				 
			}catch(Exception e){System.out.println(e);}
		return status;
	}

	
}
