<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Group users</title>
    <link href='<c:url value="/css/style.css"/>' rel="stylesheet" type="text/css">
</head>

<body>
<%@ include file="/WEB-INF/fragment/navbar.jsp" %>

<h1>${groupName} users:</h1>
<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Actions</th>
    </tr>
    </thead>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>
                <c:out value="${user.userName}"/>
            </td>
            <td>
                <a href="${contextPath}/infoUser?userId=${user.id}">Details</a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
