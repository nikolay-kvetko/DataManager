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
                    <c:when test="${fieldType eq 'look_up'}">
                        <spring:url var="action"
                                    value='/entity/${EntitySchema.id}/field/add/look_up'/>
                    </c:when>
                    <c:when test="${fieldType eq 'image'}">
                        <spring:url var="action"
                                    value='/entity/${EntitySchema.id}/field/add/image'/>
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
                        <c:when test="${fieldType eq 'multi_choice'}">
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Type each choice on a separate
                                    line*</label>

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

                                <label class="col-sm-4 control-label">Display choices using*</label>

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
                                            Radio Buttons
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
                                            Checkboxes
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
                                            Dropdown Menu
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </c:when>
                        <c:when test="${fieldType eq 'text_area'}">
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Number of lines for
                                    editing*</label>

                                <div class="col-sm-4">
                                    <input class="form-control" type="number" name="countLine"
                                           min="1"
                                           max="5" required="required"
                                            <c:if test="${field.countLine != null}">
                                                value="<c:out value="${field.countLine}"/>"
                                            </c:if>
                                            />
                                </div>
                                <label class="col-sm-4" style="font-weight: normal !important;">(can
                                    be from 1 to 5)</label>
                            </div>
                        </c:when>
                        <c:when test="${fieldType eq 'number'}">
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Minimum allowed value</label>

                                <div class="col-sm-3">
                                    <input class="form-control" type="number" name="minValue"
                                            <c:if test="${field.minValue != null}">
                                                value="<c:out value="${field.minValue}"/>"
                                            </c:if>
                                            />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Maximum allowed value</label>

                                <div class="col-sm-3">
                                    <input class="form-control" type="number" name="maxValue"
                                            <c:if test="${field.maxValue != null}">
                                                value="<c:out value="${field.maxValue}"/>"
                                            </c:if>
                                            />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Number of decimal
                                    places*</label>

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
                        <c:when test="${fieldType eq 'look_up'}">
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Get information from entity*</label>

                                <div class="col-sm-8">
                                    <select name="selectEntity" class="form-control" id="entityList">
                                        <c:forEach var="entity" items="${listEntity}">
                                            <option <c:if
                                                    test="${entity.id == field.lookUpEntityId}">
                                                selected
                                            </c:if> value="${entity.id}"><c:out value="${entity.name}"/></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">In this field*</label>

                                <div class="col-sm-8" id="fieldList">
                                    <select name="selectField" class="form-control" id="selectField">
                                        <c:forEach var="fieldItem" items="${listField}">
                                            <option <c:if
                                                    test="${fieldItem.fieldId == field.lookUpFieldId}">
                                                selected
                                            </c:if> value="${fieldItem.fieldId}"><c:out value="${fieldItem.name}"/></option>
                                        </c:forEach>
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

<script type="text/javascript">
    $('#entityList').change(function () {
        var entityId = $('#entityList')[0].value;

        $.ajax({
            type: 'POST',
            url: '/ajax/getNewFieldList',
            data: ({entityId: entityId, currentEntityId : '${field.fieldId}'}),
            success: function (fieldList) {
                $("#fieldList").html(fieldList);
            }
        });
    })
</script>

