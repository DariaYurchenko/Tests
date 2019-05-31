package model.dao.impl;

import exception.DaoException;
import model.dao.TestDao;
import model.dao.TestInfoDao;
import model.dao.UserDao;
import model.dao.connector.Connector;
import model.entity.TestInfo;
import org.apache.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestInfoDaoImpl extends GenericDaoImpl<TestInfo> implements TestInfoDao {
    private static final Logger LOGGER = Logger.getLogger(TestInfoDaoImpl.class);

    private static final String FIND_TEST_INFO_BY_PARAMETER = "SELECT name, lastname, login, user_number_of_points, " +
            "user_max_number_of_points, theme_name, test_number_of_points, test_max_number_of_points," +
            " date, test_status FROM tests t JOIN users u ON t.test_user_id=u.user_id JOIN themes th ON " +
            "t.test_theme_id=th.theme_id WHERE %s = ?;";
    private static final String FIND_TESTS_FOR_PAGINATION = "SELECT name, lastname, login, user_number_of_points, " +
            "user_max_number_of_points, theme_name, test_number_of_points, test_max_number_of_points," +
            " date, test_status FROM tests t JOIN users u ON t.test_user_id=u.user_id JOIN themes th ON " +
            "t.test_theme_id=th.theme_id LIMIT ?, ?;";
    private static final String FIND_USER_TESTS_FOR_PAGINATION = "SELECT name, lastname, login, user_number_of_points, " +
            "user_max_number_of_points, theme_name, test_number_of_points, test_max_number_of_points," +
            " date, test_status FROM tests t JOIN users u ON t.test_user_id=u.user_id JOIN themes th ON " +
            "t.test_theme_id=th.theme_id WHERE test_user_id = ? LIMIT ?, ?;";

    private static final String USER_NAME = "name";
    private static final String USER_LASTNAME = "lastname";
    private static final String USER_LOGIN = "login";
    private static final String THEME = "theme_name";
    private static final String TEST_POINTS = "test_number_of_points";
    private static final String TEST_MAX_POINTS = "test_max_number_of_points";
    private static final String DATE = "date";

    private UserDao userDao;
    private TestDao testDao;

    public TestInfoDaoImpl() {
        this.userDao = new UserDaoImpl();
        this.testDao = new TestDaoImpl();
    }

    @Override
    public String createQueryToSave() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String createQueryToDelete() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String createQueryToDeleteAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String createQueryToFindAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String createQueryToUpdate(String column) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String createQueryToFindById() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void prepareStatementToSave(PreparedStatement ps, TestInfo object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<TestInfo> parseResultSetToFindById(ResultSet rs) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String createQueryToPagination() {
        return FIND_TESTS_FOR_PAGINATION;
    }

    @Override
    public String createQueryToFindByParameter(String column) {
        return String.format(FIND_TEST_INFO_BY_PARAMETER, column);
    }

    @Override
    public List<TestInfo> parseResultSet(ResultSet rs) {
        List<TestInfo> testsInfoList = new ArrayList<>();
        try {
            while(rs.next()) {
                testsInfoList.add(buildTestInfo(rs));
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException with parsing resultset of testsInfo: " + e.getMessage());
            throw new DaoException();
        }
        return testsInfoList;
    }

    private LocalDate setDate(ResultSet rs) throws SQLException {
        return LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate(DATE)));
    }

    private TestInfo buildTestInfo(ResultSet rs) throws SQLException {
        return new TestInfo.Builder()
                .withUserName(rs.getString(USER_NAME))
                .withUserLastName(rs.getString(USER_LASTNAME))
                .withUserLogin(rs.getString(USER_LOGIN))
                .withTheme(rs.getString(THEME))
                .withDate(setDate(rs))
                .withUserPoints(rs.getInt(TEST_POINTS))
                .withMaxPoints(rs.getInt(TEST_MAX_POINTS))
                .withUserRank(userDao.setUserRank(rs))
                .withPercentOfRightAnswers(testDao.setPercentOfUserPoints(rs))
                .withTestStatus(testDao.setTestStatus(rs))
                .build();
    }

    public List<TestInfo> findUserTestsForPagination(Long userId, int currentPage, int recordsPerPage) {
        List<TestInfo> testsInfoList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connector.getConnection().prepareStatement(FIND_USER_TESTS_FOR_PAGINATION)) {
            preparedStatement.setLong(1, userId);
            preparedStatement.setInt(2, currentPage);
            preparedStatement.setInt(3, recordsPerPage);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                testsInfoList.add(buildTestInfo(rs));
            }
            return testsInfoList;
        } catch (SQLException e) {
            LOGGER.warn("SQLException with finding for pagination: " + e.getMessage());
            throw new DaoException(e);
        }
    }
}
