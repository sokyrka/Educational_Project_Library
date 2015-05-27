<%@ page import="java.util.ResourceBundle" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Eugine Sokirka
  Date: 19.05.2015
  Time: 12:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%ResourceBundle resourceBundle = ResourceBundle.getBundle("message", request.getLocale());%>
<html>
<head>
  <title>User cabinet</title>
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>">
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/form_style.css"/>">
</head>
<body>
<h1><%=resourceBundle.getString("label.hello")%> ${msg}</h1>
  <form style="float: left">
    <button class="myButton" formaction="${pageContext.request.contextPath}/freeBooks.html"><%=resourceBundle.getString("label.free-book")%></button>
    <button class="myButton" formaction="${pageContext.request.contextPath}/findBook.html"><%=resourceBundle.getString("label.find-book")%></button>
    <button class="myButton" formaction="${pageContext.request.contextPath}/usersBook.html"><%=resourceBundle.getString("label.my-book")%></button>
  </form>
</body>
</html>
