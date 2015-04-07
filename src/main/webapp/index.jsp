<%--
  Created by IntelliJ IDEA.
  User: a.chervyakovsky
  Date: 06.04.2015
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<c:set var="entityNames" scope="session" value="${entityNamesList}"/>
<h2>Hello World!</h2>
<br>
<ul>
    <c:forEach var="field" items="${entityNames}">
        <li><c:out value="${field.name}"/></li>
    </c:forEach>
</ul>
</body>
</html>
