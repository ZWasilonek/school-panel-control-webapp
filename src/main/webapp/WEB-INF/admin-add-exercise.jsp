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
                <label>
                    Exercise name:<br>
                    <input type="text" name="title">
                    <c:if test="${not empty blankTitle}">
                        <br><label class="hasError"><c:out value="${blankTitle}"/></label>
                    </c:if>
                </label>
                <br>

                <label>
                    Exercise description:<br>
                    <input type="text" name="description">
                    <c:if test="${not empty blankDescription}">
                        <br><label class="hasError"><c:out value="${blankDescription}"/></label>
                    </c:if>
                </label><br>
                <input type="submit" value="Save">
            </form>
        </div>
    </div>

    <div class="backButton">
        <button class="addButton"><a class="addButton" href="${contextPath}/adminExercises">Back</a></button>
    </div>

    <c:if test="${not empty exerciseCreated and exerciseCreated==true}">
        <p>New exercise created.</p>
    </c:if>

</body>
</html>