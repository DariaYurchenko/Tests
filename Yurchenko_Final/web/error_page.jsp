<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${appLocale}" scope="session"/>
<fmt:setBundle basename="messages/messages"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="css/bootstrap.min.js"></script>
    <title>Error</title>
    <style>
        @font-face{
            font-family: "Cookie Regular";
            src: url("fonts/Cookie Regular.ttf")
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
            padding-top: 20px;
            font-size: 25px;
            margin: 10px auto
        }
        section > div{
            margin: 15px auto;
            position: relative
        }
        section > div > img{
            width: 100%;
            max-width: 700px
        }
        section > div > div.error-text{
            position: absolute;
            right: 50px;
            top: 100px
        }
        @media (max-width: 768px) {
            section > div > div.error-text{
                position: static;
            }
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
                    <input type="hidden" value="error_page.jsp" name="address">
                    <button type="submit" id="lang1" class="form-control" name="lang" value="eng"><img src="images/uk_flag.jpg"></button>
                    <button type="submit" id="lang2" class="form-control" name="lang" value="rus"><img src="images/rus_flag.jpg"></button>
                </form>
            </div>
        </nav>
    </header>
    <section>
        <div class="error-container"><div class="error-text text-center"><fmt:message key="error"/></div><img src="images/error_fox.jpg"></div>
    </section>
</div>
</body>
</html>
