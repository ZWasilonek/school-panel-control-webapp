<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="<c:url value='/resources/css/styles.css'/>" rel="stylesheet" type="text/css">
        <title>Login</title>
    </head>

    <body>

        <%@ include file="/WEB-INF/fragment/navbar.jsp" %>

        <div class="main-container-add">
            <div class="container-add">
                <h1>LOGIN</h1>

                <div class="content-add">

                    <form action="${contextPath}/login" method="post">
                        <label>
                            <input type="email" name="userEmail" placeholder="email" value="<c:out value='${userEmailVal}'/>">
                            <c:if test="${not empty blankUserEmail}">
                                <label class="hasError"><c:out value="${blankUserEmail}"/></label>
                            </c:if>
                            <c:if test="${not empty capacityExceededUserEmail}">
                                <label class="hasError"><c:out value="${capacityExceededUserEmaile}"/></label>
                            </c:if>
                        </label>

                        <label>
                            <input type="password" name="userPass" placeholder="password">
                            <c:if test="${not empty blankUserPass}">
                                <label class="hasError"><c:out value="${blankUserPass}"/></label>
                            </c:if>
                            <c:if test="${not empty capacityExceededUserPass}">
                                <label class="hasError"><c:out value="${capacityExceededUserPass}"/></label>
                            </c:if>
                        </label>

                        <label>
                        <c:if test="${failedLogin}">
                            <label class="hasError">incorrect email or password</label>
                        </c:if>
                        </label>

                        <label>
                            <input type="submit" value="Login">
                        </label>
                    </form>

                </div>

            </div>

        </div>

    </body>
</html>