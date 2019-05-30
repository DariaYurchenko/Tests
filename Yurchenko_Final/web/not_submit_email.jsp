<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${appLocale}" scope="session"/>
<fmt:setBundle basename="messages/messages"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/not_submit_email.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <style>
        @font-face{
            font-family: "Cookie Regular";
            src: url("fonts/shumi.otf")
        }
        body{
            background: #F2F2F2;
            font-family: apple-system,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif,"Apple Color Emoji","Segoe UI Emoji","Segoe UI Symbol"
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
        div.row{
            width: 100%;
            margin-left: 0;
            margin-right: 0
        }
        section{
            width: 100%;
            padding: 20px 10px
        }
        section div.decorative-img{
            margin: 25px 20px 0;
            padding-left: 0;
            padding-right: 0
        }
        section div.decorative-img > img{
            width: 100%;
            min-width: 145px;
            max-width: 250px
        }
        section div > form a, section div.message-container > div.message form > button{
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
        section div > form a:hover, section div > form a:active, section div > form a:visited{
            color: white;
            text-decoration: none
        }
        section div > form a:hover, section div.message-container > div.message form > button:hover{
            transform: scale(1.1);
            transition: transform 1s
        }
        section div.message-container > div.message{
            border: 2px solid black;
            padding: 10px;
            background: #ffa500;
            border-radius: 10px
        }
        section div.message-container > div.message > p{
            font-size: 20px
        }
        section div.message-container > div.message form{
            vertical-align: top
        }

        section div.message-container > div.message form > button{
            padding: 10px 25px
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

    <title>Not confirm email</title>
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
                    <input type="hidden" value="not_submit_email.jsp" name="address">
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
                <c:if test="${requestScope.sent == null}">
                <p class="text-center"><fmt:message key="did_not_conf_email"/>(<br><fmt:message key="send_again"/></p>
                <div class="text-center">
                    <form class="d-inline" action="tests" method="get">
                        <input type="hidden" name="command" value="SEND_EMAIL_AGAIN">
                        <button class="btn" type="submit"><fmt:message key="yes"/></button>
                        <c:if test="${requestScope.sent != null}">
                            <a href="start_page.jsp"><fmt:message key="back"/></a>
                        </c:if>
                    </form>
                </div>
                </c:if>
                <c:if test="${requestScope.sent != null}">
                    <p class="text-center"><fmt:message key="sent_email_again"/></p>
                    <div class="text-center">
                        <form class="d-inline" action="tests" method="get">
                                <a href="start_page.jsp"><fmt:message key="back"/></a>
                        </form>
                    </div>
                </c:if>
            </div>
        </div>
    </section>
</div>
</body>
</html>
