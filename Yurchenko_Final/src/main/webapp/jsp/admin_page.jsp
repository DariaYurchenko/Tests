<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${appLocale}" scope="session"/>
<fmt:setBundle basename="languages/messages"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Admin page</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/admin_page.css"/>">
</head>
<body>
<div class="row">
<header class="header col-lg-12">
    <nav class="navbar navbar-dark bg-dark navbar-expand-md col-lg-12">
        <%@include file="header.jsp" %>
        <div class="languages">
            <form action="tests" method="GET">
                <input type="hidden" value="CHANGE_LANGUAGE" name="command">
                <input type="hidden" value="jsp/admin_page.jsp" name="address">
                <button type="submit" id="lang1" class="form-control" name="lang" value="eng"><img src="<c:url value="/resources/images/uk_flag.jpg"/>"></button>
                <button type="submit" id="lang2" class="form-control" name="lang" value="rus"><img src="<c:url value="/resources/images/rus_flag.jpg"/>"></button>
            </form>
        </div>
    </nav>
</header>

<div class="d-flex">
    <%@include file="admin_aside.jsp" %>
    <section class="col-lg-9">
        <h1 class="text-center"><fmt:message key="welcome_admin"/></h1>
        <div class="img-div text-center"><img src="<c:url value="/resources/images/reg_fox.jpg"/>"></div>
    </section>
</div>
    <c:if test="${submitWindow == 'TRUE'}">
        <div class="alert-window">
            <p class="text-center text-white col-lg-4 col-md-6 col-sm-8"><strong><fmt:message key="email_was_sent"/><span class="cross bg-white">&#10006;</span></strong></p>
        </div>
        <script>
            $(document).ready(function(){
                $("div.alert-window p strong span").on('click', function(){
                    $("div.alert-window").remove();
                });
            });
        </script>
    </c:if>
</div>

</body>
</html>
