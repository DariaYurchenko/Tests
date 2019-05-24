package model.dao.impl;

import exception.DaoException;
import model.dao.QuestionDao;
import model.dao.connector.Connector;
import model.entity.Question;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDaoImpl extends GenericDaoImpl<Question> implements QuestionDao {
    private static final Logger LOGGER = Logger.getLogger(QuestionDaoImpl.class);

    private static final String INSERT_QUESTION = "INSERT INTO questions(courseId, percentOfRightAnswers, question, incorrectOption1, " +
            "incorrectOption2, incorrectOption3, correctOption) VALUES(?, ?, ?, ?, ?, ?, ?);";
    //private static final String SELECT_ALL = "SELECT * FROM questions;";
    //private static final String DELETE_QUESTION = "DELETE * FROM questions WHERE id = ?;";

    //TODO:!!!
//    private static final String SELECT_QUESTIONS_OF_COURSE = "SELECT course.courseName, question.question, " +
//            "question.incorrectOption1, question.incorrectOption2, question.incorrectOption3, question.correctOption " +
//            " FROM questions JOIN courses WHERE course.id = ?;";

    public QuestionDaoImpl(Connector connector) {
        super(connector);
    }


    @Override
    public String createQueryToAdd() {
        return INSERT_QUESTION;
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
    public void prepareStatementToAdd(PreparedStatement ps, model.entity.Question question) {
        try {
            ps.setLong(1, question.getCourseId());
            ps.setDouble(2, question.getPercentOfRightAnswers());
            ps.setString(3, question.getQuestion());
            ps.setString(4, question.getIncorrectOption1());
            ps.setString(5, question.getIncorrectOption2());
            ps.setString(6, question.getIncorrectOption3());
            ps.setString(7, question.getCorrectOption());
        } catch (SQLException e) {
            LOGGER.error("SQLException with preparing statement for creating question: " + e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public List<Question> parseResultSet(ResultSet rs) {
        List<Question> questions = new ArrayList<>();
        try {
            while(rs.next()) {
                questions.add(( new Question.Builder()
                        .setId(rs.getInt(1))
                        .setCourseId(rs.getInt(2))
                        .setPercentOfRightAnswers(rs.getDouble(3))
                        .setQuestion(rs.getString(4))
                        .setInCorrectOption1(rs.getString(5))
                        .setInCorrectOption2(rs.getString(6))
                        .setInCorrectOption3(rs.getString(7))
                        .setCorrectOption(rs.getString(8))
                        .build()));
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException with parsing resultset of question: " + e.getMessage());
            throw new DaoException(e);
        }
        return questions;
    }

    @Override
    public Question parseResultSetToFindById(ResultSet rs) {
        try {
            return new Question.Builder()
                    .setId(rs.getInt(1))
                    .setCourseId(rs.getInt(2))
                    .setPercentOfRightAnswers(rs.getDouble(3))
                    .setQuestion(rs.getString(4))
                    .setInCorrectOption1(rs.getString(5))
                    .setInCorrectOption2(rs.getString(6))
                    .setInCorrectOption3(rs.getString(7))
                    .setCorrectOption(rs.getString(8))
                    .build();
        } catch (SQLException e) {
            LOGGER.error("SQLException with parsing resultset of user: " + e.getMessage());
            throw new DaoException(e);
        }
    }

    /*@Override
    public String createQueryToDeleteRow() {
        return DELETE_QUESTION;
    }*/

}
