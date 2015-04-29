<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div class="container">
    <div class="page-header">
        <h3><spring:message code="label.datastructure"/></h3>
    </div>
    <div class="row">
        <sec:authorize access="hasAnyRole('Admin','ReadWrite')">
            <div class="col-xs-4 col-xs-offset-8 col-sm-2 col-sm-offset-10">
                <button form="CreateEntitySchema" class="btn btn-success" data-toggle="modal" data-target="#entityModal">
                    <span class="glyphicon glyphicon-plus-sign"></span> <spring:message code="button.createentity"/>
                </button>
            </div>
        </sec:authorize>
    </div>
    <table class="table table-hover">
        <thead>
        <tr>
            <th><spring:message code="label.entities"/></th>
            <th><spring:message code="label.lastmodified"/></th>
            <th><spring:message code="label.created"/></th>
            <sec:authorize access="hasAnyRole('Admin','ReadWrite')">
                <th><spring:message code="label.action"/></th>
            </sec:authorize>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="entity" items="${entitySchemaList}">
            <tr>
                <td>
                    <a href="/entity/<c:out value="${entity.id}"/>/field/list" style="display: block; text-decoration: none">
                        <c:out value="${entity.name}"/>
                    </a>
                </td>
                <td>
                    <c:out value="${entity.modifiedDate}"/>
                </td>
                <td>
                    <c:out value="${entity.createDate}"/>
                </td>
                <sec:authorize access="hasAnyRole('Admin','ReadWrite')">
                    <td>
                        <a href="/entity/delete/<c:out value="${entity.id}"/>/confirm" title="<spring:message code="label.delete"/> <c:out value="${entity.name}"/>">
                            <span class="glyphicon glyphicon-trash" style="font-size: 1.1em; color: #ff8018"></span>
                        </a>
                    </td>
                </sec:authorize>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <form id="CreateEntitySchema" action="/entity/create" method="post"></form>
</div>
