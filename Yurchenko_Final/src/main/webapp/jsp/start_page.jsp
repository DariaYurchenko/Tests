<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${appLocale}" scope="session"/>
<fmt:setBundle basename="languages/messages"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>JavaFox</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/resources/js/owl.carousel.min.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/resources/css/owl.theme.default.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/owl.carousel.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/start_page.css"/>">

</head>
<body>
<div class="row">
    <header class="header col-lg-12">
        <nav class="navbar navbar-dark bg-dark navbar-expand-md col-lg-12">
            <%@include file="header.jsp" %>
            <div class="languages">
                <form action="tests" method="GET">
                    <input type="hidden" value="CHANGE_LANGUAGE" name="command">
                    <input type="hidden" value="jsp/start_page.jsp" name="address">
                    <button type="submit" id="lang1" class="form-control" name="lang" value="eng"><img src="<c:url value="/resources/images/uk_flag.jpg"/>"></button>
                    <button type="submit" id="lang2" class="form-control" name="lang" value="rus"><img src="<c:url value="/resources/images/rus_flag.jpg"/>"></button>
                </form>
            </div>
        </nav>
    </header>

    <section class="first-section col-lg-12">
        <div class="col-lg-6 col-md-6 col-sm-12 text-center">
            <h1><fmt:message key="best_developer"/></h1>
            <div><a href="<c:url value="/tests?page=register_page"/>" class="btn sign-up-button"><fmt:message key="start_regiseter"/></a></div>
            <div><fmt:message key="start_message"/></div>
            <div class="fox col-sm-0"><img src="<c:url value="/resources/images/fox.jpg"/>"></div>
        </div>
        <div class="owl-carousel">
            <div><a href="<c:url value="/tests?page=tests_to_pass"/>">Collections</a></div>
            <div><a href="<c:url value="/tests?page=tests_to_pass"/>">If else, switch and loops </a></div>
            <div><a href="<c:url value="/tests?page=tests_to_pass"/>">Inheritance and polymorphism</a></div>
            <div><a href="<c:url value="/tests?page=tests_to_pass"/>">Threads, concurrency</a></div>
            <div><a href="<c:url value="/tests?page=tests_to_pass"/>">Operators</a></div>
            <div><a href="<c:url value="/tests?page=tests_to_pass"/>">Primitive types conversions</a></div>
        </div>
    </section>


    <script>
        $(function() {
            $(".owl-carousel").owlCarousel({
                nav: true,
                responsive:{
                    0:{
                        items:1
                    },
                    600:{
                        items:2
                    },
                    1000:{
                        items:3
                    }
                }
            });
        });

    </script>
</div>
</body>
</html>
