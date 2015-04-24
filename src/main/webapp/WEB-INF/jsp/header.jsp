<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbarCollapse" aria-expanded="true">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Image</a>
        </div>
        <div id="navbarCollapse" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="/home/entity/list"><spring:message code="label.homepage"/></a></li>
                <li><a href="#"><spring:message code="label.users"/></a></li>
                <li><a href="/entity/list"><spring:message code="label.datastructure"/></a></li>
                </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                       aria-expanded="false">Dropdown <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#"><spring:message code="label.edituser"/></a></li>
                        <li><a href="#"><spring:message code="label.editcompany"/></a></li>
                        <li class="divider"></li>
                        <li><a href="/j_spring_security_logout"><spring:message code="label.logout"/></a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
