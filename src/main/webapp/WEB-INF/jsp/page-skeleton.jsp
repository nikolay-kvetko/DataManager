<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>DataManager</title>
    <link REL="StyleSheet" TYPE="text/css" HREF="/css/bootstrap.min.css">
    <link REL="StyleSheet" TYPE="text/css" HREF="/css/bootstrap-theme.min.css">
    <link REL="StyleSheet" TYPE="text/css" HREF="/css/bootstrap-datetimepicker.css">
    <link REL="StyleSheet" TYPE="text/css" HREF="/css/datepicker.css">
    <script type="text/javascript" src="/js/moment.js"></script>
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/jquery.validate.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript" src="/js/bootstrap-datepicker.min.js"></script>
    <script type="text/javascript" src="/js/date.format.js"></script>
    <script type="text/javascript" src="/js/bootstrap-filestyle.min.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js"></script>
    <script>
        $(window).load(function(){
            $('#myModal').modal('show');
        });
    </script>
</head>
<body>
<tiles:insertAttribute name="header"/>
<div style="padding-top: 70px">
    <tiles:insertAttribute name="content"/>
</div>
<tiles:insertAttribute name="modal"/>
</body>
</html>