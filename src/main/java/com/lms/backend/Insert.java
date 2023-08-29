package com.lms.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Insert
 */
@WebServlet("/Insert")
public class Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		out.print("hello");
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management_system","root","root123");
			PreparedStatement pd = con.prepareStatement("INSERT INTO books VALUES(?,?,?,?)");
			
			pd.setString(1, request.getParameter("addedName"));
			pd.setString(2, request.getParameter("addedAuthor"));
			pd.setString(3, request.getParameter("addedIsbn"));
			pd.setString(4, request.getParameter("addedDescription"));
			
			int count = pd.executeUpdate();
			
			response.sendRedirect(request.getContextPath() + "/GetAll");
			
		}catch(Exception e) {
			System.out.println(e);
			out.print(e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);
		}
	}

}
