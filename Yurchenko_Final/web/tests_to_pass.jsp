<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${appLocale}" scope="session"/>
<fmt:setBundle basename="messages/messages"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Themes</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/tests_to_pass.css" rel="stylesheet">
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
        section.courses > div.course-container > div.course form{
            margin-bottom: 0
        }
        section.courses > div.course-container > div.course form button{
            width: 100%;
            outline: none;
            background: white;
            border: none;
            font-size: 20px;
            padding: 30px;
            border-radius: 10px;
            cursor: pointer
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
                    <input type="hidden" value="tests_to_pass.jsp" name="address">
                    <button type="submit" id="lang1" class="form-control" name="lang" value="eng"><img src="images/uk_flag.jpg"></button>
                    <button type="submit" id="lang2" class="form-control" name="lang" value="rus"><img src="images/rus_flag.jpg"></button>
                </form>
            </div>
        </nav>
    </header>
    <section class="courses col-lg-8 col-md-9 col-sm-12">
        <div class="select-div"><fmt:message key="select_module"/></div>
        <div class="course-container d-flex align-items-center">
            <div class="course-img-container"><img src="images/arrays.svg"></div>
            <div class="course">
                <form method="get" action="tests">
                    <input type="hidden" value="1" name="theme_id">
                    <input type="hidden" name="counter" value="0">
                    <input type="hidden" name="command" value="START_TEST">
                    <button type="submit">Collections</button>
                </form>
            </div>
        </div>
        <div class="course-container d-flex align-items-center">
            <div class="course-img-container"><img src="images/if-else.svg"></div>
            <div class="course">
                <form method="get" action="tests">
                    <input type="hidden" value="2" name="theme_id">
                    <input type="hidden" name="counter" value="0">
                    <input type="hidden" name="command" value="START_TEST">
                    <button type="submit">If else, switch and loops</button>
                </form>
            </div>
        </div>
        <div class="course-container d-flex align-items-center">
            <div class="course-img-container"><img src="images/inheritance.svg"></div>
            <div class="course">
                <form method="get" action="tests">
                    <input type="hidden" value="3" name="theme_id">
                    <input type="hidden" name="counter" value="0">
                    <input type="hidden" name="command" value="START_TEST">
                    <button type="submit">Inheritance and polymorphism</button>
                </form>
            </div>
        </div>
        <div class="course-container d-flex align-items-center">
            <div class="course-img-container"><img src="images/threads.svg"></div>
            <div class="course">
                <form method="get" action="tests">
                    <input type="hidden" value="4" name="theme_id">
                    <input type="hidden" name="counter" value="0">
                    <input type="hidden" name="command" value="START_TEST">
                    <button type="submit">Threads, concurrency</button>
                </form>
            </div>
        </div>
        <div class="course-container d-flex align-items-center">
            <div class="course-img-container"><img src="images/primitive.svg"></div>
            <div class="course">
                <form method="get" action="tests">
                    <input type="hidden" value="5" name="theme_id">
                    <input type="hidden" name="counter" value="0">
                    <input type="hidden" name="command" value="START_TEST">
                    <button type="submit">Primitive type conversions</button>
                </form>
            </div>
        </div>
        <div class="course-container d-flex align-items-center">
            <div class="course-img-container"><img src="operators.svg"></div>
            <div class="course">
                <form method="get" action="tests">
                    <input type="hidden" value="6" name="theme_id">
                    <input type="hidden" name="counter" value="0">
                    <input type="hidden" name="command" value="START_TEST">
                    <button type="submit">Operators</button>
                </form>
            </div>
        </div>
        <div class="decorative-img col-lg-3 col-md-3 col-sm-5"><img src="images/fox.jpg"></div>

    </section>

</div>
</body>
</html>
