<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Admin edit user</title>
    <link href='<c:url value="/css/style.css"/>' rel="stylesheet" type="text/css">
</head>

<body>

    <%@ include file="/WEB-INF/fragment/navbar.jsp" %>

    <h1>Edit user ${user.userName}</h1>

    <form action="${contextPath}/adminEditUser" method="post">
        User name <input type="text" name="userName" placeholder="new user name"><br>
        User email <input type="text" name="userEmail" placeholder="new email"><br>
        User password <input type="password" name="userPass" placeholder="new password"><br>
        Repeat password <input type="password" name="rePass" placeholder="new password"><br>

        User group
        <select name="groupName">
            <c:forEach var="group" items="${groups}">
                <option>${group.name}</option>
            </c:forEach>
        </select><br>

        <input type="submit" value="Save"><br>

    </form>

    <c:if test="${not empty hasGroup and !hasGroup}">
        <p>You need to create some group first</p>
    </c:if>

    <c:if test="${not empty isUpdated and isUpdated}">
        <p>User updated.</p>
    </c:if>

</body>
</html>