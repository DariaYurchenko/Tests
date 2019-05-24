<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Login</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/log_page.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="row">
    <header class="header col-lg-12">
        <nav class="navbar navbar-dark bg-dark navbar-expand-md col-lg-12">
            <a class="navbar-brand" href="#">JavaFox</a>
            <div class="signUp"><button onclick="window.location = 'register_page.jsp'" class="btn btn-primary">Sign Up</button></div>
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
        </nav>
    </header>
    <form action="tests" method="POST" name="login-form" class="col-lg-6 col-md-8 col-sm-9">
        <input value="LOGIN" name="command" type="text" hidden>
        <div class="form-group">
            <label for="login">Email</label>
            <input type="email" class="form-control" id="login" placeholder="ivan.petrov@gmail.com" name="login">
            <c:if test="${requestScope.errLogin != null}">
                <small class="text-danger"><c:out value="${requestScope.errLogin}"/></small>
            </c:if>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" name="password">
            <c:if test="${requestScope.errPassword != null}">
                <small class="text-danger"><c:out value="${requestScope.errPassword}"/></small>
            </c:if>
        </div>

        <div class="button-div form-group col-md-12 text-center">
            <input type="submit" class="btn" value="Log In">
        </div>
    </form>
    <section class="img-section">
        <div class="img-div text-center"><img src="images/reg_fox.jpg"></div>
    </section>
</div>
</body>
</html>
