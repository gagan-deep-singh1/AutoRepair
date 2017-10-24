package mypack;
import javax.servlet.*;
import java.util.Random;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class PartnerRegisterServlet extends HttpServlet {
public void doPost(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException
{ 
	try
	{	
		Random randomGenerator = new Random();
		 int TechnicianId = randomGenerator.nextInt(10000);
	String name=request.getParameter("name");
	String mobileNo=request.getParameter("mobile");
	int time=Integer.parseInt(request.getParameter("time"));
	String workPlace=request.getParameter("address");
	String email=request.getParameter("email");
	String password=request.getParameter("password");
	response.setContentType("text/html");
	PrintWriter out=response.getWriter();
	ServletConfig config=getServletConfig();
	ServletContext ctx=config.getServletContext();
	Class.forName(ctx.getInitParameter("driverClass"));
	Connection con=DriverManager.getConnection(ctx.getInitParameter("url"),
	ctx.getInitParameter("username"),
	ctx.getInitParameter("password"));
	PreparedStatement stmt=con.prepareStatement("Insert into TECHNICIAN(TechnicianId,Name,MobileNo,EmailAddress,Password,WorkingTime,WorkPlace) VALUES(?,?,?,?,?,?,?)");
	stmt.setInt(1,TechnicianId);
	stmt.setString(2,name);
	stmt.setString(3,mobileNo);
	stmt.setString(4,email);
	stmt.setString(5,password);
	stmt.setInt(6,time);
	stmt.setString(7,workPlace);	
	stmt.executeUpdate();
	RequestDispatcher rd=request.getRequestDispatcher("index5.html");
	rd.include(request,response);
	}catch(Exception e)
	{
		System.out.println(e);
	}
}
}