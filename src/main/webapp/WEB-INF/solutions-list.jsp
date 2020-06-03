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

    <div class="container-exc-sol">
        <div class="left-exercise-description">
            <h2>Exercise description:</h2>
            <p><c:out value="${exercise.description}"/></p>
        </div>

        <div class="center-info-table-sol">
            <table>
                <thead>
                <tr>
                    <th><c:out value="${exercise.title}"/> solution details</th>
                    <th>Solved the exercise</th>
                </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${solutions.size() ne 0}">
                        <c:forEach var="solution" items="${solutions}">
                            <tr>
                                <td><c:out value="${solution.description}"/></td>
                                <td><c:out value="${solution.user.userName}"/></td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="2" class="feedback-text-submitted">There are no solutions for this exercise yet</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </div>

        <div class="rightContainer"></div>
    </div>

</body>
</html>
