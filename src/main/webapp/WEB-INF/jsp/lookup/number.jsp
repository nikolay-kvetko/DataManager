<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<select name="${fieldId}" class="form-control">
    <c:forEach var="fieldValue" items="${fieldValues}">
        <c:if test="${not empty fieldValue.numberValue}">
            <option
                    <c:if test="${selectLookUp != null && selectLookUp==fieldValue.id}">selected</c:if>
                    value="${fieldValue.id}">${fieldValue.numberValue}</option>
        </c:if>
    </c:forEach>
</select>