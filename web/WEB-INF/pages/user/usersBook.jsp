<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.work.common.Book" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Eugine Sokirka
  Date: 21.05.2015
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Users Books</title>
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>">
</head>
<body>
<form action="${pageContext.request.contextPath}/deletedBook.html" method="post">
  <table style="border: 1px solid; width: 500px; text-align:center">
    <thead style="background:#22ff1f">
    <tr>
      <th>Title</th>
      <th>Author</th>
      <th>Year</th>
      <th>Pages</th>
      <th>Delete</th>
    </tr>
    </thead>
    <%List<Book> array = (ArrayList<Book>) request.getAttribute("usersBook");%>
    <%if(array.isEmpty()){%>
    <tr>
      <h1>There are no books</h1>
    </tr>
    <%}%>
    <%for(Book book : array){%>
    <tr>
      <td><%=book.getTitle()%></td>
      <td><%=book.getAuthor()%></td>
      <td><%=book.getYear()%></td>
      <td><%=book.getPages()%></td>
      <td><input type="checkbox" name="check" value="<%=book.getTitle()%>"></td>
    </tr>
    <%}%>
  </table><br>
  ${msg}<br>
  <input type="submit" value="Submit">
</form>
</body>
</html>
