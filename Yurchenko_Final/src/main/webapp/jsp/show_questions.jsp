<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${appLocale}" scope="session"/>
<fmt:setBundle basename="languages/messages"/>
<html>
<head>
    <title>Questions</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="../resources/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="../resources/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<c:url value="../resources/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="../resources/css/show_questions.css"/>">
</head>
<body>
<div class="row">
    <header class="header col-lg-12">
        <nav class="navbar navbar-dark bg-dark navbar-expand-md col-lg-12">
            <%@include file="header.jsp" %>
            <div class="languages">
                <form action="tests" method="GET">
                    <input type="hidden" value="CHANGE_LANGUAGE" name="command">
                    <input type="hidden" value="jsp/show_questions.jsp" name="address">
                    <button type="submit" id="lang1" class="form-control" name="lang" value="eng"><img src="../resources/images/uk_flag.jpg"></button>
                    <button type="submit" id="lang2" class="form-control" name="lang" value="rus"><img src="../resources/images/rus_flag.jpg"></button>
                </form>
            </div>
        </nav>
    </header>
<div class="d-flex">
    <%@include file="admin_aside.jsp" %>
    <section class="col-lg-9">
        <c:if test="${act != 'SHOW_BY_THEME' && act != 'SHOW_BY_THEME_ID'}">
            <div class="text-center"><fmt:message key="records_per_page"/></div>
            <ul class="pagination justify-content-center">
                <li class="page-item"><a class="page-link" href="<c:url value="/tests?command=SHOW_ALL_QUESTIONS&recordsPerPage=5"/>">5</a>
                </li>
                <li class="page-item"><a class="page-link" href="<c:url value="/tests?command=SHOW_ALL_QUESTIONS&recordsPerPage=10"/>">10</a>
                </li>
                <li class="page-item"><a class="page-link" href="<c:url value="/tests?command=SHOW_ALL_QUESTIONS&recordsPerPage=50"/>">50</a>
                </li>
            </ul>
            <h1 class="text-center"><fmt:message key="admin_questions"/></h1>
            <form class="main-form" action="tests" method="get">
                <div class="table-div">
                <table class="text-center table table-striped">
                    <c:set var="counter" value="1"/>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col"><fmt:message key="admin_q"/></th>
                        <th scope="col"><fmt:message key="admin_q_a_percent"/></th>
                        <th scope="col"><fmt:message key="admin_incor_1"/></th>
                        <th scope="col"><fmt:message key="admin_incor_2"/></th>
                        <th scope="col"><fmt:message key="admin_incor_3"/></th>
                        <th scope="col"><fmt:message key="admin_cor_1"/></th>
                        <th scope="col"><fmt:message key="admin_cor_2"/></th>
                        <th scope="col"><fmt:message key="admin_cor_3"/></th>
                        <th scope="col"><fmt:message key="admin_q_type"/></th>
                        <th scope="col"><fmt:message key="admin_theme"/></th>
                    </tr>
                    <c:forEach items="${questions}" var="questions">
                        <tr>
                            <th scope="row"><c:out value="${counter}"/>1</th>
                            <td><c:out value="${questions.question}"/></td>
                            <td><c:out value="${questions.percentOfRightAnswers}"/></td>
                            <td><c:out value="${questions.incorrectOption1}"/></td>
                            <td><c:out value="${questions.incorrectOption2}"/></td>
                            <td><c:out value="${questions.incorrectOption3}"/></td>
                            <td><c:out value="${questions.correctOption1}"/></td>
                            <td><c:out value="${questions.correctOption2}"/></td>
                            <td><c:out value="${questions.correctOption3}"/></td>
                            <td><c:out value="${questions.questionType.type}"/></td>
                            <td><c:out value="${questions.theme.themeName}"/></td>
                        </tr>
                        <c:set var="counter" value="${counter+1}"/>

                    </c:forEach>
                </table>
                <c:if test="${questionsSize == 0}">
                    <p class="text-center"><fmt:message key="no_questions"/></p>
                </c:if>
            </div>
                <ul class="pagination justify-content-center">
                    <c:if test="${currentPage != 1}">
                        <li class="page-item"><a class="page-link"
                                                 href="<c:url value="/tests?command=SHOW_ALL_QUESTIONS&recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}"/>"><fmt:message key="prev_ad"/></a>
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
                                <li class="page-item"><a class="page-link" href="
