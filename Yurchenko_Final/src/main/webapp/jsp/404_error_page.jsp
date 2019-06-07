<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${appLocale}" scope="session"/>
<fmt:setBundle basename="languages/messages"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="../resources/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<c:url value="../resources/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="../resources/css/error_page.css"/>">
    <title>Error</title>
    <style>
    </style>
</head>
<body>
<div class="row">
    <header class="header col-lg-12">
        <nav class="navbar navbar-dark bg-dark navbar-expand-md col-lg-12">
            <%@include file="header.jsp" %>
            <div class="languages">
                <form action="tests" method="GET">
                    <input type="hidden" value="CHANGE_LANGUAGE" name="command">
                    <input type="hidden" value="jsp/404_error_page.jsp" name="address">
                    <button type="submit" id="lang1" class="form-control" name="lang" value="eng"><img src="../resources/images/uk_flag.jpg"></button>
                    <button type="submit" id="lang2" class="form-control" name="lang" value="rus"><img src="../resources/images/rus_flag.jpg"></button>
                </form>
            </div>
        </nav>
    </header>
    <section>
        <div class="error-container"><img src="../resources/images/404-error.jpg"></div>
    </section>
</div>
</body>
</html>
