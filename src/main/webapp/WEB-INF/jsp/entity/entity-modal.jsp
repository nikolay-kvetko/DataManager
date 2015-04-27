<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="myModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><spring:message code="header.createentity"/> <spring:message code="header.editentity"/></h4>
            </div>
            <div class="modal-body form-horizontal">
                <spring:url var = "action" value='/entity/save'/>
                <s:form id="EntitySchema" name="EntitySchema" action="${action}" modelAttribute="EntitySchema" method="post">
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Entity Name*</label>
                        <spring:bind path="name">
                        <div class="col-sm-8">
                            <s:hidden path="id"/>
                            <spring:message code="label.modal.fieldname" var="labelentityname"/>
                            <div class="${status.error ? 'form-group has-error col-lg-12' : ''} ">
                                <s:input cssClass="form-control" type="text"  placeholder="${labelentityname}" path="name" required="required"/>
                                <label class="control-label"><s:errors path="name"/></label>
                            </div>
                        </div>
                        </spring:bind>
                    </div>
                </s:form>
            </div>
            <div class="modal-footer">
                <c:choose>
                    <c:when test="${modalSaveButton eq 'Create'}">
                        <form id="Cancel" action="/entity/list" method="get"></form>
                        <button form="Cancel" type="submit" class="btn btn-default"><spring:message code="button.cancel"/></button>
                        <button form="EntitySchema" type="submit" class="btn btn-primary"><c:out value="${modalSaveButton}"/></button>
                    </c:when>
                    <c:otherwise>
                        <form id="Cancel" action="/entity/<c:out value="${EntitySchema.id}"/>/field/list" method="get"></form>
                        <button form="Cancel" type="submit" class="btn btn-default"><spring:message code="button.cancel"/></button>
                        <button form="EntitySchema" type="submit" class="btn btn-primary"><c:out value="${modalSaveButton}"/></button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>

