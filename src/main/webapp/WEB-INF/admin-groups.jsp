<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Admin groups</title>
    <link href='<c:url value="/css/style.css"/>' rel="stylesheet" type="text/css">
</head>

<body>

    <%@ include file="/WEB-INF/fragment/navbar.jsp" %>

    <h1>Groups</h1>

    <table>
        <thead>
        <tr>
            <th>Group name</th>
            <th>Actions</th>
        </tr>
        </thead>
        <c:forEach items="${groups}" var="group">
            <tr>
                <td>
                    <c:out value="${group.name}"/>
                </td>
                <td>
                    <a href="${contextPath}/adminEditGroup?groupId=${group.id}">Edit</a>
                    <a href="${contextPath}/adminDeleteGroup?groupId=${group.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <a href="${contextPath}/adminAddGroup">Add new</a>

    <%--    //GRZESIEK???--%>
    <c:if test="${not empty isEmpty and !isEmpty}">
        <p>You can delete only empty group</p>
    </c:if>

</body>
</html>
