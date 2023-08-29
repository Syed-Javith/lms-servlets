package com.lms.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.model.Book;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bookIsbnTobeDeleted = request.getParameter("book_isbn");
		
		PrintWriter out = response.getWriter();
		
		System.out.println(bookIsbnTobeDeleted);
		
		System.out.println("delete");
		
		try {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management_system","root", "root123");
		PreparedStatement pd = con.prepareStatement("DELETE FROM books WHERE isbn=?");
		pd.setString(1, bookIsbnTobeDeleted);
		int rs = pd.executeUpdate();
		
		if(rs > 0) {
			System.out.println("book deleted...");
		}else {
			System.out.print("no such book...");
		}
		
	}catch(Exception e) {
		System.out.print(e);
		out.print(e.getMessage());
		RequestDispatcher rd = request.getRequestDispatcher("index.html");
		rd.include(request, response);
		
	}
		
		response.sendRedirect(request.getContextPath() + "/GetAll");
		
	}

	

}
