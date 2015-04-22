<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<div class="container">
    <div class="row">
        <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3>Data Manager - Admin Panel</h3>
                    <h4 class="text-center">Login</h4>
                </div>
                <div class="panel-body">
                    <form:form role="form" modelAttribute="user" action="/login">

                        <div class="row">
                            <div class="col-xs-8 col-sm-8 col-md-8">
                                <div class="form-group">
                                    <form:input path="email" type="text" cssClass="form-control input-sm"/>
                                    <form:errors path="email"/>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-xs-8 col-sm-8 col-md-8">
                                <div class="form-group">
                                    <form:input path="password" type="password" cssClass="form-control input-sm"/>
                                    <form:errors path="password"/>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-xs-3 col-sm-3 col-md-3">
                                <input type="submit" value="Login" class="btn btn-primary">
                            </div>
                            <div class="col-xs-offset-4 col-sm-offset-4 col-md-offset-4"/>
                            <div class="col-xs-3 col-sm-3 col-md-3">
                                <a href="/registration">Registration</a>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>