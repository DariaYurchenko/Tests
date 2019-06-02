<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${appLocale}" scope="session"/>
<fmt:setBundle basename="languages/messages"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Login</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="../resources/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<c:url value="../resources/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="../resources/css/login_page.css"/>">
</head>
<body>
<div class="row">
    <header class="header col-lg-12">
        <nav class="navbar navbar-dark bg-dark navbar-expand-md col-lg-12">
            <a class="navbar-brand" href="<c:url value="/tests?page=start_page"/>">JavaFox</a>
            <div class="signUp">
                <ul class="navbar-nav mr-4 d-inline-block">
                    <c:if test="${sessionScope.user != null && sessionScope.userStatus == 'Student'}">
                        <li class="nav-item d-inline-block">
                            <a class="nav-link"><fmt:message key="hello"/><c:out value="${sessionScope.user.name}"/></a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope.user != null && sessionScope.userStatus == 'Admin'}">
                        <li class="nav-item d-inline-block">
                            <a class="nav-link" href="<c:url value="/tests?page=admin_page"/>"><fmt:message key="admin_page"/></a>
                        </li>
                    </c:if>
                </ul>
                <a href="<c:url value="/tests?page=register_page"/>" class="btn btn-primary"><fmt:message key="sign_up"/></a>
            </div>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse " id="navbarSupportedContent">
                <ul class="navbar-nav mr-4">
                    <c:if test="${sessionScope.user == null}">
                        <li class="nav-item">
                            <a class="nav-link" href="<c:url value="/tests?page=login_page"/>"><fmt:message key="log_in"/></a>
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
                        <a class="nav-link " href="<c:url value="/tests?page=tests_to_pass"/>"><fmt:message key="tests"/></a>
                    </li>
                </ul>

            </div>
            <div class="languages">
                <form action="tests" method="GET">
                    <input type="hidden" value="CHANGE_LANGUAGE" name="command">
                    <input type="hidden" value="jsp/login_page.jsp" name="address">
                    <button type="submit" id="lang1" class="form-control" name="lang" value="eng"><img src="../resources/images/uk_flag.jpg"></button>
                    <button type="submit" id="lang2" class="form-control" name="lang" value="rus"><img src="../resources/images/rus_flag.jpg"></button>
                </form>
            </div>
        </nav>
    </header>

    <section>
        <form action="tests" method="POST" name="login-form" class="col-lg-6 col-md-8 col-sm-9" novalidate>
            <div class="form-group">
                <label for="login"><fmt:message key="register_login"/></label>
                <input type="email" class="form-control" id="login" placeholder="ivan.petrov@gmail.com" name="login">
                <c:if test="${requestScope.errLogin != null}">
                    <small class="text-danger"><fmt:message key="incorrect_login"/></small>
                </c:if>
                <c:if test="${requestScope.loginMessage != null}">
                    <small class="text-danger"><fmt:message key="no_user"/></small>
                </c:if>
            </div>
            <c:if test="${requestScope.forgot != null}">
                <input name="command" value="CHANGE_PASSWORD" type="hidden">
                <div class="form-group">
                    <label for="newPassword"><fmt:message key="new_password"/></label>
                    <input type="password" class="form-control" id="newPassword" name="newPassword">
                    <c:if test="${requestScope.errPassword != null}">
                        <small class="text-danger"><fmt:message key="incorrect_password"/></small>
                    </c:if>
                </div>
            </c:if>
            <c:if test="${requestScope.forgot == null}">
                <input value="LOGIN" name="command" type="hidden">
                <div class="form-group">
                    <label for="password"><fmt:message key="register_password"/></label>
                    <input type="password" class="form-control" id="password" name="password">
                    <c:if test="${requestScope.errPassword != null}">
                        <small class="text-danger"><fmt:message key="incorrect_password"/></small>
                    </c:if>
                </div>
            </c:if>

            <div class="button-div form-group col-md-12 text-center">
                <input type="submit" class="btn" value="Log In">
            </div>
        </form>
        <script>
            jQuery(function($) {
                $('form[name="login-form"]').on('submit', function(event) {
                    if(validateForm()) {
                        event.preventDefault();
                    }
                });

                function checkName(name){
                    return name.split('').every(function(item){
                        return item.search(/[а-яa-zё\s\'-]/i) === 0;
                    });
                }
                function addErrorMessage(input, message){
                    input.after('<small class="text-error text-danger for-login">' + message + '</small>');
                    $(".for-login").css({top: input.position().top + input.outerHeight() + 2});
                }
                function validateForm() {
                    $(".text-error").remove();
                    var reg     = /^\w+([\.-]?\w+)*@(((([a-z0-9]{1,})|([a-z0-9][-][a-z0-9]+))[\.][a-z0-9])|([a-z0-9]+[-]?))+[a-z0-9]{0,}\.([a-z]{2}|(com|net|org|edu|int|mil|gov|arpa|biz|aero|name|coop|info|pro|museum))$/i;
                    var email    = $("#login");
                    var v_email = email.val()?false:true;
                    if ( v_email ) {
                        addErrorMessage(email, '<fmt:message key="need_to_put_in"/>');
                    } else if ( !reg.test( email.val() ) ) {
                        v_email = true;
                        addErrorMessage(email, '<fmt:message key="incorrect_login"/>');
                    }
                    $("#login").toggleClass('error', v_email );
                    // Проверка паролей
                    //1
                    if ( $("#password").length ) {
                        var password    = $("#password");
                        var v_pass = password.val()?false:true;
                        if ( v_pass ) {
                            var v_pass = true;
                            addErrorMessage(password, '<fmt:message key="need_to_put_in"/>');
                        } else if ( password.val().length < 5 ) {
                            var v_pass = true;
                            addErrorMessage(password, '<fmt:message key="incorrect_password"/>');
                        }
                        $("#password").toggleClass('error', v_pass );
                        return ( v_email || v_pass );
                    } else {
                        //2
                        var newPassword    = $("#newPassword");
                        var v_newPass = newPassword.val()?false:true;
                        if ( v_newPass ) {
                            var v_newPass = true;
                            addErrorMessage(newPassword, '<fmt:message key="need_to_put_in"/>');
                        } else if ( newPassword.val().length < 8 ) {
                            var v_newPass = true;
                            addErrorMessage(newPassword, '<fmt:message key="incorrect_password"/>');
                        }
                        $("#newPassword").toggleClass('error', v_newPass );
                        return ( v_email || v_newPass );
                    }

                }});
        </script>
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
            <fmt:message key="password_changed"/>
        </c:if>
    </section>
    <section class="img-section">
        <div class="img-div text-center"><img src="../resources/images/reg_fox.jpg"></div>
    </section>
</div>
</body>
</html>