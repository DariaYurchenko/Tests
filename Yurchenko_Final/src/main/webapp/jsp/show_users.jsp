<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${appLocale}" scope="session"/>
<fmt:setBundle basename="languages/messages"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Users</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/show_users.css"/>">
</head>
<body>
<div class="row">
<header class="header col-lg-12">
    <nav class="navbar navbar-dark bg-dark navbar-expand-md col-lg-12">
        <%@include file="header.jsp" %>
        <div class="languages">
            <form action="tests" method="GET">
                <input type="hidden" value="CHANGE_LANGUAGE" name="command">
                <input type="hidden" value="jsp/show_users.jsp" name="address">
                <button type="submit" id="lang1" class="form-control" name="lang" value="eng"><img src="<c:url value="/resources/images/uk_flag.jpg"/>"></button>
                <button type="submit" id="lang2" class="form-control" name="lang" value="rus"><img src="<c:url value="/resources/images/rus_flag.jpg"/>"></button>
            </form>
        </div>
    </nav>
</header>
<div class="d-flex">
    <%@include file="admin_aside.jsp" %>
    <section class="col-lg-9">
        <c:if test="${act != 'REGISTER_ADMIN'}">
            <div class="text-center"><fmt:message key="records_per_page"/></div>
            <ul class="pagination justify-content-center">
                <li class="page-item"><a class="page-link" href="<c:url value="/tests?command=SHOW_ALL_USERS&recordsPerPage=5"/>">5</a>
                </li>
                <li class="page-item"><a class="page-link" href="<c:url value="/tests?command=SHOW_ALL_USERS&recordsPerPage=10"/>">10</a>
                </li>
                <li class="page-item"><a class="page-link" href="<c:url value="/tests?command=SHOW_ALL_USERS&recordsPerPage=50"/>">50</a>
                </li>
            </ul>
            <h1 class="text-center"><fmt:message key="admin_all_users"/></h1>
            <form class="main-form" method="get" action="tests">
                <c:if test="${showUsers != null}">
                    <div class="table-div">
                    <table class="text-center table table-striped">
                        <c:set var="counter" value="1"/>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col"><fmt:message key="id_u"/></th>
                            <th scope="col"><fmt:message key="login_u"/></th>
                            <th scope="col"><fmt:message key="name_u"/></th>
                            <th scope="col"><fmt:message key="lastname_u"/></th>
                            <th scope="col"><fmt:message key="rank_u"/></th>
                    <c:if test="${act == 'DELETE_USER_BY_ID' || act == 'SHOW_USER_RESULTS'}">
                            <th scope="col"></th>
                    </c:if>
                        </tr>
                        <c:forEach items="${users}" var="students">
                            <tr>
                                <th scope="row"><c:out value="${counter}"/>1</th>
                                <td><c:out value="${students.userId}"/></td>
                                <td><c:out value="${students.login}"/></td>
                                <td><c:out value="${students.name}"/></td>
                                <td><c:out value="${students.lastName}"/></td>
                                <td><c:out value="${students.userRank}"/></td>
                                <c:if test="${act == 'DELETE_USER_BY_ID' || act == 'SHOW_USER_RESULTS'}">
                                    <td><input type="radio" id="id${counter}" name="radio" value="${students.userId}"></td>
                                </c:if>
                            </tr>
                            <c:set
                                    var="counter" value="${counter+1}"/>

                        </c:forEach>
                    </table>
                    <c:if test="${usersSize == 0}">
                        <p class="text-center"><fmt:message key="no_user"/></p>
                    </c:if>
                    </div>
                </c:if>
                <ul class="pagination justify-content-center">
                    <c:if test="${currentPage != 1}">
                        <li class="page-item"><a class="page-link" href="<c:url value="/tests?command=SHOW_ALL_USERS&recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}"/>"><fmt:message key="prev_ad"/></a>
                        </li>
                    </c:if>
                    <c:forEach begin="1" end="${noOfPages}" var="i">
                        <c:choose>
                            <c:when test="${currentPage eq i}">
                                <li class="page-item active"><a class="page-link">
                                        ${i} <span class="sr-only">(current)</span></a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item"><a class="page-link" href="<c:url value="/tests?command=SHOW_ALL_USERS&recordsPerPage=${recordsPerPage}&currentPage=${i}"/>">${i}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <c:if test="${currentPage lt noOfPages}">
                        <li class="page-item"><a class="page-link" href="<c:url value="/tests?command=SHOW_ALL_USERS&recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}"/>"><fmt:message key="next"/></a>
                        </li>
                    </c:if>
                </ul>
                <div>
                    <c:if test="${act == 'DELETE_ALL_USERS'}">
                        <input type="hidden" name="command" value="DELETE_ALL_USERS">
                        <input type="hidden" name="currentPage" value="${currentPage}">
                        <div class="button-div form-group col-md-12 text-center">
                            <button class="btn" type="submit"><fmt:message key="admin_delete_u_all"/></button>
                        </div>
                    </c:if>
                    <c:if test="${act == 'DELETE_USER_BY_ID'}">
                        <input type="hidden" name="command" value="DELETE_USER_BY_ID">
                        <input type="hidden" name="currentPage" value="${currentPage}">
                        <div class="button-div form-group col-md-12 text-center">
                            <button class="btn" type="submit"><fmt:message key="admin_delete_u_id"/></button>
                        </div>
                    </c:if>
                    <c:if test="${act == 'SHOW_USER_RESULTS'}">
                        <input type="hidden" name="command" value="SHOW_USER_RESULTS">
                        <input type="hidden" name="currentPage" value="1">
                        <div class="button-div form-group col-md-12 text-center">
                            <button class="btn" type="submit"><fmt:message key="admin_show_results"/></button>
                        </div>
                    </c:if>
                </div>
            </form>
            </div>
        </c:if>

        <c:if test="${act == 'REGISTER_ADMIN'}">
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
                    <input type="hidden" name="userType" value="Admin">
                    <input type="submit" class="btn" value="Sign Up">
                </div>
                <div><c:if test="${requestScope.user_exists != null}">
                    <small class="text-danger"><c:out value="${requestScope.user_exists}"/></small>
                </c:if></div>
            </form>
            <section class="img-section">
                <div class="img-div text-center"><img src="<c:url value="/resources/images/reg_fox.jpg"/>"></div>
            </section>
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
                        return ( v_name || v_lastname || v_email || v_pass );
                    }

                });
            </script>
        </c:if>
    </section>
</div>
</div>
</body>
</html>
