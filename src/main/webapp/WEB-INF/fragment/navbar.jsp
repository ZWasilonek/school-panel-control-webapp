<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<ul class="nav">
    <li><a class="active" href="${contextPath}/">Home</a></li>
    <li><a href="${contextPath}/groups/panel">Groups</a></li>
    <li><a href="${contextPath}/exercises/panel">Exercises</a></li>
    <c:if test="${sessionScope.adminId != null}">
        <li><a href="${contextPath}/admin/panel">Admin panel</a></li>
    </c:if>
    <c:if test="${sessionScope.userId != null or sessionScope.adminId != null}">
        <li style="float: right"><a href="${contextPath}/logout">Logout</a></li>
    </c:if>
    <li style="float: right"><a href="${contextPath}/login">Login</a></li>
</ul>