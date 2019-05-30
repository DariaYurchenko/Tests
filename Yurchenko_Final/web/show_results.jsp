<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${appLocale}" scope="session"/>
<fmt:setBundle basename="messages/messages"/>
<html>
<head>
    <title>Results</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <style>
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
        @font-face{
            font-family: "Cookie Regular";
            src: url("fonts/shumi.otf")

        }
        body{
            background: #ffa500;
            font-family: apple-system,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif,"Apple Color Emoji","Segoe UI Emoji","Segoe UI Symbol"
        }
        div.row{
            width: 100%;
            margin-left: 0;
            margin-right: 0
        }
        section{
            margin: 0 200px;
            transform: translateY(20%);
            padding: 10px;
            border: 2px solid;
            border-radius: 10px;
            background: #F2F2F2
        }
        section p{
            font-size: 20px
        }
        a.pretty-button, button.pretty-button{
            display: inline-block;
            padding: 10px 25px;
            margin: 5px 15px;
            border-radius: 4px;
            background: blue;
            color: white;
            font-size: 20px;
            font-family: apple-system,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif,"Apple Color Emoji","Segoe UI Emoji","Segoe UI Symbol";
            transition: transform 1s;
            vertical-align: middle;
            border: none
        }
        a.pretty-button:hover, a.pretty-button:active, a.pretty-button:visited{
            color: white;
            text-decoration: none
        }
        a.pretty-button:hover, button.pretty-button:hover{
            transform: scale(1.1);
            transition: transform 1s
        }
        span.correct-percentage{
            text-decoration: underline;
            font-weight: bold
        }
        div.decorative-img img{
            width: 250px;
        }
        div.results-container{
            padding-left: 20px
        }
        div.decorative-img{
            padding-right: 20px
        }
        div.results-container{
            flex-grow: 1
        }
        @media (max-width: 768px) {
            section{
                width: 80%;
                margin-top: 10px;
                transform: none
            }
            div.results-container{
                order: 1;
                margin-bottom: 15px
            }
            div.decorative-img{
                order: 2
            }
        }
    </style>
</head>
<body>
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
                        <a class="nav-link" href="admin/admin_page.jsp"><fmt:message key="admin_page"/></a>
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
                <input type="hidden" value="block/show_results.jsp" name="address">
                <button type="submit" id="lang1" class="form-control" name="lang" value="eng"><img src="images/uk_flag.jpg"></button>
                <button type="submit" id="lang2" class="form-control" name="lang" value="rus"><img src="images/rus_flag.jpg"></button>
            </form>
        </div>
    </nav>
</header>
<section class="text-center">
    <div class="d-flex align-items-center justify-content-center flex-wrap">
        <div class="decorative-img"><img src="images/reg_fox.jpg"></div>
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
                    <a class="pretty-button" href="start_page.jsp"><fmt:message key="no"/></a>
                </form>
            </c:if>
            <c:if test="${sent != null}">
                <p><fmt:message key="was_sent"/></p>
                <a class="pretty-button" href="start_page.jsp"><fmt:message key="back"/></a>
            </c:if>
        </div>
    </div>
</section>



</body>
</html>
