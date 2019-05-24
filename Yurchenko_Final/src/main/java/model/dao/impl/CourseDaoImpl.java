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

public class CourseDaoImpl extends GenericDaoImpl<Course> implements CourseDao {
    private static final Logger LOGGER = Logger.getLogger(CourseDaoImpl.class);

    private static final String INSERT_COURSE = "INSERT INTO courses(name, amountOfQuestions) VALUES(?, ?);";
//    private static final String SELECT_ALL = "SELECT * FROM courses;";
//    private static final String DELETE_COURSE = "DELETE * FROM courses WHERE id = ?;";

    private static final String SELECT_COURSES_WITH_QUESTIONS = "SELECT courses.name, questions.question FROM courses" +
            " LEFT JOIN questions ON courses.id = questions.courseId;";
    private static final String SELECT_COURSE_WITH_QUESTIONS = "SELECT courses.name, questions.question FROM courses" +
            " LEFT JOIN questions ON courses.id = questions.courseId WHERE course.id = ?;";

    public CourseDaoImpl(Connector connector) {
        super(connector);
    }

    @Override
    public String createQueryToAdd() {
        return INSERT_COURSE;
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


    /*@Override
    public String createQueryToFindAll() {
        return SELECT_ALL;
    }

    @Override
    public String createQueryToDeleteRow() {
        return DELETE_COURSE;
    }*/

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
        List<model.entity.Course> courses = new ArrayList<>();
        try {
            while(rs.next()) {
                courses.add(new Course.Builder()
                        .setCourseName(rs.getString(2))
                        .setAmountOfQuestions(rs.getInt(3))
                        .setUsersPassed(rs.getLong(4))
                        .build());
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException with parsing resultset of question: " + e.getMessage());
            throw new DaoException(e);
        }
        return courses;
    }

    @Override
    public Course parseResultSetToFindById(ResultSet rs) {
        try {
            return new Course.Builder()
                    .setId(rs.getInt(1))
                    .setCourseName(rs.getString(2))
                    .setAmountOfQuestions(rs.getInt(3))
                    .setUsersPassed(rs.getLong(4))
                    .build();
        } catch (SQLException e) {
            LOGGER.error("SQLException with parsing resultset of course: " + e.getMessage());
            throw new DaoException(e);
        }
    }

}
