<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Admin edit exercise</title>
    <link href='<c:url value="/css/style.css"/>' rel="stylesheet" type="text/css">
</head>

<body>

    <%@ include file="/WEB-INF/fragment/navbar.jsp" %>

    <h1>Edit exercise ${exercise.title}</h1>

<%--    //DODAJ FUNKCJONALNOŚĆ ZAPISYWANIA SIĘ POPRZEDNIEGO TYTUŁU I OPISU--%>
    <form action="${contextPath}/adminEditExercise" method="post">
        Exercise title <input type="text" name="title" placeholder="new exercise title"></br>
        Exercise description <input type="text" name="description" placeholder="new exercise description"></br>
        <input type="submit" value="Save">
    </form>

    <c:if test="${not empty isUpdated and isUpdated == true}">
        <p>Exercise updated.</p>
    </c:if>

</body>
</html>