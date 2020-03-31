<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Admin add group</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>

<body>

    <pre>${pageContext.request.contextPath}</pre>
    <%@ include file="/WEB-INF/fragment/navbar.jsp" %>

    <h1>Add group</h1>

    <form action="/adminAddGroup" method="post">
        Group name <input type="text" name="groupName"></br>
        <input type="submit" value="Save">
    </form>

    <c:if test="${not empty groupCreated and groupCreated==true}">
        <p>New group created.</p>
    </c:if>

</body>
</html>