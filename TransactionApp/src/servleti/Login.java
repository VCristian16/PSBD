package servleti;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@SuppressWarnings("serial")
public class Login extends HttpServlet {
 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String n=request.getParameter("userid");
		String p=request.getParameter("password");
		
		if(Cautare.validate(n, p)==1){
			RequestDispatcher rd=request.getRequestDispatcher("AdminPage");
			rd.forward(request,response);
			System.out.println("Apelat admin");
		}
		else if(Cautare.validate(n, p)==2)
		{
			RequestDispatcher rd=request.getRequestDispatcher("UserPage");
			rd.forward(request,response);
			System.out.println("Apelat user");

		}
		else 
		{
			out.print("<script>");
			out.print("function myFunction() {");
			out.print("    alert(\"NU S-AU GASIT ZBORURI\");");
			out.print("}");
			out.print("</script>");
			out.print("myFunction()");
		}
		
		out.close();
	}

}
