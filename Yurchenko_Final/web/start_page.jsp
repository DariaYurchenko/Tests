<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>JavaFox</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/owl.carousel.min.css">
    <link rel="stylesheet" href="css/owl.theme.default.min.css">
    <link href="css/start_page.css" rel="stylesheet">
    <script src="js/owl.carousel.min.js"></script>
</head>
<body>
<div class="row">
    <header class="header col-lg-12">
        <nav class="navbar navbar-dark bg-dark navbar-expand-md col-lg-12">
            <a class="navbar-brand" href="#">JavaFox</a>
            <div class="signUp"><button onclick="window.location = 'register_page.jsp'" class="btn btn-primary">Sign Up</button></div>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse " id="navbarSupportedContent">
                <ul class="navbar-nav mr-4">
                    <li class="nav-item">
                        <a class="nav-link" href="login_page.jsp">Log In</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link " href="tests_to_pass.jsp">Tests</a>
                    </li>
                </ul>
            </div>
            <div class="languages">
                <form action="tests" method="GET" name="lang-form">
                    <input type="hidden" value="CHANGE_LANGUAGE" name="command">
                    <input type="hidden" value="start_page.jsp" name="address">
                    <button type="submit" id="lang1" class="form-control" name="lang" value="eng"><img src="images/uk_flag.jpg"></button>
                    <button type="submit" id="lang2" class="form-control" name="lang" value="rus"><img src="images/rus_flag.jpg"></button>
                </form>
            </div>
        </nav>
    </header>
    <section class="first-section col-lg-12">
        <div class="col-lg-6 col-md-6 col-sm-12 text-center">
            <h1>Become the Best Java Developer</h1>
            <div><button onclick="window.location = 'register_page.jsp'" class="btn sign-up-button">SIGN UP. IT IS FREE</button></div>
            <div>Learn programming languages, enroll for the course and programming quizzes with explanations and theory</div>
            <div class="fox col-sm-0"><img src="images/fox.jpg"></div>
        </div>
        <div class="owl-carousel">
            <div><a href="#">Portfolio</a></div>
            <div><a href="#">Portfolio</a></div>
            <div><a href="#">Portfolio</a></div>
            <div><a href="#">Portfolio</a></div>
            <div><a href="#">Portfolio</a></div>
            <div><a href="#">Portfolio</a></div>
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
