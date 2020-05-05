<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Solution details</title>
    <link href='<c:url value="/css/style.css"/>' rel="stylesheet" type="text/css">
</head>

<body>
    <%@ include file="/WEB-INF/fragment/navbar.jsp" %>

    <div class="container-group">
        <table>
            <thead>
                <tr>
                    <th><c:out value="${exerciseTitle}"/> solution details:</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="solution" items="${solutions}">
                <tr>
                    <td><c:out value="${solution.description}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</body>
</html>
