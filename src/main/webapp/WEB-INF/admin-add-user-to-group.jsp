<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="<c:url value='/resources/css/styles.css'/>" rel="stylesheet" type="text/css">
        <title>Admin users group</title>
    </head>

    <body>

        <%@ include file="/WEB-INF/fragment/navbar.jsp" %>

        <div class="container-group">
            <h1>USERS</h1>

            <table>
                <thead>
                <tr>
                    <th>User name</th>
                    <th>User group</th>
                    <th colspan="2" class="action-th">Actions</th>
                </tr>
                </thead>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td class="text-breaker">
                            <c:out value="${user.userName}"/>
                        </td>
                        <td class="text-breaker">
                            <c:out value="${user.group.name}"/>
                        </td>
                        <td class="td-action">
                            <c:if test="${user.group.name ne group.name}">
                                <a href="${contextPath}/admin/add/userToGroup?userId=${user.id}&groupId=${group.id}">Assign to group <c:out value="${group.name}"/></a>
                            </c:if>
                        </td>
                        <td class="td-action">
                            <a href="${contextPath}/user/info?userId=${user.id}">User detail</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>

        </div>

    </body>
</html>
