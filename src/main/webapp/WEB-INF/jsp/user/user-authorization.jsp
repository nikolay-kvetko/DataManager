<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<head>
    <meta charset="UTF-8">
    <title><spring:message code="title.login.page"/></title>
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
                    <h4 class="text-center"><spring:message code="header.login"/></h4>
                </div>

                <div class="panel-body">
                    <form role="form" id="loginForm" action='/j_spring_security_check' method='POST'>

                        <div class="row">
                            <label class="col-xs-12 col-sm-12 col-md-12 text-center text-danger">
                                <c:out value="${error}"/>
                            </label>
                        </div>

                        <div class="row">
                            <div class="col-xs-8 col-sm-8 col-md-8">
                                <div class="form-group">
                                    <input type="email" name="j_username" id="email"
                                           class="form-control input-sm" placeholder="Email Address"
                                           required="required" autofocus="true">
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-xs-8 col-sm-8 col-md-8">
                                <div class="form-group">
                                    <input type="password" name="j_password" id="password"
                                           class="form-control input-sm" placeholder="Password"
                                           required="required">
                                </div>
                            </div>
                        </div>
                    </form>
                    <div class="row">
                        <div class="col-xs-3 col-sm-3 col-md-3">
                            <input form="loginForm" type="submit" value="<spring:message code="button.login"/>" class="btn btn-primary">
                        </div>
                        <input type="submit" form="registrationForm" value="<spring:message code="button.signUp"/>" class="btn btn-info">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<form id="registrationForm" action="/registration"></form>
</body>