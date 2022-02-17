<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2/17/2022
  Time: 4:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/dictionary/dictionary" method="get">
    <input type="text" name="key">
    <input type="submit" value="Convert">
</form>
<p>${result}</p>
</body>
</html>
