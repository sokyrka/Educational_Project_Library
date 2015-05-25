<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Eugine Sokirka
  Date: 19.05.2015
  Time: 12:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Admin cabinet</title>
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>">
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/form_style.css"/>">
</head>
<body>
<h1>Hello Admin</h1>
  <form style="float: left">
    <button class="myButton" formaction="${pageContext.request.contextPath}/addBook.html">Add book</button>
    <button class="myButton" formaction="${pageContext.request.contextPath}/deleteBook.html">Delete book</button>
    <button class="myButton" formaction="${pageContext.request.contextPath}/allRequests.html">Requests</button>
  </form>
</body>
</html>
