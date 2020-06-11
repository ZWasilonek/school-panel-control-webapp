<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="<c:url value='/resources/css/styles.css'/>" rel="stylesheet" type="text/css">
        <title>Groups</title>
    </head>

    <body>

        <%@ include file="/WEB-INF/fragment/navbar.jsp" %>

        <div class="container-group">
            <table>
                <c:choose>
                    <c:when test="${groups.size() == 0}">
                        <th class="feedback-text-submitted">No group found in the registry.</th>
                    </c:when>

                    <c:otherwise>
                        <c:forEach items="${groups}" var="group" varStatus="groupNumber">
                            <tr>
                                <td class="index"><c:out value="${groupNumber.index + 1}"/></td>
                                <td>
                                    <a href="${contextPath}/group/users/info?group=${group.name}&groupId=${group.id}">
                                        <c:out value="${group.name}"/>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </table>
        </div>

    </body>
</html>