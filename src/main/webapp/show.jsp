<%--
  Created by IntelliJ IDEA.
  User: a.chervyakovsky
  Date: 06.04.2015
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All Entities</title>
</head>
<body>
<c:set var="entityNames" value="${entityNamesList}"/>
<ul>
    <c:forEach var="entity" items="${entityNames}">
        <li>
            <a href="/show?entityNameId=<c:out value="${entity.entityNameId}"/>">
                <c:out value="${entity.name}"/>
            </a>
        </li>
    </c:forEach>
</ul>
</body>
</html>
