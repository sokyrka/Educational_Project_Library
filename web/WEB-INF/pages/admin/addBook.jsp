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
  <title>Add book</title>
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>">
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/form_style.css"/>">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body>
<form style="float: left">
  <button class="myButton" formaction="${pageContext.request.contextPath}/admin.html">Back</button>
</form>
<div id="wrapper" style="height: 300px; width: 300px">
  <div id=form_wrapper>
    <form action="${pageContext.request.contextPath}/successfulAddedBook.html" method="post">
      <div id="l">
        <p>Title</p>
        <p>Author</p>
        <p>Year</p>
        <p>Pages</p>
      </div>
      <div id="r">
        <input required type="text" name="title" class="text_field"><br><br>
        <input required type="text" name="author" class="text_field"><br><br>
        <input required type="text" name="year" class="text_field"><br><br>
        <input required type="text" name="pages" class="text_field"><br><br>
      </div>
      <input type="submit" value="Add" class="myButton"><br><br>
      <span class="error">${msg}</span>
    </form>
  </div>
</div>
</body>
</html>
