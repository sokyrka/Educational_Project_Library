<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Eugine Sokirka
  Date: 20.05.2015
  Time: 17:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Find book</title>
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>">
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/form_style.css"/>">
</head>
<body>
  <div id="wrapper" style="height: 130px;">
    <div id=form_wrapper>
      <form action="${pageContext.request.contextPath}/foundBook.html" method="post">
      <div id="l">
        <p style="font-size: 20px">Title</p>
      </div>
      <div id="r">
        <input type="text" name="title" class="text_field"><br><br>
        <input type="submit" value="Find" class="myButton">
      </div><br><br>
      </form>
    </div><br><br>
    <span class="error">${msg}</span>
  </div>
</body>
</html>
