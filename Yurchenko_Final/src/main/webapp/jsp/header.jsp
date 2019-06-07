
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
