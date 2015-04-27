<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<head>
    <meta charset="UTF-8">
    <title><spring:message code="header.admin.panel"/></title>
    <link REL="StyleSheet" TYPE="text/css" HREF="/css/bootstrap.min.css">
    <link REL="StyleSheet" TYPE="text/css" HREF="/css/bootstrap-theme.min.css">
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="text-center"><spring:message code="label.authorization"/></h4>
                </div>

                <div class="panel-body">
                    <s:form role="form" id="loginForm" action='/j_spring_security_check'
                            method='POST' modelAttribute="user">

                        <div class="row">
                            <spring:bind path="email">
                                <label class="col-xs-3 col-sm-4 col-md-4 control-label"><spring:message
                                        code="label.email"/></label>

                                <div class="col-xs-8 col-sm-8 col-md-8">
                                    <div class="${status.error ? 'form-group has-error' : ''}">
                                        <s:input path="email" name="j_username"
                                                 id="email"
                                                 class="form-control input-sm"
                                                 placeholder="Email Address"
                                                 required="required" autofocus="true"/>
                                        <label class="control-label"><s:errors
                                                path="email"/></label>

                                    </div>
                                </div>
                            </spring:bind>
                        </div>

                        <div class="row">
                            <spring:bind path="password">
                                <label class="col-xs-3 col-sm-4 col-md-4 control-label"><spring:message
                                        code="label.password"/></label>
                                <div class="col-xs-8 col-sm-8 col-md-8">
                                    <div class="${status.error ? 'form-group has-error' : ''}">
                                        <s:input path="password" type="password" name="j_password" id="password"
                                               class="form-control input-sm" placeholder="Password"
                                               required="required"/>
                                        <label class="control-label"><s:errors
                                                path="password"/></label>
                                    </div>
                                </div>
                            </spring:bind>
                        </div>
                    </s:form>
                    <div class="row">
                        <div class="col-xs-3 col-sm-3 col-md-3">
                            <input form="loginForm" type="submit"
                                   value="<spring:message code="button.authorization"/>"
                                   class="btn btn-primary">
                        </div>
                        <input type="submit" form="registrationForm"
                               value="<spring:message code="button.registration"/>"
                               class="btn btn-info">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<form id="registrationForm" action="/registration"></form>
</body>