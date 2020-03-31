<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Admin add exercise</title>
    <link href="<c:url value="/css/style.css"/>" rel="stylesheet" type="text/css">
</head>

<body>

    <%@ include file="/WEB-INF/fragment/navbar.jsp" %>

    <h1>Add Exercise</h1>

    <form action="/adminAddExercise" method="post">
        Exercise name <input type="text" name="title"></br>
        Exercise description <input type="text" name="description"></br>
        <input type="submit" value="Save">
    </form>

    <c:if test="${not empty exerciseCreated and exerciseCreated==true}">
        <p>New exercise created.</p>
    </c:if>

</body>
</html>