<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${appLocale}" scope="session"/>
<fmt:setBundle basename="messages/messages"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Tests</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
    <style>
        @font-face{
            font-family: "Cookie Regular";
            src: url("fonts/shumi.otf")

        }
        form[name="logout-form"]{
            margin-bottom: 0;
            padding: 8px
        }
        form[name="logout-form"] button[type="submit"]{
            background: transparent;
            border: none;
            outline: none;
            font-size: 20px;
            color: white
        }
        form[name="logout-form"] button[type="submit"]:hover{
            color: #CBCBCE;
            cursor: pointer
        }
        .signUp li > a, .signUp > button{
            font-size: 20px;
            font-family: apple-system,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif,"Apple Color Emoji","Segoe UI Emoji","Segoe UI Symbol";
        }
        .signUp > button{
            border: none;
            vertical-align: top
        }
        header.header{
            padding-left: 0;
            padding-right: 0;
            position: sticky;
            top: 0;
            z-index: 11
        }
        .navbar-dark {
            background: #2F2F64 !important
        }
        .navbar-dark .navbar-brand{
            font: 30px "Cookie Regular";
            color: #ffa500;
        }
        #navbarSupportedContent{
            justify-content: flex-end;
            flex-grow: 0;

        }
        .navbar-collapse{

        }
        .nav-item{
            margin: 0 5px;
            font-size: 20px
        }
        .navbar-dark .navbar-nav .nav-link{
            text-align: right;
            color: white
        }
        .signUp{
            flex-grow: 1;
            text-align: right;
            font: 22px bold;
            margin-right: 15px;
        }
        .signUp > button{
            font-size: 20px;
            font-family: apple-system,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif,"Apple Color Emoji","Segoe UI Emoji","Segoe UI Symbol"
        }
        div.languages{
            margin-left: 15px
        }
        div.languages > form img{
            width: 30px;
            height: 15px
        }
        div.languages > form{
            margin-bottom: 0
        }
        div.languages > form > button{
            padding: 0;
            border: none;
            display: inline-block;
            width: auto
        }
        div.row{
            width: 100%;
            margin-left: 0;
            margin-right: 0
        }
        div.container{
            width: 100%
        }
        section.work-section{
            background: white;
            padding: 15px;
            margin: 15px auto;
            border-radius: 15px
        }
        section.work-section > div > pre{
            background: #F2F2F2;
            padding: 7px
        }
        div.progress{
            width: 80%;
            margin: 10px auto
        }
        section > div.course-img-container{
            width: 100px;
            height: 100px;
            padding: 30px;
            margin: 10px auto;
            border-radius: 50px;
            background: #ffa500
        }
        section > div.course-img-container > img{
            width: 40px
        }
        aside > section{
            background: white;
            border-radius: 15px;
            padding: 10px;
            margin: 15px auto
        }
        aside > section > h2{
            font-size: 27px
        }
        section.stats > img{
            width: 70%;
        }
        .custom-radio .custom-control-input:checked~.custom-control-label::before, .custom-control-input:checked~.custom-control-label::before{
            background: #2F2F64
        }
        div.answers{
            background: #D3D3D3;
            padding: 5px
        }
        div.answers form div.custom-radio{
            background: white;
            padding: 7px;
            padding-left: 24px;
            margin: 5px
        }
        div.answers form div.custom-radio input[type="text"]{
            border: none;
            border-bottom: 1px solid;
            outline: none
        }
        div.answers form{
            position: relative
        }
        div.answers form div.button-div{
            margin-top: 10px
        }
        div.answers form div.button-div button{
            background: #ffa500;
            padding: 5px 20px;
            transition: transform 1s;
            color: white
        }
        div.answers form div.button-div button:hover{
            transform: scale(1.1);
            transition: transform 1s
        }
        div.answers .custom-control-label::before, .custom-radio .custom-control-input:checked~.custom-control-label::after{
            left: 5px;
            top: 11px;
        }
        div.result{
            position: relative
        }
        div.result p{
            position: absolute;
            top: 50%;
            transform: translateY(-50%);
            left: -120px
        }
        div.success{
            background: #97CFB6 !important
        }
        div.danger{
            background: #FBE3E4 !important
        }
    </style>
