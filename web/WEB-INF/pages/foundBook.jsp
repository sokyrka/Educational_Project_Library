<%@ page import="com.work.common.Book" %>
<%--
  Created by IntelliJ IDEA.
  User: Eugine Sokirka
  Date: 20.05.2015
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
  <table style="border: 1px solid; width: 500px; text-align:center">
    <thead style="background:#22ff1f">
    <tr>
      <th>Title</th>
      <th>Author</th>
      <th>Year</th>
      <th>Pages</th>
      <th>Add</th>
    </tr>
    </thead>
    <%Book book = (Book) request.getAttribute("book");%>
    <tr>
      <td><%=book.getTitle()%></td>
      <td><%=book.getAuthor()%></td>
      <td><%=book.getYear()%></td>
      <td><%=book.getPages()%></td>
      <td><input type="checkbox"></td>
    </tr>
  </table>
</body>
</html>
