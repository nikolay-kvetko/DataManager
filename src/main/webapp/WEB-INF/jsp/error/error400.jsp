<%--
  Created by IntelliJ IDEA.
  User: Кузнец
  Date: 21.04.2015
  Time: 8:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>400</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/font-awesome.min.css">
    <link rel="stylesheet" href="/css/main.min.css">
</head>
<body class="error">
<div class="container">
    <div class="col-lg-8 col-lg-offset-2 text-center">
        <div class="logo">
            <h1>400</h1>
        </div>
        <p class="lead text-muted"><spring:message code="error.400"/></p>
        <div class="clearfix"></div>
        <br>
        <div class="col-lg-6 col-lg-offset-3">
            <div class="btn-group">
                <input type="button" value="Back" class="btn btn-info" onclick="history.go(-1);"/>
            </div>
        </div>
    </div>
</div>
</body>
</html>
