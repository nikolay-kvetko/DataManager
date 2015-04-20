<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<div class="container">
    <div class="row">
        <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3><spring:message code="header.admin.panel"/></h3>
                    <h4 class="text-center"><spring:message code="header.create.company"/></h4>
                </div>
                <div class="panel-body">
                    <form role="form" action="/registration/create_company" method="post">
                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="text" name="name" id="name"
                                           class="form-control input-sm"
                                           placeholder="<spring:message code="placeholder.company.name"/>"
                                           required="required">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="text" name="address" id="address"
                                           class="form-control input-sm"
                                           placeholder="<spring:message code="placeholder.company.address"/>">
                                </div>
                            </div>
                        </div>
                        <input type="submit" form="logout" value="<spring:message code="button.logout"/>" class="btn btn-default">
                        <input type="submit" value="<spring:message code="button.create"/>" class="btn btn-primary">
                    </form>
                    <form id="logout" action="/j_spring_security_logout"></form>
                </div>
            </div>
        </div>
    </div>
</div>