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
        <title>Admin users</title>
        <link href='<c:url value="/resources/css/styles.css"/>' rel="stylesheet" type="text/css">
    </head>

    <body>

        <%@ include file="/WEB-INF/fragment/navbar.jsp" %>

        <div class="container-group">
            <table>
                <thead>
                <tr>
                    <th>User name</th>
                    <th colspan="3" class="action-th">Actions</th>
                </tr>
                </thead>
                <c:choose>
                    <c:when test="${users.size() == 0}">
                        <tr>
                            <td colspan="2" class="feedback-text-submitted">No user found in the registry.</td>
                        </tr>
                    </c:when>

                    <c:otherwise>
                        <c:forEach items="${users}" var="user">
                            <tr>
                                <td class="text-breaker">
                                    <c:out value="${user.userName}"/>
                                </td>
                                <td class="td-action">
                                    <a href="${contextPath}/user/info?userId=${user.id}">Show</a>
                                </td>
                                <td class="td-action">
                                    <a href="${contextPath}/admin/edit/user?userId=${user.id}">Edit</a>
                                </td>
                                <td class="td-action">
                                    <a href="${contextPath}/admin/delete/user?userId=${user.id}">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </table>

            <button class="addButton">
                <a class="addButton" href="${contextPath}/admin/add/user">Add new</a>
            </button>
        </div>

    </body>
</html>
