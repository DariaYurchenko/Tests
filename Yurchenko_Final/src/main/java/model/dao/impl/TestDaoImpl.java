package model.dao.impl;

import exception.DaoException;
import model.dao.TestDao;
import model.dao.connector.Connector;
import model.entity.Test;
import model.entity.status.TestStatus;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestDaoImpl extends GenericDaoImpl<Test> implements TestDao {
    private static final Logger LOGGER = Logger.getLogger(TestDaoImpl.class);

    private static final String INSERT_TEST = "INSERT INTO tests(test_user_Id, test_theme_Id, test_number_of_points, test_max_number_of_points, " +
            "date, test_status) VALUES(?, ?, ?, ?, ?, ?);";
    private static final String FIND_TEST_BY_ID = "SELECT * FROM tests WHERE test_id = ?;";
    private static final String SELECT_ALL_TESTS = "SELECT * FROM tests;";
    private static final String DELETE_TEST = "DELETE FROM users WHERE test_user_id = ?;";
    private static final String DELETE_ALL_TESTS = "DELETE FROM tests;";
    private static final String FIND_TEST_BY_PARAMETER = "SELECT * FROM tests WHERE %s = ?;";

    private static final String TEST_ID = "test_id";
    private static final String USER_ID = "test_user_id";
    private static final String THEME_ID = "test_theme_id";
    private static final String MAX_POINTS = "test_max_number_of_points";
    private static final String USER_POINTS = "test_number_of_points";
    private static final String DATE = "date";
    private static final String TEST_STATUS = "test_status";

    @Override
    public String createQueryToSave() {
        return INSERT_TEST;
    }

    @Override
    public String createQueryToFindById() {
        return FIND_TEST_BY_ID;
    }

    @Override
    public String createQueryToDelete() {
        return DELETE_TEST;
    }

    @Override
    public String createQueryToDeleteAll() {
        return DELETE_ALL_TESTS;
    }

    @Override
    public String createQueryToFindAll() {
        return SELECT_ALL_TESTS;
    }

    @Override
    public String createQueryToPagination() {
       throw new UnsupportedOperationException();
    }

    @Override
    public String createQueryToFindByParameter(String column) {
        return String.format(FIND_TEST_BY_PARAMETER, column);
    }

    @Override
    public String createQueryToUpdate(String column) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void prepareStatementToSave(PreparedStatement ps, Test test) {
        try {
            ps.setLong(1, test.getUserId());
            ps.setLong(2, test.getThemeId());
            ps.setInt(3, test.getUserPoints());
            ps.setInt(4, test.getMaxPoints());
            ps.setDate(5, Date.valueOf(test.getDate()));
            ps.setInt(6, mapStatusToTable(test));
        } catch (SQLException e) {
            LOGGER.error("SQLException with preparing statement for adding test: " + e.getMessage());
            throw new DaoException();
        }
    }

    private int mapStatusToTable(Test test) {
        return test.getTestStatus().equals(TestStatus.PASSED) ? 1 : 2;
    }

    @Override
    public List<Test> parseResultSet(ResultSet rs) {
        List<Test> tests = new ArrayList<>();
        try {
            while(rs.next()) {
                tests.add(buildTest(rs));
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException with parsing resultset of tests: " + e.getMessage());
            throw new DaoException(e);
        }
        return tests;
    }

    private LocalDate setDate(ResultSet rs) throws SQLException {
        return LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate(DATE)));
    }

    public Double setPercentOfUserPoints(ResultSet rs) {
        try {
            int rightAnswers = rs.getInt(USER_POINTS);
            int allAnswers = rs.getInt(MAX_POINTS);
            return countPercentOfUserPoints(rightAnswers, allAnswers);
        } catch (SQLException e) {
            LOGGER.error("SQLException with counting of percent of user's points: " + e.getMessage());
            throw new DaoException(e);
        }
    }

    private Double countPercentOfUserPoints(int rightAnswers, int allAnswers) {
        return Math.round((rightAnswers * 1.0 / allAnswers) * 100) / 1.0;
    }

    public TestStatus setTestStatus(ResultSet rs) throws SQLException {
        return rs.getInt(TEST_STATUS) == 1 ? TestStatus.PASSED : TestStatus.NOT_PASSED;
    }

    @Override
    public Optional<Test> parseResultSetToFindById(ResultSet rs) {
       try {
            return Optional.ofNullable(buildTest(rs));
        } catch (SQLException e) {
            LOGGER.error("SQLException with parsing resultset of test: " + e.getMessage());
            throw new DaoException();
        }
    }

    private Test buildTest(ResultSet rs) throws SQLException {
        return new Test.Builder()
                .withId(rs.getLong(TEST_ID))
                .withUserId(rs.getLong(USER_ID))
                .withThemeId(rs.getLong(THEME_ID))
                .withDate(setDate(rs))
                .withUserPoints(rs.getInt(USER_POINTS))
                .withMaxPoints(rs.getInt(MAX_POINTS))
                .withRightAnswersPercent(setPercentOfUserPoints(rs))
                .withTestStatus(setTestStatus(rs))
                .build();
    }

}
