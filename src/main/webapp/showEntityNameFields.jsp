<%--
  Created by IntelliJ IDEA.
  User: a.chervyakovsky
  Date: 06.04.2015
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>List of EntityName's Fields</title>
</head>
<body>
<a href="/addFieldToEntityName?entityNameId=<c:out value="${entityName.entityNameId}"/>">
    Add Field
</a>
<br>
<ul>
    <c:forEach var="field" items="${entityName.fields}">
        <li><c:out value="${field.name}"/></li>
    </c:forEach>
</ul>
</body>
</html>
