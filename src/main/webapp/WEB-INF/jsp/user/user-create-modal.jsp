<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="myModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><spring:message code="header.modal.user.create"/></h4>
            </div>
            <div class="modal-body form-horizontal">
                <spring:url var = "action" value='/manage_users/add'/>
                <s:form id="user" name="user" action="${action}" modelAttribute="newUser" method="post">
                    <div class="form-group">
                        <label class="col-sm-4 control-label">
                            <spring:message code="label.modal.user.fn"/>*
                        </label>
                            <spring:bind path="firstName">
                                <div class="col-sm-7">
                                    <div class="${status.error ? 'form-group has-error' : ''}">
                                        <s:input path="firstName" type="text"
                                                    cssClass="form-control" autofocus="true" required="required"/>
                                        <label class="control-label"><s:errors
                                                path="firstName"/></label>
                                    </div>
                                </div>
                            </spring:bind>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-4 control-label">
                            <spring:message code="label.modal.user.ln"/>*
                        </label>

                            <spring:bind path="lastName">
                                <div class="col-sm-7">
                                    <div class="${status.error ? 'form-group has-error' : ''}">
                                        <s:input path="lastName" type="text"
                                                    cssClass="form-control" required="required"/>
                                        <label class="control-label"><s:errors
                                                path="lastName"/></label>
                                    </div>
                                </div>
                            </spring:bind>

                    </div>

                    <div class="form-group">
                        <label class="col-sm-4 control-label">
                            <spring:message code="label.modal.user.email"/>*
                        </label>

                            <spring:bind path="email">
                                <div class="col-sm-7">
                                    <div class="${status.error ? 'form-group has-error' : ''}">
                                        <s:input path="email" type="text"
                                                    cssClass="form-control"/>
                                        <label class="control-label"><s:errors
                                                path="email"/></label>
                                    </div>
                                </div>
                            </spring:bind>

                    </div>

                    <div class="form-group">
                        <label class="col-sm-4 control-label">
                            <spring:message code="label.modal.user.role"/>*
                        </label>
                        <div class="col-sm-7">
                            <select class="form-control" name="userRole" required="required">
                                <c:forEach var="currentRole" items="${rolesList}">
                                    <option value="${currentRole.name}"> ${currentRole.name} </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <s:input path="password" type="hidden" value="1234"/>
                    <s:input path="confirmPassword" type="hidden" value="1234"/>
                </s:form>
            </div>
            <div class="modal-footer">
                <form id="Cancel" action="/manage_users/list"></form>
                <button form="Cancel" type="submit" class="btn btn-default">
                    <spring:message code="button.cancel"/>
                </button>
                <button form="user" type="submit" class="btn btn-primary">
                    <spring:message code="button.create"/>
                </button>
            </div>
        </div>
    </div>
</div>

