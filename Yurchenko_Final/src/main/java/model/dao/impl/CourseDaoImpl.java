package model.dao.impl;

import exception.DaoException;
import model.dao.CourseDao;
import model.dao.connector.Connector;
import model.entity.Course;
import org.apache.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseDaoImpl extends GenericDaoImpl<Course> implements CourseDao {
    private static final Logger LOGGER = Logger.getLogger(CourseDaoImpl.class);

    public CourseDaoImpl(Connector connector) {
        super(connector);
    }

    @Override
    public String createQueryToAdd() {
        return INSERT_COURSE;
    }

    @Override
    public String createQueryToFindById() {
        return FIND_COURSE_BY_ID;
    }

    @Override
    public String createQueryToDelete() {
        return DELETE_COURSE;
    }

    @Override
    public String createQueryToDeleteAll() {
        return DELETE_ALL_COURSES;
    }

    @Override
    public String createQueryToFindAll() {
        return SELECT_ALL_COURSES;
    }

    @Override
    public String createQueryToFindByParameter(String column) {
        return String.format(FIND_COURSE_BY_PARAMETER, column);
    }

    @Override
    public String createQueryToUpdate(String column) {
        return String.format(UPDATE_COURSE, column);
    }

    @Override
    public void prepareStatementToAdd(PreparedStatement ps, Course course) {
        try {
            ps.setString(1, course.getCourseName());
            ps.setInt(2, course.getAmountOfQuestions());
        } catch (SQLException e) {
            LOGGER.error("SQLException with preparing statement for creating course: " + e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public List<Course> parseResultSet(ResultSet rs) {
        List<Course> courses = new ArrayList<>();
        try {
            while(rs.next()) {
                courses.add(new Course.Builder()
                        .withId(rs.getLong(COURSE_ID))
                        .withCourseName(rs.getString(COURSE_NAME))
                        .withAmountOfQuestions(rs.getInt(AMOUNT_OF_QUESTIONS))
                        .withUsersPassed(rs.getLong(USERS_PASSED))
                        .build());
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException with parsing resultset of question: " + e.getMessage());
            throw new DaoException(e);
        }
        return courses;
    }

    @Override
    public Optional<Course> parseResultSetToFindById(ResultSet rs) {
        try {
            return Optional.ofNullable(new Course.Builder()
                    .withId(rs.getLong(COURSE_ID))
                    .withCourseName(rs.getString(COURSE_NAME))
                    .withAmountOfQuestions(rs.getInt(AMOUNT_OF_QUESTIONS))
                    .withUsersPassed(rs.getLong(USERS_PASSED))
                    .build());
        } catch (SQLException e) {
            LOGGER.error("SQLException with parsing resultset of course: " + e.getMessage());
            throw new DaoException(e);
        }
    }

}
