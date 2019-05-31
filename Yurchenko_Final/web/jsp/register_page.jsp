<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${appLocale}" scope="session"/>
<fmt:setBundle basename="languages/messages"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Registration</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="../resources/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<c:url value="../resources/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="../resources/css/register_page.css"/>">
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
                    <input type="hidden" value="jsp/register_page.jsp" name="address">
                    <button type="submit" id="lang1" class="form-control" name="lang" value="eng"><img src="../resources/images/uk_flag.jpg"></button>
                    <button type="submit" id="lang2" class="form-control" name="lang" value="rus"><img src="../resources/images/rus_flag.jpg"></button>
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
                <small class="text-danger"><fmt:message key="incorrect_name"/></small>
            </c:if>
        </div>
        <div class="form-group">
            <label for="lastname"><fmt:message key="register_lastname"/></label>
            <input type="text" class="form-control" id="lastname" placeholder="Petrov" name="lastname">
            <c:if test="${requestScope.errLastname != null}">
                <small class="text-danger"><fmt:message key="incorrect_lastname"/></small>
            </c:if>
        </div>
        <div class="form-row login-pass">
            <div class="form-group col-md-6 margin-none">
                <label for="email"><fmt:message key="register_login"/></label>
                <input type="email" class="form-control" id="email" placeholder="ivan.petrov@gmail.com" name="email">
                <c:if test="${requestScope.errLogin != null}">
                    <small class="text-danger"><fmt:message key="incorrect_login"/></small>
                </c:if>
            </div>
            <div class="form-group col-md-6 margin-none">
                <label for="password"><fmt:message key="register_password"/></label>
                <input type="password" class="form-control" id="password" name="password">
                <c:if test="${requestScope.errPassword != null}">
                    <small class="text-danger"><fmt:message key="incorrect_password"/></small>
                </c:if>
            </div>
        </div>
        <div><c:if test="${requestScope.user_exists != null}">
            <small class="text-danger"><fmt:message key="user_exists"/></small>
        </c:if></div>
        <div class="button-div form-group col-md-12 text-center">
            <input type="hidden" name="userType" value="Student">
            <input type="submit" class="btn" value="Sign Up">
        </div>
    </form>
    <script>
        jQuery(function($) {
            $('form[name="sign-up-form"]').on('submit', function(event) {
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

                // Проверка логина
                var name = $("#name");
                if ( !checkName(name.val()) ) {
                    var v_name = true;
                    addErrorMessage(name, 'Имя может содержать лишь буквы, пробелы, знаки "-" и "\'"');
                } else if ( name.val().length === 0 ) {
                    var v_name = true;
                    addErrorMessage(name, 'Это поле обязательно для заполнения');
                }
                $("#name").toggleClass('error', v_name );


                var lastname    = $("#lastname");
                if ( !checkName(lastname.val()) ) {
                    var v_lastname = true;
                    addErrorMessage(lastname, 'Имя может содержать лишь буквы, пробелы, знаки "-" и "\'"');
                } else if ( lastname.val().length === 0 ) {
                    var v_lastname = true;
                    addErrorMessage(lastname, 'Это поле обязательно для заполнения');
                }
                $("#lastname").toggleClass('error', v_lastname );
                // Проверка e-mail

                var reg     = /^\w+([\.-]?\w+)*@(((([a-z0-9]{1,})|([a-z0-9][-][a-z0-9]+))[\.][a-z0-9])|([a-z0-9]+[-]?))+[a-z0-9]{0,}\.([a-z]{2}|(com|net|org|edu|int|mil|gov|arpa|biz|aero|name|coop|info|pro|museum))$/i;
                var email    = $("#email");
                var v_email = email.val()?false:true;

                if ( v_email ) {
                    addErrorMessage(email, 'Это поле обязательно для заполнения');
                } else if ( !reg.test( email.val() ) ) {
                    v_email = true;
                    addErrorMessage(email, 'Вы указали недопустимый e-mail');
                }
                $("#email").toggleClass('error', v_email );

                // Проверка пароля

                var password    = $("#password");

                var v_pass = password.val()?false:true;

                if ( v_pass ) {
                    var v_pass = true;
                    addErrorMessage(password, 'Это поле обязательно для заполнения');
                } else if ( password.val().length < 8 ) {
                    var v_pass = true;
                    addErrorMessage(password, 'Пароль должен содержать не менее 8 символов');
                }

                $("#password").toggleClass('error', v_pass );
                return ( v_name || v_lastname || v_email || v_pass );
            }

        });
    </script>
    <section class="img-section">
        <div class="img-div text-center"><img src="../resources/images/reg_fox.jpg"></div>
    </section>
    <div class="alert-window">
        <p class="text-center text-white col-lg-4 col-md-6 col-sm-8"><strong>The email was sent.<span class="cross bg-white">&#10006;</span></strong></p>
    </div>

</div>
</body>
</html>
