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
        <a class="navbar-brand" href="<c:url value="/tests?page=start_page"/>">JavaFox</a>
        <div class="signUp">
            <ul class="navbar-nav mr-4 d-inline-block">
                <c:if test="${sessionScope.user != null && sessionScope.userStatus == 'Student'}">
                    <li class="nav-item d-inline-block">
                        <a class="nav-link"><fmt:message key="hello"/><c:out value="${sessionScope.user.name}"/></a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.user != null && sessionScope.userStatus == 'Admin'}">
                    <li class="nav-item d-inline-block">
                        <a class="nav-link" href="<c:url value="/tests?page=admin_page"/>"><fmt:message key="admin_page"/></a>
                    </li>
                </c:if>
            </ul>
            <a href="<c:url value="/tests?page=register_page"/>" class="btn btn-primary"><fmt:message key="sign_up"/></a>
        </div>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse " id="navbarSupportedContent">
            <ul class="navbar-nav mr-4">
                <c:if test="${sessionScope.user == null}">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/tests?page=login_page"/>"><fmt:message key="log_in"/></a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.user != null}">
                    <li>
                        <form method="get" action="tests" name="logout-form">
                            <input type="hidden" name="command" value="LOGOUT">
                            <div>
                                <button type="submit"><fmt:message key="log_out"/></button>
                            </div>
                        </form>
                    </li>
                </c:if>
                <li class="nav-item">
                    <a class="nav-link " href="<c:url value="/tests?page=tests_to_pass"/>"><fmt:message key="tests"/></a>
                </li>
            </ul>

        </div>
        <div class="languages">
            <form action="tests" method="GET">
                <input type="hidden" value="CHANGE_LANGUAGE" name="command">
                <input type="hidden" value="jsp/show_results.jsp" name="address">
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
