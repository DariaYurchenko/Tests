package model.dao.impl;

import exception.DaoException;
import model.dao.QuestionDao;
import model.dao.connector.Connector;
import model.entity.Question;
import model.entity.entityenum.QuestionType;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class QuestionDaoImpl extends GenericDaoImpl<Question> implements QuestionDao {
    private static final Logger LOGGER = Logger.getLogger(QuestionDaoImpl.class);

    public QuestionDaoImpl(Connector connector) {
        super(connector);
    }

    @Override
    public String createQueryToAdd() {
        return INSERT_QUESTION;
    }

    @Override
    public String createQueryToFindById() {
        return FIND_QUESTION_BY_ID;
    }

    @Override
    public String createQueryToDelete() {
        return DELETE_QUESTION;
    }

    @Override
    public String createQueryToDeleteAll() {
        return DELETE_ALL_QUESTIONS;
    }

    @Override
    public String createQueryToFindAll() {
        return SELECT_ALL_QUESTIONS;
    }

    @Override
    public String createQueryToFindByParameter(String column) {
        return String.format(FIND_QUESTION_BY_PARAMETER, column);
    }

    @Override
    public String createQueryToUpdate(String column) {
        return  String.format(UPDATE_QUESTION, column);
    }

    @Override
    public void prepareStatementToAdd(PreparedStatement ps, Question question) {
        try {
            ps.setInt(1, mapTypeToTable(question));
            ps.setLong(2, question.getCourseId());
            ps.setString(3, question.getQuestion());
            ps.setString(4, question.getIncorrectOption1());
            ps.setString(5, question.getIncorrectOption2());
            ps.setString(6, question.getIncorrectOption3());
            ps.setString(7, question.getCorrectOption1());
            ps.setString(8, question.getCorrectOption2());
            ps.setString(9, question.getCorrectOption3());
        } catch (SQLException e) {
            LOGGER.error("SQLException with preparing statement for creating question: " + e.getMessage());
            throw new DaoException(e);
        }
    }

    //TODO: find out how to do that
    //TODO: admin can choose type of question to add by checkbox
    private int mapTypeToTable(Question question) {
        if (question.getQuestionType().getType().equalsIgnoreCase("Radio")) {
            return 1;
        }
        else if (question.getQuestionType().getType().equalsIgnoreCase("Checkbox")) {
            return 2;
        }
        else {
            return 3;
        }
    }

    @Override
    public List<Question> parseResultSet(ResultSet rs) {
        List<Question> questions = new ArrayList<>();
        try {
            while(rs.next()) {
                questions.add(( new Question.Builder()
                        .withQuestionType(new QuestionType(rs.getInt(QUESTION_TYPE_ID), rs.getString(QUESTION_TYPE)))
                        .withId(rs.getLong(QUESTION_ID))
                        .withCourseId(rs.getLong(COURSE_ID))
                        .withPercentOfRightAnswers(setPercentOfRightAnswers(rs))
                        .withQuestion(rs.getString(QUESTION))
                        .withIncorrectOption1(rs.getString(INCORRECT_OPTION_1))
                        .withIncorrectOption2(rs.getString(INCORRECT_OPTION_2))
                        .withIncorrectOption3(rs.getString(INCORRECT_OPTION_3))
                        .withCorrectOption1(rs.getString(CORRECT_OPTION_1))
                        .withCorrectOption2(rs.getString(CORRECT_OPTION_2))
                        .withCorrectOption3(rs.getString(CORRECT_OPTION_3))
                        .build()));
            }
            return questions;
        } catch (SQLException e) {
            LOGGER.error("SQLException with parsing resultset of question: " + e.getMessage());
            throw new DaoException(e);
        }
    }

    private Double setPercentOfRightAnswers(ResultSet rs) {
        try {
            int rightAnswers = rs.getInt(RIGHT_ANSWERS);
            int answers = rs.getInt(ANSWERS);
            return Math.round((rightAnswers * 100.0 / answers) * 100.0) / 100.0;
        } catch (SQLException e) {
            LOGGER.error("SQLException with counting of percent of right answers: " + e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Question> parseResultSetToFindById(ResultSet rs) {
        try {
            return Optional.ofNullable(new Question.Builder()
                    .withQuestionType(new QuestionType(rs.getInt(QUESTION_TYPE_ID), rs.getString(QUESTION_TYPE)))
                    .withId(rs.getLong(QUESTION_ID))
                    .withCourseId(rs.getLong(COURSE_ID))
                    .withPercentOfRightAnswers(setPercentOfRightAnswers(rs))
                    .withQuestion(rs.getString(QUESTION))
                    .withIncorrectOption1(rs.getString(INCORRECT_OPTION_1))
                    .withIncorrectOption2(rs.getString(INCORRECT_OPTION_2))
                    .withIncorrectOption3(rs.getString(INCORRECT_OPTION_3))
                    .withCorrectOption1(rs.getString(CORRECT_OPTION_1))
                    .withCorrectOption2(rs.getString(CORRECT_OPTION_2))
                    .withCorrectOption3(rs.getString(CORRECT_OPTION_3))
                    .build());
        } catch (SQLException e) {
            LOGGER.error("SQLException with parsing resultset of question while finding by id: " + e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public Map<String, Integer> getCurrentAmountOfAnswers(Long id) {
        Map<String, Integer> answers = new HashMap<>();
        int rightAnswers = 0;
        int allAnswers = 0;
        try (PreparedStatement ps = connector.getConnection().prepareStatement(FIND_QUESTION_BY_ID)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                rightAnswers = rs.getInt(RIGHT_ANSWERS);
                allAnswers = rs.getInt(ANSWERS);
            }
            answers.put("rightAnswers", rightAnswers);
            answers.put("answers", allAnswers);
            return answers;
        } catch (SQLException e) {
            LOGGER.warn("SQLException with getting answers: " + e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public void changeAmountOfAnswers(Long id, Integer plusRightAnswers, Integer plusAnswers) {
        try (PreparedStatement ps = connector.getConnection().prepareStatement(UPDATE_QUESTION_ANSWERS)) {
            ps.setInt(1, plusRightAnswers);
            ps.setInt(2, plusAnswers);
            ps.setLong(3, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn("SQLException with updating question's answers: " + e.getMessage());
            throw new DaoException(e);
        }

    }

    @Override
    public List<Question> getCourseQuestions(Long id) {
        List<Question> courseQuestions = new ArrayList<>();
        try (PreparedStatement ps = connector.getConnection().prepareStatement(FIND_QUESTIONS_OF_COURSE)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                courseQuestions.add(( new Question.Builder()
                        .withQuestionType(new QuestionType(rs.getInt(QUESTION_TYPE_ID), rs.getString(QUESTION_TYPE)))
                        .withId(rs.getLong(QUESTION_ID))
                        .withCourseId(rs.getLong(COURSE_ID))
                        .withPercentOfRightAnswers(setPercentOfRightAnswers(rs))
                        .withQuestion(rs.getString(QUESTION))
                        .withIncorrectOption1(rs.getString(INCORRECT_OPTION_1))
                        .withIncorrectOption2(rs.getString(INCORRECT_OPTION_2))
                        .withIncorrectOption3(rs.getString(INCORRECT_OPTION_3))
                        .withCorrectOption1(rs.getString(CORRECT_OPTION_1))
                        .withCorrectOption2(rs.getString(CORRECT_OPTION_2))
                        .withCorrectOption3(rs.getString(CORRECT_OPTION_3))
                        .build()));
            }
            return courseQuestions;

        } catch (SQLException e) {
            LOGGER.warn("SQLException with getting questions of the course: " + e.getMessage());
            throw new DaoException(e);
        }
    }
}
