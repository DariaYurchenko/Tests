<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${appLocale}" scope="session"/>
<fmt:setBundle basename="messages/messages"/>
<html>
<head>
    <title>Submit registration</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/submit_key.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <style>
        @font-face{
            font-family: "Cookie Regular";
            src: url("fonts/shumi.otf")

        }
        form[name="logout-form"]{
            margin-bottom: 0;
            padding: 8px
        }
        form[name="logout-form"] button[type="submit"]{
            background: transparent;
            border: none;
            outline: none;
            font-size: 20px;
            color: white
        }
        form[name="logout-form"] button[type="submit"]:hover{
            color: #CBCBCE;
            cursor: pointer
        }
        .signUp li > a, .signUp > button{
            font-size: 20px;
            font-family: apple-system,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif,"Apple Color Emoji","Segoe UI Emoji","Segoe UI Symbol";
        }
        .signUp > button{
            border: none;
            vertical-align: top
        }
    </style>
</head>
<body>
<div class="row">
    <header class="header col-lg-12">
        <nav class="navbar navbar-dark bg-dark navbar-expand-md col-lg-12">
            <a class="navbar-brand" href="start_page.jsp">JavaFox</a>
            <div class="signUp">
                <ul class="navbar-nav mr-4 d-inline-block">
                    <c:if test="${sessionScope.user != null && sessionScope.userStatus == 'Student'}">
                        <li class="nav-item d-inline-block">
                            <a class="nav-link"><fmt:message key="hello"/><c:out value="${sessionScope.user.name}"/></a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope.user != null && sessionScope.userStatus == 'Admin'}">
                        <li class="nav-item d-inline-block">
                            <a class="nav-link" href="admin_page.jsp"><fmt:message key="admin_page"/></a>
                        </li>
                    </c:if>
                </ul>
                <button onclick="window.location = 'register_page.jsp'" class="btn btn-primary"><fmt:message key="sign_up"/></button>
            </div>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse " id="navbarSupportedContent">
                <ul class="navbar-nav mr-4">
                    <c:if test="${sessionScope.user == null}">
                        <li class="nav-item">
                            <a class="nav-link" href="login_page.jsp"><fmt:message key="log_in"/></a>
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
                        <a class="nav-link " href="tests_to_pass.jsp"><fmt:message key="tests"/></a>
                    </li>
                </ul>

            </div>
            <div class="languages">
                <form action="tests" method="GET">
                    <input type="hidden" value="CHANGE_LANGUAGE" name="command">
                    <input type="hidden" value="block/submit_key.jsp" name="address">
                    <button type="submit" id="lang1" class="form-control" name="lang" value="eng"><img src="images/uk_flag.jpg"></button>
                    <button type="submit" id="lang2" class="form-control" name="lang" value="rus"><img src="images/rus_flag.jpg"></button>
                </form>
            </div>
        </nav>
    </header>
    <section>
        <div class="message-container d-flex align-items-center justify-content-center flex-wrap">
            <div class="decorative-img col-lg-3 col-md-4 col-sm-12 text-left"><img src="images/fox.jpg"></div>
            <div class="message col-lg-6 col-md-7 col-sm-12">
                <c:if test="${incorrectKey == null}">
                <p class="text-center"><fmt:message key="email_success_confirm"/></p>
                </c:if>
                <c:if test="${incorrectKey != null}">
                    <p class="text-center"><fmt:message key="not_correct_submit_key"/></p>
                </c:if>
                <div class="text-center"><a href="start_page.jsp"><fmt:message key="back"></fmt:message></a></div>
            </div>
        </div>
    </section>
</div>
</body>


</html>
