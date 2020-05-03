<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Admin exercises</title>
    <link href='<c:url value="/css/style.css"/>' rel="stylesheet" type="text/css">
</head>

<body>

    <%@ include file="/WEB-INF/fragment/navbar.jsp" %>

    <div class="container-group">
        <table>
            <thead>
            <tr>
                <th>Exercise name</th>
                <th>Actions</th>
            </tr>
            </thead>
            <c:forEach items="${exercises}" var="exercise">
                <tr>
                    <td>
                        <a href="${contextPath}/infoSolutions?exerciseId=${exercise.id}">
                            <c:out value="${exercise.title}"/>
                        </a>
                    </td>
                    <td>
                        <a href="${contextPath}/adminEditExercise?exerciseId=${exercise.id}">Edit</a>
                        <a href="${contextPath}/adminDeleteExercise?exerciseId=${exercise.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <button class="addButton"><a class="addButton" href="${contextPath}/adminAddExercise">Add new</a></button>

    </div>

    <%@ include file="/WEB-INF/fragment/footer.jsp" %>

</body>
</html>
