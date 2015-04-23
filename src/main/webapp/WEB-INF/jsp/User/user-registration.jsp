<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<div class="container">
    <div class="row">
        <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3>Data Manager - Admin Panel</h3>
                    <h4 class="text-center">Registration</h4>
                </div>
                <div class="panel-body">
                    <form:form role="form" modelAttribute="user" action="/create_admin"
                               method="post">
                        <div class="row">
                            <label class="col-xs-3 col-sm-4 col-md-4 control-label">First
                                Name:</label>

                            <spring:bind path="firstName">
                                <c:if test="${status.error eq true}">
                                    <div class="col-xs-8 col-sm-8 col-md-8">
                                        <div class="form-group has-error">
                                            <form:input path="firstName" type="text"
                                                        cssClass="form-control"/>
                                            <label class="control-label"><form:errors
                                                    path="firstName"/></label>
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${status.error eq false}">
                                    <div class="col-xs-8 col-sm-8 col-md-8">
                                        <div class="form-group">
                                            <form:input path="firstName" type="text"
                                                        cssClass="form-control input-sm"/>
                                        </div>
                                    </div>
                                </c:if>
                            </spring:bind>
                        </div>
                        <div class="row">

                            <label class="col-xs-3 col-sm-4 col-md-4 control-label">Last
                                Name:</label>

                            <spring:bind path="lastName">
                                <c:if test="${status.error eq true}">
                                    <div class="col-xs-8 col-sm-8 col-md-8">
                                        <div class="form-group has-error">
                                            <form:input path="lastName" type="text"
                                                        cssClass="form-control"/>
                                            <label class="control-label"><form:errors
                                                    path="lastName"/></label>
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${status.error eq false}">
                                    <div class="col-xs-8 col-sm-8 col-md-8">
                                        <div class="form-group">
                                            <form:input path="lastName" type="text"
                                                        cssClass="form-control input-sm"/>
                                        </div>
                                    </div>
                                </c:if>
                            </spring:bind>

                        </div>

                        <div class="row">
                            <label class="col-xs-3 col-sm-4 col-md-4 control-label">Email:</label>

                            <spring:bind path="email">
                                <c:if test="${status.error eq true}">
                                    <div class="col-xs-8 col-sm-8 col-md-8">
                                        <div class="form-group has-error">
                                            <form:input path="email" type="text"
                                                        cssClass="form-control"/>
                                            <label class="control-label"><form:errors
                                                    path="email"/></label>
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${status.error eq false}">
                                    <div class="col-xs-8 col-sm-8 col-md-8">
                                        <div class="form-group">
                                            <form:input path="email" type="text"
                                                        cssClass="form-control input-sm"/>
                                        </div>
                                    </div>
                                </c:if>
                            </spring:bind>
                        </div>

                        <div class="row">
                            <label class="col-xs-3 col-sm-4 col-md-4 control-label">Password:</label>

                            <spring:bind path="password">
                                <c:if test="${status.error eq true}">
                                    <div class="col-xs-8 col-sm-8 col-md-8">
                                        <div class="form-group has-error">
                                            <form:input path="password" type="text"
                                                        cssClass="form-control"/>
                                            <label class="control-label"><form:errors
                                                    path="password"/></label>
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${status.error eq false}">
                                    <div class="col-xs-8 col-sm-8 col-md-8">
                                        <div class="form-group">
                                            <form:input path="password" type="password"
                                                        cssClass="form-control input-sm"/>
                                        </div>
                                    </div>
                                </c:if>
                            </spring:bind>

                        </div>
                        <div class="row">
                            <label class="col-xs-3 col-sm-4 col-md-4 control-label">Verify
                                Password</label>

                            <spring:bind path="confirmPassword">
                                <c:if test="${status.error eq true}">
                                    <div class="col-xs-8 col-sm-8 col-md-8">
                                        <div class="form-group has-error">
                                            <form:input path="confirmPassword" type="text"
                                                        cssClass="form-control"/>
                                            <label class="control-label"><form:errors
                                                    path="confirmPassword"/></label>
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${status.error eq false}">
                                    <div class="col-xs-8 col-sm-8 col-md-8">
                                        <div class="form-group">
                                            <form:input path="confirmPassword" type="password"
                                                        cssClass="form-control input-sm"/>
                                        </div>
                                    </div>
                                </c:if>
                            </spring:bind>
                        </div>
                        <input type="submit" value="Register" class="btn btn-primary">

                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>