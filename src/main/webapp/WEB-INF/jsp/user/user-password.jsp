<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<div class="container">
    <div class="row">
        <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="text-center"><spring:message code="header.password"/></h4>
                </div>

                <div class="panel-body">
                    <form role="form" id="passwordForm" action='/registration/password/add' method='POST'>

                        <div class="row">
                            <div class="col-xs-8 col-sm-8 col-md-8">
                                <div class="form-group">
                                    <input type="password" name="newPassword" id="newPassword"
                                           class="form-control input-sm" placeholder="Password"
                                           required="required">
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-xs-8 col-sm-8 col-md-8">
                                <div class="form-group">
                                    <input type="password" name="confirmPassword" id="confirmPassword"
                                           class="form-control input-sm" placeholder="Confirm Password"
                                           required="required">
                                </div>
                            </div>
                        </div>
                    </form>
                        <div class="row">
                            <div class="col-xs-3 col-sm-3 col-md-3">
                                <input form="passwordForm" type="submit" value="<spring:message code="button.set"/>" class="btn btn-primary">
                            </div>
                            <form id="logout" action="/j_spring_security_logout"></form>
                            <input type="submit" form="logout" value="<spring:message code="button.logout"/>" class="btn btn-default">
                        </div>
                </div>
            </div>
        </div>
    </div>
</div>
