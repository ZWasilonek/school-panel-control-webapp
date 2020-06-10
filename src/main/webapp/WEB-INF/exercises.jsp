<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="<c:url value='/resources/css/styles.css'/>" rel="stylesheet" type="text/css">
        <title>Exercises</title>
    </head>

    <body>

        <%@ include file="/WEB-INF/fragment/navbar.jsp" %>

        <div class="container-group">
            <table>
                <c:choose>
                    <c:when test="${exercisesNotFound}">
                        <tr>
                            <td class="feedback-text-submitted">No exercise found in the registry.</td>
                        </tr>
                    </c:when>

                    <c:otherwise>
                        <c:forEach items="${exercises}" var="exercise" varStatus="exerciseNumber">
                            <tr>
                                <td class="index">
                                    <c:out value="${exerciseNumber.index + 1}"/>
                                </td>
                                <td>
                                    <a href="${contextPath}/exercise/solutions/info?exerciseId=${exercise.id}">
                                        <c:out value="${exercise.title}"/>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </table>
        </div>

    </body>
</html>
