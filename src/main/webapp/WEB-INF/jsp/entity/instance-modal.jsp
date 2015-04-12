<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="myModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Create Instance</h4>
            </div>
            <div class="modal-body form-horizontal">
                <spring:url var = "action" value='/home/entity/${EntitySchema.id}/instance/add'/>
                <form id="Instance" name="Instance" action="${action}" method="post">
                    <div class="form-group">
                        <c:forEach var="field" items="${EntitySchema.fields}">
                            <c:choose>
                                <c:when test="${field.valueType eq 'STRING'}">
                                    <label class="col-sm-4 control-label"><c:out value="${field.name}"/></label>
                                    <div class="col-sm-8">
                                        <input class="form-control" type="text" name="<c:out value="${field.fieldId}"/>"
                                               maxlength="<c:out value="${field.size}"/>" required="<c:out value="${field.require}"/>"/>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <label class="col-sm-4 control-label"><c:out value="${field.name}"/></label>
                                    <div class="col-sm-8">
                                        <c:forEach var="choice" items="${field.choices}">
                                            <input type="checkbox" name="<c:out value="${field.fieldId}"/>"
                                                   value="<c:out value="${choice.id}"/>"> <c:out value="${choice.name}"/>
                                            <br>
                                        </c:forEach>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </div>
                </form>
            </div>

            <div class="modal-footer">
                <c:choose>
                    <c:when test="${modalSaveButton eq 'Create'}">
                        <form id="Cancel" action="/home/entity/<c:out value="${EntitySchema.id}"/>/instance/list" method="get"></form>
                        <button form="Cancel" type="submit" class="btn btn-default">Cancel</button>
                        <button form="Instance" type="submit" class="btn btn-primary"><c:out value="${modalSaveButton}"/></button>
                    </c:when>
                    <c:otherwise>
                        <form id="Cancel" action="/home/entity/<c:out value="${EntitySchema.id}"/>/instance/list" method="get"></form>
                        <button form="Cancel" type="submit" class="btn btn-default">Cancel</button>
                        <button form="Instance" type="submit" class="btn btn-primary"><c:out value="${modalSaveButton}"/></button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>

