<%@page import="com.lms.model.Book"%>
<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%

%>
<h1>Change Any book description of the book  ${EditBook.book_name}</h1>

<p>${ EditBook.book_name}</p>
<p>${ EditBook.book_author} </p>
<p>${ EditBook.book_description }</p>

<form action="Edit" method="post">
	<input type="hidden" name="editedIsbn" value="${ EditBook.book_isbn }"/>
	<input type="text" name="editedTitle" value="${ EditBook.book_name}" />
	<input type="text" name="editedAuthor" value="${ EditBook.book_author }" />
	<input type="text" name="editedDescription" value="${ EditBook.book_description }"/>
	<input type="submit" value="submit"/>
</form>


</body>
</html>