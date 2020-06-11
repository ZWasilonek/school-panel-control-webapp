<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="<c:url value='/resources/css/styles.css'/>" rel="stylesheet" type="text/css">
        <title>Admin edit group</title>
    </head>

    <body>

        <%@ include file="/WEB-INF/fragment/navbar.jsp" %>

        <div class="main-container-add">
            <div class="container-add">

                <h2 class="text-breaker">EDIT GROUP <br> <c:out value="${group.name}"/></h2>

                <div class="content-add">
                    <c:choose>

                        <c:when test="${groupNotExists}">
                            <p class="feedback-text-submitted">Such group is not registered.</p>
                        </c:when>

                        <c:otherwise>
                            <form action="${contextPath}/admin/edit/group" method="post">
                                <label>
                                    <input type="text" name="groupName" placeholder="Group name" value="<c:out value='${groupNameVal}'/>"/>
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
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="backButton">
                <button><a href="${contextPath}/admin/groups">Back</a></button>
            </div>

            <c:if test="${isUpdated}">
                <p class="feedback-text-submitted">Group updated.</p>
            </c:if>
        </div>

    </body>
</html>