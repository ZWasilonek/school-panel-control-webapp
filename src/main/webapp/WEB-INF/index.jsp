<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="<c:url value='/resources/css/styles.css'/>" rel="stylesheet" type="text/css">
        <title>School</title>
    </head>

    <body>

        <%@ include file="/WEB-INF/fragment/navbar.jsp" %>

        <div class="container">
            <h1>THE LATEST SOLUTIONS FOR EXERCISES</h1>
            <table>
                <thead>
                <tr>
                    <th style="width: 50%;">Exercise name</th>
                    <th>Author</th>
                    <th>Created</th>
                    <th>Updated</th>
                    <th>Action</th>
                </tr>
                </thead>
                <c:choose>
                    <c:when test="${solutions.size() == 0}">
                        <th colspan="5" class="feedback-text-submitted">
                            Welcome, there are no solutions for the exercises in the system yet.
                        </th>
                    </c:when>

                    <c:otherwise>
                        <c:forEach items="${solutions}" var="solution">
                            <tr>
                                <td><c:out value="${solution.exercise.title}"/></td>
                                <td class="td-action"><c:out value="${solution.user.userName}"/></td>
                                <td class="td-action"><c:out value="${solution.created}"/></td>
                                <td class="td-action"><c:out value="${solution.updated}"/></td>
                                <td class="td-action">
                                    <a href="${contextPath}/solution/info?solutionId=${solution.id}">Details</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </table>
        </div>

    </body>
</html>