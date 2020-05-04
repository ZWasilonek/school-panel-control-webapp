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

    <div class="container-add-exc">
        <h1>Edit exercise ${exercise.title}</h1>

        <div class="content-add-exc">
    <%--    //DODAJ FUNKCJONALNOŚĆ ZAPISYWANIA SIĘ POPRZEDNIEGO TYTUŁU I OPISU--%>
            <form action="${contextPath}/adminEditExercise" method="post">
                <label>
                    <input type="text" name="title" placeholder="Exercise name">
                    <c:if test="${not empty blankTitle}">
                        <br><label class="hasError"><c:out value="${blankTitle}"/></label>
                    </c:if>
                </label>
                <br>

                <label>
                    <input type="text" name="description" placeholder="Exercise description">
                    <c:if test="${not empty blankDescription}">
                        <br><label class="hasError"><c:out value="${blankDescription}"/></label>
                    </c:if>
                </label><br>
                <input class="addButton" type="submit" value="Save">
            </form>
        </div>
    </div>

    <c:if test="${not empty isUpdated and isUpdated == true}">
        <p>Exercise updated.</p>
    </c:if>

</body>
</html>