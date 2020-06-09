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
        <title>Add solution</title>
        <link href='<c:url value="/resources/css/styles.css"/>' rel="stylesheet" type="text/css">
    </head>

    <body>

        <%@ include file="/WEB-INF/fragment/navbar.jsp" %>

        <div class="main-container-add">
            <div class="container-add">
                <h1>ADD SOLUTION</h1>

                <div class="content-add">
                    <form action="${contextPath}/add/solution" method="post">
                        <input type="hidden" name="exercise.id"/>
                        <input type="hidden" name="user.id"/>

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
                </div>
            </div>

            <div class="backButton">
                <button><a href="${contextPath}/admin/groups">Back</a></button>
            </div>

            <c:if test="${solutionCreated}">
                <p class="feedback-text-submitted">New solution created.</p>
            </c:if>

        </div>

    </body>
</html>