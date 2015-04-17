<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<div class="container">
    <div class="page-header">
        <h3><a href="/entity/list">Data Structure</a> > <c:out value="${EntitySchema.name}"/>
            <a href="/entity/edit/<c:out value="${EntitySchema.id}"/>" title="Edit <c:out value="${EntitySchema.name}"/>">
                <span class="glyphicon glyphicon-edit" style="font-size: 0.8em"></span>
            </a>
        </h3>
    </div>
    <div class="row">
        <div class="col-xs-4 col-xs-offset-2 col-sm-2 col-sm-offset-10">
            <div class="dropdown">
                <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown"
                        aria-expanded="false">
                    Add Field
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" role="menu">
                    <li><a href="/entity/<c:out value="${EntitySchema.id}"/>/field/create/string">Text Field</a></li>
                    <li><a href="/entity/<c:out value="${EntitySchema.id}"/>/field/create/text_area">Text Area</a></li>
                    <li><a href="/entity/<c:out value="${EntitySchema.id}"/>/field/create/multi_choice">Choice Field</a></li>
                    <li><a href="/entity/<c:out value="${EntitySchema.id}"/>/field/create/number">Number Field</a></li>
                    <li><a href="/entity/<c:out value="${EntitySchema.id}"/>/field/create/look_up">Look Up</a></li>
                    <li><a href="/entity/<c:out value="${EntitySchema.id}"/>/field/create/image">Image Field</a></li>
                </ul>
            </div>
        </div>
    </div>
    <table class="table table-hover">
        <thead>
        <tr>
            <th>Field Name</th>
            <th>Required</th>
            <th>Last Modified</th>
            <th>Created</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="field" items="${EntitySchema.fields}">
            <tr>
                <td>
                    <a href="/entity/<c:out value="${EntitySchema.id}"/>/field/edit/<c:out value="${field.fieldId}"/>"
                       style="display: block; text-decoration: none">
                        <c:out value="${field.name}"/>
                    </a>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${field.require eq true}">
                            <span class="glyphicon glyphicon-ok" style="font-size: 1.5em; color: #4cae4c"></span>
                        </c:when>
                        <c:otherwise>
                            <span class="glyphicon glyphicon-remove" style="font-size: 1.5em; color: orangered"></span>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:out value="${field.modifiedDate}"/>
                </td>
                <td>
                    <c:out value="${field.createDate}"/>
                </td>
                <td>
                    <a href="/entity/<c:out value="${EntitySchema.id}"/>/field/delete/<c:out value="${field.fieldId}"/>/confirm"
                       title="Delete <c:out value="${field.name}"/>">
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
                <li><a href="#">First page</a></li>
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
                <li><a href="#">Last page</a></li>
            </ul>
        </div>
    </div>
</div>
