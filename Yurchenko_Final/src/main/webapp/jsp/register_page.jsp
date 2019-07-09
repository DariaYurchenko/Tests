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
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/register_page.css"/>">
</head>
<body>
<div class="row">
    <header class="header col-lg-12">
        <nav class="navbar navbar-dark bg-dark navbar-expand-md col-lg-12">
            <%@include file="header.jsp" %>
            <div class="languages">
                <form action="tests" method="GET">
                    <input type="hidden" value="CHANGE_LANGUAGE" name="command">
                    <input type="hidden" value="jsp/register_page.jsp" name="address">
                    <button type="submit" id="lang1" class="form-control" name="lang" value="eng"><img src="<c:url value="/resources/images/uk_flag.jpg"/>"></button>
                    <button type="submit" id="lang2" class="form-control" name="lang" value="rus"><img src="<c:url value="/resources/images/rus_flag.jpg"/>"></button>
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
                return name.search(/^[а-яa-zё\s\'-]+$/i);
            }
            function addErrorMessage(input, message){
                input.after('<small class="text-error text-danger for-login">' + message + '</small>');
                $(".for-login").css({top: input.position().top + input.outerHeight() + 2});
            }
            function validateForm() {
                $(".text-error").remove();

                // Проверка имени и фамилии
                var name = $("#name");
                if ( name.val().length === 0 ) {
                    var v_name = true;
                    addErrorMessage(name, '<fmt:message key="need_to_put_in"/>');
                } else if ( checkName(name.val()) ) {
                    var v_name = true;
                    addErrorMessage(name, '<fmt:message key="incorrect_name_js"/>');
                }
                $("#name").toggleClass('error', v_name );


                var lastname    = $("#lastname");
                if ( lastname.val().length === 0 ) {
                    var v_lastname = true;
                    addErrorMessage(lastname, '<fmt:message key="need_to_put_in"/>');
                } else if ( checkName(lastname.val()) ) {
                    var v_lastname = true;
                    addErrorMessage(lastname, '<fmt:message key="incorrect_lastname_js"/>');
                }
                $("#lastname").toggleClass('error', v_lastname );
                // Проверка e-mail

                var reg     = /^\w+([\.-]?\w+)*@(((([a-z0-9]{1,})|([a-z0-9][-][a-z0-9]+))[\.][a-z0-9])|([a-z0-9]+[-]?))+[a-z0-9]{0,}\.([a-z]{2}|(com|net|org|edu|int|mil|gov|arpa|biz|aero|name|coop|info|pro|museum))$/i;
                var email    = $("#email");
                var v_email = email.val()?false:true;

                if ( v_email ) {
                    addErrorMessage(email, '<fmt:message key="need_to_put_in"/>');
                } else if ( !reg.test( email.val() ) ) {
                    v_email = true;
                    addErrorMessage(email, '<fmt:message key="incorrect_login"/>');
                }
                $("#email").toggleClass('error', v_email );

                // Проверка пароля

                var password    = $("#password");

                var v_pass = password.val()?false:true;

                if ( v_pass ) {
                    var v_pass = true;
                    addErrorMessage(password, '<fmt:message key="need_to_put_in"/>');
                } else if ( password.val().length < 5 || password.val().length > 10 ) {
                    var v_pass = true;
                    addErrorMessage(password, '<fmt:message key="incorrect_password"/>');
                }
                $("#password").toggleClass('error', v_pass );
                return ( v_name || v_lastname || v_email || v_pass );
            }

        });
    </script>
    <section class="img-section">
        <div class="img-div text-center"><img src="<c:url value="/resources/images/reg_fox.jpg"/>"></div>
    </section>

</div>
</body>
</html>
