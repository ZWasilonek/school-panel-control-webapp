<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Groups</title>
    <link href='<c:url value="/css/style.css"/>' rel="stylesheet" type="text/css">
</head>

<body>
<%@ include file="/WEB-INF/fragment/navbar.jsp" %>
<table>
    <c:forEach items="${groups}" var="group">
        <tr>
            <td>
                <c:out value="${group.name }" />
                <a href="${contextPath}/infoGroupUsers?group=${group.name}&groupId=${group.id}">
                        <c:out value="${group.id}"/><a/>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
