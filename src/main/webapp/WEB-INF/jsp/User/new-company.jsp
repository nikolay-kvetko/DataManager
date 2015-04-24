<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<div class="container">
    <div class="row">
        <div class="col-xs-12 col-sm-6 col-md-4 col-sm-offset-3 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="text-center"><spring:message code="header.admin.panel"/></h3>
                    <h4 class="text-center"><spring:message code="header.create.company"/></h4>
                </div>
                <div class="panel-body">
                    <s:form id="createCompany" role="form" action="/registration/company/add"
                            enctype="multipart/form-data" method="post" modelAttribute="company">
                        <div class="row">

                            <div class="col-md-8">
                                <div class="form-group">
                                    <label class="control-label"><spring:message code="placeholder.company.name"/></label>
                                    <s:input type="text" name="name" id="name" path="name"
                                             class="form-control input-sm"
                                             required="required"/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-8">
                                <div class="form-group">
                                    <label class="control-label"><spring:message code="placeholder.company.address"/></label>
                                    <s:input type="text" name="address" id="address" path="address"
                                             class="form-control input-sm"/>
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
                    </s:form>
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