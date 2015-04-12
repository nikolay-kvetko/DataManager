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
                    <c:when test="${fieldType eq 'string'}">
                        <spring:url var = "action" value='/entity/${EntitySchema.id}/field/add/string'/>
                    </c:when>
                    <c:otherwise>
                        <spring:url var = "action" value='/entity/${EntitySchema.id}/field/add/multi_choice'/>
                    </c:otherwise>
                </c:choose>
                <form id="textField" name="textField" action="${action}" method="post">
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Field Name*</label>
                        <div class="col-sm-8">
                            <input type="hidden" name="fieldId"/>
                            <input class="form-control" type="text"  name="fieldName"  placeholder="Field Name" required="required"/>
                            <input type="checkbox" name="active" value="true"> Require that this column contains information
                        </div>
                        <c:choose>
                            <c:when test="${fieldType eq 'string'}">
                                <label class="col-sm-4 control-label">String Size*</label>
                                <div class="col-sm-3">
                                    <input class="form-control" type="number" name="size" min="1" max="255" required="required"/>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <label class="col-sm-4 control-label">Type each choice on a separate line*</label>
                                <div class="col-sm-8">
                                    <textarea class="form-control" rows="5" cols="20" name="choices" required="required"></textarea>
                                </div>

                                <label class="col-sm-4 control-label">Display choices using*</label>
                                <div class="col-sm-8">
                                    <input type="radio" name="display" value="radio"/> Radio Buttons <br>
                                    <input type="radio" name="display" value="checkbox"/> Checkboxes <br>
                                    <input type="radio" name="display" value="dropdown"/> Drop-down Menu
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <c:choose>
                    <c:when test="${modalSaveButton eq 'Create'}">
                        <form id="Cancel" action="/entity/<c:out value="${EntitySchema.id}"/>/field/list" method="get"></form>
                        <button form="Cancel" type="submit" class="btn btn-default">Cancel</button>
                        <button form="textField" type="submit" class="btn btn-primary"><c:out value="${modalSaveButton}"/></button>
                    </c:when>
                    <c:otherwise>
                        <form id="Cancel" action="/entity/<c:out value="${EntitySchema.id}"/>/field/list" method="get"></form>
                        <button form="Cancel" type="submit" class="btn btn-default">Cancel</button>
                        <button form="textField" type="submit" class="btn btn-primary"><c:out value="${modalSaveButton}"/></button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>

