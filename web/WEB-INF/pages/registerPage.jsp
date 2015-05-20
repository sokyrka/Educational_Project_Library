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
      width:280px;
      height:175px;
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
<div id="wrapper">
  <div id="names">
    <p>First Name</p>
    <p>Second Name</p>
    <p>Login</p>
    <p>Password</p>
  </div>
  <div id="form">
    <form action="${pageContext.request.contextPath}/successRegister.html" method="post">
      <input type="text" name="first_name"><br><br>
      <input type="text" name="second_name"><br><br>
      <input type="text" name="login"><br><br>
      <input type="text" name="password"><br><br>
      <input type="submit" value="Register"><br><br>
      ${msg}
    </form>
  </div>
</div>
</body>
</html>
