<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="<c:url value='/resources/css/styles.css'/>" rel="stylesheet" type="text/css">
        <title>Solutions list</title>
    </head>

    <body>

        <%@ include file="/WEB-INF/fragment/navbar.jsp" %>

        <div class="container-exc-sol">

            <div class="left-exercise-description">
                <h2>Exercise description:</h2>
                <p><c:out value="${exercise.description}"/></p>
            </div>

            <div class="center-info-table-sol">

                <div class="content-title">
                    <h1>EXERCISE SOLUTIONS</h1>
                    <c:if test="${!exerciseNotExists}">
                        <button class="addButton">
                            <a class="addButton" href="${contextPath}/solution/add?exerciseId=${exercise.id}">Add new</a>
                        </button>
                    </c:if>
                </div>

                <table>
                    <thead>
                    <tr>
                        <th colspan="2" class="text-breaker"><c:out value="${exercise.title}"/></th>
                        <th style="width: 15%">Solved the exercise</th>
                        <th colspan="3" style="width: 10%">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:choose>
                        <c:when test="${exerciseNotExists}">
                            <th colspan="6" class="feedback-text-submitted">Such exercise is not registered.</th>
                        </c:when>

                        <c:otherwise>
                            <c:choose>
                                <c:when test="${solutions.size() ne 0}">
                                    <c:forEach var="solution" items="${solutions}" varStatus="counter">
                                        <tr>
                                            <td class="index"><c:out value="${counter.index + 1}"/></td>
                                            <td class="text-breaker solution-break-space"><c:out value="${solution.description}"/></td>
                                            <td class="td-action text-breaker"><c:out value="${solution.user.userName}"/></td>
                                            <td class="td-action">
                                                <a href="${contextPath}/user/info?userId=${solution.user.id}">User details</a>
                                            </td>
                                            <td>
                                                <c:if test="${sessionScope.adminId != null or solution.user.id == sessionScope.userId}">
                                                    <a href="${contextPath}/solution/edit?solutionId=${solution.id}&exerciseId=${exercise.id}">Edit</a>
                                                </c:if>
                                            </td>
                                            <td>
                                                <c:if test="${sessionScope.adminId != null or solution.user.id == sessionScope.userId}">
                                                    <a href="${contextPath}/solution/delete?solutionId=${solution.id}">Delete</a>
                                                </c:if>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:when>

                                <c:otherwise>
                                    <tr>
                                        <td colspan="6" class="feedback-text-submitted">There are no solutions for this exercise yet.</td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                        </c:otherwise>

                    </c:choose>
                    </tbody>
                </table>

                <c:if test="${not empty hasPermissionToDelete and !hasPermissionToDelete}">
                    <p class="feedback-text-submitted">You do not have permission to remove this solution.</p>
                </c:if>

            </div>

            <div class="rightContainer"></div>
        </div>

    </body>
</html>