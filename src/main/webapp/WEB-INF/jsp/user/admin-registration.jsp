<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<div class="container">
    <div class="row">
        <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3><spring:message code="header.admin.panel"/></h3>
                    <h4 class="text-center"><spring:message code="header.registration"/></h4>
                </div>
                <div class="panel-body">
                    <form:form id="registrationForm" role="form" modelAttribute="user" action="/create_admin"
                               method="post">
                        <div class="row">
                            <label class="col-xs-3 col-sm-4 col-md-4 control-label"><spring:message
                                    code="label.firstName"/></label>
                            <spring:bind path="firstName">
                                <div class="col-xs-8 col-sm-8 col-md-8">
                                    <div class="${status.error ? 'form-group has-error' : ''}">
                                        <form:input path="firstName" type="text"
                                                    cssClass="form-control" autofocus="true"/>
                                        <label class="control-label"><form:errors
                                                path="firstName"/></label>
                                    </div>
                                </div>
                            </spring:bind>
                        </div>
                        <div class="row">
                            <label class="col-xs-3 col-sm-4 col-md-4 control-label"><spring:message
                                    code="label.lastName"/></label>
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
                        </div>
                        <div class="row">
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
                        <div class="row">
                            <label class="col-xs-3 col-sm-4 col-md-4 control-label"><spring:message
                                    code="label.password"/></label>
                            <spring:bind path="password">
                                <div class="col-xs-8 col-sm-8 col-md-8">
                                    <div class="${status.error ? 'form-group has-error' : ''}">
                                        <form:input path="password" type="password"
                                                    cssClass="form-control"/>
                                        <label class="control-label"><form:errors
                                                path="password"/></label>
                                    </div>
                                </div>
                            </spring:bind>
                        </div>
                        <div class="row">
                            <label class="col-xs-3 col-sm-4 col-md-4 control-label"><spring:message
                                    code="label.confirmPassword"/></label>
                            <spring:bind path="confirmPassword">
                                <div class="col-xs-8 col-sm-8 col-md-8">
                                    <div class="${status.error ? 'form-group has-error' : ''}">
                                        <form:input path="confirmPassword" type="password"
                                                    cssClass="form-control"/>
                                        <label class="control-label"><form:errors
                                                path="confirmPassword"/></label>
                                    </div>
                                </div>
                            </spring:bind>
                        </div>
                    </form:form>
                </div>
                <div class="panel-footer">
                    <input form="registrationForm" type="submit" value="<spring:message code="label.registration"/>"
                           class="btn btn-primary">
                    <input form="backToLogin" type="submit" value="<spring:message code="button.back.login"/>"
                           class="btn btn-info">
                </div>
            </div>
        </div>
    </div>
</div>
<form id="backToLogin" action="/"></form>