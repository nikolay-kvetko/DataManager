<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<div id="myModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body form-horizontal">
                <div class="modal-header">
                    <h4 class="modal-title">Edit User</h4>
                </div>
                <div class="panel-body">
                    <form:form id="userEdit" role="form" modelAttribute="user"
                               action="/manage_users/save">
                    <div class="modal-body form-horizontal">
                        <spring:url var="action" value='/entity/save'/>
                        <label class="col-xs-3 col-sm-4 col-md-4 control-label">
                            <spring:message code="label.firstName"/></label>
                        <spring:bind path="firstName">
                            <div class="col-xs-8 col-sm-8 col-md-8">
                                <div class="${status.error ? 'form-group has-error' : ''}">
                                    <form:input path="firstName" type="text"
                                                cssClass="form-control"/>
                                    <label class="control-label"><form:errors
                                            path="firstName"/></label>
                                </div>
                            </div>
                        </spring:bind>


                        <label class="col-xs-3 col-sm-4 col-md-4 control-label">
                            <spring:message code="label.lastName"/></label>
                        <spring:bind path="lastName">
                            <div class="col-xs-8 col-sm-8 col-md-8">
                                <div class="${status.error ? 'form-group has-error' : ''}">
                                    <form:input path="lastName" type="text"
                                                cssClass="form-control"/>
                                    <label class="control-label"><form:errors
                                            path="lastName"/></label>
                                </div>
                            </div>
                        </spring:bind>


                        <label class="col-xs-3 col-sm-4 col-md-4 control-label"><spring:message
                                code="label.email"/></label>
                        <spring:bind path="email">
                            <div class="col-xs-8 col-sm-8 col-md-8">
                                <div class="${status.error ? 'form-group has-error' : ''}">
                                    <form:input path="email" type="text"
                                                cssClass="form-control"/>
                                    <label class="control-label"><form:errors
                                            path="email"/></label>
                                </div>
                            </div>
                        </spring:bind>

                        <label class="col-sm-4 control-label">
                            <spring:message code="label.modal.user.role"/>*
                        </label>

                        <div class="col-sm-8">
                            <select class="form-control" name="userRole" required="required">
                                <c:forEach var="currentRole" items="${rolesList}">
                                    <option
                                            <c:if test="${user.role.name eq currentRole.name}">selected</c:if>
                                            value="${currentRole.name}"> ${currentRole.name} </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                </form:form>
            </div>
            <div class="modal-footer">
                <form id="Cancel" action="/manage_users/list"></form>
                <button form="Cancel" type="submit" class="btn btn-default">
                    <spring:message code="button.cancel"/>
                </button>
                <button form="userEdit" type="submit" class="btn btn-primary">
                    <spring:message code="button.edit"/>
                </button>
            </div>
        </div>
    </div>
</div>
