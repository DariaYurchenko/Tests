<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${appLocale}" scope="session"/>
<fmt:setBundle basename="messages/messages"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>JavaFox</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/owl.carousel.min.css">
    <link rel="stylesheet" href="css/owl.theme.default.min.css">
    <link href="css/start_page.css" rel="stylesheet">
    <script src="js/owl.carousel.min.js"></script>
    <style>
        @font-face{
            font-family: "Cookie Regular";
            src: url("../fonts/shumi.otf")

        }
        .owl-item > div > a{
            color: black;
            text-decoration: none;
            padding: 30px 10px;
            display: inline-block
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
        body{
            background: #F2F2F2
        }
        .row{
            margin-right: 0;
            margin-left: 0
        }
        header.header{
            padding-left: 0;
            padding-right: 0;
            position: sticky;
            top: 0;
            z-index: 11
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
        .first-section, header.header{
            padding-left: 0;
            padding-right: 0
        }
        .first-section > div:first-child{
            margin: 100px auto;
            position: relative
        }
        .first-section > div:first-child > .fox{
            position: absolute;
            right: 100%;
            top: 0;
            height: 100%;
        }
        .first-section > div:first-child > .fox > img{
            height: 100%
        }
        .first-section > div:first-child > div{
            margin-top: 20px
        }
        .first-section > div:first-child > h1{
            margin-bottom: 35px;
            font-family: "Cookie Regular";
        }
        .first-section .sign-up-button{
            background: #2F2F64;
            color: #ffa500;
            font-family: "Cookie Regular";
            transition: transform 1s
        }
        .first-section .sign-up-button:hover{
            transform: scale(1.15);
            transition: transform 1s
        }
        .owl-nav{
            display: none
        }
        .owl-dots{
            text-align: center
        }
        .owl-dots button{
            border: 0;
            outline: 0;
        }
        .owl-dot span{
            width: 10px;
            height: 10px;
            margin: 5px 7px;
            background: #D6D6D6;
            display: block;
            border-radius: 30px
        }
        .owl-dot.active span{
            background: grey;
        }
        .owl-item > div{
            margin: auto;
            width: 70%;
            text-align: center;
            background: orange;
            border-radius: 15px;
            border: 2px solid black
        }
        .owl-stage-outer{
            margin: 15px 0
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
                    <input type="hidden" value="start_page.jsp" name="address">
                    <button type="submit" id="lang1" class="form-control" name="lang" value="eng"><img src="images/uk_flag.jpg"></button>
                    <button type="submit" id="lang2" class="form-control" name="lang" value="rus"><img src="images/rus_flag.jpg"></button>
                </form>
            </div>
        </nav>
    </header>

    <section class="first-section col-lg-12">
        <div class="col-lg-6 col-md-6 col-sm-12 text-center">
            <h1><fmt:message key="best_developer"/></h1>
            <div><button onclick="window.location = 'register_page.jsp'" class="btn sign-up-button"><fmt:message key="start_regiseter"/></button></div>
            <div><fmt:message key="start_message"/></div>
            <div class="fox col-sm-0"><img src="images/fox.jpg"></div>
        </div>
        <div class="owl-carousel">
            <div><a href="tests_to_pass.jsp">Collections</a></div>
            <div><a href="tests_to_pass.jsp">If else, switch and loops </a></div>
            <div><a href="tests_to_pass.jsp">Inheritance and polymorphism</a></div>
            <div><a href="tests_to_pass.jsp">Threads, concurrency</a></div>
            <div><a href="tests_to_pass.jsp">Operators</a></div>
            <div><a href="tests_to_pass.jsp">Primitive types conversions</a></div>
        </div>
    </section>
    <script>
        $(function() {
            $(".owl-carousel").owlCarousel({
                nav: true,
                responsive:{
                    0:{
                        items:1
                    },
                    600:{
                        items:2
                    },
                    1000:{
                        items:3
                    }
                }
            });
        });

    </script>
</div>
</body>
</html>
