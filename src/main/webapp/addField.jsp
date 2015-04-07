<%--
  Created by IntelliJ IDEA.
  User: a.chervyakovsky
  Date: 06.04.2015
  Time: 14:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Field</title>
</head>
<body>
<form action="/saveField" method="post">
    <input type="text" name="fieldName"/>
    <input type="text" name="fieldType"/>
    <input type="submit"/>
</form>
</body>
</html>
