<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="myModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><spring:message code="header.createinstance"/></h4>
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
                                <c:when test="${coincidedValue.field.valueType eq 'NUMBER'}">
                                    <div class="form-group">
                                        <label class="col-sm-4 control-label"><c:out
                                                value="${field.name}"/></label>

                                        <div class="col-sm-8">
                                            <input class="form-control" type="number"
                                                   name="<c:out value="${field.fieldId}"/>"
                                                   min="<c:out value="${field.minValue}"/>"
                                                   max="<c:out value="${field.maxValue}"/>"
                                                    <c:set var="step" value="${1}"/>
                                                    <c:forEach begin="1"
                                                               end="${field.numberDecimal}">
                                                        <c:set var="step" value="${step / 10}"/>
                                                    </c:forEach>
                                                   step="<c:out value="${step}"/>"
                                                    <c:if test="${field.require}">
                                                        required="<c:out
                                                            value="${field.require}"/>
                                                    </c:if>"
                                                   value="<c:out value="${coincidedValue.numberValue}"/>"
                                                    />
                                        </div>
                                    </div>
                                </c:when>
                                <c:when test="${coincidedValue.field.valueType eq 'MULTI_CHOICE'}">
                                    <div class="form-group">
                                        <label class="col-sm-4 control-label"><c:out
                                                value="${field.name}"/></label>

                                        <c:choose>
                                            <c:when test="${field.choiceType eq 'radio'}">
                                                <c:forEach var="choice" items="${field.choices}">
                                                    <c:set var="checked" value="false"
                                                           scope="page"/>
                                                    <c:forEach var="choiced"
                                                               items="${coincidedValue.choices}">
                                                        <c:if test="${choice.name eq choiced.name}">
                                                            <c:set var="checked" value="true"
                                                                   scope="page"/>
                                                            <c:set var="checkedValue"
                                                                   value="${choiced.name}"
                                                                   scope="page"/>
                                                        </c:if>
                                                    </c:forEach>
                                                    <div class="col-sm-8 col-sm-offset-4">
                                                        <div class="radio">
                                                            <label>
                                                                <c:choose>
                                                                    <c:when test="${checked eq true}">
                                                                        <input type="radio"
                                                                               name="<c:out value="${field.fieldId}"/>"
                                                                               value="<c:out value="${choice.id}"/>"
                                                                               checked>
                                                                        <c:out value="${checkedValue}"/>
                                                                    </c:when>
                                                                    <c:when test="${checked eq false}">
                                                                        <input type="radio"
                                                                               name="<c:out value="${field.fieldId}"/>"
                                                                               value="<c:out value="${choice.id}"/>">
                                                                        <c:out value="${choice.name}"/>
                                                                    </c:when>
                                                                </c:choose>
                                                            </label>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </c:when>
                                            <c:when test="${field.choiceType eq 'checkbox'}">
                                                <c:forEach var="choice" items="${field.choices}">
                                                    <c:set var="checked" value="false"
                                                           scope="page"/>
                                                    <c:forEach var="choiced"
                                                               items="${coincidedValue.choices}">
                                                        <c:if test="${choice.name eq choiced.name}">
                                                            <c:set var="checked" value="true"
                                                                   scope="page"/>
                                                            <c:set var="checkedValue"
                                                                   value="${choiced.name}"
                                                                   scope="page"/>
                                                        </c:if>
                                                    </c:forEach>
                                                    <div class="col-sm-8 col-sm-offset-4">
                                                        <div class="checkbox">
                                                            <label>
                                                                <c:choose>
                                                                    <c:when test="${checked eq true}">
                                                                        <input type="checkbox"
                                                                               name="<c:out value="${field.fieldId}"/>"
                                                                               value="<c:out value="${choice.id}"/>"
                                                                               checked>
                                                                        <c:out value="${checkedValue}"/>
                                                                    </c:when>
                                                                    <c:when test="${checked eq false}">
                                                                        <input type="checkbox"
                                                                               name="<c:out value="${field.fieldId}"/>"
                                                                               value="<c:out value="${choice.id}"/>">
                                                                        <c:out value="${choice.name}"/>
                                                                    </c:when>
                                                                </c:choose>
                                                            </label>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </c:when>
                                            <c:when test="${field.choiceType eq 'dropdown'}">
                                                <div class="col-sm-8 col-sm-offset-4">
                                                    <select name="<c:out value="${field.fieldId}"/>"
                                                            class="form-control">
                                                        <c:forEach var="choice"
                                                                   items="${field.choices}">
                                                            <c:set var="checked" value="false"
                                                                   scope="page"/>
                                                            <c:forEach var="choiced"
                                                                       items="${coincidedValue.choices}">
                                                                <c:if test="${choice.name eq choiced.name}">
                                                                    <c:set var="checked"
                                                                           value="true"
                                                                           scope="page"/>
                                                                    <c:set var="checkedValue"
                                                                           value="${choiced.name}"
                                                                           scope="page"/>
                                                                </c:if>
                                                            </c:forEach>
                                                            <c:choose>
                                                                <c:when test="${checked eq true}">
                                                                    <option value="<c:out value="${choice.id}"/>"
                                                                            selected>
                                                                        <c:out value="${choice.name}"/></option>
                                                                </c:when>
                                                                <c:when test="${checked eq false}">
                                                                    <option value="<c:out value="${choice.id}"/>">
                                                                        <c:out value="${choice.name}"/></option>
                                                                </c:when>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </c:when>
                                        </c:choose>
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
                                <c:when test="${field.valueType eq 'NUMBER'}">
                                    <div class="form-group">
                                        <label class="col-sm-4 control-label"><c:out
                                                value="${field.name}"/></label>

                                        <div class="col-sm-8">
                                            <input class="form-control" type="number"
                                                   name="<c:out value="${field.fieldId}"/>"
                                                   min="<c:out value="${field.minValue}"/>"
                                                   max="<c:out value="${field.maxValue}"/>"
                                                    <c:set var="step" value="${1}"/>
                                                    <c:forEach begin="1"
                                                               end="${field.numberDecimal}">
                                                        <c:set var="step" value="${step / 10}"/>
                                                    </c:forEach>
                                                   step="<c:out value="${step}"/>"
                                                    <c:if test="${field.require}">
                                                        required="<c:out
                                                            value="${field.require}"/>
                                                    </c:if>"
                                                    />
                                        </div>
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
                                <c:when test="${field.valueType eq 'MULTI_CHOICE'}">
                                    <div class="form-group">
                                        <label class="col-sm-4 control-label"><c:out
                                                value="${field.name}"/></label>
                                        <c:choose>
                                            <c:when test="${field.choiceType eq 'radio'}">
                                                <c:forEach var="choice" items="${field.choices}">
                                                    <div class="col-sm-8 col-sm-offset-4">
                                                        <div class="radio">
                                                            <label>
                                                                <input type="radio"
                                                                       name="<c:out value="${field.fieldId}"/>"
                                                                       value="<c:out value="${choice.id}"/>">
                                                                <c:out value="${choice.name}"/>
                                                            </label>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </c:when>
                                            <c:when test="${field.choiceType eq 'checkbox'}">
                                                <c:forEach var="choice" items="${field.choices}">
                                                    <div class="col-sm-8 col-sm-offset-4">
                                                        <div class="checkbox">
                                                            <label>
                                                                <input type="checkbox"
                                                                       name="<c:out value="${field.fieldId}"/>"
                                                                       value="<c:out value="${choice.id}"/>">
                                                                <c:out value="${choice.name}"/>
                                                            </label>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </c:when>
                                            <c:when test="${field.choiceType eq 'dropdown'}">
                                                <div class="col-sm-8 col-sm-offset-4">
                                                    <select name="<c:out value="${field.fieldId}"/>"
                                                            class="form-control">
                                                        <c:forEach var="choice"
                                                                   items="${field.choices}">
                                                            <option value="<c:out value="${choice.id}"/>">
                                                                <c:out value="${choice.name}"/></option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </c:when>
                                        </c:choose>
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

