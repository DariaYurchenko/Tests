package model.dao;

import model.entity.TestInfo;

import java.util.List;

public interface TestInfoDao extends GenericDao<TestInfo> {
    String FIND_TEST_INFO_BY_PARAMETER = "SELECT name, lastname, login, user_number_of_points, " +
            "user_max_number_of_points, theme_name, test_number_of_points, test_max_number_of_points," +
            " date, test_status FROM tests t JOIN users u ON t.test_user_id=u.user_id JOIN themes th ON " +
            "t.test_theme_id=th.theme_id WHERE %s = ?;";
    String FIND_TESTS_FOR_PAGINATION = "SELECT name, lastname, login, user_number_of_points, " +
            "user_max_number_of_points, theme_name, test_number_of_points, test_max_number_of_points," +
            " date, test_status FROM tests t JOIN users u ON t.test_user_id=u.user_id JOIN themes th ON " +
            "t.test_theme_id=th.theme_id LIMIT ?, ?;";
    String FIND_USER_TESTS_FOR_PAGINATION = "SELECT name, lastname, login, user_number_of_points, " +
            "user_max_number_of_points, theme_name, test_number_of_points, test_max_number_of_points," +
            " date, test_status FROM tests t JOIN users u ON t.test_user_id=u.user_id JOIN themes th ON " +
            "t.test_theme_id=th.theme_id WHERE test_user_id = ? LIMIT ?, ?;";

    String USER_NAME = "name";
    String USER_LASTNAME = "lastname";
    String USER_LOGIN = "login";
    String THEME = "theme_name";
    String TEST_POINTS = "test_number_of_points";
    String TEST_MAX_POINTS = "test_max_number_of_points";
    String DATE = "date";

    List<TestInfo> findUserTestsForPagination(Long userId, int currentPage, int recordsPerPage);
}
