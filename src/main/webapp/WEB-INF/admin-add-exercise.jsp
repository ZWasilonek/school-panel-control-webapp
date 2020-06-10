<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="<c:url value='/resources/css/styles.css'/>" rel="stylesheet" type="text/css">
        <title>Admin add exercise</title>
    </head>

    <body>

        <%@ include file="/WEB-INF/fragment/navbar.jsp" %>

        <div class="main-container-add">
            <div class="container-add">

                <h1>ADD EXERCISE</h1>

                <div class="content-add">
                    <form action="${contextPath}/admin/add/exercise" method="post" accept-charset="UTF-8">
                        <label>
                            <input type="text" name="title" placeholder="title" value="<c:out value='${titleVal}'/>">
                            <c:if test="${not empty blankTitle}">
                                <label class="hasError"><c:out value="${blankTitle}"/></label>
                            </c:if>
                            <c:if test="${not empty capacityExceededTitle}">
                                <label class="hasError"><c:out value="${capacityExceededTitle}"/></label>
                            </c:if>
                        </label>
                        <br>
                        <label>
                            <textarea name="description" placeholder="description" rows="10"><c:out value="${descriptionVal}"/></textarea>
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
                <button><a href="${contextPath}/admin/exercises">Back</a></button>
            </div>

            <c:if test="${exerciseCreated}">
                <p class="feedback-text-submitted">New exercise created.</p>
            </c:if>
        </div>

    </body>
</html>