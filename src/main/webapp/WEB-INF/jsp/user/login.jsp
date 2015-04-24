<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>
<head>
  <title>Spring Security Login page</title>
</head>
<body>
<h2 align="center" style="color:#ff0000"><c:out value="${error}"/></h2>
<form name='form' action='j_spring_security_check' method='POST'>
  <table align="center">
    <tr>
      <td>User Email:</td>
      <td><input type='text' name='j_username' value=''></td>
    </tr>
    <tr>
      <td>Password:</td>
      <td><input type='password' name='j_password'/></td>
    </tr>
    <tr>
      <td colspan='2'>
        <input name="submit" type="submit" value="Login"/></td>
    </tr>
  </table>
</form>
</body>
</html>
