<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${lookUpType eq 'STRING'}">
    <c:out value="${lookUpValue}"/>
</c:if>
<c:if test="${lookUpType eq 'TEXT_AREA'}">
    <jsp:include page="text-area.jsp">
        <jsp:param name="fieldValue" value="${lookUpValue}"/>
    </jsp:include>
</c:if>
<c:if test="${lookUpType eq 'NUMBER'}">
    <c:out value="${lookUpValue}"/>
</c:if>
<c:if test="${lookUpType eq 'DATE'}">
    <c:out value="${lookUpValue}"/>
</c:if>
<c:if test="${lookUpType eq 'IMAGE'}">
    <c:if test="${not empty lookUpValue}">
        <jsp:include page="image.jsp">
            <jsp:param name="fieldValue" value="${lookUpValue}"/>
        </jsp:include>
    </c:if>
</c:if>