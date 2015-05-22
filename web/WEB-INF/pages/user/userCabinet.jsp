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
  <title>Cabinet</title>
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>">
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/form_style.css"/>">
</head>
<body>
<div id="wrapper">
<h1>Hello ${msg}</h1>
  <form>
    <p>You can see all the available books</p>
    <button class="myButton" formaction="${pageContext.request.contextPath}/freeBooks.html">Free books</button>
    <p>You can find any book by title</p>
    <button class="myButton" formaction="${pageContext.request.contextPath}/findBook.html">Find book</button>
    <p>You can see the list of books that you have now</p>
    <button class="myButton" formaction="${pageContext.request.contextPath}/usersBook.html">My book</button>
  </form>
</div>
</body>
</html>
