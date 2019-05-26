package model.dao.impl;

import exception.DaoException;
import model.dao.TestDao;
import model.dao.connector.Connector;
import model.entity.Test;
import model.entity.entityenum.TestStatus;
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

    public TestDaoImpl(Connector connector) {
        super(connector);
    }

    @Override
    public String createQueryToAdd() {
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
    public String createQueryToFindByParameter(String column) {
        return String.format(FIND_TEST_BY_PARAMETER, column);
    }

    @Override
    public String createQueryToUpdate(String column) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void prepareStatementToAdd(PreparedStatement ps, Test test) {
        try {
            ps.setLong(1, test.getUserId());
            ps.setLong(2, test.getThemeId());
            ps.setInt(3, test.getUserPoints());
            ps.setInt(4, test.getMaxPoints());
            ps.setDate(5, Date.valueOf(test.getDate()));
            ps.setInt(6, mapStatusToTable(test));
        } catch (SQLException e) {
            LOGGER.error("SQLException with preparing statement for creating test: " + e.getMessage());
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
                tests.add(createTest(rs));
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException with parsing resultset of tests: " + e.getMessage());
            throw new DaoException();
        }
        return tests;
    }

    private LocalDate setDate(ResultSet rs) throws SQLException {
        return LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate(DATE)));
    }

    public Double setPointsPercent(ResultSet rs) {
        try {
            int rightAnswers = rs.getInt(USER_POINTS);
            int answers = rs.getInt(MAX_POINTS);
            return Math.round((rightAnswers * 1.0 / answers) * 100) / 1.0;
        } catch (SQLException e) {
            LOGGER.error("SQLException with counting of percent of user's points: " + e.getMessage());
            throw new DaoException(e);
        }
    }

    public TestStatus setTestStatus(ResultSet rs) throws SQLException {
        return rs.getInt(TEST_STATUS) == 1 ? TestStatus.PASSED : TestStatus.NOT_PASSED;
    }

    @Override
    public Optional<Test> parseResultSetToFindById(ResultSet rs) {
       try {
            return Optional.ofNullable(createTest(rs));
        } catch (SQLException e) {
            LOGGER.error("SQLException with parsing resultset of test: " + e.getMessage());
            throw new DaoException();
        }
    }

    private Test createTest(ResultSet rs) throws SQLException {
        return new Test.Builder()
                .withId(rs.getLong(TEST_ID))
                .withUserId(rs.getLong(USER_ID))
                .withThemeId(rs.getLong(THEME_ID))
                .withDate(setDate(rs))
                .withUserPoints(rs.getInt(USER_POINTS))
                .withMaxPoints(rs.getInt(MAX_POINTS))
                .withRightAnswersPercent(setPointsPercent(rs))
                .withTestStatus(setTestStatus(rs))
                .build();
    }

}
