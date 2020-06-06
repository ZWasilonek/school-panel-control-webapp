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
        <title>Admin add group</title>
        <link href='<c:url value="/resources/css/styles.css"/>' rel="stylesheet" type="text/css">
    </head>

    <body>

        <%@ include file="/WEB-INF/fragment/navbar.jsp" %>

        <div class="main-container-add">
            <div class="container-add">
                <h1>ADD GROUP</h1>

                <div class="content-add">
                    <form action="${contextPath}/adminAddGroup" method="post">
                        <label>
                            <input type="text" name="groupName" placeholder="group name" value="<c:out value='${groupNameVal}'/>"/>
                            <c:if test="${not empty blankGroupName}">
                                <label class="hasError"><c:out value="${blankGroupName}"/></label>
                            </c:if>
                            <c:if test="${not empty notUniqueGroupName}">
                                <label class="hasError"><c:out value="${notUniqueGroupName}"/></label>
                            </c:if>
                            <c:if test="${not empty capacityExceededGroupName}">
                                <label class="hasError"><c:out value="${capacityExceededGroupName}"/></label>
                            </c:if>
                        </label>

                        <label>
                            <input type="submit" value="Save">
                        </label>
                    </form>
                </div>
            </div>

            <div class="backButton">
                <button><a href="${contextPath}/adminGroups">Back</a></button>
            </div>

            <c:if test="${groupCreated}">
                <p class="feedback-text-submitted">New group created.</p>
            </c:if>

        </div>

    </body>
</html>