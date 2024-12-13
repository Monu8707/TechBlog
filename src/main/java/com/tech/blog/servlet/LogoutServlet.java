package com.tech.blog.servlet;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tech.blog.entities.Massage;

public class LogoutServlet extends HttpServlet{
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Servlet Logout</title>");
		out.println("</head>");
		out.println("<body>");
	    
		HttpSession s=request.getSession();
		
		s.removeAttribute("currentUser");
		Massage m=new Massage("Logout Successfully","success","alert-success");
		response.sendRedirect("login_page.jsp");
		s.setAttribute("msg", m);
		
		
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
