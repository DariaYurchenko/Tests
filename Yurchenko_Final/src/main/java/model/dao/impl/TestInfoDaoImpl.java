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

    private UserDao userDao;
    private TestDao testDao;

    public TestInfoDaoImpl(Connector connector) {
        super(connector);
        this.userDao = new UserDaoImpl(connector);
        this.testDao = new TestDaoImpl(connector);
    }

    @Override
    public String createQueryToAdd() {
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
    public String createQueryToFindByParameter(String column) {
        return String.format(FIND_TEST_INFO_BY_PARAMETER, column);
    }

    @Override
    public void prepareStatementToAdd(PreparedStatement ps, TestInfo object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<TestInfo> parseResultSetToFindById(ResultSet rs) {
        return Optional.empty();
    }

    @Override
    public List<TestInfo> parseResultSet(ResultSet rs) {
        List<TestInfo> testsInfoList = new ArrayList<>();
        try {
            while(rs.next()) {
                testsInfoList.add(createTestInfo(rs));
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

    private TestInfo createTestInfo(ResultSet rs) throws SQLException {
        return new TestInfo.Builder()
                .withUserName(rs.getString(USER_NAME))
                .withUserLastName(rs.getString(USER_LASTNAME))
                .withUserLogin(rs.getString(USER_LOGIN))
                .withTheme(rs.getString(THEME))
                .withDate(setDate(rs))
                .withUserPoints(rs.getInt(TEST_POINTS))
                .withMaxPoints(rs.getInt(TEST_MAX_POINTS))
                .withUserRank(userDao.setUserRank(rs))
                .withPercentOfRightAnswers(testDao.setPointsPercent(rs))
                .withTestStatus(testDao.setTestStatus(rs))
                .build();
    }
}
