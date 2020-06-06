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
        <title>Admin edit user</title>
        <link href='<c:url value="/resources/css/styles.css"/>' rel="stylesheet" type="text/css">
    </head>

    <body>

        <%@ include file="/WEB-INF/fragment/navbar.jsp" %>

        <div class="main-container-add">
            <div class="container-add">

                <h2 class="text-breaker">EDIT USER <br> <c:out value="${user.userName}"/></h2>

                <div class="content-add">
                    <c:choose>

                        <c:when test="${userNotExists}">
                            <p class="feedback-text-submitted">Such user is not registered.</p>
                        </c:when>

                        <c:otherwise>
                            <form action="${contextPath}/adminEditUser" method="post">
                                <input type="hidden" value="${user.id}" name="userId">
                                <label>
                                    <input type="text" name="userName" placeholder="name" value="<c:out value='${userNameVal}'/>"/>
                                    <c:if test="${not empty blankUserName}">
                                        <label class="hasError"><c:out value="${blankUserName}"/></label>
                                    </c:if>
                                    <c:if test="${not empty capacityExceededUserName}">
                                        <label class="hasError"><c:out value="${capacityExceededUserName}"/></label>
                                    </c:if>
                                </label>

                                <label>
                                    <input type="email" name="userEmail" placeholder="email" value="<c:out value='${userEmailVal}'/>">
                                    <c:if test="${not empty blankUserEmail}">
                                        <label class="hasError"><c:out value="${blankUserEmail}"/></label>
                                    </c:if>
                                    <c:if test="${not empty notUniqueUserEmail}">
                                        <label class="hasError"><c:out value="${notUniqueUserEmail}"/></label>
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
                                    <c:if test="${not empty passwordToShort}">
                                        <label class="hasError"><c:out value="${passwordToShort}"/></label>
                                    </c:if>
                                    <c:if test="${not empty passwordsNotMatch}">
                                        <label class="hasError"><c:out value="${passwordsNotMatch}"/></label>
                                    </c:if>
                                </label>

                                <label>
                                    <input type="password" name="rePass" placeholder="repeat password">
                                    <c:if test="${not empty blankRePass}">
                                        <label class="hasError"><c:out value="${blankRePass}"/></label>
                                    </c:if>
                                    <c:if test="${not empty passwordsNotMatch}">
                                        <label class="hasError"><c:out value="${passwordsNotMatch}"/></label>
                                    </c:if>
                                </label>

                                <label class="selector-add-user">
                                    <div>
                                        <c:choose>
                                            <c:when test="${not empty hasGroup and hasGroup eq false}">
                                                <span>register groups first before you start adding a user</span>
                                            </c:when>

                                            <c:otherwise>
                                                <span>Select group: </span>
                                                <select name="groupName">
                                                    <c:forEach var="group" items="${groups}">
                                                        <option>${group.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </label>

                                <label>
                                    <input type="submit" value="Save">
                                </label>
                            </form>
                        </c:otherwise>

                    </c:choose>
                </div>
            </div>
            <div class="backButton">
                <button><a href="${contextPath}/adminUsers">Back</a></button>
            </div>

            <c:if test="${isUpdated}">
                <p class="feedback-text-submitted">User updated.</p>
            </c:if>
        </div>

    </body>
</html>