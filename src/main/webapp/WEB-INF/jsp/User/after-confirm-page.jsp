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
    <title><spring:message code="after.confirm.title"/></title>
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script>
        window.setTimeout(function() {
            location.href = document.getElementsByClassName("redirect-create-company")[0].href;
        }, 4000);
    </script>
</head>
<body>
<div>
    <spring:message code="after.confirm.message"/>
    <a hidden="hidden" class="redirect-create-company" href="/registration/company/create"></a>
</div>
</body>
</html>
