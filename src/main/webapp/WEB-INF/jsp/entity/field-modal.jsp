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
                    <c:when test="${fieldType eq 'multi_choice'}">
                        <spring:url var="action"
                                    value='/entity/${EntitySchema.id}/field/add/multi_choice'/>
                    </c:when>
                    <c:when test="${fieldType eq 'text_area'}">
                        <spring:url var="action"
                                    value='/entity/${EntitySchema.id}/field/add/text_area'/>
                    </c:when>
                    <c:when test="${fieldType eq 'number'}">
                        <spring:url var="action"
                                    value='/entity/${EntitySchema.id}/field/add/number'/>
                    </c:when>
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
                            <label for="requireId" style="font-weight: normal !important;">
                                <spring:message code="label.modal.require"/></label>
                        </div>
                    </div>
                    <c:choose>
                        <c:when test="${fieldType eq 'string'}">
                            <div class="form-group">
                                <label class="col-sm-4 control-label"><spring:message
                                        code="label.modal.stringsize"/></label>

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
                        <c:when test="${fieldType eq 'multi_choice'}">
                            <div class="form-group">
                                <label class="col-sm-4 control-label"><spring:message
                                        code="label.modal.choicevalues"/></label>

                                <div class="col-sm-8">
                                    <textarea class="form-control" rows="5" name="choices"
                                              required="required" style="resize:none;"><c:if
                                            test="${field.choices != null}"><c:forEach
                                            var="choiceValue" items="${field.choices}"><c:out
                                            value="${choiceValue.name}"/>&#13;&#10;</c:forEach></c:if
                                            ></textarea>
                                </div>
                            </div>
                            <div class="form-group">

                                <label class="col-sm-4 control-label"><spring:message
                                        code="label.modal.choicetype"/></label>

                                <div class="col-sm-8">
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="display" value="radio"
                                                    <c:if test="${field.choiceType == null}">
                                                        checked
                                                    </c:if>
                                                    <c:if test="${field.choiceType eq 'radio'}">
                                                        checked
                                                    </c:if>/>
                                            <spring:message code="label.modal.radio"/>
                                        </label>
                                    </div>
                                </div>
                                <div class="col-sm-8 col-sm-offset-4">
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="display" value="checkbox"
                                                    <c:if test="${field.choiceType eq 'checkbox'}">
                                                        checked
                                                    </c:if>/>
                                            <spring:message code="label.modal.checkbox"/>
                                        </label>
                                    </div>
                                </div>
                                <div class="col-sm-8 col-sm-offset-4">
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="display" value="dropdown"
                                                    <c:if test="${field.choiceType eq 'dropdown'}">
                                                        checked
                                                    </c:if>/>
                                            <spring:message code="label.modal.dropdown"/>
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </c:when>
                        <c:when test="${fieldType eq 'text_area'}">
                            <div class="form-group">
                                <label class="col-sm-4 control-label"><spring:message
                                        code="label.modal.numberoflines"/></label>

                                <div class="col-sm-4">
                                    <input class="form-control" type="number" name="countLine"
                                           min="1"
                                           max="5" required="required"
                                            <c:if test="${field.countLine != null}">
                                                value="<c:out value="${field.countLine}"/>"
                                            </c:if>
                                            />
                                </div>
                                <label class="col-sm-4"
                                       style="font-weight: normal !important;"><spring:message
                                        code="label.modal.numberoflinesadditional"/></label>
                            </div>
                        </c:when>
                        <c:when test="${fieldType eq 'number'}">
                            <div class="form-group">
                                <label class="col-sm-4 control-label"><spring:message
                                        code="label.modal.number.min"/></label>

                                <div class="col-sm-3">
                                    <input class="form-control" type="number" name="minValue"
                                            <c:if test="${field.minValue != null}">
                                                value="<c:out value="${field.minValue}"/>"
                                            </c:if>
                                            />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label"><spring:message
                                        code="label.modal.number.max"/></label>

                                <div class="col-sm-3">
                                    <input class="form-control" type="number" name="maxValue"
                                            <c:if test="${field.maxValue != null}">
                                                value="<c:out value="${field.maxValue}"/>"
                                            </c:if>
                                            />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label"><spring:message
                                        code="label.modal.number.decimalplaces"/></label>

                                <div class="col-sm-8">
                                    <select class="form-control" name="numberDecimal">
                                        <option <c:if test="${field.numberDecimal == 0}">
                                            selected
                                        </c:if> value="0">0
                                        </option>
                                        <option <c:if test="${field.numberDecimal == 1}">
                                            selected
                                        </c:if> value="1">1
                                        </option>
                                        <option <c:if test="${field.numberDecimal == 2}">
                                            selected
                                        </c:if> value="2">2
                                        </option>
                                        <option <c:if test="${field.numberDecimal == 3}">
                                            selected
                                        </c:if> value="3">3
                                        </option>
                                        <option <c:if test="${field.numberDecimal == 4}">
                                            selected
                                        </c:if> value="4">4
                                        </option>
                                        <option <c:if test="${field.numberDecimal == 5}">
                                            selected
                                        </c:if> value="5">5
                                        </option>
                                    </select>
                                </div>
                            </div>
                        </c:when>
                    </c:choose>
                </form>
            </div>
            <div class="modal-footer">
                <c:choose>
                    <c:when test="${modalSaveButton eq 'Create'}">
                        <form id="Cancel"
                              action="/entity/<c:out value="${EntitySchema.id}"/>/field/list"
                              method="get"></form>
                        <button form="Cancel" type="submit" class="btn btn-default"><spring:message
                                code="button.cancel"/></button>
                        <button form="Field" type="submit" class="btn btn-primary"><c:out
                                value="${modalSaveButton}"/></button>
                    </c:when>
                    <c:otherwise>
                        <form id="Cancel"
                              action="/entity/<c:out value="${EntitySchema.id}"/>/field/list"
                              method="get"></form>
                        <button form="Cancel" type="submit" class="btn btn-default"><spring:message
                                code="button.cancel"/></button>
                        <button form="Field" type="submit" class="btn btn-primary"><c:out
                                value="${modalSaveButton}"/></button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>

