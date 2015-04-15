<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="myModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content alert-danger">
            <div class="modal-body">
                <c:choose>
                    <c:when test="${EntitySchema != null && field eq null && entityInstance eq null}">
                        <h4>Are you sure you want to delete "<c:out value="${EntitySchema.name}"/>"</h4>
                    </c:when>

                    <c:when test="${entityInstance != null}">
                        <h4>Are you sure you want to delete this instance of "<c:out value="${EntitySchema.name}"/>"</h4>
                    </c:when>

                    <c:otherwise>
                        <h4>Are you sure you want to delete "<c:out value="${field.name}"/>"</h4>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="modal-footer">
                <c:choose>
                    <c:when test="${EntitySchema != null && field eq null && entityInstance eq null}">
                        <form id="Cancel" action="/entity/list" method="get"></form>
                        <form id="Delete" action="/entity/delete/<c:out value="${EntitySchema.id}"/>" method="get"></form>
                        <button form="Cancel" type="submit" class="btn btn-default">Cancel</button>
                        <button form="Delete" type="submit" class="btn btn-danger">Delete</button>
                    </c:when>

                    <c:when test="${entityInstance != null}">
                        <form id="Cancel" action="/home/entity/<c:out value="${EntitySchema.id}"/>/instance/list" method="get"></form>
                        <form id="Delete" action="/home/entity/<c:out value="${EntitySchema.id}"/>/instance/delete/<c:out value="${entityInstance.id}"/>" method="get"></form>
                        <button form="Cancel" type="submit" class="btn btn-default">Cancel</button>
                        <button form="Delete" type="submit" class="btn btn-danger">Delete</button>
                    </c:when>

                    <c:otherwise>
                        <form id="Cancel" action="/entity/<c:out value="${EntitySchema.id}"/>/field/list" method="get"></form>
                        <form id="Delete" action="/entity/<c:out value="${EntitySchema.id}"/>/field/delete/<c:out value="${field.fieldId}"/>" method="get"></form>
                        <button form="Cancel" type="submit" class="btn btn-default">Cancel</button>
                        <button form="Delete" type="submit" class="btn btn-danger">Delete</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>