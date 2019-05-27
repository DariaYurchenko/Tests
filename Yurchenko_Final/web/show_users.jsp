<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${act != 'REGISTER_ADMIN'}">
<form method="get" action="tests">
    <c:if test="${showUsers != null}">

    <table>
            <c:set var="counter" value="1"/>
        <c:forEach items="${users}" var="students">
        <tr>
            <th><c:out value="${counter}"/></th>
            <td><c:out value="${students.userId}"/></td>
            <td><c:out value="${students.login}"/></td>
            <td><c:out value="${students.name}"/></td>
            <td><c:out value="${students.lastName}"/></td>
            <td><c:out value="${students.rank}"/></td>
            <c:if test="${act == 'DELETE_USER_BY_ID' || act == 'SHOW_USER_RESULTS'}">
            <td><input type="radio" class="custom-control-input" id="id${counter}" name="radio" value="${students.userId}"></td>
            </c:if>
        </tr>
            <c:set var="counter" value="${counter+1}"/>

        </c:forEach>
        <c:if test="${act == 'DELETE_USER_BY_ID'}">
            <input type="hidden" name="command" value="DELETE_USER_BY_ID">
            <input type="hidden" name="currentPage" value="${currentPage}">
            <button type="submit">Delete by id</button>
        </c:if><c:if test="${act == 'DELETE_USER_BY_ID'}">
            <input type="hidden" name="command" value="DELETE_USER_BY_ID">
            <input type="hidden" name="currentPage" value="${currentPage}">
            <button type="submit">Delete by id</button>
        </c:if>
        <c:if test="${act == 'SHOW_USER_RESULTS'}">
            <input type="hidden" name="command" value="SHOW_USER_RESULTS">
            <input type="hidden" name="currentPage" value="${currentPage}">
            <button type="submit">Show user's results</button>
        </c:if>


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
</c:if>

<c:if test="${act == 'REGISTER_ADMIN'}">
<form action="tests" method="POST" name="sign-up-form" class="col-lg-8 col-md-10 col-sm-11">
    <input type="hidden" name="command" value="REGISTER">
    <input type="hidden" name="userType" value="ADMIN">
    <div class="form-group">
        <label for="name"><fmt:message key="register_name"/></label>
        <input type="text" id="name" class="form-control" placeholder="Ivan" name="name">
        <c:if test="${requestScope.errName != null}">
            <small class="text-danger"><c:out value="${requestScope.errName}"/></small>
        </c:if>
    </div>
    <div class="form-group">
        <label for="lastname"><fmt:message key="register_lastname"/></label>
        <input type="text" class="form-control" id="lastname" placeholder="Petrov" name="lastname">
        <c:if test="${requestScope.errLastname != null}">
            <small class="text-danger"><c:out value="${requestScope.errLastname}"/></small>
        </c:if>
    </div>
    <div class="form-row">
        <div class="form-group col-md-6">
            <label for="email"><fmt:message key="register_login"/></label>
            <input type="email" class="form-control" id="email" placeholder="ivan.petrov@gmail.com" name="email">
            <c:if test="${requestScope.errLogin != null}">
                <small class="text-danger"><c:out value="${requestScope.errLogin}"/></small>
            </c:if>
        </div>
        <div class="form-group col-md-6">
            <label for="password"><fmt:message key="register_password"/></label>
            <input type="password" class="form-control" id="password" name="password">
            <c:if test="${requestScope.errPassword != null}">
                <small class="text-danger"><c:out value="${requestScope.errPassword}"/></small>
            </c:if>
        </div>
    </div>
    <div class="button-div form-group col-md-12 text-center">
        <input type="submit" class="btn" value="Sign Up">
    </div>
    <div><c:if test="${requestScope.user_exists != null}">
        <small class="text-danger"><c:out value="${requestScope.user_exists}"/></small>
    </c:if></div>
</form>
<section class="img-section">
    <div class="img-div text-center"><img src="images/reg_fox.jpg"></div>
</section>
</div>
</c:if>
</body>
</html>
