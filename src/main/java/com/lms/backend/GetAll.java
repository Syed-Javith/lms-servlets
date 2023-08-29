package com.lms.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.model.Book;

/**
 * Servlet implementation class GetAll
 */
@WebServlet("/GetAll")
public class GetAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Book> bookList = new ArrayList<Book>();
		PrintWriter out = response.getWriter();
		//connect to database and set the values
		String search = request.getParameter("bookname");
		String stmt ;
		try {
			System.out.println(search);
			if(search == null) {	
				stmt = "SELECT * FROM books";
			}else {
				stmt = "SELECT * FROM books WHERE title LIKE '%" + search +"%'" ;
			}
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management_system","root", "root123");
			PreparedStatement pd = con.prepareStatement(stmt);
//			pd.setString(1, "A Song of Ice & Fire");
			ResultSet rs = pd.executeQuery();
			
			while(rs.next()) {
				String book_name = rs.getString("title");
				String book_author = rs.getString("author");
				String book_isbn = rs.getString("isbn");
				String book_description = rs.getString("description");
				
				Book book = new Book(book_name,book_author,book_isbn, book_description);
				
				System.out.println(book_name + " "+ book_author + " "+ book_isbn);
				
				bookList.add(book);
				
				
			}
			
		}catch(Exception e) {
			System.out.print(e);
			out.print(e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("index.html");
			rd.include(request, response);
			
		}
		
		request.setAttribute("bookList", bookList);
		out.print("<h1>Hello Admin</h1>");
		RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
		rd.include(request, response);
		
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}


//
//<% 
//String isbn =  request.getParameter("bookIsbnToBeEdited");
//String title = request.getParameter("bookTitleToBeEdited"); 
//String author = request.getParameter("bookAuthorToBeEdited");
//String description = request.getParameter("bookDescriptionToBeEdited");
//%>