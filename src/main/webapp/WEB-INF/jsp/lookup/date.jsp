<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<select name="${fieldId}" class="form-control">
    <c:forEach var="fieldValue" items="${fieldValues}">
        <option
                <c:if test="${selectLookUp != null && selectLookUp==fieldValue.id}">selected</c:if>
                value="${fieldValue.id}">${fieldValue.dateValue}</option>
    </c:forEach>
</select>