</head>
<body>
<div class="row">
    <header class="header col-lg-12">
        <nav class="navbar navbar-dark bg-dark navbar-expand-md col-lg-12">
            <a class="navbar-brand" href="start_page.jsp">JavaFox</a>
            <div class="signUp">
                <ul class="navbar-nav mr-4 d-inline-block">
                    <c:if test="${sessionScope.user != null && sessionScope.userStatus == 'Student'}">
                        <li class="nav-item d-inline-block">
                            <a class="nav-link"><fmt:message key="hello"/><c:out value="${sessionScope.user.name}"/></a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope.user != null && sessionScope.userStatus == 'Admin'}">
                        <li class="nav-item d-inline-block">
                            <a class="nav-link" href="admin_page.jsp"><fmt:message key="admin_page"/></a>
                        </li>
                    </c:if>
                </ul>
                <button onclick="window.location = 'register_page.jsp'" class="btn btn-primary"><fmt:message key="sign_up"/></button>
            </div>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse " id="navbarSupportedContent">
                <ul class="navbar-nav mr-4">
                    <c:if test="${sessionScope.user == null}">
                        <li class="nav-item">
                            <a class="nav-link" href="login_page.jsp"><fmt:message key="log_in"/></a>
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
                        <a class="nav-link " href="tests_to_pass.jsp"><fmt:message key="tests"/></a>
                    </li>
                </ul>

            </div>
            <div class="languages">
                <form action="tests" method="GET">
                    <input type="hidden" value="CHANGE_LANGUAGE" name="command">
                    <input type="hidden" value="block/tests.jsp" name="address">
                    <button type="submit" id="lang1" class="form-control" name="lang" value="eng"><img src="images/uk_flag.jpg"></button>
                    <button type="submit" id="lang2" class="form-control" name="lang" value="rus"><img src="images/rus_flag.jpg"></button>
                </form>
            </div>
        </nav>
    </header>
    <div class="container d-flex justify-content-center">
        <div class="col-lg-8 col-md-8">
            <section class="work-section">
                <div class="progress">
                    <div class="progress-bar" role="progressbar" style="width:${requestScope.progress}%" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100"></div>
                </div>
                <div>
                    <div>What will be printed as a result of the following code execution?</div>
                    <pre><c:out value="${question.question}"/></pre>
                    <div class="answers">
     <c:if test="${question.questionType.type eq 'RADIO'}">
         <form action="tests" method="get">
             <c:set var="id_counter" value="1"/>
             <c:if test="${requestScope.forward == null}">
                 <c:forEach items="${options}" var="options">
                     <c:if test="${options != null}">
                         <div class="custom-control custom-radio">
                             <input type="radio" class="custom-control-input" id="id${id_counter}" name="radio" value="${options}" required>
                             <label class="custom-control-label" for="id${id_counter}"><c:out value="${options}"/></label>
                             <c:set var="id_counter" value="${id_counter + 1}"/>
                         </div></c:if>
                 </c:forEach>
             </c:if>

             <c:if test="${requestScope.forward != null}">
                 <c:forEach items="${options}" var="options">
                     <c:if test="${options == question.correctOption1}">
                         <div class="custom-control custom-radio success result">
                             <c:out value="${options}"/>
                             <c:if test="${options == userAnswer}">
                                 <p>Your answer</p>
                             </c:if>
                         </div>
                     </c:if>
                     <c:if test="${options != question.correctOption1}">
                         <div class="custom-control custom-radio danger result">
                             <c:out value="${options}"/>
                             <c:if test="${options == userAnswer}">
                                 <p>Your answer</p>
                             </c:if>
                         </div>
                 </c:if>
                     <c:set var="id_counter" value="${id_counter + 1}"/>
                 </c:forEach>
                 <p>Percent of right answers: <c:out value="${requestScope.answerPercent}"/>%</p>
                 </c:if>
                 </c:if>
                 <c:if test="${question.questionType.type eq 'CHECKBOX'}">
                 <form action="tests" method="get">
                     <c:set var="id_counter" value="1"/>
                     <c:if test="${requestScope.forward == null }">
                         <c:forEach items="${options}" var="options">
                             <div class="custom-control custom-radio">
                                 <c:if test="${options != null}">
                                     <input type="checkbox" class="custom-control-input" id="id${id_counter}" name="checkbox" value="${options}" >
                                     <label class="custom-control-label" for="id${id_counter}"><c:out value="${options}"/></label>
                                     <c:set var="id_counter" value="${id_counter + 1}"/>
                                 </c:if>
                             </div>
                         </c:forEach>
                     </c:if>
                     <c:if test="${requestScope.forward != null}">
                         <c:forEach items="${options}" var="options">
                             <c:if test="${options == question.correctOption1 || options == question.correctOption2 || options == question.correctOption3}">
                                 <div class="custom-control custom-radio success result">
                                     <c:out value="${options}"/>
                                     <c:if test="${options == userAnswer1 || options == userAnswer2 || options == userAnswer3}">
                                         <p>Your answer</p>
                                     </c:if>

                                 </div>
                             </c:if>
                             <c:if test="${options != question.correctOption1 && options != question.correctOption2 && options != question.correctOption3}">
                                 <div class="custom-control custom-radio danger result">
                                     <c:out value="${options}"/>
                                     <c:if test="${options == userAnswer1 || options == userAnswer2 || options == userAnswer3}">
                                         <p>Your answer</p>
                                     </c:if>

                                 </div>
                             </c:if>

                             <c:set var="id_counter" value="${id_counter + 1}"/>
                         </c:forEach>
                         <p>Percent of right answers: <c:out value="${requestScope.answerPercent}"/>%</p>
                     </c:if>
                 </c:if>
                         <c:if test="${question.questionType.type eq 'TEXT'}">
                         <form action="tests" method="get">
                              <c:set var="id_counter" value="1"/>
                             <c:if test="${requestScope.forward == null }">
                                 <div class="custom-radio">
                                     <c:if test="${options != null}">
                                         <label for="id">Your answer: </label>
                                         <input type="text" id="id" name="radio">
                                     </c:if>
                                 </div>
                             </c:if>

                             <c:if test="${requestScope.forward != null}">
                                 <c:if test="${userAnswer == question.correctOption1}">
                                     <div class="custom-control custom-radio success result">
                                         <p>Your answer</p>
                                         <c:out value="${userAnswer}"/>
                                     </div>
                                 </c:if>
                                 <c:if test="${userAnswer != question.correctOption1}">
                                     <div class="custom-control custom-radio success">
                                         <c:out value="${question.correctOption1}"/>
                                     </div>
                                  <c:if test="${userAnswer != ''}">
                                      <div class="custom-control custom-radio danger result">
                                         <c:out value="${userAnswer}"/>
                                         <p>Your answer</p>
                                     </div>
                                  </c:if>
                                 </c:if>
                             </c:if>
                                 <c:set var="id_counter" value="${id_counter + 1}"/>
                                 <p>Percent of right answers: <c:out value="${requestScope.answerPercent}"/>%</p>
                             </c:if>
                             <c:if test="${(counter < length) && (requestScope.forward == null)}">
                                 <input type="hidden" name="command" value="PASS_TESTS">
                                 <input type="hidden" name="forward" value="TRUE">
                                 <input type="hidden" name="counter" value="${counter}">
                                 <input type="hidden" name="theme_id" value="1">
                                 <div class="button-div text-center"><button class="btn" type="submit">Continue</button></div>
                             </c:if>
                             <c:if test="${(counter < length) && (requestScope.forward != null)}">
                                 <input type="hidden" name="command" value="PASS_TESTS">
                                 <input type="hidden" name="forward" value="FALSE">
                                 <input type="hidden" name="counter" value="${counter}">
                                 <input type="hidden" name="theme_id" value="1">
                                 <div class="button-div text-center"><button class="btn" type="submit">Next</button></div>
                             </c:if>
                             <c:if test="${counter >= length}">
                                 <input type="hidden" name="command" value="SHOW_RESULTS">
                                 <div class="button-div text-center"><button class="btn" type="submit">Next</button></div>
                             </c:if>
                         </form>
                    </div>
                </div>
            </section>
        </div>
        <aside class="col-lg-4 col-md-4">
            <section>
                <h2 class="text-center"><c:out value="${question.theme.themeName}"/></h2>
                <c:if test="${question.theme.themeId == 1}">
                <div class="course-img-container"><img src="images/arrays.svg"></div>
                </c:if>
                <c:if test="${question.theme.themeId == 2}">
                    <div class="course-img-container"><img src="images/if-else.svg"></div>
                </c:if>
                <c:if test="${question.theme.themeId == 3}">
                    <div class="course-img-container"><img src="images/inheritance.svg"></div>
                </c:if>
                <c:if test="${question.theme.themeId == 4}">
                    <div class="course-img-container"><img src="images/threads.svg"></div>
                </c:if>
                <c:if test="${question.theme.themeId == 5}">
                    <div class="course-img-container"><img src="images/primitive.svg"></div>
                </c:if>
                <c:if test="${question.theme.themeId == 6}">
                    <div class="course-img-container"><img src="images/operators.svg"></div>
                </c:if>
            </section>
            <section class="stats text-center">
                <img src="images/white_fox.jpg">
            </section>
        </aside>
    </div>
</div>
</body>
</html>
