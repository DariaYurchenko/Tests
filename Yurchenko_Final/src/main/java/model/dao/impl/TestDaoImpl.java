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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestDaoImpl extends GenericDaoImpl<Test> implements TestDao {
    private static final Logger LOGGER = Logger.getLogger(TestDaoImpl.class);

    private static final String INSERT_TEST = "INSERT INTO tests(userId, courseId, date, grade, status) VALUES(?, ?, ?, ?, ?);";

    public TestDaoImpl(Connector connector) {
        super(connector);
    }
    /*private static final String SELECT_ALL = "SELECT * FROM tests;";
    private static final String DELETE_TEST = "DELETE * FROM tests WHERE id = ?;";*/
    //TODO: getResults, getTestsPassed.


    @Override
    public String createQueryToAdd() {
        return INSERT_TEST;
    }

    @Override
    public String createQueryToFindById() {
        return null;
    }

    @Override
    public String createQueryToDelete() {
        return null;
    }

    @Override
    public String createQueryToDeleteAll() {
        return null;
    }

    @Override
    public String createQueryToFindAll() {
        return null;
    }

    @Override
    public String createQueryToFindByParameter(String column) {
        return null;
    }

    @Override
    public String createQueryToUpdate(String column) {
        return null;
    }


    @Override
    public void prepareStatementToAdd(PreparedStatement ps, Test test) {
        try {
            ps.setLong(1, test.getUserId());
            ps.setLong(2, test.getCourseId());
            ps.setDate(3, Date.valueOf(test.getDate()));
            ps.setDouble(4, test.getGrade());
            ps.setString(5, test.getTestStatus().name());
        } catch (SQLException e) {
            LOGGER.error("SQLException with preparing statement for creating test: " + e.getMessage());
            throw new DaoException();
        }
    }

    @Override
    public List<Test> parseResultSet(ResultSet rs) {
        List<Test> tests = new ArrayList<>();
        try {
            while(rs.next()) {
                tests.add(( new Test.Builder()
                        .setId(rs.getInt(1))
                        .setUserId(rs.getLong(2))
                        .setCourseId(rs.getInt(3))
                        .setDate(LocalDate.parse(rs.getString(4)))
                        .setGrade(rs.getDouble(5))
                        .setTestStatus(TestStatus.valueOf(rs.getString(6)))
                        .build()));
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException with parsing resultset of tests: " + e.getMessage());
            throw new DaoException();
        }
        return tests;
    }

    @Override
    public Optional<Test> parseResultSetToFindById(ResultSet rs) {
       /* try {
            return new Test.Builder()
                    .withId(rs.getInt(1))
                    .setUserId(rs.getLong(2))
                    .withThemeId(rs.getInt(3))
                    .setDate(LocalDate.parse(rs.getString(4)))
                    .setGrade(rs.getDouble(5))
                    .setTestStatus(TestStatus.valueOf(rs.getString(6)))
                    .build();
        } catch (SQLException e) {
            LOGGER.error("SQLException with parsing resultset of test: " + e.getMessage());
            throw new DaoException();
        }*/
       return null;
    }

}
