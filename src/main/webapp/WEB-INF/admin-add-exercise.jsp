<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Admin add exercise</title>
    <link href='<c:url value="/css/style.css"/>' rel="stylesheet" type="text/css">
</head>

<body>

    <%@ include file="/WEB-INF/fragment/navbar.jsp" %>

    <div class="container-add-exc">
        <h1>Add Exercise</h1>
        <div class="content-add-exc">
            <form action="${contextPath}/adminAddExercise" method="post">
                Exercise name:<br>
                <label><input type="text" name="title"></label><br>
                <br>
                Exercise description:<br>
                <label><input type="text" name="description"></label><br><br>
                <input type="submit" value="Save">
            </form>
        </div>
    </div>

    <c:if test="${not empty exerciseCreated and exerciseCreated==true}">
        <p>New exercise created.</p>
    </c:if>

</body>
</html>