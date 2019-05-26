package model.dao;

import model.entity.Test;
import model.entity.entityenum.TestStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface TestDao extends GenericDao<Test> {
    static final String INSERT_TEST = "INSERT INTO tests(test_user_Id, test_theme_Id, test_number_of_points, test_max_number_of_points, " +
            "date, test_status) VALUES(?, ?, ?, ?, ?, ?);";
    static final String FIND_TEST_BY_ID = "SELECT * FROM tests WHERE test_id = ?;";
    static final String SELECT_ALL_TESTS = "SELECT * FROM tests;";
    static final String DELETE_TEST = "DELETE FROM users WHERE test_user_id = ?;";
    static final String DELETE_ALL_TESTS = "DELETE FROM tests;";
    static final String FIND_TEST_BY_PARAMETER = "SELECT * FROM tests WHERE %s = ?;";

    static final String TEST_ID = "test_id";
    static final String USER_ID = "test_user_id";
    static final String THEME_ID = "theme_id";
    static final String MAX_POINTS = "test_max_number_of_points";
    static final String USER_POINTS = "test_number_of_points";
    static final String DATE = "date";
    static final String TEST_STATUS = "test_status";

    Double setPointsPercent(ResultSet rs);

    TestStatus setTestStatus(ResultSet rs) throws SQLException;

}
