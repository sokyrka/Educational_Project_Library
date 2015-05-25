<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Eugine Sokirka
  Date: 25.05.2015
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Deleted book</title>
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>">
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/form_style.css"/>">
</head>
<body>
<div id="wrapper" style="height: auto; width: 240px">
  <h1 align="center">Book deleted</h1>
  <form action="${pageContext.request.contextPath}/admin.html" method="post">
    <input type="submit" value="Back to admin cabinet" class="myButton">
  </form>
</div>
</body>
</html>
