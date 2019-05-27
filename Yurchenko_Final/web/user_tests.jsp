<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User results</title>
</head>
<body>
<c:if test="${testInfoList != null}">

<table>
    <c:set var="counter" value="1"/>
    <c:forEach items="${testInfoList}" var="tests">
        <tr>
            <th><c:out value="${counter}"/></th>
            <td><c:out value="${tests.userName}"/></td>
            <td><c:out value="${tests.userLastName}"/></td>
            <td><c:out value="${tests.userLogin}"/></td>
            <td><c:out value="${tests.userRank}"/></td>
            <td><c:out value="${tests.theme}"/></td>
            <td><c:out value="${tests.userPoints}"/></td>
            <td><c:out value="${tests.maxPoints}"/></td>
            <td><c:out value="${tests.rightAnswersPercent}"/></td>
            <td><c:out value="${tests.date}"/></td>
            <td><c:out value="${tests.testStatus}"/></td>
        </tr>
        <c:set var="counter" value="${counter+1}"/>

    </c:forEach>

    </c:if>
</table>
</form>
<ul class="pagination">
    <c:if test="${currentPage != 1}">
        <li class="page-item"><a class="page-link"
                                 href="<c:url value="/tests?command=SHOW_ALL_USERS&recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}"/>">Previous</a>
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
                <li class="page-item"><a class="page-link" href="<c:url value="/tests?command=SHOW_ALL_USERS&recordsPerPage=${recordsPerPage}&currentPage=${i}"/>">${i}</a>
                </li>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:if test="${currentPage lt noOfPages}">
        <li class="page-item"><a class="page-link" href="<c:url value="/tests?command=SHOW_ALL_USERS&recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}"/>">Next</a>
        </li>
    </c:if>
</ul>

</body>
</html>
