<%@ page import="java.util.List" %>
<%@ page import="com.work.common.Book" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Eugine Sokirka
  Date: 20.05.2015
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Free books</title>
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>">
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/form_style.css"/>">
</head>
<body>
<div id="wrapper" style="height: auto; width: 500px">
  <form action="${pageContext.request.contextPath}/addedBook.html" method="post">
    <table style="border: 1px solid; width: 500px; text-align:center">
      <thead style="background:#f7ff2c">
      <tr>
        <th>Title</th>
        <th>Author</th>
        <th>Year</th>
        <th>Pages</th>
        <th>Add</th>
      </tr>
      </thead>
      <%List<Book> array = (ArrayList<Book>) request.getAttribute("books");%>
      <%if(array.isEmpty()){%>
      <tr>
        <h1>There are no free books</h1>
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
      <input class="myButton" type="submit" value="Submit">
  </form>
</div>
</body>
</html>
