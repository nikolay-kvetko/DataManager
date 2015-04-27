<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${param.lookUpType eq 'STRING'}">
    <c:out value="${param.lookUpValue}"/>
</c:if>
<c:if test="${param.lookUpType eq 'TEXT_AREA'}">
    <jsp:include page="text-area.jsp">
        <jsp:param name="fieldValue" value="${param.lookUpValue}"/>
    </jsp:include>
</c:if>
<c:if test="${param.lookUpType eq 'NUMBER'}">
    <c:out value="${param.lookUpValue}"/>
</c:if>
<c:if test="${param.lookUpType eq 'DATE'}">
    <c:out value="${param.lookUpValue}"/>
</c:if>
<c:if test="${param.lookUpType eq 'IMAGE'}">
    <jsp:include page="image.jsp">
        <jsp:param name="fieldValue" value="${param.lookUpValue}"/>
    </jsp:include>
</c:if>