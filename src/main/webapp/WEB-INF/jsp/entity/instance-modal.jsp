<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="myModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Create\Edit Instance</h4>
            </div>
            <div class="modal-body form-horizontal">
                <c:choose>
                    <c:when test="${empty entityInstance}">
                        <spring:url var="action"
                                    value='/home/entity/${EntitySchema.id}/instance/add'/>
                    </c:when>
                    <c:otherwise>
                        <spring:url var="action"
                                    value='/home/entity/${EntitySchema.id}/instance/update/${entityInstance.id}'/>
                    </c:otherwise>
                </c:choose>
                <form id="Instance" name="Instance" action="${action}" method="post">
                    <c:forEach var="field" items="${EntitySchema.fields}">
                        <c:set var="coincidence" value="false" scope="page"/>
                        <c:if test="${not empty entityInstance}">
                            <c:forEach var="value" items="${entityInstance.values}">
                                <c:choose>
                                    <c:when test="${field.fieldId eq value.field.fieldId}">
                                        <c:set var="coincidence" value="true" scope="page"/>
                                        <c:set var="coincidedValue" value="${value}" scope="page"/>
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                        </c:if>
                        <c:if test="${coincidence eq true}">
                            <c:choose>
                                <c:when test="${coincidedValue.field.valueType eq 'STRING'}">
                                    <div class="form-group">
                                        <label class="col-sm-4 control-label"><c:out
                                                value="${field.name}"/></label>

                                        <div class="col-sm-8">
                                            <input class="form-control" type="text"
                                                   name="<c:out value="${field.fieldId}"/>"
                                                   maxlength="<c:out value="${field.size}"/>"
                                                    <c:if test="${field.require}">
                                                        required="<c:out
                                                            value="${field.require}"/>
                                                    </c:if>"
                                                   value="<c:out value="${coincidedValue.value}"/>"
                                                    />
                                        </div>
                                    </div>
                                </c:when>
                                <c:when test="${coincidedValue.field.valueType eq 'MULTI_CHOICE'}">
                                    <div class="form-group">
                                        <label class="col-sm-4 control-label"><c:out
                                                value="${field.name}"/></label>

                                        <c:forEach var="choice" items="${field.choices}">
                                            <div class="col-sm-8 col-sm-offset-4">

                                                <input type="checkbox"
                                                       name="<c:out value="${field.fieldId}"/>"
                                                       value="<c:out value="${choice.id}"/>"
                                                       id="<c:out value="${field.fieldId}"/><c:out value="${choice.id}"/>">
                                                <label for="<c:out value="${field.fieldId}"/><c:out value="${choice.id}"/>"
                                                       style="font-weight: normal !important;">
                                                    <c:out
                                                            value="${choice.name}"/></label>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </c:when>
                                <c:when test="${coincidedValue.field.valueType eq 'TEXT_AREA'}">
                                    <div class="form-group">
                                        <label class="col-sm-4 control-label">
                                            <c:out value="${field.name}"/>
                                        </label>

                                        <div class="col-sm-8">
                                        <textarea class="form-control"
                                                  rows="<c:out value="${field.countLine}" />"
                                                  name="<c:out value="${field.fieldId}"/>"
                                                  style="resize:none;"
                                                <c:if test="${field.require}">
                                                    required="<c:out value="${field.require}"/>
                                                </c:if>"><c:out
                                                value="${coincidedValue.textAreaValue}"/></textarea>
                                        </div>
                                    </div>
                                </c:when>
                            </c:choose>
                        </c:if>
                        <c:if test="${coincidence eq false}">
                            <c:choose>
                                <c:when test="${field.valueType eq 'STRING'}">
                                    <div class="form-group">
                                        <label class="col-sm-4 control-label"><c:out
                                                value="${field.name}"/></label>

                                        <div class="col-sm-8">
                                            <input class="form-control" type="text"
                                                   name="<c:out value="${field.fieldId}"/>"
                                                   maxlength="<c:out value="${field.size}"/>"
                                                    <c:if test="${field.require}">
                                                        required="<c:out
                                                            value="${field.require}"/>
                                                    </c:if>"
                                                    />
                                        </div>
                                    </div>
                                </c:when>
                                <c:when test="${field.valueType eq 'MULTI_CHOICE'}">
                                    <div class="form-group">
                                        <label class="col-sm-4 control-label"><c:out
                                                value="${field.name}"/></label>

                                        <c:forEach var="choice" items="${field.choices}">
                                            <div class="col-sm-8 col-sm-offset-4">

                                                <input type="checkbox"
                                                       name="<c:out value="${field.fieldId}"/>"
                                                       value="<c:out value="${choice.id}"/>"
                                                       id="<c:out value="${field.fieldId}"/><c:out value="${choice.id}"/>">
                                                <label for="<c:out value="${field.fieldId}"/><c:out value="${choice.id}"/>"
                                                       style="font-weight: normal !important;">
                                                    <c:out
                                                            value="${choice.name}"/></label>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </c:when>
                                <c:when test="${field.valueType eq 'TEXT_AREA'}">
                                    <div class="form-group">
                                        <label class="col-sm-4 control-label">
                                            <c:out value="${field.name}"/>
                                        </label>

                                        <div class="col-sm-8">
                                        <textarea class="form-control"
                                                  rows="<c:out value="${field.countLine}" />"
                                                  name="<c:out value="${field.fieldId}"/>"
                                                  style="resize:none;"
                                                <c:if test="${field.require}">
                                                    required="<c:out value="${field.require}"/>
                                                </c:if>"></textarea>
                                        </div>
                                    </div>
                                </c:when>
                            </c:choose>
                        </c:if>

                    </c:forEach>
                </form>
            </div>

            <div class="modal-footer">
                <c:choose>
                    <c:when test="${modalSaveButton eq 'Create'}">
                        <form id="Cancel"
                              action="/home/entity/<c:out value="${EntitySchema.id}"/>/instance/list"
                              method="get"></form>
                        <button form="Cancel" type="submit" class="btn btn-default">Cancel</button>
                        <button form="Instance" type="submit" class="btn btn-primary"><c:out
                                value="${modalSaveButton}"/></button>
                    </c:when>
                    <c:otherwise>
                        <form id="Cancel"
                              action="/home/entity/<c:out value="${EntitySchema.id}"/>/instance/list"
                              method="get"></form>
                        <button form="Cancel" type="submit" class="btn btn-default">Cancel</button>
                        <button form="Instance" type="submit" class="btn btn-primary"><c:out
                                value="${modalSaveButton}"/></button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>

