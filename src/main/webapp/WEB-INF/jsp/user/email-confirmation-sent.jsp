<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
    <div class="jumbotron">
        <h2><spring:message code="email.confirmation"/></h2>
        <p><spring:message code="email.confirmation.sent.message"/></p>
        <p><a class="btn btn-primary btn-lg" href="#" role="button"><spring:message code="button.tologin"/></a></p>
    </div>
</div>
