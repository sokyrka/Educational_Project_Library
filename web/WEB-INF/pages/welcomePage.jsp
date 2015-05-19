<%--
  Created by IntelliJ IDEA.
  User: Eugine Sokirka
  Date: 19.05.2015
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Welcome</title>
  <style>
    #form{
      float:left;
    }
    #names{
      float:left;
      padding:0px;
      margin-top:-10px;
      margin-right:10px;
    }
    #wrapper{
      width:250px;
      height:100px;
      border:1px dotted black;
      border-radius:15px;
      padding:15px;
      margin:auto;
      position:relative;
      top:30%;

    }
  </style>
</head>
<body>
<h3>${msg}</h3>
<div id="wrapper">
  <div id="names">
    <p>Login</p>
    <p>Password</p>
  </div>
  <div id="form">
    <form action="${pageContext.request.contextPath}/userCabinet.html" method="post">
      <input type="text" name="login"><br><br>
      <input type="text" name="password"><br><br>
      <input type="submit" value="Sign in">
      <button formaction="${pageContext.request.contextPath}/registerPage.html">Sign up</button>
    </form>
  </div>
</div>
</body>
</html>
