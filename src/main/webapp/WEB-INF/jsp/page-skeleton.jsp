<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home page</title>
    <link REL="StyleSheet" TYPE="text/css" HREF="../css/bootstrap.min.css">
    <link REL="StyleSheet" TYPE="text/css" HREF="../css/bootstrap-theme.min.css">
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/bootstrap.min.js"></script>
</head>
<body>
<tiles:insertAttribute name="header"/>
<div style="padding-top: 70px">
    <tiles:insertAttribute name="content"/>
</div>
<tiles:insertAttribute name="modal"/>
</body>
</html>