<%@ page import="com.work.common.Request" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Eugine Sokirka
  Date: 25.05.2015
  Time: 12:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Requests</title>
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>">
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/form_style.css"/>">
</head>
<body>
<form style="float: left">
  <button class="myButton" formaction="${pageContext.request.contextPath}/admin.html">Back</button>
</form>
<div id="wrapper" style="height: auto; width: 400px;">
  <form action="${pageContext.request.contextPath}/updatedRequest.html" method="post">
    <table style="width: 400px; text-align:center">
      <thead style="background:#f7ff2c">
      <tr>
        <th>Book</th>
        <th>User</th>
        <th>To home</th>
        <th>To library</th>
        <th>Done</th>
      </tr>
      </thead>
      <%List<Request> array = (ArrayList<Request>) request.getAttribute("requestList");%>
      <%if(array.isEmpty()){%>
      <tr>
        <h1>There are no requests</h1>
      </tr>
      <%}%>
      <%for(Request request1 : array){%>
      <tr>
        <td><%=request1.getBook_id()%></td>
        <td><%=request1.getUser_id()%></td>
        <td><input type="radio" name="radio_param" value="home"></td>
        <td><input type="radio" name="radio_param" value="library>"></td>
        <td><input type="checkbox" name="request_id" value="<%=request1.getRequest_id()%>"></td>
      </tr>
      <%}%>
    </table><br>
    <input class="myButton" type="submit" value="Submit">
  </form>
</div>
</body>
</html>
