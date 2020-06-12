<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="<c:url value='/resources/css/styles.css'/>" rel="stylesheet" type="text/css">
        <title>Add solution</title>
    </head>

    <body>

        <%@ include file="/WEB-INF/fragment/navbar.jsp" %>

        <div class="container-exc-sol">

            <div class="left-exercise-description">
                <h2>Exercise description:</h2>
                <p><c:out value="${exercise.description}"/></p>
            </div>

            <div class="center-info-table-sol">
                <div class="container-add sol-center">
                    <h1>ADD SOLUTION</h1>

                    <div class="content-add">
                        <c:choose>

                            <c:when test="${exerciseNotExists}">
                                <p class="feedback-text-submitted">Such exercise is not registered.</p>
                            </c:when>

                            <c:otherwise>
                                <form action="${contextPath}/solution/add" method="post">
                                    <label>
                                        <textarea name="description" placeholder="description" rows="25"><c:out value="${descriptionVal}"/></textarea>
                                        <c:if test="${not empty blankDescription}">
                                            <label class="hasError"><c:out value="${blankDescription}"/></label>
                                        </c:if>
                                        <c:if test="${not empty capacityExceededDescription}">
                                            <label class="hasError"><c:out value="${capacityExceededDescription}"/></label>
                                        </c:if>
                                    </label>

                                    <label>
                                        <input type="submit" value="Save">
                                    </label>
                                </form>
                            </c:otherwise>

                        </c:choose>
                    </div>
                </div>

                <c:if test="${solutionCreated}">
                    <p class="feedback-text-submitted">New solution created.</p>
                </c:if>

            </div>

            <div class="rightContainer"></div>
        </div>

    </body>
</html>