<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>Results</p>
<p>You got </p>
<c:out value="${sessionScope.userPoints}"/>
<p>points from</p>
<c:out value="${sessionScope.maxPoints}"/>
<p>Percent of right answers is</p>
<c:out value="${sessionScope.percent}"/>
<c:if test="${sessionScope.passed != null}">
    <p>YOU PASSED!</p>
</c:if>
<p>For more detailed information send email:</p>
<form action="tests" method="get">
    <input type="hidden" name="command" value="SEND_RESULTS">
    <button type="submit">Send!</button>
</form>
    <a href="start_page.jsp">No</a>


</body>
</html>
