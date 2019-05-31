<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 27.05.2019
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
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

</body>
</html>
