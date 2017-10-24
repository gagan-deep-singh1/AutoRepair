package mypack;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.Calendar;
public class LoginServlet extends HttpServlet {

public void doPost(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException
{
	try{
		int userId=0;
		int tId=0;
	String email=request.getParameter("email");
	String p=request.getParameter("password");
	response.setContentType("text/html");
	PrintWriter out=response.getWriter();
	ServletConfig config=getServletConfig();
	ServletContext ctx=config.getServletContext();
	Class.forName(ctx.getInitParameter("driverClass"));
	Connection con=DriverManager.getConnection(ctx.getInitParameter("url"),
	ctx.getInitParameter("username"),
	ctx.getInitParameter("password"));
		
	
	
	
	//To get the name of the user
	
	PreparedStatement stmt=con.prepareStatement("Select* from USER where EmailAddress=? and Password=?");
	stmt.setString(1,email);
	stmt.setString(2,p);
	
	
	
	PreparedStatement stmts=con.prepareStatement("Select* from TECHNICIAN where EmailAddress=? and Password=?");
	stmts.setString(1,email);
	stmts.setString(2,p);
	ResultSet rset=stmt.executeQuery();
	ResultSet rsets=stmts.executeQuery();
	if(rset.next())
	{
		 RequestDispatcher rd=request.getRequestDispatcher("index7.html"); //Nav bar
		rd.include(request,response);
		userId=rset.getInt(1);
		PreparedStatement stms=con.prepareStatement("Select * from PreviousBooking where UserId=?");
		stms.setInt(1,userId);
		ResultSet previousBookingGetter=stms.executeQuery();
		out.println("<h2><center>Welcome,"+rset.getString(2)+"</center></h2>");
		out.println("<br/>");
		out.println("<br/>");
		request.setAttribute("userId",userId);
		RequestDispatcher rd1=request.getRequestDispatcher("index9.jsp"); //Map
		rd1.include(request,response);
		
		
//		RequestDispatcher rd2=request.getRequestDispatcher("request.jsp"); //Form to raise the request
//		rd2.include(request,response);
		
		
		
		
		out.println("<br/>");
		out.println("<br/>");
		out.println("<br/>");
		out.println("<br/>");
		
		
			
		out.println("<h2>Your previous Bookings --- ");
		out.println("<br/>");
		if(previousBookingGetter.next())
		{
			while(previousBookingGetter.next())
			{
				out.println("<h2>1. On"+previousBookingGetter.getDate(1)+previousBookingGetter.getString(2));
			}
		}
		
		
		
	else
	{
		out.println("<h2>There is no previous booking.");
	}
	}
	else if(rsets.next())
	{
		
		 RequestDispatcher rd=request.getRequestDispatcher("index8.html");
			rd.include(request,response);

			 
			    out.println("<h2><center>Welcome,"+rsets.getString(2)+"</center></h2>");
			    RequestDispatcher rd1=request.getRequestDispatcher("index9.html");
				rd1.include(request,response);
		
				
	}
	else
	{
	RequestDispatcher rd=request.getRequestDispatcher("index6.html");
	rd.include(request,response);
	}
	
	
	
	
	}catch(Exception e)
	{
		System.out.println(e);
	}
}
}