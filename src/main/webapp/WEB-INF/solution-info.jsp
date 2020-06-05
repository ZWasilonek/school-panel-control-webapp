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
    <title>Solution details</title>
    <link href='<c:url value="/resources/css/styles.css"/>' rel="stylesheet" type="text/css">
</head>

<body>
<%@ include file="/WEB-INF/fragment/navbar.jsp" %>

    <div class="container-details-solution">

        <div class="content-details-solution">
            <h1>SOLUTION DETAILS</h1>
            <p><strong>AUTHOR:</strong> <c:out value="${solution.user.userName}"/></p>
            <p><strong>CREATED:</strong> <c:out value="${solution.created}"/></p>
            <p><strong>UPDATED:</strong> <c:out value="${solution.updated}"/></p>
        </div>

        <div class="solution">
            <h2><c:out value="${solution.exercise.title}"/></h2>
            <p><c:out value="${solution.description}"/></p>
        </div>

    </div>

</body>
</html>
