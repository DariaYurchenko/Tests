<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${appLocale}" scope="session"/>
<fmt:setBundle basename="languages/messages"/>
<html>
<head>
    <title>Submit registration</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="../resources/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<c:url value="../resources/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="../resources/css/submit_key.css"/>">
</head>
<body>
<div class="row">
    <header class="header col-lg-12">
        <nav class="navbar navbar-dark bg-dark navbar-expand-md col-lg-12">
            <%@include file="header.jsp" %>
            <div class="languages">
                <form action="tests" method="GET">
                    <input type="hidden" value="CHANGE_LANGUAGE" name="command">
                    <input type="hidden" value="jsp/submit_key.jsp" name="address">
                    <button type="submit" id="lang1" class="form-control" name="lang" value="eng"><img src="../resources/images/uk_flag.jpg"></button>
                    <button type="submit" id="lang2" class="form-control" name="lang" value="rus"><img src="../resources/images/rus_flag.jpg"></button>
                </form>
            </div>
        </nav>
    </header>
    <section>
        <div class="message-container d-flex align-items-center justify-content-center flex-wrap">
            <div class="decorative-img col-lg-3 col-md-4 col-sm-12 text-left"><img src="../resources/images/fox.jpg"></div>
            <div class="message col-lg-6 col-md-7 col-sm-12">
                <c:if test="${incorrectKey == null}">
                <p class="text-center"><fmt:message key="email_success_confirm"/></p>
                </c:if>
                <c:if test="${incorrectKey != null}">
                    <p class="text-center"><fmt:message key="not_correct_submit_key"/></p>
                </c:if>

                <div class="text-center"><a class="navbar-brand" href="<c:url value="/tests?page=start_page"/>"><fmt:message key="back"/></a></div>
    </section>
</div>
</body>
</html>
