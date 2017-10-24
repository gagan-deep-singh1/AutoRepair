package mypack;
import javax.servlet.*;
import java.util.Random;
import java.text.SimpleDateFormat;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class MemberRegisterServlet extends HttpServlet {
public void doPost(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException
{ 
	try
	{	
		Random randomGenerator = new Random();
		 int userId = randomGenerator.nextInt(10000);
	String name=request.getParameter("name");
	String mobileNo=request.getParameter("Mobile");
	String email=request.getParameter("email");
	String password=request.getParameter("password");
	ServletConfig config=getServletConfig();
	ServletContext ctx=config.getServletContext();
	Class.forName(ctx.getInitParameter("driverClass"));
	Connection con=DriverManager.getConnection(ctx.getInitParameter("url"),
	ctx.getInitParameter("username"),
	ctx.getInitParameter("password"));
	PreparedStatement stmt=con.prepareStatement("Insert into USER(UserId,Name,MobileNo,EmailAddress,Password) VALUES(?,?,?,?,?)");
	stmt.setInt(1,userId);
	stmt.setString(2,name);
	stmt.setString(3,mobileNo);
	stmt.setString(4,email);
	stmt.setString(5,password);
	stmt.executeUpdate();
	RequestDispatcher rd=request.getRequestDispatcher("index5.html");
	rd.include(request,response);  
	}catch(Exception e)
	{
		e.printStackTrace();;
	}
}
}