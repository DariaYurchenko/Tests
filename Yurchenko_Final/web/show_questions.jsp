<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${act != 'SHOW_BY_THEME' || act != 'SHOW_BY_THEME_ID'}">
    <form action="tests" method="get">
<table>
    <c:set var="counter" value="1"/>
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
            <c:if test="${act == 'DELETE_QUESTION_BY_ID'}">
                <td><input type="radio" class="custom-control-input" id="id${counter}" name="radio" value="${questions.questionId}"></td>
            </c:if>
        </tr>
        <c:set var="counter" value="${counter+1}"/>

    </c:forEach>
    <c:if test="${act == 'DELETE_QUESTION_BY_ID'}">
        <input type="hidden" name="command" value="DELETE_QUESTION_BY_ID">
        <input type="hidden" name="currentPage" value="${currentPage}">
        <button type="submit">Delete by id</button>
    </c:if>
</table>
    </form>

<ul class="pagination">
    <c:if test="${currentPage != 1}">
        <li class="page-item"><a class="page-link"
                                 href="<c:url value="/tests?command=SHOW_ALL_QUESTIONS&recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}"/>">Previous</a>
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
                <li class="page-item"><a class="page-link" href="<c:url value="/tests?command=SHOW_ALL_QUESTIONS&recordsPerPage=${recordsPerPage}&currentPage=${i}"/>">${i}</a>
                </li>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:if test="${currentPage lt noOfPages}">
        <li class="page-item"><a class="page-link" href="<c:url value="/tests?command=SHOW_ALL_QUESTIONS&recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}"/>">Next</a>
        </li>
    </c:if>
</ul>
</c:if>

<c:if test="${act == 'SHOW_BY_THEME'}">
    <form action="tests" method="get">
    <table>
        <c:set var="counter" value="1"/>
        <c:forEach items="${themes}" var="themes">
            <tr>
                <th><c:out value="${counter}"/></th>
                <td><c:out value="${themes.themeName}"/></td>
                <td><input type="radio" class="custom-control-input" id="id${counter}" name="radio" value="${themes.themeId}"></td>
            </tr>
            <c:set var="counter" value="${counter+1}"/>
        </c:forEach>
    </table>

    <ul class="pagination">
        <c:if test="${currentPage != 1}">
            <li class="page-item"><a class="page-link"
                                     href="<c:url value="/tests?command=SHOW_ALL_QUESTIONS&recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}"/>">Previous</a>
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
                    <li class="page-item"><a class="page-link" href="<c:url value="/tests?command=SHOW_ALL_QUESTIONS&recordsPerPage=${recordsPerPage}&currentPage=${i}"/>">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${currentPage lt noOfPages}">
            <li class="page-item"><a class="page-link" href="<c:url value="/tests?command=SHOW_ALL_QUESTIONS&recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}"/>">Next</a>
            </li>
        </c:if>
    </ul>
            <input type="hidden" name="command" value="SHOW_THEME_QUESTIONS">
            <input type="hidden" name="currentPage" value="1">
            <button type="submit">SHOW_THEME_QUESTIONS</button>
    </form>
</c:if>
<c:if test="${act == 'SHOW_BY_THEME_ID'}">
    <table>
        <c:set var="counter" value="1"/>
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

    <ul class="pagination">
        <c:if test="${currentPage != 1}">
            <li class="page-item"><a class="page-link"
                                     href="<c:url value="/tests?command=SHOW_ALL_QUESTIONS&recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}"/>">Previous</a>
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
                    <li class="page-item"><a class="page-link" href="<c:url value="/tests?command=SHOW_ALL_QUESTIONS&recordsPerPage=${recordsPerPage}&currentPage=${i}"/>">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${currentPage lt noOfPages}">
            <li class="page-item"><a class="page-link" href="<c:url value="/tests?command=SHOW_ALL_QUESTIONS&recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}"/>">Next</a>
            </li>
        </c:if>
    </ul>
</c:if>

</body>
</html>
