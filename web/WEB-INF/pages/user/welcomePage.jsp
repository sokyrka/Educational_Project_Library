<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
  Created by IntelliJ IDEA.
  User: Eugine Sokirka
  Date: 19.05.2015
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Welcome</title>
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>">
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/form_style.css"/>">
</head>
<body>
<div id="wrapper">
  <div id=form_wrapper>
    <form action="${pageContext.request.contextPath}/userCabinet.html" method="post">
      <div id="l">
        <p>Login</p>
        <p>Password</p>
      </div>
      <div id="r">
        <span><input type="text" name="login" class="text_field"></span><br><br>
        <span><input type="text" name="password" class="text_field"></span><br>
      </div>
      <input type="submit" value="Sign in" class="myButton">
      <button class="myButton" formaction="${pageContext.request.contextPath}/registerPage.html">Sign up</button><br><br>
      <span class="error">${msg}</span>
    </form>
  </div>
</div>
</body>
</html>
