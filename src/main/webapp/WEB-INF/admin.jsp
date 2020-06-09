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
        <title>Admin</title>
        <link href='<c:url value="/resources/css/styles.css"/>' rel="stylesheet" type="text/css">
    </head>

    <body>

        <%@ include file="/WEB-INF/fragment/navbar.jsp" %>

        <div class="container-group">
            <table>
                <thead>
                    <tr>
                        <th>Manage</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><a href="${contextPath}/admin/exercises">Exercises</a></td>
                    </tr>
                    <tr>
                        <td><a href="${contextPath}/admin/groups">Groups</a></td>
                    </tr>
                    <tr>
                        <td><a href="${contextPath}/admin/users">Users</a></td>
                    </tr>
                </tbody>
            </table>
        </div>

    </body>
</html>
