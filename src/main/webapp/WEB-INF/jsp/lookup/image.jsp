<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach var="fieldValue" items="${fieldValues}">
    <div class="radio">
        <label>
            <input type="radio" name="${fieldId}" value="${fieldValue.id}">
            <img src="data:image/png;base64,${fieldValue.image}"
                 style="max-height: 150px; max-width: 150px">
        </label>
    </div>
</c:forEach>