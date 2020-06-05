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
    <title>Group users</title>
    <link href='<c:url value="/resources/css/styles.css"/>' rel="stylesheet" type="text/css">
</head>

<body>
    <%@ include file="/WEB-INF/fragment/navbar.jsp" %>

    <div class="container-group">
        <h1>${groupName} users:</h1>
        <table>
            <thead>
            <tr>
                <th>User name</th>
                <th class="action-th">Actions</th>
            </tr>
            </thead>
            <c:choose>
                <c:when test="${groupNotExists eq true}">
                    <td colspan="2" class="feedback-text-submitted">Such group is not registered.</td>
                </c:when>

                <c:otherwise>
                    <c:choose>
                        <c:when test="${users.size() ne 0}">
                            <c:forEach items="${users}" var="user">
                                <tr>
                                    <td class="text-breaker">
                                        <c:out value="${user.userName}"/>
                                    </td>
                                    <td class="td-action">
                                        <a href="${contextPath}/infoUser?userId=${user.id}">Details</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="2" class="feedback-text-submitted">This group has no members yet</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>

            </c:choose>
        </table>
    </div>

</body>
</html>
