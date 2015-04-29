<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    window.setTimeout(function () {
        location.href = document.getElementsByClassName("redirect-create-company")[0].href;
    }, 4000);
</script>
<div class="container">
    <div class="jumbotron">
        <h2><spring:message code="email.confirmation"/></h2>

        <p><spring:message code="email.confirmation.message"/></p>
    </div>
    <a hidden="hidden" class="redirect-create-company" href="/registration/company/create"></a>
</div>
