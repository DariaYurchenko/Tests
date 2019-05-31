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
    <script src="../resources/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<c:url value="../resources/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="../resources/css/show_users.css"/>">
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
                <input type="hidden" value="jsp/show_users.jsp" name="address">
                <button type="submit" id="lang1" class="form-control" name="lang" value="eng"><img src="../resources/images/uk_flag.jpg"></button>
                <button type="submit" id="lang2" class="form-control" name="lang" value="rus"><img src="../resources/images/rus_flag.jpg"></button>
            </form>
        </div>
    </nav>
</header>
<div class="d-flex">
    <aside class="col-lg-3">
        <div class="bg-white text-center">
            <h2><fmt:message key="admin_users"/></h2>
            <form method="get" action="tests">
                <input type="hidden" name="command" value="SHOW_ALL_USERS">
                <input type="hidden" name="act" value="JUST_SHOW">
                <input type="hidden" name="currentPage" value="1">
                <button class="btn" type="submit"><fmt:message key="admin_show_u"/></button>
            </form>
            <form method="get" action="tests">
                <input type="hidden" name="command" value="SHOW_ALL_USERS">
                <input type="hidden" name="act" value="DELETE_USER_BY_ID">
                <input type="hidden" name="currentPage" value="1">
                <button class="btn" type="submit"><fmt:message key="admin_delete_u_id"/></button>
            </form>
            <form method="get" action="tests">
                <input type="hidden" name="command" value="SHOW_ALL_USERS">
                <input type="hidden" name="act" value="DELETE_ALL_USERS">
                <input type="hidden" name="currentPage" value="1">
                <button class="btn" type="submit"><fmt:message key="admin_delete_u_all"/></button>
            </form>
            <form method="get" action="tests">
                <input type="hidden" name="command" value="SHOW_ALL_USERS">
                <input type="hidden" name="act" value="SHOW_USER_RESULTS">
                <input type="hidden" name="currentPage" value="1">
                <button class="btn" type="submit"><fmt:message key="admin_show_results"/></button>
            </form>
        </div>
        <div class="bg-white text-center">
            <h2><fmt:message key="admin_questions"/></h2>
            <form method="get" action="tests">
                <input type="hidden" name="currentPage" value="1">
                <input type="hidden" name="act" value="JUST_SHOW">
                <input type="hidden" name="command" value="SHOW_ALL_QUESTIONS">
                <button class="btn" type="submit"><fmt:message key="admin_show_questions"/></button>
            </form>
            <form method="get" action="tests">
                <input type="hidden" name="currentPage" value="1">
                <input type="hidden" name="act" value="SHOW_BY_THEME">
                <input type="hidden" name="command" value="SHOW_QUESTIONS_BY_THEME">
                <button class="btn" type="submit"><fmt:message key="admin_show_theme_questions"/></button>
            </form>
        </div>
        <div class="bg-white text-center">
            <h2><fmt:message key="admin_others"/></h2>
            <form method="get" action="tests">
                <input type="hidden" name="currentPage" value="1">
                <input type="hidden" name="command" value="SHOW_ALL_USERS">
                <input type="hidden" name="act" value="REGISTER_ADMIN">
                <button class="btn" type="submit"><fmt:message key="admin_register"/></button>
            </form>
        </div>
    </aside>
    <section class="col-lg-9">
        <c:if test="${act != 'REGISTER_ADMIN'}">
            <h1 class="text-center"><fmt:message key="admin_all_users"/></h1>
            <form method="get" action="tests">
                <c:if test="${showUsers != null}">

                    <table class="text-center table table-striped">
                        <c:set var="counter" value="1"/>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col"><fmt:message key="id_u"/></th>
                            <th scope="col"><fmt:message key="login_u"/></th>
                            <th scope="col"><fmt:message key="name_u"/></th>
                            <th scope="col"><fmt:message key="lastname_u"/></th>
                            <th scope="col"><fmt:message key="rank_u"/></th>
                            <th scope="col"></th>
                        </tr>
                        <c:forEach items="${users}" var="students">
                            <tr>
                                <th scope="row"><c:out value="${counter}"/></th>
                                <td><c:out value="${students.userId}"/></td>
                                <td><c:out value="${students.login}"/></td>
                                <td><c:out value="${students.name}"/></td>
                                <td><c:out value="${students.lastName}"/></td>
                                <td><c:out value="${students.rank}"/></td>
                                <c:if test="${act == 'DELETE_USER_BY_ID' || act == 'SHOW_USER_RESULTS'}">
                                    <td><input type="radio" id="id${counter}" name="radio" value="${students.userId}"></td>
                                </c:if>
                            </tr>
                            <c:set
                                    var="counter" value="${counter+1}"/>

                        </c:forEach>
                    </table>
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
                        <input type="hidden" name="currentPage" value="${currentPage}">
                        <div class="button-div form-group col-md-12 text-center">
                            <button class="btn" type="submit"><fmt:message key="admin_show_results"/></button>
                        </div>
                    </c:if>
                </div>
            </form>
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
                <div class="img-div text-center"><img src="../resources/images/reg_fox.jpg"></div>
            </section>
        </c:if>
    </section>
</div>
</div>
</body>
</html>
