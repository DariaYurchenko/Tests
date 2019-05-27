<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
You did not submit email. Send again?
<form action="tests" method="get">
    <input type="hidden" name="command" value="SEND_EMAIL_AGAIN">
    <button type="submit">Yes</button>
</form>
<c:if test="${requestScope.sent != null}">
<a href="start_page.jsp">Back</a>
</c:if>

</body>
</html>
