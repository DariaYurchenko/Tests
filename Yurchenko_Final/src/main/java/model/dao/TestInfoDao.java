package model.dao;

import model.entity.TestInfo;

import java.util.List;

public interface TestInfoDao extends GenericDao<TestInfo> {
    static final String FIND_TEST_INFO_BY_PARAMETER = "SELECT name, lastname, login, user_number_of_points, " +
            "user_max_number_of_points, theme_name, test_number_of_points, test_max_number_of_points," +
            " date, test_status FROM tests t JOIN users u ON t.test_user_id=u.user_id JOIN themes th ON " +
            "t.test_theme_id=th.theme_id WHERE %s = ?;";
    static final String FIND_TESTS_FOR_PAGINATION = "SELECT name, lastname, login, user_number_of_points, " +
            "user_max_number_of_points, theme_name, test_number_of_points, test_max_number_of_points," +
            " date, test_status FROM tests t JOIN users u ON t.test_user_id=u.user_id JOIN themes th ON " +
            "t.test_theme_id=th.theme_id WHERE test_user_id = ? LIMIT ?, ?;";

    static final String USER_NAME = "name";
    static final String USER_LASTNAME = "lastname";
    static final String USER_LOGIN = "login";
    //static final String USER_POINTS = "user_number_of_points";
    //static final String MAX_USER_POINTS = "user_max_number_of_points";
    static final String THEME = "theme_name";
    static final String TEST_POINTS = "test_number_of_points";
    static final String TEST_MAX_POINTS = "test_max_number_of_points";
    static final String DATE = "date";
    //static final String TEST_STATUS = "test_status";

    List<TestInfo> findTestsForPagination(Long userId, int currentPage, int recordsPerPage);
}
