<%--
  Created by IntelliJ IDEA.
  User: Eugine Sokirka
  Date: 18.05.2015
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="${pageContext.request.contextPath}/success.html" method="get">
  <input type="text" name="first_name"><br><br>
  <input type="text" name="second_name"><br><br>
  <input type="text" name="login"><br><br>
  <input type="text" name="password"><br><br>
  <input type="text" name="book_id"><br><br>
  <input type="submit" value="Add">
  <h3 style="color: red">${msg}</h3>
</form>
</body>
</html>
