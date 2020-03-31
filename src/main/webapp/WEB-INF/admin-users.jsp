<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Admin users</title>
    <link href='<c:url value="/css/style.css"/>' rel="stylesheet" type="text/css">
</head>

<body>

    <%@ include file="/WEB-INF/fragment/navbar.jsp" %>

    <table>
        <thead>
        <tr>
            <th>User name</th>
            <th>Actions</th>
        </tr>
        </thead>
        <c:forEach items="${users}" var="user">
            <tr>
                <td><c:out value="${user.userName}"/></td>
                <td>
                    <a href="${contextPath}/adminEditUser?userId=${user.id}">Edit</a>
                    <a href="${contextPath}/adminDeleteUser?userId=${user.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <a href="${contextPath}/adminAddUser">Add new</a><br>

    <%@ include file="/WEB-INF/fragment/footer.jsp" %>
</body>
</html>
