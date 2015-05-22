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
      height:60px;
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
      <p>Title</p>
    </div>
    <div id="form">
      <form action="${pageContext.request.contextPath}/foundBook.html" method="post">
        <input type="text" name="title"><br><br>
        <input type="submit" value="Find"><br><br>
        ${msg}
      </form>
    </div>
  </div>
</body>
</html>
