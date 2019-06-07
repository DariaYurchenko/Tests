<aside class="col-lg-3">
    <div class="bg-white text-center">
        <h2><fmt:message key="admin_users"/></h2>
        <form method="get" action="tests">
            <input type="hidden" name="command" value="SHOW_ALL_USERS">
            <input type="hidden" name="act" value="JUST_SHOW">
            <input type="hidden" name="currentPage" value="1">
            <button class="btn" type="submit"><fmt:message key="admin_show_u"/></button>
        </form>
        <form method="get" action="tests">
            <input type="hidden" name="command" value="SHOW_ALL_USERS">
            <input type="hidden" name="act" value="DELETE_USER_BY_ID">
            <input type="hidden" name="currentPage" value="1">
            <button class="btn" type="submit"><fmt:message key="admin_delete_u_id"/></button>
        </form>
        <form method="get" action="tests">
            <input type="hidden" name="command" value="SHOW_ALL_USERS">
            <input type="hidden" name="act" value="DELETE_ALL_USERS">
            <input type="hidden" name="currentPage" value="1">
            <button class="btn" type="submit"><fmt:message key="admin_delete_u_all"/></button>
        </form>
        <form method="get" action="tests">
            <input type="hidden" name="command" value="SHOW_ALL_USERS">
            <input type="hidden" name="act" value="SHOW_USER_RESULTS">
            <input type="hidden" name="currentPage" value="1">
            <button class="btn" type="submit"><fmt:message key="admin_show_results"/></button>
        </form>
    </div>
    <div class="bg-white text-center">
        <h2><fmt:message key="admin_questions"/></h2>
        <form method="get" action="tests">
            <input type="hidden" name="currentPage" value="1">
            <input type="hidden" name="act" value="JUST_SHOW">
            <input type="hidden" name="command" value="SHOW_ALL_QUESTIONS">
            <button class="btn" type="submit"><fmt:message key="admin_show_questions"/></button>
        </form>
        <form method="get" action="tests">
            <input type="hidden" name="currentPage" value="1">
            <input type="hidden" name="act" value="SHOW_BY_THEME">
            <input type="hidden" name="command" value="SHOW_THEMES">
            <button class="btn" type="submit"><fmt:message key="admin_show_theme_questions"/></button>
        </form>
    </div>
    <div class="bg-white text-center">
        <h2><fmt:message key="admin_others"/></h2>
        <form method="get" action="tests">
            <input type="hidden" name="currentPage" value="1">
            <input type="hidden" name="command" value="SHOW_ALL_USERS">
            <input type="hidden" name="act" value="REGISTER_ADMIN">
            <button class="btn" type="submit"><fmt:message key="admin_register"/></button>
        </form>
    </div>
</aside>
