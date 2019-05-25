package model.dao;

import model.entity.Test;

public interface TestDao extends GenericDao<Test> {
    static final String INSERT_TEST = "INSERT INTO tests(userId, themeId, number_of_points, maxnumber_of_points, " +
            "date, status) VALUES(?, ?, ?, ?, ?, ?);";
    static final String FIND_TEST_BY_ID = "SELECT * FROM tests WHERE test_id = ?;";
    static final String SELECT_ALL_TESTS = "SELECT * FROM tests;";
    static final String DELETE_TEST = "DELETE FROM users WHERE user_id = ?;";
    static final String DELETE_ALL_TESTS = "DELETE FROM tests;";
    static final String FIND_TEST_BY_PARAMETER = "SELECT * FROM tests WHERE %s = ?;";

    static final String TEST_ID = "test_id";
    static final String USER_ID = "user_id";
    static final String THEME_ID = "theme_id";
    static final String MAX_POINTS = " maxnumber_of_points";
    static final String USER_POINTS = "number_of_points";
    static final String DATE = "date";
    static final String TEST_STATUS = "test_status";

}
