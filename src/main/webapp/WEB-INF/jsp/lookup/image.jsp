<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach var="fieldValue" items="${fieldValues}">
    <c:if test="${not empty fieldValue.image}">
        <div class="radio">
            <label>
                <input type="radio"
                       <c:if test="${selectLookUp != null && selectLookUp==fieldValue.id}">checked=""</c:if>
                       name="${fieldId}" value="${fieldValue.id}">
                <img src="data:image/png;base64,${fieldValue.image}"
                     style="max-height: 150px; max-width: 150px">
            </label>
        </div>
    </c:if>
</c:forEach>