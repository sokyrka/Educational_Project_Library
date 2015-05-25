<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Eugine Sokirka
  Date: 25.05.2015
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Delete book</title>
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>">
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/form_style.css"/>">
</head>
<body>
<div id="wrapper">
  <div id=form_wrapper>
    <form action="${pageContext.request.contextPath}/successfulDeletedBook.html" method="post">
      <div id="l">
        <p>Title</p>
        <p>Author</p>
      </div>
      <div id="r">
        <span><input type="text" name="title" class="text_field"></span><br><br>
        <span><input type="text" name="author" class="text_field"></span><br>
      </div>
      <input type="submit" value="Delete" class="myButton">
      <span class="error">${msg}</span>
    </form>
  </div>
</div>
</body>
</html>
