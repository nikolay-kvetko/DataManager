<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<div class="container">
    <div class="row">
        <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3>Data Manager - Admin Panel</h3>
                    <h4 class="text-center">Registration</h4>
                </div>
                <div class="panel-body">
                    <form role="form">
                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="text" name="first_name" id="first_name"
                                           class="form-control input-sm" placeholder="First Name"
                                           required="required">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="text" name="last_name" id="last_name"
                                           class="form-control input-sm" placeholder="Last Name"
                                           required="required">
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="email" name="email" id="email"
                                           class="form-control input-sm" placeholder="Email Address"
                                           required="required">
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="password" name="password" id="password"
                                           class="form-control input-sm" placeholder="Password"
                                           required="required">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="password" name="password_confirmation"
                                           id="password_confirmation" class="form-control input-sm"
                                           placeholder="Verify Password" required="required">
                                </div>
                            </div>
                        </div>

                        <input type="submit" value="Register" class="btn btn-primary">

                    </form>
                </div>
            </div>
        </div>
    </div>
</div>