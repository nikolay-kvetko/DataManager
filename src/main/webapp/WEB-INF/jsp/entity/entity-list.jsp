<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<div class="container">
    <div class="page-header">
        <h2><spring:message code="label.datastructure"/></h2>
    </div>
    <div class="row">
        <div class="col-xs-4 col-xs-offset-8 col-sm-2 col-sm-offset-10">
            <button form="CreateEntitySchema" class="btn btn-success" data-toggle="modal" data-target="#entityModal"><span class="glyphicon glyphicon-plus-sign"></span> <spring:message code="button.createentity"/></button>
        </div>
    </div>
    <table class="table table-hover">
        <thead>
        <tr>
            <th><spring:message code="label.entities"/></th>
            <th><spring:message code="label.lastmodified"/></th>
            <th><spring:message code="label.created"/></th>
            <th><spring:message code="label.action"/></th>
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
                <td>
                    <a href="/entity/delete/<c:out value="${entity.id}"/>/confirm" title="<spring:message code="label.delete"/> <c:out value="${entity.name}"/>">
                        <span class="glyphicon glyphicon-trash" style="font-size: 1.1em; color: #ff8018"></span>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="row">
        <div class="col-xs-6 col-xs-offset-3 col-sm-6 col-sm-offset-3">
            <ul class="pagination">
                <li><a href="#"><spring:message code="label.firstpage"/></a></li>
                <li>
                    <a href="#" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <li class="active"><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
                <li>
                    <a href="#" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
                <li><a href="#"><spring:message code="label.lastpage"/></a></li>
            </ul>
        </div>
    </div>
    <form id="CreateEntitySchema" action="/entity/create" method="post"></form>
</div>
