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

import com.lms.model.Book;

@WebServlet("/Edit")
public class Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bookIsbnToBeEdited =  request.getParameter("book_isbn") ;
		String bookTitleToBeEdited = request.getParameter("book_title");
		String bookAuthorToBeEdited =  request.getParameter("book_author");
		String bookDescriptionToBeEdited = request.getParameter("book_description");
		
		System.out.println(bookAuthorToBeEdited + " "+bookDescriptionToBeEdited + " "+bookIsbnToBeEdited + " "+bookTitleToBeEdited);
		
		Book EditBook = new Book(bookTitleToBeEdited , bookAuthorToBeEdited , bookIsbnToBeEdited , bookDescriptionToBeEdited);
		
		request.setAttribute("EditBook", EditBook);
		RequestDispatcher rd = request.getRequestDispatcher("edit.jsp");
		rd.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management_system", "root", "root123");
			
			PreparedStatement pd = con.prepareStatement("UPDATE books SET title=? , author=? , description = ? WHERE isbn=?");
			
			pd.setString(1, request.getParameter("editedTitle"));
			pd.setString(2, request.getParameter("editedAuthor"));
			pd.setString(3, request.getParameter("editedDescription"));
			pd.setString(4, request.getParameter("editedIsbn"));
			
			pd.executeUpdate();
			
			response.sendRedirect(request.getContextPath() + "/GetAll");
			
		}catch(Exception e) {
			System.out.println(e);
			out.print(e.getMessage());
		}
		
	}

}
