<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:if test="${showUsers != null}">
    <table>
            <c:set var="counter" value="1"/>
        <c:forEach items="${users}" var="students">
        <tr>
            <th><c:out value="${counter}"/></th>
            <td><c:out value="${students.id}"/></td>
            <td><c:out value="${students.login}"/></td>
            <td><c:out value="${students.name}"/></td>
            <td><c:out value="${students.lastName}"/></td>
            <td><c:out value="${students.rank}"/></td>
        </tr>
            <c:set var="counter" value="${counter+1}"/>
        </c:forEach>
        </c:if>
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
