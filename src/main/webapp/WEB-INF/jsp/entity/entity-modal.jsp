<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="myModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><c:out value="${modalTitle}"/></h4>
            </div>
            <div class="modal-body form-horizontal">
                <spring:url var = "actionEdited" value='/entity/save'/>
                <s:form id="EntitySchema" name="EntitySchema" action="${actionEdited}" modelAttribute="EntitySchema" method="post">
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Entity Name*</label>
                        <div class="col-sm-8">
                            <s:input type="hidden" path="id"/>
                            <s:input class="form-control" type="text"  placeholder="Entity Name" path="name" required="required"/>
                        </div>
                    </div>
                </s:form>
            </div>
            <div class="modal-footer">
                <c:choose>
                    <c:when test="${modalSaveButton eq 'Create'}">
                        <form id="Cancel" action="/entity/list" method="get"></form>
                        <button form="Cancel" type="submit" class="btn btn-default">Cancel</button>
                        <button form="EntitySchema" type="submit" class="btn btn-primary"><c:out value="${modalSaveButton}"/></button>
                    </c:when>
                    <c:otherwise>
                        <form id="Cancel" action="/entity/<c:out value="${EntitySchema.id}"/>/field/list" method="get"></form>
                        <button form="Cancel" type="submit" class="btn btn-default">Cancel</button>
                        <button form="EntitySchema" type="submit" class="btn btn-primary"><c:out value="${modalSaveButton}"/></button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>

