<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${appLocale}" scope="session"/>
<fmt:setBundle basename="languages/messages"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/not_submit_email.css"/>" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <title>Not confirm email</title>
</head>
<body>
<div class="row">
    <header class="header col-lg-12">
        <nav class="navbar navbar-dark bg-dark navbar-expand-md col-lg-12">
            <%@include file="header.jsp" %>
            <div class="languages">
                <form action="tests" method="GET">
                    <input type="hidden" value="CHANGE_LANGUAGE" name="command">
                    <input type="hidden" value="jsp/not_submit_email.jsp" name="address">
                    <button type="submit" id="lang1" class="form-control" name="lang" value="eng"><img src="<c:url value="/resources/images/uk_flag.jpg"/>"></button>
                    <button type="submit" id="lang2" class="form-control" name="lang" value="rus"><img src="<c:url value="/resources/images/rus_flag.jpg"/>"></button>
                </form>
            </div>
        </nav>
    </header>

    <section>
        <div class="message-container d-flex align-items-center justify-content-center flex-wrap">
            <div class="decorative-img col-lg-3 col-md-4 col-sm-12 text-left"><img src="<c:url value="/resources/images/fox.jpg"/>"></div>
            <div class="message col-lg-6 col-md-7 col-sm-12">
                <c:if test="${sessionScope.sent == null}">
                <p class="text-center"><fmt:message key="did_not_conf_email"/>(<br><fmt:message key="send_again"/></p>
                <div class="text-center">
                    <form class="d-inline" action="tests" method="get">
                        <input type="hidden" name="command" value="SEND_EMAIL_AGAIN">
                        <button class="btn" type="submit"><fmt:message key="yes"/></button>
                        <c:if test="${sessionScope.sent != null}">
                            <a href="<c:url value="/tests?page=start_page"/>"><fmt:message key="back"/></a>
                        </c:if>
                    </form>
                </div>
                </c:if>
                <c:if test="${sessionScope.sent != null}">
                    <p class="text-center"><fmt:message key="sent_email_again"/></p>
                    <div class="text-center">
                        <form class="d-inline" action="tests" method="get">
                            <a class="navbar-brand" href="<c:url value="/tests?page=start_page"/>"><fmt:message key="back"/></a>
                        </form>
                    </div>
                </c:if>
            </div>
        </div>
    </section>
</div>
</body>
</html>
