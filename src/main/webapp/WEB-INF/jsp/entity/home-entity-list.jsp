<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<div class="container">
    <div class="page-header">
        <h3><spring:message code="label.homepage"/></h3>
    </div>
    <div class="row"></div>

    <table class="table table-hover">
        <thead>
        <tr>
            <th><spring:message code="label.entities"/></th>
            <th><spring:message code="label.lastmodified"/></th>
            <th><spring:message code="label.created"/></th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="entity" items="${entitySchemaList}">
            <tr>
                <td>
                    <a href="/home/entity/<c:out value="${entity.id}"/>/instance/list" style="display: block; text-decoration: none">
                        <c:out value="${entity.name}"/>
                    </a>
                </td>
                <td>
                    <c:out value="${entity.modifiedDate}"/>
                </td>
                <td>
                    <c:out value="${entity.createDate}"/>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>
