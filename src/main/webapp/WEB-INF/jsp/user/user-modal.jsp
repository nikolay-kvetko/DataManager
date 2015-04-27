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
                    <form:form role="form" modelAttribute="user" action="/manage_users/save"
                <div class="modal-body form-horizontal">
                    <spring:url var="action" value='/entity/save'/>
                    <form:form id="userEdit" role="form" modelAttribute="user" action="/user/save"
                               method="post">
                    <div class="form-group">
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
                    </div>
                </div>
                </form:form>
            </div>
            <div class="modal-footer">
                <input type="submit" value="Cancel" class="btn btn-default">
                <input type="submit" form="userEdit" value="Edit" class="btn btn-primary">
            </div>
        </div>
    </div>
</div>
