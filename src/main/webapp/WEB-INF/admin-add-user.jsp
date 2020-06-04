<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<style>
    <%@ include file="/resources/css/styles.css"%>
</style>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Admin add user</title>
    <link href='<c:url value="/resources/css/styles.css"/>' rel="stylesheet" type="text/css">
</head>

<body>

    <%@ include file="/WEB-INF/fragment/navbar.jsp" %>

    <form action="${contextPath}/adminAddUser" method="post">
        User name <input type="text" name="userName"><br>
        User email <input type="text" name="userEmail"><br>
        User password <input type="password" name="userPass"><br>
        Repeat password <input type="password" name="rePass" placeholder="new password"><br>

        User group
        <select name="groupName">
            <c:forEach var="group" items="${groups}">
            <option>${group.name}</option>
            </c:forEach>
        </select><br>

        <input type="submit" value="Save"><br>
    </form>

    <c:if test="${userCreated eq true}">
        <p class="feedback-text-submitted">New user created.</p>
    </c:if>

</body>
</html>