<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<div class="container">
    <div class="page-header">
        <h2>Home Page</h2>
    </div>
    <div class="row">
        <div class="col-xs-3 col-xs-offset-9 col-sm-2 col-sm-offset-10">
            <form id="CreateInstance" action="/home/entity/${EntitySchema.id}/instance/create" method="post"></form>
            <button form="CreateInstance" class="btn btn-success" data-toggle="modal" data-target="#entityModal">
                <span class="glyphicon glyphicon-plus-sign"></span> Create Instance
            </button>
        </div>
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
                Action
            </th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="instance" items="${entityInstances}">
            <tr>
                <c:forEach var="value" items="${instance.values}">
                    <td>
                        <c:choose>
                            <c:when test="${value.field.valueType eq 'STRING'}">
                                <c:out value="${value.value}"/>
                            </c:when>
                            <c:when test="${value.field.valueType eq 'MULTI_CHOICE'}">
                                <c:forEach var="choiceValue" items="${value.choices}">
                                    <c:out value="${choiceValue.name}"/> <br>
                                </c:forEach>
                            </c:when>
                            <c:when test="${value.field.valueType eq 'TEXT_AREA'}">
                                <textarea style="resize: none; border: none; background: none" readonly
                                          rows="<c:out value="${value.field.countLine}"/>"><c:out
                                        value="${value.textAreaValue}"/></textarea>
                            </c:when>
                        </c:choose>
                    </td>
                </c:forEach>
                <td>
                    <a href="/home/entity/<c:out value="${EntitySchema.id}"/>/instance/delete/<c:out value="${instance.id}"/>/confirm"
                       title="Delete">
                        <span class="glyphicon glyphicon-trash" style="font-size: 1.1em; color: #ff8018"></span>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
