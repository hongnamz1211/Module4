<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2/17/2022
  Time: 3:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/currency" method="get">
    <input type="text" name="exchangeRate" placeholder="tỉ giá">
    <input type="text" name="usd" placeholder="usd">
    <input type="submit" value="Convert">
</form>
<p>${result} VND</p>
</body>
</html>
