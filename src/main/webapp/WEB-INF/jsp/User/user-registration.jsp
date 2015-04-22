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
                            <label class="col-xs-3 col-sm-4 col-md-4 control-label">First Name:</label>
                            <div class="col-xs-8 col-sm-8 col-md-8">
                                <div class="form-group">
                                    <form:input path="firstName" type="text"
                                                cssClass="form-control input-sm"/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <label class="col-xs-3 col-sm-4 col-md-4 control-label">Last Name:</label>
                            <div class="col-xs-8 col-sm-8 col-md-8">
                                <div class="form-group">
                                    <form:input path="lastName" type="text"
                                                cssClass="form-control input-sm"/>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <label class="col-xs-3 col-sm-4 col-md-4 control-label">Email:</label>
                            <div class="col-xs-8 col-sm-8 col-md-8">
                                <div class="form-group">
                                    <form:input path="email" type="text"
                                                cssClass="form-control input-sm"/>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <label class="col-xs-3 col-sm-4 col-md-4 control-label">Password:</label>
                            <div class="col-xs-8 col-sm-8 col-md-8">
                                <div class="form-group">
                                    <form:input path="password" cssClass="form-control input-sm"/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <label class="col-xs-3 col-sm-4 col-md-4 control-label">Verify Password</label>
                            <div class="col-xs-8 col-sm-8 col-md-8">
                                <div class="form-group">
                                    <form:input path="confirmPassword"
                                                cssClass="form-control input-sm"/>
                                </div>
                            </div>
                        </div>
                        <p class="bg-danger"><form:errors path="*"/></p>
                        <input type="submit" value="Register" class="btn btn-primary">

                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>