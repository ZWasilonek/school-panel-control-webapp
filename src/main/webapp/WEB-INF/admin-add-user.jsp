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
        <title>Admin add user</title>
        <link href='<c:url value="/resources/css/styles.css"/>' rel="stylesheet" type="text/css">
    </head>

    <body>

        <%@ include file="/WEB-INF/fragment/navbar.jsp" %>

        <div class="main-container-add">
            <div class="container-add">
                <h1>ADD USER</h1>

                <div class="content-add">

                    <form action="${contextPath}/admin/add/user" method="post">
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

                                <c:when test="${hasGroup eq false}">
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

                </div>

            </div>

            <div class="backButton">
                <button><a href="${contextPath}/admin/groups">Back</a></button>
            </div>

            <c:if test="${userCreated}">
                <p class="feedback-text-submitted">New user created.</p>
            </c:if>

        </div>

    </body>
</html>