<%@ page import="java.util.ResourceBundle" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Eugine Sokirka
  Date: 21.05.2015
  Time: 14:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%ResourceBundle resourceBundle = ResourceBundle.getBundle("message", request.getLocale());%>
<html>
<head>
  <title>Added book</title>
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>">
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/form_style.css"/>">
</head>
<body>
<div id="wrapper" style="height: auto; width: 500px">
  <h1 align="center">${msg}</h1>
  <%String login = (String) request.getSession().getAttribute("user_login");%>
  <%String password = (String) request.getSession().getAttribute("user_pass");%>
  <form action="${pageContext.request.contextPath}/userCabinet.html" method="post">
    <input type="hidden" name="login" value="<%=login%>">
    <input type="hidden" name="password" value="<%=password%>">
    <input type="submit" value="<%=resourceBundle.getString("label.back")%>" class="myButton" align="center">
  </form>
</div>
</body>
</html>
