<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${appLocale}" scope="session"/>
<fmt:setBundle basename="languages/messages"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>User's Tests</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/user_tests.css"/>">
</head>
<body>
<div class="row">
    <header class="header col-lg-12">
        <nav class="navbar navbar-dark bg-dark navbar-expand-md col-lg-12">
            <%@include file="header.jsp" %>
            <div class="languages">
                <form action="tests" method="GET">
                    <input type="hidden" value="CHANGE_LANGUAGE" name="command">
                    <input type="hidden" value="jsp/user_tests.jsp" name="address">
                    <button type="submit" id="lang1" class="form-control" name="lang" value="eng"><img src="<c:url value="/resources/images/uk_flag.jpg"/>"></button>
                    <button type="submit" id="lang2" class="form-control" name="lang" value="rus"><img src="<c:url value="/resources/images/rus_flag.jpg"/>"></button>
                </form>
            </div>
        </nav>
    </header>
    <div class="d-flex col-lg-12">
        <%@include file="admin_aside.jsp" %>
        <section class="col-lg-9">
            <div class="text-center"><fmt:message key="records_per_page"/></div>
            <ul class="pagination justify-content-center">
                <li class="page-item"><a class="page-link" href="<c:url value="/tests?command=SHOW_USER_RESULTS&recordsPerPage=5&radio=${userId}"/>">5</a>
                </li>
                <li class="page-item"><a class="page-link" href="<c:url value="/tests?command=SHOW_USER_RESULTS&recordsPerPage=10&radio=${userId}"/>">10</a>
                </li>
                <li class="page-item"><a class="page-link" href="<c:url value="/tests?command=SHOW_USER_RESULTS&recordsPerPage=50&radio=${userId}"/>">50</a>
                </li>
            </ul>
            <c:if test="${testInfoList != null}">
                <h1 class="text-center"><fmt:message key="admin_u_tests"/></h1>
                <div class="table-div">
                <table class="text-center table table-striped">
                        <c:set var="counter" value="1"/>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col"><fmt:message key="name_u"/></th>
                            <th scope="col"><fmt:message key="lastname_u"/></th>
                            <th scope="col"><fmt:message key="login_u"/></th>
                            <th scope="col"><fmt:message key="rank_u"/></th>
                            <th scope="col"><fmt:message key="admin_theme"/></th>
                            <th scope="col"><fmt:message key="admin_r_a"/></th>
                            <th scope="col"><fmt:message key="admin_all_a"/></th>
                            <th scope="col"><fmt:message key="admin_u_percent"/></th>
                            <th scope="col"><fmt:message key="admin_date"/></th>
                            <th scope="col"><fmt:message key="admin_test_status"/></th>
                            <th scope="col"></th>
                        </tr>
                        <c:forEach items="${testInfoList}" var="tests">
                            <tr>
                                <th scope="row"><c:out value="${counter}"/></th>
                                <td><c:out value="${tests.userName}"/></td>
                                <td><c:out value="${tests.userLastName}"/></td>
                                <td><c:out value="${tests.userLogin}"/></td>
                                <td><c:out value="${tests.userRank}"/></td>
                                <td><c:out value="${tests.theme}"/></td>
                                <td><c:out value="${tests.userPoints}"/></td>
                                <td><c:out value="${tests.maxPossiblePoints}"/></td>
                                <td><c:out value="${tests.rightAnswersPercent}"/></td>
                                <td><c:out value="${tests.date}"/></td>
                                <td><c:out value="${tests.testStatus}"/></td>
                            </tr>
                            <c:set var="counter" value="${counter+1}"/>

                        </c:forEach>


                    </table>
                    <c:if test="${testsSize == 0}">
                        <p class="text-center"><fmt:message key="didnot_pass_tests"/></p>
                    </c:if>
                    </c:if>
                </div>
                    <ul class="pagination justify-content-center">
                        <c:if test="${currentPage != 1}">
                            <li class="page-item"><a class="page-link"
                                                     href="<c:url value="/tests?command=SHOW_USER_RESULTS&recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}"/>"><fmt:message key="prev_ad"/></a>
                            </li>
                        </c:if>
                        <c:forEach begin="1" end="${noOfPages}" var="i">
                            <c:choose>
                                <c:when test="${currentPage eq i}">
                                    <li class="page-item active"><a class="page-link">
                                            ${i} <span class="sr-only">(current)</span></a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item"><a class="page-link" href="<c:url value="/tests?command=SHOW_USER_RESULTS&recordsPerPage=${recordsPerPage}&currentPage=${i}"/>">${i}</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>

                        <c:if test="${currentPage lt noOfPages}">
                            <li class="page-item"><a class="page-link" href="<c:url value="/tests?command=SHOW_USER_RESULTS&recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}"/>"><fmt:message key="next"/></a>
                            </li>
                        </c:if>
                    </ul>
        </section>
        <
    </div>
</div>

</body>
</html>
