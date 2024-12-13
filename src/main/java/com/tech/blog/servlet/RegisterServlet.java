package com.tech.blog.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tech.blog.dao.UserDao;
import com.tech.blog.entities.User;
import com.tech.blog.helper.ConnectionProvider;

@MultipartConfig
public class RegisterServlet extends HttpServlet {


	protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		
		//fecth all form data
		String check=request.getParameter("check");
		if(check==null) {
			out.println("Box not checked");
		}else {
			//baki ka data yaha nikalana hai
			String name=request.getParameter("user_name");
			String email=request.getParameter("user_email");
			String password=request.getParameter("user_password");
			String gender=request.getParameter("gender");
			String about=request.getParameter("about");
			
			//create user object and set all data to that object
			User user=new User(name,email,password,gender,about);
			
			
			//create a User dao object...
			UserDao dao=new UserDao(ConnectionProvider.getConnection());
			if(dao.saveUser(user)) {
				out.println("done");
			}
			else {
				out.println("error");
			}
		}
		
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
