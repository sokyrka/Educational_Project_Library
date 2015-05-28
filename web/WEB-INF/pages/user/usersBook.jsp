<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.work.entity.Book" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ResourceBundle" %>
<%--
  Created by IntelliJ IDEA.
  User: Eugine Sokirka
  Date: 21.05.2015
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%ResourceBundle resourceBundle = ResourceBundle.getBundle("message", request.getLocale());%>
<html>
<head>
  <title>Users Books</title>
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>">
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/form_style.css"/>">
</head>
<body>
<%String login = (String) request.getSession().getAttribute("user_login");%>
<%String password = (String) request.getSession().getAttribute("user_pass");%>
<form action="${pageContext.request.contextPath}/userCabinet.html" method="post" style="float: left">
  <input type="hidden" name="login" value="<%=login%>">
  <input type="hidden" name="password" value="<%=password%>">
  <input type="submit" value="<%=resourceBundle.getString("label.back")%>" class="myButton">
</form>
<div id="wrapper" style="height: auto; width: 500px">
  <form action="${pageContext.request.contextPath}/deletedBook.html" method="post">
    <table style="width: 500px; text-align:center">
      <thead style="background:#f7ff2c">
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
    <input class="myButton" type="submit" value="<%=resourceBundle.getString("label.submit")%>">
  </form>
</div>
</body>
</html>
