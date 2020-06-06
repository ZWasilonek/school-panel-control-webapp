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
    <title>Admin groups</title>
    <link href='<c:url value="/resources/css/styles.css"/>' rel="stylesheet" type="text/css">
</head>

<body>

    <%@ include file="/WEB-INF/fragment/navbar.jsp" %>

    <div class="container-group">
        <h1>Groups</h1>

        <table>
            <thead>
            <tr>
                <th>Group name</th>
                <th colspan="4" class="action-th">Actions</th>
            </tr>
            </thead>
            <c:choose>
                <c:when test="${groupsNotFound}">
                    <td colspan="2" class="feedback-text-submitted">No group found in the registry.</td>
                </c:when>

                <c:otherwise>
                    <c:forEach items="${groups}" var="group">
                        <tr>
                            <td class="text-breaker">
                                <c:out value="${group.name}"/>
                            </td>
                            <td class="td-action">
                                <a href="${contextPath}/infoGroupUsers?group=${group.name}&groupId=${group.id}">Show members</a>
                            </td>
                            <td class="td-action">
                                <a href="${contextPath}/adminAddUserToGroup?groupId=${group.id}">Assign member</a>
                            </td>
                            <td class="td-action">
                                <a href="${contextPath}/adminEditGroup?groupId=${group.id}">Edit</a>
                            </td>
                            <td class="td-action">
                                <a href="${contextPath}/adminDeleteGroup?groupId=${group.id}">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </table>

        <button class="addButton"><a class="addButton" href="${contextPath}/adminAddGroup">Add new</a></button>

        <c:if test="${not empty isEmpty and !isEmpty}">
            <p class="feedback-text-submitted">You can delete only empty group</p>
        </c:if>

    </div>

</body>
</html>
