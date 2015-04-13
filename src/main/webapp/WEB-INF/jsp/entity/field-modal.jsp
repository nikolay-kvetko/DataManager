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
                <c:choose>
                    <c:when test="${field != null}">
                        <spring:url var="action"
                                    value='/entity/${EntitySchema.id}/field/change/${field.fieldId}'/>
                    </c:when>
                    <c:when test="${fieldType eq 'string'}">
                        <spring:url var="action"
                                    value='/entity/${EntitySchema.id}/field/add/string'/>
                    </c:when>
                    <c:otherwise>
                        <spring:url var="action"
                                    value='/entity/${EntitySchema.id}/field/add/multi_choice'/>
                    </c:otherwise>
                </c:choose>
                <form id="Field" name="Field" action="${action}" method="post">
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Field Name*</label>

                        <div class="col-sm-8">
                            <input type="hidden" name="fieldId"/>
                            <input class="form-control" type="text" name="fieldName"
                                   placeholder="Field Name" required="required"
                                    <c:if test="${field.name != null}">
                                        value="<c:out value="${field.name}"/>"
                                    </c:if>/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-8 col-sm-offset-4">
                            <input type="checkbox" name="active" value="true" id="requireId"
                                    <c:if test="${field.require eq true}">
                                        checked
                                    </c:if>/>
                            <label for="requireId" style="font-weight: normal !important;"> Require
                                that this column contains information</label>
                        </div>
                    </div>
                    <c:choose>
                        <c:when test="${fieldType eq 'string'}">
                            <div class="form-group">
                                <label class="col-sm-4 control-label">String Size*</label>

                                <div class="col-sm-3">
                                    <input class="form-control" type="number" name="size" min="1"
                                           max="255" required="required"
                                            <c:if test="${field.size != null}">
                                                value="<c:out value="${field.size}"/>"
                                            </c:if>
                                            />
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Type each choice on a separate
                                    line*</label>

                                <div class="col-sm-8">
                                    <textarea class="form-control" rows="5" cols="20" name="choices"
                                              required="required"><c:if
                                            test="${field.choices != null}"><c:forEach
                                            var="choiceValue" items="${field.choices}"><c:out
                                            value="${choiceValue.name}"/>&#13;&#10;</c:forEach></c:if
                                            ></textarea>
                                </div>
                            </div>
                            <div class="form-group">

                                <label class="col-sm-4 control-label">Display choices using*</label>

                                <div class="col-sm-8">
                                    <input type="radio" name="display" value="radio" id="radioId"/>
                                    <label for="radioId" style="font-weight: normal !important;">
                                        Radio Buttons</label>
                                </div>
                                <div class="col-sm-8 col-sm-offset-4">
                                    <input type="radio" name="display" value="checkbox"
                                           id="checkboxId"/>
                                    <label for="checkboxId" style="font-weight: normal !important;">
                                        Checkboxes</label>
                                </div>
                                <div class="col-sm-8 col-sm-offset-4">
                                    <input type="radio" name="display" value="dropdown"
                                           id="dropdownId"/>
                                    <label for="dropdownId" style="font-weight: normal !important;">
                                        Dropdown Menu</label>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </form>
            </div>
            <div class="modal-footer">
                <c:choose>
                    <c:when test="${modalSaveButton eq 'Create'}">
                        <form id="Cancel"
                              action="/entity/<c:out value="${EntitySchema.id}"/>/field/list"
                              method="get"></form>
                        <button form="Cancel" type="submit" class="btn btn-default">Cancel</button>
                        <button form="Field" type="submit" class="btn btn-primary"><c:out
                                value="${modalSaveButton}"/></button>
                    </c:when>
                    <c:otherwise>
                        <form id="Cancel"
                              action="/entity/<c:out value="${EntitySchema.id}"/>/field/list"
                              method="get"></form>
                        <button form="Cancel" type="submit" class="btn btn-default">Cancel</button>
                        <button form="Field" type="submit" class="btn btn-primary"><c:out
                                value="${modalSaveButton}"/></button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>

