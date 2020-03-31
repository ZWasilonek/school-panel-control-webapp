<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Admin edit group</title>
    <link href='<c:url value="/css/style.css"/>' rel="stylesheet" type="text/css">
</head>

<body>

    <%@ include file="/WEB-INF/fragment/navbar.jsp" %>

    <h1>Edit group ${group.name}</h1>

    <form action="${contextPath}/adminEditGroup" method="post">
        Group name <input type="text" name="groupName" placeholder="new group name"></br>
        <input type="submit" value="Save">
    </form>

    <c:if test="${not empty isUpdated and isUpdated}">
        <p>Group updated.</p>
    </c:if>

</body>
</html>