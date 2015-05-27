<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Eugine Sokirka
  Date: 19.05.2015
  Time: 12:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Register</title>
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>">
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/form_style.css"/>">
</head>
<body>
<div id="wrapper" style="height: 300px; width: 400px">
  <div id=form_wrapper>
    <form action="${pageContext.request.contextPath}/successRegister.html" method="post">
      <div id="l">
      <p>First Name</p>
      <p>Second Name</p>
      <p>Login</p>
      <p>Password</p>
      </div>
      <div id="r">
      <input required type="text" name="first_name" class="text_field"><br><br>
      <input required type="text" name="second_name" class="text_field"><br><br>
      <input required type="text" name="login" class="text_field"><br><br>
      <input required type="password" name="password" class="text_field"><br><br>
      </div>
      <input type="submit" value="Register" class="myButton"><br><br>
      <span class="error">${msg}</span>
    </form>
  </div>
</div>
</body>
</html>
