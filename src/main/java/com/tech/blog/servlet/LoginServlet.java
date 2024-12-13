package com.tech.blog.servlet;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tech.blog.dao.UserDao;
import com.tech.blog.entities.Massage;
import com.tech.blog.entities.User;
import com.tech.blog.helper.ConnectionProvider;




public class LoginServlet extends HttpServlet {


	protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Servlet Login</title>");
		out.println("</head>");
		out.println("<body>");
		// fetch username and password from request
		String userEmail=request.getParameter("email");
		String userPassword=request.getParameter("password");
		
		
		UserDao dao=new UserDao(ConnectionProvider.getConnection());
		
		User u=dao.getUSerByEmailAndPassword(userEmail, userPassword);
		 
		if(u==null) {
			//login error
			//out.println("Invalid Details.. try again");
			Massage msg=new Massage("Invalid details! try with another","error","alert-danger");
			HttpSession s=request.getSession();
			s.setAttribute("msg", msg);
			response.sendRedirect("login_page.jsp");
		}else
		{
			//login success
			
			
			HttpSession s=request.getSession();
			s.setAttribute("currentUser", u);
			response.sendRedirect("profile.jsp");
		}
		
		
		
		
		out.println("</body>");
		out.println("</html>");

	}
	@Override 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processRequest(request, response);
	}
}