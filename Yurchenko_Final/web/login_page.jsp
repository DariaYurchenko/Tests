<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${appLocale}" scope="session"/>
<fmt:setBundle basename="messages/messages"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Login</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!--<link href="css/log_page.css" rel="stylesheet">-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <style>
        @font-face{
            font-family: "Cookie Regular";
            src: url("fonts/shumi.otf")

        }
        body{
            background: #F2F2F2
        }
        header.header{
            padding-left: 0;
            padding-right: 0
        }
        .navbar-dark {
            background: #2F2F64 !important
        }
        .navbar-dark .navbar-brand{
            font: 30px "Cookie Regular";
            color: #ffa500;
        }
        #navbarSupportedContent{
            justify-content: flex-end;
            flex-grow: 0;

        }
        .navbar-collapse{

        }
        .nav-item{
            margin: 0 5px;
            font-size: 20px
        }
        .navbar-dark .navbar-nav .nav-link{
            text-align: right;
            color: white
        }
        .signUp{
            flex-grow: 1;
            text-align: right;
            font: 22px bold;
            margin-right: 15px;
        }
        .signUp > button{
            font-size: 20px;
            font-family: apple-system,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif,"Apple Color Emoji","Segoe UI Emoji","Segoe UI Symbol"
        }
        div.languages{
            margin-left: 15px
        }
        div.languages > form img{
            width: 30px;
            height: 15px
        }
        div.languages > form{
            margin-bottom: 0
        }
        div.languages > form > button{
            padding: 0;
            border: none;
            display: inline-block;
            width: auto
        }
        div.row{
            width: 100%;
            margin-left: 0;
            margin-right: 0
        }
        section{
            width: 100%
        }
        form[name="login-form"]{
            margin: 20px auto 0 auto;
            padding: 10px
        }
        label{
            font-weight: 600;
            font-size: 20px
        }
        form[name="forgot-pass-form"]{
            margin: 5px auto
        }
        form[name="forgot-pass-form"] > p.forgot-message{
            font-size: 20px
        }
        section.img-section{
            width: 100%
        }
        section.img-section > div.img-div{
            width: 25%;
            margin-left: 10%
        }
        section.img-section > div.img-div > img{
            width: 75%;
            min-width: 130px
        }

        div.button-div button.btn, div.button-div input.btn{
            width: 170px;
            height: 40px;
            transition: transform 1s;
            background: #2F2F64;
            font-family: "Cookie Regular";
            color: #ffa500
        }
        div.button-div button.btn:hover, div.button-div input.btn:hover{
            transform: scale(1.1);
            transition: transform 1s
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
                    <input type="hidden" value="login_page.jsp" name="address">
                    <button type="submit" id="lang1" class="form-control" name="lang" value="eng"><img src="images/uk_flag.jpg"></button>
                    <button type="submit" id="lang2" class="form-control" name="lang" value="rus"><img src="images/rus_flag.jpg"></button>
                </form>
            </div>
        </nav>
    </header>
    <section>
        <form action="tests" method="POST" name="login-form" class="col-lg-6 col-md-8 col-sm-9">
            <div class="form-group">
                <label for="login"><fmt:message key="register_login"/></label>
                <input type="email" class="form-control" id="login" placeholder="ivan.petrov@gmail.com" name="login">
                <c:if test="${requestScope.errLogin != null}">
                    <small class="text-danger"><c:out value="${requestScope.errLogin}"/></small>
                </c:if>
                <c:if test="${requestScope.loginMessage != null}">
                    <small class="text-danger"><c:out value="${requestScope.loginMessage}"/></small>
                </c:if>
            </div>
            <c:if test="${requestScope.forgot != null}">
                <input name="command" value="CHANGE_PASSWORD" type="hidden">
                <div class="form-group">
                    <label for="newPassword"><fmt:message key="new_password"/></label>
                    <input type="password" class="form-control" id="newPassword" name="newPassword">
                    <c:if test="${requestScope.errPassword != null}">
                        <small class="text-danger"><c:out value="${requestScope.errPassword}"/></small>
                    </c:if>
                </div>
            </c:if>
            <c:if test="${requestScope.forgot == null}">
                <input value="LOGIN" name="command" type="hidden">
                <div class="form-group">
                    <label for="password"><fmt:message key="register_password"/></label>
                    <input type="password" class="form-control" id="password" name="password">
                    <c:if test="${requestScope.errPassword != null}">
                        <small class="text-danger"><c:out value="${requestScope.errPassword}"/></small>
                    </c:if>
                </div>
            </c:if>

            <div class="button-div form-group col-md-12 text-center">
                <input type="submit" class="btn" value="Log In">
            </div>
        </form>
    </section>
    <section class="text-center">
        <c:if test="${requestScope.forgotPassword != null}">
            <form class="col-lg-6" action="tests" name="forgot-pass-form" method="get">
                <input value="LOGIN" name="command" type="hidden">
                <input type="hidden" name="ifForgotPassword" value="TRUE">
                <p class="forgot-message"><fmt:message key="forgot_password"/></p>
                <div class="button-div form-group col-md-12 text-center">
                    <button class="btn" type="submit"><fmt:message key="yes"/></button>
                </div>
            </form>
        </c:if>

        <c:if test="${requestScope.passwordChanged != null}">
            <c:out value="${requestScope.passwordChanged}"/>
        </c:if>
    </section>
    <section class="img-section">
        <div class="img-div text-center"><img src="images/reg_fox.jpg"></div>
    </section>
</div>
</body>
</html>