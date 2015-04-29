<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach var="fieldValue" items="${fieldValues}">
    <div class="radio">
        <label>
            <input type="radio" <c:if test="${selectLookUp != null && selectLookUp==fieldValue.id}">checked=""</c:if>
                   name="${fieldId}" value="${fieldValue.id}">
            <textarea style="resize: none; border: none; background: none;" readonly
                      rows="${rowsCount}">${fieldValue.textAreaValue}</textarea>
        </label>
    </div>
</c:forEach>