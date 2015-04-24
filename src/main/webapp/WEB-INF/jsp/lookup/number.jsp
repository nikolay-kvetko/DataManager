<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<select name="${fieldId}" class="form-control">
    <c:forEach var="fieldValue" items="${fieldValues}">
    <option value="${fieldValue.id}">${fieldValue.numberValue}</option>
        </c:forEach>
</select>