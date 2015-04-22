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
                    <form id="createCompany" role="form" action="/registration/company/add" enctype="multipart/form-data" method="post">
                        <div class="row">
                            <div class="col-xs-7 col-sm-7 col-md-7">
                                <div class="form-group">
                                    <input type="text" name="name" id="name"
                                           class="form-control input-sm"
                                           placeholder="<spring:message code="placeholder.company.name"/>"
                                           required="required">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-7 col-sm-7 col-md-7">
                                <div class="form-group">
                                    <input type="text" name="address" id="address"
                                           class="form-control input-sm"
                                           placeholder="<spring:message code="placeholder.company.address"/>">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-8 col-sm-8 col-md-8">
                                <div class="form-group">
                                    <input type="file" name="image" id="image" class="filestyle"
                                           data-buttonName="btn-info"
                                           data-size="sm"
                                           data-input="false"
                                           data-buttonText="<spring:message code="placeholder.company.image"/>">
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="panel-footer">
                    <form id="logout" action="/j_spring_security_logout"></form>
                    <input type="submit" form="logout" value="<spring:message code="button.logout"/>" class="btn btn-default">
                    <input type="submit" form="createCompany" value="<spring:message code="button.create"/>" class="btn btn-primary">
                </div>
            </div>
        </div>
    </div>
</div>