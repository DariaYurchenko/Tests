<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<fmt:setLocale value="${appLocale}" scope="session"/>
<fmt:setBundle basename="languages/messages"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Themes</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="../resources/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<c:url value="../resources/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="../resources/css/tests_to_pass.css"/>">
</head>
<body>
<div class="row">
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
                    <input type="hidden" value="jsp/tests_to_pass.jsp" name="address">
                    <button type="submit" id="lang1" class="form-control" name="lang" value="eng"><img src="../resources/images/uk_flag.jpg"></button>
                    <button type="submit" id="lang2" class="form-control" name="lang" value="rus"><img src="../resources/images/rus_flag.jpg"></button>
                </form>
            </div>
        </nav>
    </header>
    <section class="courses col-lg-8 col-md-9 col-sm-12">
        <div class="select-div"><fmt:message key="select_module"/></div>
        <div class="course-container d-flex align-items-center">
            <div class="course-img-container"><img src="../resources/images/arrays.svg"></div>
            <div class="course">
                <form method="get" action="tests">
                    <input type="hidden" value="1" name="theme_id">
                    <input type="hidden" name="counter" value="0">
                    <input type="hidden" name="command" value="START_TEST">
                    <c:if test="${fn:contains(userThemes, 1)}">
                        <button type="submit" disabled>Collections</button>
                    </c:if>
                    <c:if test="${not fn:contains(userThemes, 1)}">
                        <button type="submit">Collections</button>
                    </c:if>
                    <%--<button type="submit">Collections</button>--%>
                </form>
            </div>
        </div>
        <div class="course-container d-flex align-items-center">
            <div class="course-img-container"><img src="../resources/images/if-else.svg"></div>
            <div class="course">
                <form method="get" action="tests">
                    <input type="hidden" value="2" name="theme_id">
                    <input type="hidden" name="counter" value="0">
                    <input type="hidden" name="command" value="START_TEST">
                    <c:if test="${fn:contains(userThemes, 2)}">
                        <button type="submit" disabled>If else, switch and loopss</button>
                    </c:if>
                    <c:if test="${not fn:contains(userThemes, 2)}">
                        <button type="submit">If else, switch and loops</button>
                    </c:if>
                    <%--<button type="submit">If else, switch and loops</button>--%>
                </form>
            </div>
        </div>
        <div class="course-container d-flex align-items-center">
            <div class="course-img-container"><img src="../resources/images/inheritance.svg"></div>
            <div class="course">
                <form method="get" action="tests">
                    <input type="hidden" value="3" name="theme_id">
                    <input type="hidden" name="counter" value="0">
                    <input type="hidden" name="command" value="START_TEST">
                    <c:if test="${fn:contains(userThemes, 3)}">
                        <button type="submit" disabled>Inheritance and polymorphism</button>
                    </c:if>
                    <c:if test="${not fn:contains(userThemes, 3)}">
                        <button type="submit">Inheritance and polymorphism</button>
                    </c:if>
                    <%--<button type="submit">Inheritance and polymorphism</button>--%>
                </form>
            </div>
        </div>
        <div class="course-container d-flex align-items-center">
            <div class="course-img-container"><img src="../resources/images/threads.svg"></div>
            <div class="course">
                <form method="get" action="tests">
                    <input type="hidden" value="4" name="theme_id">
                    <input type="hidden" name="counter" value="0">
                    <input type="hidden" name="command" value="START_TEST">
                    <c:if test="${fn:contains(userThemes, 4)}">
                        <button type="submit" disabled>Threads, concurrency</button>
                    </c:if>
                    <c:if test="${not fn:contains(userThemes, 4)}">
                        <button type="submit">Threads, concurrency</button>
                    </c:if>
                    <%--<button type="submit">Threads, concurrency</button>--%>
                </form>
            </div>
        </div>
        <div class="course-container d-flex align-items-center">
            <div class="course-img-container"><img src="../resources/images/primitive.svg"></div>
            <div class="course">
                <form method="get" action="tests">
                    <input type="hidden" value="5" name="theme_id">
                    <input type="hidden" name="counter" value="0">
                    <input type="hidden" name="command" value="START_TEST">
                    <c:if test="${fn:contains(userThemes, 5)}">
                        <button type="submit" disabled>Primitive type conversions</button>
                    </c:if>
                    <c:if test="${not fn:contains(userThemes, 5)}">
                        <button type="submit">Primitive type conversions</button>
                    </c:if>
                    <%--<button type="submit">Primitive type conversions</button>--%>
                </form>
            </div>
        </div>
        <div class="course-container d-flex align-items-center">
            <div class="course-img-container"><img src="../resources/images/operators.svg"></div>
            <div class="course">
                <form method="get" action="tests">
                    <input type="hidden" value="6" name="theme_id">
                    <input type="hidden" name="counter" value="0">
                    <input type="hidden" name="command" value="START_TEST">
                    <c:if test="${fn:contains(userThemes, 6)}">
                        <button type="submit" disabled>Operators</button>
                    </c:if>
                    <c:if test="${not fn:contains(userThemes, 6)}">
                        <button type="submit">Operators</button>
                    </c:if>
                    <%--<button type="submit">Operators</button>--%>
                </form>
            </div>
        </div>
        <div class="decorative-img col-lg-3 col-md-3 col-sm-5"><img src="../resources/images/fox.jpg"></div>
    </section>
    <c:if test="${submitWindow == 'TRUE'}">
    <div class="alert-window">
        <p class="text-center text-white col-lg-4 col-md-6 col-sm-8"><strong><fmt:message key="email_was_sent"/><span class="cross bg-white">&#10006;</span></strong></p>
    </div>
    <script>
        $(document).ready(function(){
            $("div.alert-window p strong span").on('click', function(){
                $("div.alert-window").remove();
            });
        });
    </script>
    </c:if>
</div>
</body>
</html>
