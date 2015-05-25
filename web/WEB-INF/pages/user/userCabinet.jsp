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
  <title>User cabinet</title>
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>">
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/form_style.css"/>">
</head>
<body>
<h1>Hello ${msg}</h1>
  <form style="float: left">
    <button class="myButton" formaction="${pageContext.request.contextPath}/freeBooks.html">Free books</button>
    <button class="myButton" formaction="${pageContext.request.contextPath}/findBook.html">Find book</button>
    <button class="myButton" formaction="${pageContext.request.contextPath}/usersBook.html">My book</button>
  </form>
</body>
</html>
