<%--
  Created by IntelliJ IDEA.
  User: Кузнец
  Date: 26.04.2015
  Time: 4:09
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<div class="container">
    <div class="page-header">
        <h3><spring:message code="label.users"/></h3>
    </div>
    <div class="row">
        <sec:authorize access="hasRole('Admin')">
            <div class="col-xs-4 col-xs-offset-8 col-sm-2 col-sm-offset-10">
                <button form="createUser" class="btn btn-success" data-toggle="modal" data-target="#entityModal">
                    <span class="glyphicon glyphicon-plus-sign"></span> <spring:message code="button.create.user"/>
                </button>
            </div>
        </sec:authorize>
    </div>
    <table class="table table-hover">
        <thead>
        <tr>
            <th><spring:message code="label.user"/></th>
            <th><spring:message code="label.email"/></th>
            <th><spring:message code="label.role"/></th>
            <th><spring:message code="label.lastmodified"/></th>
            <th><spring:message code="label.created"/></th>
            <th><spring:message code="label.confirmed"/></th>
            <sec:authorize access="hasRole('Admin')">
                <th><spring:message code="label.action"/></th>
            </sec:authorize>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="user" items="${usersList}">
            <tr>
                <td>
                    <sec:authorize access="hasRole('Admin')">
                        <a href="/manage_users/edit/<c:out value="${user.userId}"/>" style="display: block; text-decoration: none">
                    </sec:authorize>
                            <c:out value="${user.firstName}"/>
                            <c:out value="${user.lastName}"/>
                        </a>
                </td>
                <td>
                    <c:out value="${user.email}"/>
                </td>
                <td>
                    <c:out value="${user.role.name}"/>
                </td>
                <td>
                    <c:out value="${user.modifiedDate}"/>
                </td>
                <td>
                    <c:out value="${user.createDate}"/>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${user.confirmed eq true}">
                            <span class="glyphicon glyphicon-ok" style="font-size: 1.5em; color: #4cae4c"></span>
                        </c:when>
                        <c:otherwise>
                            <span class="glyphicon glyphicon-remove" style="font-size: 1.5em; color: orangered"></span>
                        </c:otherwise>
                    </c:choose>
                </td>
                <sec:authorize access="hasRole('Admin')">
                    <td>
                        <c:if test="${user.role.name != 'Admin'}">
                            <a href="/manage_users/delete/<c:out value="${user.userId}"/>/confirm"
                               title="<spring:message code="label.delete"/> <c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/>">
                                <span class="glyphicon glyphicon-trash" style="font-size: 1.1em; color: #ff8018"></span>
                            </a>
                        </c:if>
                    </td>
                </sec:authorize>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <form id="createUser" action="/manage_users/create" method="post"></form>
</div>
