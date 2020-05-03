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
        <div class="container-group">
            <table>
                <c:forEach items="${groups}" var="group">
                    <tr>
                        <td>
                            <a href="${contextPath}/infoGroupUsers?group=${group.name}&groupId=${group.id}">
                                <c:out value="${group.id}"/> :
                                <c:out value="${group.name }"/>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
