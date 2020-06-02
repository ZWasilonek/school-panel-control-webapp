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
    <title>User Details</title>
    <link href='<c:url value="/resources/css/styles.css"/>' rel="stylesheet" type="text/css">
</head>

<body>

<%@ include file="/WEB-INF/fragment/navbar.jsp" %>

<div class="container-user">

    <div class="user-details-content">
        <h1><c:out value="${user.userName}"/> user details:</h1>
        <p>Email: <c:out value="${user.email}"/></p>
        <p>Group: <c:out value="${user.group.name}"/></p>
        <p><strong>Added tasks solutions:</strong></p>
    </div>

    <table>
        <thead>
        <tr>
            <th>Exercise name</th>
            <th>Creation date</th>
            <th>Update date</th>
            <th>Actions</th>
        </tr>
        </thead>
        <c:choose>
            <c:when test="${userSolutions.size() ne 0}">
                <c:forEach items="${userSolutions}" var="solution">
                    <tr>
                        <td><c:out value="${solution.exercise.title}"/></td>
                        <td><c:out value="${solution.created}"/></td>
                        <td><c:out value="${solution.updated}"/></td>
                        <td>
                            <a href="${pageContext.request.contextPath}/infoSolution?solutionId=${solution.id}">Details</a>
                        </td>
                    </tr>
                </c:forEach>
            </c:when>

            <c:otherwise>
                <tr>
                    <td colspan="4" class="has-solution-response">The user has not solved any exercise yet</td>
                </tr>
            </c:otherwise>
        </c:choose>
    </table>

</div>
</body>
</html>