<c:url value="/tests?command=SHOW_ALL_QUESTIONS&recordsPerPage=${recordsPerPage}&currentPage=${i}"/>">${i}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <c:if test="${currentPage lt noOfPages}">
                        <li class="page-item"><a class="page-link" href="<c:url value="/tests?command=SHOW_ALL_QUESTIONS&recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}"/>"><fmt:message key="next"/></a>
                        </li>
                    </c:if>
                </ul>
            </form>
        </c:if>

        <c:if test="${act == 'SHOW_BY_THEME'}">
            <div class="text-center"><fmt:message key="records_per_page"/></div>
            <ul class="pagination justify-content-center">
                <li class="page-item"><a class="page-link" href="<c:url value="/tests?command=SHOW_THEMES&recordsPerPage=5"/>">5</a>
                </li>
                <li class="page-item"><a class="page-link" href="<c:url value="/tests?command=SHOW_THEMES&recordsPerPage=10"/>">10</a>
                </li>
                <li class="page-item"><a class="page-link" href="<c:url value="/tests?command=SHOW_THEMES&recordsPerPage=50"/>">50</a>
                </li>
            </ul>
            <h1 class="text-center"><fmt:message key="admin_themes"/></h1>
            <form class="main-form" action="tests" method="get">
                <table class="text-center table table-striped">
                    <c:set var="counter" value="1"/>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col"><fmt:message key="admin_theme"/></th>
                        <th scope="col"></th>
                    </tr>
                    <c:forEach items="${themes}" var="themes">
                        <tr>
                            <th><c:out value="${counter}"/></th>
                            <td><c:out value="${themes.themeName}"/></td>
                            <td><input type="radio" id="id${counter}" name="radio" value="${themes.themeId}"></td>
                        </tr>
                        <c:set var="counter" value="${counter+1}"/>
                    </c:forEach>
                </table>
                <c:if test="${themesSize == 0}">
                    <p class="text-center"><fmt:message key="no_themes"/></p>
                </c:if>
                <ul class="pagination justify-content-center">
                    <c:if test="${currentPage != 1}">
                        <li class="page-item"><a class="page-link"
                                                 href="<c:url value="/tests?command=SHOW_THEMES&recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}"/>"><fmt:message key="prev_ad"/></a>
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
                                <li class="page-item"><a class="page-link" href="<c:url value="/tests?command=SHOW_THEMES&recordsPerPage=${recordsPerPage}&currentPage=${i}"/>">${i}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <c:if test="${currentPage lt noOfPages}">
                        <li class="page-item"><a class="page-link" href="<c:url value="/tests?command=SHOW_THEMES&recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}"/>"><fmt:message key="next"/></a>
                        </li>
                    </c:if>
                </ul>
                <div>
                    <input type="hidden" name="command" value="SHOW_THEME_QUESTIONS">
                    <input type="hidden" name="currentPage" value="1">
                    <div
                            class="button-div form-group col-md-12 text-center">
                        <button class="btn" type="submit"><fmt:message key="admin_show_q_t"/></button>
                    </div>
                </div>
            </form>
        </c:if>
        <c:if test="${act == 'SHOW_BY_THEME_ID'}">
            <div class="text-center"><fmt:message key="records_per_page"/></div>
            <ul class="pagination justify-content-center">
                <li class="page-item"><a class="page-link" href="<c:url value="/tests?command=SHOW_THEME_QUESTIONS&recordsPerPage=5"/>">5</a>
                </li>
                <li class="page-item"><a class="page-link" href="<c:url value="/tests?command=SHOW_THEME_QUESTIONS&recordsPerPage=10"/>">10</a>
                </li>
                <li class="page-item"><a class="page-link" href="<c:url value="/tests?command=SHOW_THEME_QUESTIONS&recordsPerPage=50"/>">50</a>
                </li>
            </ul>
            <div class="table-div">
            <table class="text-center table table-striped">
                <c:set var="counter" value="1"/>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col"><fmt:message key="admin_q"/></th>
                    <th scope="col"><fmt:message key="admin_q_a_percent"/></th>
                    <th scope="col"><fmt:message key="admin_incor_1"/></th>
                    <th scope="col"><fmt:message key="admin_incor_2"/></th>
                    <th scope="col"><fmt:message key="admin_incor_3"/></th>
                    <th scope="col"><fmt:message key="admin_cor_1"/></th>
                    <th scope="col"><fmt:message key="admin_cor_2"/></th>
                    <th scope="col"><fmt:message key="admin_cor_3"/></th>
                    <th scope="col"><fmt:message key="admin_q_type"/></th>
                    <th scope="col"><fmt:message key="admin_theme"/></th>
                </tr>
                <c:forEach items="${questions}" var="questions">
                    <tr>
                        <th><c:out value="${counter}"/></th>
                        <td><c:out value="${questions.question}"/></td>
                        <td><c:out value="${questions.percentOfRightAnswers}"/></td>
                        <td><c:out value="${questions.incorrectOption1}"/></td>
                        <td><c:out value="${questions.incorrectOption2}"/></td>
                        <td><c:out value="${questions.incorrectOption3}"/></td>
                        <td><c:out value="${questions.correctOption1}"/></td>
                        <td><c:out value="${questions.correctOption2}"/></td>
                        <td><c:out value="${questions.correctOption3}"/></td>
                        <td><c:out value="${questions.questionType.type}"/></td>
                        <td><c:out value="${questions.theme.themeName}"/></td>
                    </tr>
                    <c:set var="counter" value="${counter+1}"/>

                </c:forEach>
            </table>
            </div>
            <c:if test="${questionsSize == 0}">
                <p class="text-center"><fmt:message key="no_questions"/></p>
            </c:if>
            <ul class="pagination justify-content-center">
                <c:if test="${currentPage != 1}">
                    <li class="page-item"><a class="page-link" href="<c:url value="/tests?command=SHOW_THEME_QUESTIONS&recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}"/>">Previous</a>
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
                            <li class="page-item"><a class="page-link" href="<c:url value="/tests?command=SHOW_THEME_QUESTIONS&recordsPerPage=${recordsPerPage}&currentPage=${i}"/>">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${currentPage lt noOfPages}">
                    <li class="page-item"><a class="page-link" href="<c:url value="/tests?command=SHOW_THEME_QUESTIONS&recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}"/>">Next</a>
                    </li>
                </c:if>
            </ul>
        </c:if>
    </section>
</div>
</div>
</body>
</html>
