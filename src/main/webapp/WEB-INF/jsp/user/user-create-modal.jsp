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
                        <div class="col-sm-8">
                            <spring:message code="label.modal.user.fn" var="labelFn"/>
                            <s:input class="form-control" type="text"  placeholder="${labelFn}" path="firstName" required="required" autofocus="true"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-4 control-label">
                            <spring:message code="label.modal.user.ln"/>*
                        </label>
                        <div class="col-sm-8">
                            <spring:message code="label.modal.user.ln" var="labelLn"/>
                            <s:input class="form-control" type="text"  placeholder="${labelLn}" path="lastName" required="required"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-4 control-label">
                            <spring:message code="label.modal.user.email"/>*
                        </label>
                        <div class="col-sm-8">
                            <spring:message code="label.modal.user.email" var="labelEmail"/>
                            <s:input class="form-control" type="email"  placeholder="${labelEmail}" path="email" required="required"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-4 control-label">
                            <spring:message code="label.modal.user.role"/>*
                        </label>
                        <div class="col-sm-8">
                            <select class="form-control" name="userRole" required="required">
                                <c:forEach var="currentRole" items="${rolesList}">
                                    <option value="${currentRole.name}"> ${currentRole.name} </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
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

