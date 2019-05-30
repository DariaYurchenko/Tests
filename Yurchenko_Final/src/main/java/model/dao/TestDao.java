package model.dao;

import model.entity.Test;
import model.entity.status.TestStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface TestDao extends GenericDao<Test> {
    String INSERT_TEST = "INSERT INTO tests(test_user_Id, test_theme_Id, test_number_of_points, test_max_number_of_points, " +
            "date, test_status) VALUES(?, ?, ?, ?, ?, ?);";
    String FIND_TEST_BY_ID = "SELECT * FROM tests WHERE test_id = ?;";
    String SELECT_ALL_TESTS = "SELECT * FROM tests;";
    String DELETE_TEST = "DELETE FROM users WHERE test_user_id = ?;";
    String DELETE_ALL_TESTS = "DELETE FROM tests;";
    String FIND_TEST_BY_PARAMETER = "SELECT * FROM tests WHERE %s = ?;";

    String TEST_ID = "test_id";
    String USER_ID = "test_user_id";
    String THEME_ID = "theme_id";
    String MAX_POINTS = "test_max_number_of_points";
    String USER_POINTS = "test_number_of_points";
    String DATE = "date";
    String TEST_STATUS = "test_status";

    Double setPercentOfUserPoints(ResultSet rs);

    TestStatus setTestStatus(ResultSet rs) throws SQLException;

}
