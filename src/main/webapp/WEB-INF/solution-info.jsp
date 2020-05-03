<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Solution details</title>
    <link href='<c:url value="/css/style.css"/>' rel="stylesheet" type="text/css">
</head>

<body>
<%@ include file="/WEB-INF/fragment/navbar.jsp" %>

    <div class="solution">
        <h1><c:out value="${solution.exercise.title}"/> solution details:</h1>
        <p><c:out value="${solution.description}"/></p>
    </div>

</body>
</html>
