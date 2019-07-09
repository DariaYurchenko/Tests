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
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/tests.css"/>">
</head>
<body>
<script>
  window.history.forward();
</script>
<div class="row">
    <header class="header col-lg-12">
        <nav class="navbar navbar-dark bg-dark navbar-expand-md col-lg-12">
            <%@include file="header.jsp" %>
            <div class="languages">
                <form action="tests" method="GET">
                    <input type="hidden" value="CHANGE_LANGUAGE" name="command">
                    <input type="hidden" value="jsp/passing_tests.jsp" name="address">
                    <button type="submit" id="lang1" class="form-control" name="lang" value="eng"><img src="<c:url value="/resources/images/uk_flag.jpg"/>"></button>
                    <button type="submit" id="lang2" class="form-control" name="lang" value="rus"><img src="<c:url value="/resources/images/rus_flag.jpg"/>"></button>
                </form>
            </div>
        </nav>
    </header>

    <div class="container d-flex justify-content-center">
        <div class="col-lg-8 col-md-8">
            <section class="work-section">
                <div class="progress">
                    <div class="progress-bar" role="progressbar" style="width:${sessionScope.progress}%" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100"></div>
                </div>
                <div>
                    <c:if test="${appLocale == 'en_UK'}">
                        <c:set var="q" value="${question}"/>
                        <pre><c:out value="${q.question}"/></pre>
                        <c:set var="options" value="${options}"/>
                    </c:if>
                    <c:if test="${appLocale != 'en_UK'}">
                        <c:set var="q" value="${rusQuestion}"/>
                        <pre><c:out value="${q.question}"/></pre>
                        <c:set var="options" value="${optionsTranslated}"/>
                    </c:if>
                    <div class="answers">
                        <c:if test="${q.questionType.type eq 'RADIO'}">
                        <form action="tests" method="post">

                                <c:set var="id_counter" value="1"/>
                            <div><fmt:message key="question_what"/></div>
                            <c:if test="${sessionScope.forward == 'SHOW_QUESTION'}">
                            <c:forEach items="${options}" var="options">
                            <c:if test="${options != null}">
                            <div class="custom-control custom-radio">
                                <input type="radio" class="custom-control-input" id="id${id_counter}" name="singleChoice" value="${options}" required>
                                <label class="custom-control-label" for="id${id_counter}"><c:out value="${options}"/></label>
                                <c:set var="id_counter" value="${id_counter + 1}"/>
                            </div></c:if>
                            </c:forEach>
                            </c:if>
                            <c:if test="${sessionScope.forward == 'SHOW_ANSWERS'}">
                            <c:forEach items="${options}" var="options">
                            <c:if test="${options == q.correctOption1}">
                            <div class="custom-control custom-radio success result">
                                <c:out value="${options}"/>
                                <c:if test="${options == userAnswer || question.correctOption1 == userAnswer || rusQuestion.correctOption1 == userAnswer}">
                                    <p><fmt:message key="your_answer"/></p>
                                </c:if>
                            </div>
                            </c:if>
                            <c:if test="${options == q.incorrectOption1 }">
                            <div class="custom-control custom-radio danger result">
                                <c:out value="${options}"/>
                                <c:if test="${options == userAnswer || question.incorrectOption1 == userAnswer || rusQuestion.incorrectOption1 == userAnswer }">
                                    <p><fmt:message key="your_answer"/></p>
                                </c:if>
                            </div>
                            </c:if>
                                    <c:if test="${options == q.incorrectOption2 }">
                                    <div class="custom-control custom-radio danger result">
                                        <c:out value="${options}"/>
                                        <c:if test="${options == userAnswer || question.incorrectOption2 == userAnswer || rusQuestion.incorrectOption2 == userAnswer }">
                                            <p><fmt:message key="your_answer"/></p>
                                        </c:if>
                                    </div>
                                    </c:if>
                                    <c:if test="${options == q.incorrectOption3 }">
                                    <div class="custom-control custom-radio danger result">
                                        <c:out value="${options}"/>
                                        <c:if test="${options == userAnswer || question.incorrectOption3 == userAnswer || rusQuestion.incorrectOption3 == userAnswer }">
                                            <p><fmt:message key="your_answer"/></p>
                                        </c:if>
                                    </div>
                                    </c:if>
                                <c:set var="id_counter" value="${id_counter + 1}"/>
                            </c:forEach>
                            <p><fmt:message key="percent_answers"/><c:out value="${sessionScope.answerPercent}"/>%</p>
                            </c:if>
                            </c:if>
                            <c:if test="${q.questionType.type eq 'CHECKBOX'}">
                            <form action="tests" method="post">
                                <div><fmt:message key="question_checkbox"/></div>
                                    <c:set var="id_counter" value="1"/>
                                <c:if test="${sessionScope.forward == 'SHOW_QUESTION'}">
                                <c:forEach items="${options}" var="options">
                                <div class="custom-control">
                                    <c:if test="${options != null}">
                                        <input type="checkbox" id="id${id_counter}" name="multipleChoice" value="${options}" >
                                        <label for="id${id_counter}"><c:out value="${options}"/></label>
                                        <c:set var="id_counter" value="${id_counter + 1}"/>
                                    </c:if>
                                </div>
                                </c:forEach>
                                </c:if>
                                <c:if test="${sessionScope.forward == 'SHOW_ANSWERS'}">
                                <c:forEach items="${options}" var="options">
                                <c:if test="${options == question.correctOption1 || options == question.correctOption2 || options == question.correctOption3
                                || options == rusQuestion.correctOption1 || options == rusQuestion.correctOption2 || options == rusQuestion.correctOption3}">
                                <div class="custom-control success result">
                                    <c:out value="${options}"/>
                                    <c:if test="${(options == question.correctOption1 || options == rusQuestion.correctOption1) && (options == userAnswer1 || rusQuestion.correctOption1 == userAnswer1|| question.correctOption1 == userAnswer1 ||
                                    options == userAnswer2 || rusQuestion.correctOption1 == userAnswer2|| question.correctOption1 == userAnswer2 || options == userAnswer3 || rusQuestion.correctOption1 == userAnswer3
                                    || question.correctOption1 == userAnswer3 || options == userAnswer4 || rusQuestion.correctOption1 == userAnswer4|| question.correctOption1 == userAnswer4)}">
                                        <p><fmt:message key="your_answer"/></p>
                                    </c:if>
                                    <c:if test="${(options == question.correctOption2 || options == rusQuestion.correctOption2) && (options == userAnswer1 || rusQuestion.correctOption2 == userAnswer1|| question.correctOption2 == userAnswer1 ||
                                    options == userAnswer2 || rusQuestion.correctOption2 == userAnswer2|| question.correctOption2 == userAnswer2 || options == userAnswer3 || rusQuestion.correctOption2 == userAnswer3
                                    || question.correctOption2 == userAnswer3 || options == userAnswer4 || rusQuestion.correctOption2 == userAnswer4|| question.correctOption2 == userAnswer4)}">
                                        <p><fmt:message key="your_answer"/></p>
                                    </c:if>
                                    <c:if test="${(options == question.correctOption3 || options == rusQuestion.correctOption3) && (options == userAnswer1 || rusQuestion.correctOption3 == userAnswer1|| question.correctOption3 == userAnswer1 ||
                                    options == userAnswer2 || rusQuestion.correctOption3 == userAnswer2|| question.correctOption3 == userAnswer2 || options == userAnswer3 || rusQuestion.correctOption3 == userAnswer3
                                    || question.correctOption3 == userAnswer3 || options == userAnswer4 || rusQuestion.correctOption3 == userAnswer4|| question.correctOption3 == userAnswer4)}">
                                        <p><fmt:message key="your_answer"/></p>
                                    </c:if>
                                </div>
                                </c:if>
                                <c:if test="${options != question.correctOption1 && options != question.correctOption2 && options != question.correctOption3 ||
                                options != rusQuestion.correctOption1 && options != rusQuestion.correctOption2 && options != rusQuestion.correctOption3}">
                                <div class="custom-control danger result">
                                    <c:out value="${options}"/>
                                    <c:if test="${(options == question.incorrectOption1 || options == rusQuestion.incorrectOption1) && (options == userAnswer1 || rusQuestion.incorrectOption1 == userAnswer1|| question.incorrectOption1 == userAnswer1 ||
                                    options == userAnswer2 || rusQuestion.incorrectOption1 == userAnswer2|| question.incorrectOption1 == userAnswer2 || options == userAnswer3 || rusQuestion.incorrectOption1 == userAnswer3
                                    || question.incorrectOption1 == userAnswer3 || options == userAnswer4 || rusQuestion.incorrectOption1 == userAnswer4|| question.incorrectOption1 == userAnswer4)}">
                                        <p><fmt:message key="your_answer"/></p>
                                    </c:if>
                                    <c:if test="${(options == question.incorrectOption2 || options == rusQuestion.incorrectOption2) && (options == userAnswer1 || rusQuestion.incorrectOption2 == userAnswer1|| question.incorrectOption2 == userAnswer1 ||
                                    options == userAnswer2 || rusQuestion.incorrectOption2 == userAnswer2|| question.incorrectOption2 == userAnswer2 || options == userAnswer3 || rusQuestion.incorrectOption2 == userAnswer3
                                    || question.incorrectOption2 == userAnswer3 || options == userAnswer4 || rusQuestion.incorrectOption2 == userAnswer4|| question.incorrectOption2 == userAnswer4)}">
                                        <p><fmt:message key="your_answer"/></p>
                                    </c:if>
                                    <c:if test="${(options == question.incorrectOption3 || options == rusQuestion.incorrectOption3) && (options == userAnswer1 || rusQuestion.incorrectOption3 == userAnswer1|| question.incorrectOption3 == userAnswer1 ||
                                    options == userAnswer2 || rusQuestion.incorrectOption3 == userAnswer2|| question.incorrectOption3 == userAnswer2 || options == userAnswer3 || rusQuestion.incorrectOption3 == userAnswer3
                                    || question.incorrectOption3 == userAnswer3 || options == userAnswer4 || rusQuestion.incorrectOption3 == userAnswer4|| question.incorrectOption3 == userAnswer4)}">
                                        <p><fmt:message key="your_answer"/></p>
                                    </c:if>

                                </div>
                                </c:if>

                                    <c:set var="id_counter" value="${id_counter + 1}"/>
                                </c:forEach>
                                <p><fmt:message key="percent_answers"/><c:out value="${sessionScope.answerPercent}"/>%</p>
                                </c:if>
                                </c:if>
                                <c:if test="${q.questionType.type eq 'TEXT'}">
                                <form action="tests" method="post">
                                    <div><fmt:message key="question_text"/></div>
                                    <c:set var="id_counter" value="1"/>
                                    <c:if test="${sessionScope.forward == 'SHOW_QUESTION'}">
                                        <div class="custom-control">
                                            <c:if test="${options != null}">
                                                <label for="id"><fmt:message key="your_answer"/></label>
                                                <input type="text" id="id" name="singleChoice">
                                            </c:if>
                                        </div>
                                    </c:if>
                                    <c:if test="${sessionScope.forward == 'SHOW_ANSWERS'}">
                                        <c:if test="${userAnswer == q.correctOption1}">
                                            <div class="custom-control success result">
                                                <p><fmt:message key="your_answer"/></p>
                                                <c:out value="${userAnswer}"/>
                                            </div>
                                        </c:if>
                                        <c:if test="${userAnswer != q.correctOption1}">
                                            <div class="custom-control success">
                                                <c:out value="${q.correctOption1}"/>
                                            </div>
                                            <c:if test="${userAnswer != ''}">
                                                <div class="custom-control danger result">
                                                    <c:out value="${userAnswer}"/>
                                                    <p><fmt:message key="your_answer"/></p>
                                                </div>
                                            </c:if>
                                        </c:if>
                                        <p><fmt:message key="percent_answers"/> <c:out value="${sessionScope.answerPercent}"/>%</p>
                                    </c:if>
                                    <c:set var="id_counter" value="${id_counter + 1}"/>

                                    </c:if>

                                    <c:if test="${(counter < length) && (sessionScope.forward == 'SHOW_ANSWERS')}">
                                        <input type="hidden" name="command" value="SHOW_QUESTION">
                                        <input type="hidden" name="counter" value="${counter}">
                                        <div class="button-div text-center"><button class="btn" type="submit"><fmt:message key="continue"/></button></div>
                                    </c:if>
                                    <c:if test="${(counter < length) && (sessionScope.forward == 'SHOW_QUESTION')}">
                                        <input type="hidden" name="command" value="SHOW_ANSWERS">
                                        <input type="hidden" name="counter" value="${counter}">
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
                <h2 class="text-center"><c:out value="${q.theme.themeName}"/></h2>
                <c:if test="${q.theme.themeId == 1}">
                    <div class="course-img-container"><img src="<c:url value="/resources/images/arrays.svg"/>"></div>
                </c:if>
                <c:if test="${q.theme.themeId == 2}">
                    <div class="course-img-container"><img src="<c:url value="/resources/images/if-else.svg"/>"></div>
                </c:if>
                <c:if test="${q.theme.themeId == 3}">
                    <div class="course-img-container"><img src="<c:url value="/resources/images/inheritance.svg"/>"></div>
                </c:if>
                <c:if test="${q.theme.themeId == 4}">
                    <div class="course-img-container"><img src="<c:url value="/resources/images/threads.svg"/>"></div>
                </c:if>
                <c:if test="${q.theme.themeId == 5}">
                    <div class="course-img-container"><img src="<c:url value="/resources/images/primitive.svg"/>"></div>
                </c:if>
                <c:if test="${q.theme.themeId == 6}">
                    <div class="course-img-container"><img src="<c:url value="/resources/images/operators.svg"/>"></div>
                </c:if>
            </section>
            <section class="stats text-center">
                <img src="<c:url value="/resources/images/white_fox.jpg"/>">
            </section>
        </aside>
    </div>
</div>
</body>
</html>