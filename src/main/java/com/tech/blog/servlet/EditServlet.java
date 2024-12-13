package com.tech.blog.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.tech.blog.dao.UserDao;
import com.tech.blog.entities.Massage;
import com.tech.blog.entities.User;
import com.tech.blog.helper.ConnectionProvider;
import com.tech.blog.helper.Helper;

@MultipartConfig
public class EditServlet extends HttpServlet {

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Servlet Edit</title>");
		out.println("</head>");
		out.println("<body>");
		// fetch all data
		String userEmail = request.getParameter("user_email");
		String userName = request.getParameter("user_name");
		String userPassword = request.getParameter("user_password");
		String userAbout = request.getParameter("user_about");
		Part part = request.getPart("image");
		String imageFileName = part.getSubmittedFileName();

		// get the User from the session

		HttpSession s = request.getSession();
		User user = (User) s.getAttribute("currentUser");
		user.setEmail(userEmail);
		user.setName(userName);
		user.setPassword(userPassword);
		user.setAbout(userAbout);
		String oldFile = user.getProfile();
		user.setProfile(imageFileName);

		// Update database

		UserDao userDao = new UserDao(ConnectionProvider.getConnection());
		boolean ans = userDao.updateUser(user);
		if (ans) {

			String path = request.getRealPath("/")+"pics"+File.separator+imageFileName;
			System.out.println(path);
			// Delete code
			String pathOldFile = request.getRealPath("/")+"pics"+File.separator+ oldFile;
			if (!oldFile.equals("default.png")) {
				Helper.deleteFile(pathOldFile);
			}
			

			if (Helper.saveFile(part.getInputStream(), path)) {
				out.println("Profile Updated...");
				Massage msg = new Massage("Profile details Updated...", "success", "alert-success");

				s.setAttribute("msg", msg);
			} else {
				Massage msg = new Massage("Somthing went wrong..", "error", "alert-danger");

				s.setAttribute("msg", msg);

			}
		} else {
			out.println("Note Udated to DB");
			Massage msg = new Massage("Somthing went wrong..", "error", "alert-danger");

			s.setAttribute("msg", msg);
		}

		response.sendRedirect("profile.jsp");

		out.println("</body>");
		out.println("</html>");

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		processRequest(request, response);
	}
}
