<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Themes</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/tests_to_pass.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="row">
    <header class="header col-lg-12">
        <nav class="navbar navbar-dark bg-dark navbar-expand-md col-lg-12">
            <a class="navbar-brand" href="#">JavaFox</a>
            <div class="signUp"><button onclick="window.location = '#'" class="btn btn-primary">Sign Up</button></div>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse " id="navbarSupportedContent">
                <ul class="navbar-nav mr-4">
                    <li class="nav-item">
                        <a class="nav-link" href="#">Log In</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link " href="#">Tests</a>
                    </li>
                </ul>

            </div>
            <div class="languages">
                <form action="tests" method="GET" name="lang-form">
                    <input type="hidden" value="CHANGE_LANGUAGE" name="command">
                    <input type="hidden" value="register_page.jsp" name="address">
                    <button type="submit" id="lang1" class="form-control" name="lang" value="eng"><img src="images/uk_flag.jpg"></button>
                    <button type="submit" id="lang2" class="form-control" name="lang" value="rus"><img src="images/rus_flag.jpg"></button>
                    <script>
                        $('form[name="lang-form"] > input[name="address"]').attr('value', window.location);
                    </script>
                </form>
            </div>
        </nav>
    </header>
    <c:if test="${user != null}">
        <p>Hi, </p><c:out value="${user.name}"/>
    </c:if>
    <section class="courses col-lg-8 col-md-9 col-sm-12">
        <div class="select-div">Select module:</div>
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
        <c:if test="${user != null}">
            <form action="tests" method="get">
                <input type="hidden" name="command" value="LOGOUT">
                <button type="submit">LogOUT</button>
            </form>
        </c:if>
        <div class="course-container d-flex align-items-center">
            <div class="course-img-container"><img src="images/if-else.svg"></div>
            <div class="course"><a href="">If else, switch and loops</a></div>
        </div>
        <div class="course-container d-flex align-items-center">
            <div class="course-img-container"><img src="images/inheritance.svg"></div>
            <div class="course"><a href="">Inheritance and polymorphism</a></div>
        </div>
        <div class="course-container d-flex align-items-center">
            <div class="course-img-container"><img src="images/threads.svg"></div>
            <div class="course"><a href="">Threads, concurrency</a></div>
        </div>
        <div class="course-container d-flex align-items-center">
            <div class="course-img-container"><img src="images/primitive.svg"></div>
            <div class="course"><a href="">Primitive type conversions</a></div>
        </div>
        <div class="course-container d-flex align-items-center">
            <div class="course-img-container"><img src="images/operators.svg"></div>
            <div class="course"><a href="">Operators</a></div>
        </div>
        <div class="decorative-img col-lg-3 col-md-3 col-sm-5"><img src="images/fox.jpg"></div>
    </section>
</div>
</body>
</html>
