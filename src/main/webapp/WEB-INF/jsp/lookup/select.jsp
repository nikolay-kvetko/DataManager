<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<select name="${fieldId}">
    <c:forEach var="fieldValue" items="${fieldValues}">
    <option value="${fieldValue.id}">${fieldValue.value}</option>
        </c:forEach>
</select>