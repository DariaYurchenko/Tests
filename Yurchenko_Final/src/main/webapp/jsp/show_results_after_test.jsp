<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${appLocale}" scope="session"/>
<fmt:setBundle basename="languages/messages"/>
<html>
<head>
    <title>Results</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="../resources/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="../resources/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<c:url value="../resources/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="../resources/css/show_results.css"/>">
</head>
<body>
<header class="header col-lg-12">
    <nav class="navbar navbar-dark bg-dark navbar-expand-md col-lg-12">
        <%@include file="header.jsp" %>
        <div class="languages">
            <form action="tests" method="GET">
                <input type="hidden" value="CHANGE_LANGUAGE" name="command">
                <input type="hidden" value="jsp/show_results_after_test.jsp" name="address">
                <button type="submit" id="lang1" class="form-control" name="lang" value="eng"><img src="../resources/images/uk_flag.jpg"></button>
                <button type="submit" id="lang2" class="form-control" name="lang" value="rus"><img src="../resources/images/rus_flag.jpg"></button>
            </form>
        </div>
    </nav>
</header>
<section class="text-center">
    <div class="d-flex align-items-center justify-content-center flex-wrap">
        <div class="decorative-img"><img src="../resources/images/reg_fox.jpg"></div>
        <div class="results-container">
            <h1><fmt:message key="results"/></h1>
            <p><fmt:message key="you_got"/><c:out value="${sessionScope.userPoints}"/> <fmt:message key="points_from"/><c:out value="${sessionScope.maxPoints}"/>.</p>
            <p><fmt:message key="percentage"/><span class="correct-percentage"><c:out value="${sessionScope.percent}"/>%</span>.</p>
            <c:if test="${sessionScope.passed != null}">
                <p><strong class="text-success"><fmt:message key="you_passed"/></strong></p>
            </c:if>
            <p><fmt:message key="email_tests"/></p>
            <c:if test="${sent == null}">
                <form action="tests" method="get">
                    <input type="hidden" name="command" value="SEND_RESULTS">
                    <button class="pretty-button" type="submit"><fmt:message key="send"/></button>
                    <a class="pretty-button" href="<c:url value="/tests?page=start_page"/>"><fmt:message key="no"/></a>
                </form>
            </c:if>
            <c:if test="${sent != null}">
                <p><fmt:message key="was_sent"/></p>
                <a class="pretty-button" href="<c:url value="/tests?page=start_page"/>"><fmt:message key="back"/></a>
            </c:if>
        </div>
    </div>
</section>



</body>
</html>
