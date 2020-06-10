<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="<c:url value='/resources/css/styles.css'/>" rel="stylesheet" type="text/css">
        <title>Admin exercises</title>
    </head>

    <body>

        <%@ include file="/WEB-INF/fragment/navbar.jsp" %>

        <div class="container-group">
            <table>
                <thead>
                <tr>
                    <th>Exercise name</th>
                    <th colspan="3" class="action-th">Actions</th>
                </tr>
                </thead>
                <c:choose>
                    <c:when test="${exercises.size() == 0}">
                        <tr>
                            <td colspan="2" class="feedback-text-submitted">No exercise found in the registry.</td>
                        </tr>
                    </c:when>

                    <c:otherwise>
                        <c:forEach items="${exercises}" var="exercise">
                            <tr>
                                <td class="text-breaker">
                                    <c:out value="${exercise.title}"/>
                                </td>
                                <td class="td-action">
                                    <a href="${contextPath}/exercise/solutions/info?exerciseId=${exercise.id}">Solutions</a>
                                </td>
                                <td class="td-action">
                                    <a href="${contextPath}/admin/edit/exercise?exerciseId=${exercise.id}">Edit</a>
                                </td>
                                <td class="td-action">
                                    <a href="${contextPath}/admin/delete/exercise?exerciseId=${exercise.id}">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>

            </table>

            <button class="addButton"><a class="addButton" href="${contextPath}/admin/add/exercise">Add new</a></button>

        </div>

    </body>
</html>
