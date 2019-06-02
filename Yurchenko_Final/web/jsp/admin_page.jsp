<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${appLocale}" scope="session"/>
<fmt:setBundle basename="languages/messages"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Admin page</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="../resources/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<c:url value="../resources/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="../resources/css/admin_page.css"/>">
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
                <input type="hidden" value="jsp/admin_page.jsp" name="address">
                <button type="submit" id="lang1" class="form-control" name="lang" value="eng"><img src="../resources/images/uk_flag.jpg"></button>
                <button type="submit" id="lang2" class="form-control" name="lang" value="rus"><img src="../resources/images/rus_flag.jpg"></button>
            </form>
        </div>
    </nav>
</header>

<div class="d-flex">
    <aside class="col-lg-3">
        <div class="bg-white text-center">
            <h2><fmt:message key="admin_users"/></h2>
            <form method="get" action="tests">
                <input type="hidden" name="command" value="SHOW_ALL_USERS">
                <input type="hidden" name="act" value="JUST_SHOW">
                <input type="hidden" name="currentPage" value="1">
                <button class="btn" type="submit"><fmt:message key="admin_show_u"/></button>
            </form>
            <form method="get" action="tests">
                <input type="hidden" name="command" value="SHOW_ALL_USERS">
                <input type="hidden" name="act" value="DELETE_USER_BY_ID">
                <input type="hidden" name="currentPage" value="1">
                <button class="btn" type="submit"><fmt:message key="admin_delete_u_id"/></button>
            </form>
            <form method="get" action="tests">
                <input type="hidden" name="command" value="SHOW_ALL_USERS">
                <input type="hidden" name="act" value="DELETE_ALL_USERS">
                <input type="hidden" name="currentPage" value="1">
                <button class="btn" type="submit"><fmt:message key="admin_delete_u_all"/></button>
            </form>
            <form method="get" action="tests">
                <input type="hidden" name="command" value="SHOW_ALL_USERS">
                <input type="hidden" name="act" value="SHOW_USER_RESULTS">
                <input type="hidden" name="currentPage" value="1">
                <button class="btn" type="submit"><fmt:message key="admin_show_results"/></button>
            </form>
        </div>
        <div class="bg-white text-center">
            <h2><fmt:message key="admin_questions"/></h2>
            <form method="get" action="tests">
                <input type="hidden" name="currentPage" value="1">
                <input type="hidden" name="act" value="JUST_SHOW">
                <input type="hidden" name="command" value="SHOW_ALL_QUESTIONS">
                <button class="btn" type="submit"><fmt:message key="admin_show_questions"/></button>
            </form>
            <form method="get" action="tests">
                <input type="hidden" name="currentPage" value="1">
                <input type="hidden" name="act" value="SHOW_BY_THEME">
                <input type="hidden" name="command" value="SHOW_THEMES">
                <button class="btn" type="submit"><fmt:message key="admin_show_theme_questions"/></button>
            </form>
        </div>
        <div class="bg-white text-center">
            <h2><fmt:message key="admin_others"/></h2>
            <form method="get" action="tests">
                <input type="hidden" name="currentPage" value="1">
                <input type="hidden" name="command" value="SHOW_ALL_USERS">
                <input type="hidden" name="act" value="REGISTER_ADMIN">
                <button class="btn" type="submit"><fmt:message key="admin_register"/></button>
            </form>
        </div>
    </aside>
    <section class="col-lg-9">
        <h1 class="text-center"><fmt:message key="welcome_admin"/></h1>
        <div class="img-div text-center"><img src="../resources/images/reg_fox.jpg"></div>
    </section>
</div>
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
