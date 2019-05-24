<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${appLocale}" scope="session"/>
<fmt:setBundle basename="messages/messages"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Registration</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/reg_page.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="row">
    <header class="header col-lg-12">
        <nav class="navbar navbar-dark bg-dark navbar-expand-md col-lg-12">
            <a class="navbar-brand" href="#">JavaFox</a>
            <div class="signUp"><button onclick="window.location = '#'" class="btn btn-primary"><fmt:message key="sign_up"/></button></div>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse " id="navbarSupportedContent">
                <ul class="navbar-nav mr-4">
                    <li class="nav-item">
                        <a class="nav-link" href="login_page.jsp"><fmt:message key="log_in"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link " href="tests_to_pass.jsp"><fmt:message key="tests"/></a>
                    </li>
                </ul>

            </div>
            <div class="languages">
                <form action="tests" method="GET">
                    <input type="hidden" value="CHANGE_LANGUAGE" name="command">
                    <input type="hidden" value="register_page.jsp" name="address">
                    <button type="submit" id="lang1" class="form-control" name="lang" value="eng"><img src="images/uk_flag.jpg"></button>
                    <button type="submit" id="lang2" class="form-control" name="lang" value="rus"><img src="images/rus_flag.jpg"></button>
                </form>
            </div>
        </nav>
    </header>
    <form action="tests" method="POST" name="sign-up-form" class="col-lg-8 col-md-10 col-sm-11">
        <input type="hidden" name="command" value="REGISTER">
        <div class="form-group">
            <label for="name"><fmt:message key="register_name"/></label>
            <input type="text" id="name" class="form-control" placeholder="Ivan" name="name">
            <c:if test="${requestScope.errName != null}">
                <small class="text-danger"><c:out value="${requestScope.errName}"/></small>
            </c:if>
        </div>
        <div class="form-group">
            <label for="lastname"><fmt:message key="register_lastname"/></label>
            <input type="text" class="form-control" id="lastname" placeholder="Petrov" name="lastname">
            <c:if test="${requestScope.errLastname != null}">
                <small class="text-danger"><c:out value="${requestScope.errLastname}"/></small>
            </c:if>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="email"><fmt:message key="register_login"/></label>
                <input type="email" class="form-control" id="email" placeholder="ivan.petrov@gmail.com" name="email">
                <c:if test="${requestScope.errLogin != null}">
                    <small class="text-danger"><c:out value="${requestScope.errLogin}"/></small>
                </c:if>
            </div>
            <div class="form-group col-md-6">
                <label for="password"><fmt:message key="register_password"/></label>
                <input type="password" class="form-control" id="password" name="password">
                <c:if test="${requestScope.errPassword != null}">
                    <small class="text-danger"><c:out value="${requestScope.errPassword}"/></small>
                </c:if>
            </div>
        </div>
        <div class="button-div form-group col-md-12 text-center">
            <input type="submit" class="btn" value="Sign Up">
        </div>
        <div><c:if test="${requestScope.user_exists != null}">
            <small class="text-danger"><c:out value="${requestScope.user_exists}"/></small>
        </c:if></div>
    </form>
    <section class="img-section">
        <div class="img-div text-center"><img src="images/reg_fox.jpg"></div>
    </section>
</div>
</body>
</html>
