<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<div class="container">
    <div class="page-header">
        <h3>
            <a href="/home/entity/list"><spring:message code="label.homepage"/></a> > <c:out
                value="${EntitySchema.name}"/>
        </h3>
    </div>
    <div class="row">
        <sec:authorize access="hasAnyRole('Admin','ReadWrite')">
            <div class="col-xs-3 col-xs-offset-9 col-sm-2 col-sm-offset-10">
                <form id="CreateInstance" action="/home/entity/${EntitySchema.id}/instance/create"
                      method="post"></form>
                <button form="CreateInstance" class="btn btn-success" data-toggle="modal"
                        data-target="#entityModal">
                    <span class="glyphicon glyphicon-plus-sign"></span> <spring:message code="button.createinstance"/>
                </button>
            </div>
        </sec:authorize>
    </div>
    <table class="table table-hover">
        <thead>
        <tr>
            <c:forEach var="field" items="${EntitySchema.fields}">
                <th>
                    <c:out value="${field.name}"/>
                </th>
            </c:forEach>
            <th>
                <spring:message code="label.lastmodified"/>
            </th>
            <th>
                <spring:message code="label.created"/>
            </th>
            <sec:authorize access="hasAnyRole('Admin','ReadWrite')">
                <th>
                    <spring:message code="label.action"/>
                </th>
            </sec:authorize>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="instance" items="${entityInstances}">
            <tr>
                <c:forEach var="field" items="${EntitySchema.fields}">
                    <c:set var="coincidence" value="false" scope="page"/>
                    <c:forEach var="value" items="${instance.values}">
                        <c:if test="${field.fieldId eq value.field.fieldId}">
                            <c:set var="coincidence" value="true" scope="page"/>
                            <c:set var="coincidedValue" value="${value}" scope="page"/>
                        </c:if>
                    </c:forEach>
                    <c:if test="${coincidence}">
                        <td>
                        <sec:authorize access="hasAnyRole('Admin','ReadWrite')">
                            <a href="/home/entity/${EntitySchema.id}/instance/edit/${instance.id}"
                               style="display: block; text-decoration: none">
                        </sec:authorize>
                                <c:choose>
                                    <c:when test="${coincidedValue.field.valueType eq 'STRING'}">
                                        <c:out value="${coincidedValue.value}"/>
                                    </c:when>
                                    <c:when test="${coincidedValue.field.valueType eq 'MULTI_CHOICE'}">
                                        <c:forEach var="choiceValue"
                                                   items="${coincidedValue.choices}">
                                            <c:out value="${choiceValue.name}"/> <br>
                                        </c:forEach>
                                    </c:when>
                                    <c:when test="${coincidedValue.field.valueType eq 'TEXT_AREA'}">
                                        <jsp:include page="/WEB-INF/jsp/instance/list/text-area.jsp">
                                            <jsp:param name="fieldValue" value="${coincidedValue.textAreaValue}"/>
                                        </jsp:include>
                                    </c:when>
                                    <c:when test="${coincidedValue.field.valueType eq 'NUMBER'}">
                                        <c:out value="${coincidedValue.numberValue}"/>
                                    </c:when>
                                    <c:when test="${coincidedValue.field.valueType eq 'DATE'}">
                                        <c:out value="${coincidedValue.dateValue}"/>
                                    </c:when>
                                    <c:when test="${coincidedValue.field.valueType eq 'IMAGE'}">
                                        <jsp:include page="/WEB-INF/jsp/instance/list/image.jsp">
                                            <jsp:param name="fieldValue" value="${coincidedValue.image}"/>
                                        </jsp:include>
                                    </c:when>
                                    <c:when test="${coincidedValue.field.valueType eq 'GPS'}">
                                        <c:out value="${coincidedValue.latitudeValue}"/><br><c:out
                                            value="${coincidedValue.longitudeValue}"/>
                                    </c:when>
                                    <c:when test="${coincidedValue.field.valueType eq 'LOOK_UP'}">
                                        <jsp:include page="/WEB-INF/jsp/instance/list/look-up.jsp">
                                            <jsp:param name="lookUpValue" value="${coincidedValue.lookUpValue}"/>
                                            <jsp:param name="lookUpType" value="${coincidedValue.lookUpType}"/>
                                        </jsp:include>
                                    </c:when>
                                </c:choose>
                            </a>
                        </td>
                    </c:if>
                    <c:if test="${!coincidence}">
                        <td></td>
                    </c:if>
                </c:forEach>
                <td>
                    <c:out value="${instance.modifiedDate}"/>
                </td>
                <td>
                    <c:out value="${instance.createDate}"/>
                </td>
                <sec:authorize access="hasAnyRole('Admin','ReadWrite')">
                    <td>
                        <a href="/home/entity/<c:out value="${EntitySchema.id}"/>/instance/delete/<c:out value="${instance.id}"/>/confirm"
                           title="Delete">
                        <span class="glyphicon glyphicon-trash"
                              style="font-size: 1.1em; color: #ff8018"></span>
                        </a>
                    </td>
                </sec:authorize>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>