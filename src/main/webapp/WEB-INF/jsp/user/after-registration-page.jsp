<%--
  Created by IntelliJ IDEA.
  User: a.chervyakovsky
  Date: 23.04.2015
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <title><spring:message code="after.registration.title"/></title>
</head>
<body>
<div class="user">
    <spring:message code="after.registration.message.first.sentence"/> <c:out value="${email}"/>. <spring:message code="after.registration.message.second.sentence"/>
    <a href="/"><spring:message code="after.registration.message.link"/></a>.
</div>
</body>
</html>
