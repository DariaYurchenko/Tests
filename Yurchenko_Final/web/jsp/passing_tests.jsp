<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${appLocale}" scope="session"/>
<fmt:setBundle basename="languages/messages"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Tests</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="../resources/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<c:url value="../resources/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="../resources/css/tests.css"/>">
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
                    <input type="hidden" value="jsp/passing_tests.jsp" name="address">
                    <button type="submit" id="lang1" class="form-control" name="lang" value="eng"><img src="../resources/images/uk_flag.jpg"></button>
                    <button type="submit" id="lang2" class="form-control" name="lang" value="rus"><img src="../resources/images/rus_flag.jpg"></button>
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
                    <pre><c:out value="${question.question}"/></pre>
                    <div class="answers">
     <c:if test="${question.questionType.type eq 'RADIO'}">
         <form action="tests" method="get">
             <c:set var="id_counter" value="1"/>
             <div><fmt:message key="question_what"/></div>
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
                                 <p><fmt:message key="your_answer"/></p>
                             </c:if>
                         </div>
                     </c:if>
                     <c:if test="${options != question.correctOption1}">
                         <div class="custom-control custom-radio danger result">
                             <c:out value="${options}"/>
                             <c:if test="${options == userAnswer}">
                                 <p><fmt:message key="your_answer"/></p>
                             </c:if>
                         </div>
                 </c:if>
                     <c:set var="id_counter" value="${id_counter + 1}"/>
                 </c:forEach>
                 <p><fmt:message key="percent_answers"/><c:out value="${requestScope.answerPercent}"/>%</p>
                 </c:if>
                 </c:if>
                 <c:if test="${question.questionType.type eq 'CHECKBOX'}">
                 <form action="tests" method="get">
                     <div><fmt:message key="question_checkbox"/></div>
                     <c:set var="id_counter" value="1"/>
                     <c:if test="${requestScope.forward == null }">
                         <c:forEach items="${options}" var="options">
                             <div class="custom-control">
                                 <c:if test="${options != null}">
                                     <input type="checkbox" id="id${id_counter}" name="checkbox" value="${options}" >
                                     <label for="id${id_counter}"><c:out value="${options}"/></label>
                                     <c:set var="id_counter" value="${id_counter + 1}"/>
                                 </c:if>
                             </div>
                         </c:forEach>
                     </c:if>
                     <c:if test="${requestScope.forward != null}">
                         <c:forEach items="${options}" var="options">
                             <c:if test="${options == question.correctOption1 || options == question.correctOption2 || options == question.correctOption3}">
                                 <div class="custom-control success result">
                                     <c:out value="${options}"/>
                                     <c:if test="${options == userAnswer1 || options == userAnswer2 || options == userAnswer3}">
                                         <p><fmt:message key="your_answer"/></p>
                                     </c:if>

                                 </div>
                             </c:if>
                             <c:if test="${options != question.correctOption1 && options != question.correctOption2 && options != question.correctOption3}">
                                 <div class="custom-control danger result">
                                     <c:out value="${options}"/>
                                     <c:if test="${options == userAnswer1 || options == userAnswer2 || options == userAnswer3}">
                                         <p><fmt:message key="your_answer"/></p>
                                     </c:if>

                                 </div>
                             </c:if>

                             <c:set var="id_counter" value="${id_counter + 1}"/>
                         </c:forEach>
                         <p><fmt:message key="percent_answers"/><c:out value="${requestScope.answerPercent}"/>%</p>
                     </c:if>
                 </c:if>
                         <c:if test="${question.questionType.type eq 'TEXT'}">
                         <form action="tests" method="get">
                             <div><fmt:message key="question_text"/></div>
                              <c:set var="id_counter" value="1"/>
                             <c:if test="${requestScope.forward == null }">
                                 <div class="custom-control">
                                     <c:if test="${options != null}">
                                         <label for="id"><fmt:message key="your_answer"/></label>
                                         <input type="text" id="id" name="radio">
                                     </c:if>
                                 </div>
                             </c:if>

                             <c:if test="${requestScope.forward != null}">
                                 <c:if test="${userAnswer == question.correctOption1}">
                                     <div class="custom-control success result">
                                         <p><fmt:message key="your_answer"/></p>
                                         <c:out value="${userAnswer}"/>
                                     </div>
                                 </c:if>
                                 <c:if test="${userAnswer != question.correctOption1}">
                                     <div class="custom-control success">
                                         <c:out value="${question.correctOption1}"/>
                                     </div>
                                  <c:if test="${userAnswer != ''}">
                                      <div class="custom-control danger result">
                                         <c:out value="${userAnswer}"/>
                                         <p><fmt:message key="your_answer"/></p>
                                     </div>
                                  </c:if>
                                 </c:if>
                                 <p><fmt:message key="percent_answers"/> <c:out value="${requestScope.answerPercent}"/>%</p>
                             </c:if>
                                 <c:set var="id_counter" value="${id_counter + 1}"/>

                             </c:if>
                             <c:if test="${(counter < length) && (requestScope.forward == null)}">
                                 <input type="hidden" name="command" value="PASS_TESTS">
                                 <input type="hidden" name="forward" value="TRUE">
                                 <input type="hidden" name="counter" value="${counter}">
                                 <input type="hidden" name="theme_id" value="1">
                                 <div class="button-div text-center"><button class="btn" type="submit"><fmt:message key="continue"/></button></div>
                             </c:if>
                             <c:if test="${(counter < length) && (requestScope.forward != null)}">
                                 <input type="hidden" name="command" value="PASS_TESTS">
                                 <input type="hidden" name="forward" value="FALSE">
                                 <input type="hidden" name="counter" value="${counter}">
                                 <input type="hidden" name="theme_id" value="1">
                                 <div class="button-div text-center"><button class="btn" type="submit"><fmt:message key="next"/></button></div>
                             </c:if>
                             <c:if test="${counter >= length}">
                                 <input type="hidden" name="command" value="SHOW_RESULTS">
                                 <div class="button-div text-center"><button class="btn" type="submit"><fmt:message key="next"/></button></div>
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
                <div class="course-img-container"><img src="../resources/images/arrays.svg"></div>
                </c:if>
                <c:if test="${question.theme.themeId == 2}">
                    <div class="course-img-container"><img src="../resources/images/if-else.svg"></div>
                </c:if>
                <c:if test="${question.theme.themeId == 3}">
                    <div class="course-img-container"><img src="../resources/images/inheritance.svg"></div>
                </c:if>
                <c:if test="${question.theme.themeId == 4}">
                    <div class="course-img-container"><img src="../resources/images/threads.svg"></div>
                </c:if>
                <c:if test="${question.theme.themeId == 5}">
                    <div class="course-img-container"><img src="../resources/images/primitive.svg"></div>
                </c:if>
                <c:if test="${question.theme.themeId == 6}">
                    <div class="course-img-container"><img src="../resources/images/operators.svg"></div>
                </c:if>
            </section>
            <section class="stats text-center">
                <img src="../resources/images/white_fox.jpg">
            </section>
        </aside>
    </div>
</div>
</body>
</html>
