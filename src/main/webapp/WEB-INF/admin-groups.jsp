<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="<c:url value='/resources/css/styles.css'/>" rel="stylesheet" type="text/css">
        <title>Admin groups</title>
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
                                    <a href="${contextPath}/group/users/info?group=${group.name}&groupId=${group.id}">Show members</a>
                                </td>
                                <td class="td-action">
                                    <c:if test="${empty ADMIN_USERNAME and group.name != ADMIN_GROUP or ADMIN_USERNAME == true}">
                                        <a href="${contextPath}/admin/add/userToGroup?groupId=${group.id}">Assign member</a>
                                    </c:if>
                                </td>
                                <td class="td-action">
                                    <c:if test="${group.name != ADMIN_GROUP}">
                                        <a href="${contextPath}/admin/edit/group?groupId=${group.id}">Edit</a>
                                    </c:if>
                                </td>
                                <td class="td-action">
                                    <c:if test="${group.name != ADMIN_GROUP}">
                                        <a href="${contextPath}/admin/delete/group?groupId=${group.id}">Delete</a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </table>

            <button class="addButton"><a class="addButton" href="${contextPath}/admin/add/group">Add new</a></button>

            <c:if test="${not empty isEmpty and !isEmpty and !tryingAdminGroupNameDelete}">
                <p class="feedback-text-submitted">You can delete only empty group.</p>
            </c:if>

            <c:if test="${tryingAdminGroupNameDelete}">
                <p class="feedback-text-submitted">You cannot delete the ADMIN group.</p>
            </c:if>

        </div>

    </body>
</html>