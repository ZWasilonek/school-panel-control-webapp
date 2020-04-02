<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <base href="${contextPath}">
    <title>School</title>
    <link href='<c:url value="/css/style.css"/>' rel="stylesheet" type="text/css">
</head>

<body>

<%@ include file="/WEB-INF/fragment/navbar.jsp" %>

<table>
    <thead>
    <tr>
        <th>Exercise name</th>
        <th>Solution author</th>
        <th>Date</th>
        <th>Actions</th>
    </tr>
    </thead>
    <c:forEach items="${solutions}" var="solution">
        <tr>
<%--            <td>${solution.updated}</td>--%>
            <td><c:out value="${solution.exercise.title}"/></td>
            <td><c:out value="${solution.user.userName}"/></td>
            <td><c:out value="${solution.created}" /></td>
            <td>
                <a href="${contextPath}/infoSolution?solutionId=${solution.id}">Details</a>
            </td>
        </tr>
    </c:forEach>
    </table>
    <%@ include file="/WEB-INF/fragment/footer.jsp" %>
</body>
</html>
