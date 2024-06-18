<%@page import="java.net.URLEncoder"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function change(){
	let inp = document.getElementById("bookname").value ;
	var formElement = document.getElementById("searchForm");
    formElement.action = "GetAll?bookname=" + encodeURIComponent(inputElement.value);
}
</script>
</head>

<body>


<p>This is admin panel</p>

<form id="searchForm" method="get">
	<input type="text" id="bookname" name="bookname" onchange="change()"/>
	<input type="submit" value="submit" />
</form>

 		<table>
 		<tr>
 		<th> ISBN </th>
 		<th> Name </th>
 		<th> Author </th>
 		<th> Description </th>
 		</tr>
        <c:forEach var="item" items="${bookList}">
            <tr>
            	<td> ${item.book_isbn} </td>
            	<td>  ${item.book_name} </td>
            	<td> ${item.book_author} </td>
            	<td> ${item.book_description}</td>
            	<td> <a href="Delete?book_isbn=${item.book_isbn}">Delete</a> </td>
            <td> <a href="Edit?book_isbn=${item.book_isbn}&book_title=${item.book_name}&book_author=${item.book_author}&book_description=${item.book_description}">Edit</a>
 </td>
            </tr>
        </c:forEach>
        </table>

<a href="add.jsp"><button>Add</button></a>

<c:url value="/error.jsp"/>
<c:redirect url="http://facebook.com"/>    
</body>
</html>