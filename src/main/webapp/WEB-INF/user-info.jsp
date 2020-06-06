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

        <div class="container-group">

            <div class="content-details">
                <h1>USER DETAILS</h1>
                <p><strong>NAME:</strong> <c:out value="${user.userName}"/></p>
                <p><strong>EMAIL:</strong> <c:out value="${user.email}"/></p>
                <p><strong>GROUP:</strong> <c:out value="${user.group.name}"/></p>
                <p><strong>ADDED EXERCISE SOLUTIONS:</strong></p>
            </div>

            <table>
                <thead>
                <tr>
                    <th>Exercise title</th>
                    <th>Creation date</th>
                    <th>Update date</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${userNotExists eq true}">
                            <th colspan="4" class="feedback-text-submitted">Such user is not registered.</th>
                        </c:when>

                        <c:otherwise>
                            <c:choose>
                                <c:when test="${userSolutions.size() ne 0}">
                                    <c:forEach items="${userSolutions}" var="solution">
                                        <tr>
                                            <td class="text-breaker"><c:out value="${solution.exercise.title}"/></td>
                                            <td><c:out value="${solution.created}"/></td>
                                            <td><c:out value="${solution.updated}"/></td>
                                            <td class="td-action">
                                                <a href="${contextPath}/infoSolution?solutionId=${solution.id}">Details</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:when>

                                <c:otherwise>
                                    <tr>
                                        <td colspan="4" class="feedback-text-submitted">The user has not solved any exercise yet</td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>

                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </div>

    </body>
</html>