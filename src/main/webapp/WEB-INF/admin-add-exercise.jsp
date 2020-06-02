<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<style>
    <%@ include file="/resources/css/styles.css"%>
</style>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Admin add exercise</title>
    <link href='<c:url value="/resources/css/styles.css"/>' rel="stylesheet" type="text/css">
</head>

<body>

    <%@ include file="/WEB-INF/fragment/navbar.jsp" %>

    <div class="container-add-exc">
        <h1>Add Exercise</h1>
        <div class="content-add-exc">
            <form action="${contextPath}/adminAddExercise" method="post" accept-charset="UTF-8">
                <label>
                    <input type="text" name="title" placeholder="title">
                    <c:if test="${not empty blankTitle}">
                        <br><label class="hasError"><c:out value="${blankTitle}"/></label>
                    </c:if>
                </label>
                <br>
                <br>

                <label>
                    <textarea name="description" placeholder="description" rows="10"></textarea>
                    <c:if test="${not empty blankDescription}">
                        <br><label class="hasError"><c:out value="${blankDescription}"/></label>
                    </c:if>
                </label>
                <br>
                <input type="submit" value="Save">
            </form>
        </div>
    </div>

<%--    BACK not ADDbutton--%>
    <div class="backButton">
        <button class="addButton"><a class="addButton" href="${contextPath}/adminExercises">Back</a></button>
    </div>

    <c:if test="${not empty exerciseCreated and exerciseCreated==true}">
        <p class="feedback-text-submitted">New exercise created.</p>
    </c:if>

</body>
</html>