package model.dao.impl;

import exception.DaoException;
import model.dao.QuestionDao;
import model.dao.connector.Connector;
import model.entity.Question;
import model.entity.Theme;
import model.entity.QuestionType;
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
    public String createQueryToSave() {
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
    public String createQueryToPagination() {
        return FIND_QUESTIONS_FOR_PAGINATION;
    };

    @Override
    public String createQueryToFindByParameter(String column) {
        return String.format(FIND_QUESTION_BY_PARAMETER, column);
    }

    @Override
    public String createQueryToUpdate(String column) {
        return String.format(UPDATE_QUESTION, column);
    }

    @Override
    public void prepareStatementToSave(PreparedStatement ps, Question question) {
        try {
            ps.setInt(1, mapTypeToTable(question));
            ps.setLong(2, mapThemeToTable(question));
            ps.setString(3, question.getQuestion());
            ps.setString(4, question.getIncorrectOption1());
            ps.setString(5, question.getIncorrectOption2());
            ps.setString(6, question.getIncorrectOption3());
            ps.setString(7, question.getCorrectOption1());
            ps.setString(8, question.getCorrectOption2());
            ps.setString(9, question.getCorrectOption3());
        } catch (SQLException e) {
            LOGGER.error("SQLException with preparing statement for adding question: " + e.getMessage());
            throw new DaoException(e);
        }
    }

    private int mapTypeToTable(Question question) {
        if (question.getQuestionType().getType().equalsIgnoreCase("Radio")) {
            return 1;
        } else if (question.getQuestionType().getType().equalsIgnoreCase("Checkbox")) {
            return 2;
        } else {
            return 3;
        }
    }

    private long mapThemeToTable(Question question) {
        if (question.getTheme().getThemeName().equalsIgnoreCase("collections")) {
            return 1;
        } else if (question.getTheme().getThemeName().equalsIgnoreCase("if else, switch and loops")) {
            return 2;
        } else if (question.getTheme().getThemeName().equalsIgnoreCase("inheritance and polymorphism")) {
            return 3;
        } else if (question.getTheme().getThemeName().equalsIgnoreCase("threads, concurrency")) {
            return 4;
        } else if (question.getTheme().getThemeName().equalsIgnoreCase("operators")) {
            return 5;
        } else {
            return 6;
        }
    }

    @Override
    public List<Question> parseResultSet(ResultSet rs) {
        List<Question> questions = new ArrayList<>();
        try {
            while (rs.next()) {
                questions.add(buildQuestion(rs));
            }
            return questions;
        } catch (SQLException e) {
            LOGGER.error("SQLException with parsing resultset of question: " + e.getMessage());
            throw new DaoException(e);
        }
    }

    private Question buildQuestion(ResultSet rs) throws SQLException {
        return new Question.Builder()
                .withQuestionType(new QuestionType(rs.getInt(QUESTION_TYPE_ID), rs.getString(QUESTION_TYPE)))
                .withId(rs.getLong(QUESTION_ID))
                .withTheme(new Theme(rs.getLong(THEME_ID), rs.getString(THEME_NAME)))
                .withPercentOfRightAnswers(setPercentOfRightAnswers(rs))
                .withQuestion(rs.getString(QUESTION))
                .withIncorrectOption1(rs.getString(INCORRECT_OPTION_1))
                .withIncorrectOption2(rs.getString(INCORRECT_OPTION_2))
                .withIncorrectOption3(rs.getString(INCORRECT_OPTION_3))
                .withCorrectOption1(rs.getString(CORRECT_OPTION_1))
                .withCorrectOption2(rs.getString(CORRECT_OPTION_2))
                .withCorrectOption3(rs.getString(CORRECT_OPTION_3))
                .build();
    }

    private Double setPercentOfRightAnswers(ResultSet rs) {
        try {
            int rightAnswers = rs.getInt(RIGHT_ANSWERS);
            int allAnswers = rs.getInt(ANSWERS);
            return countPercentOfRightAnswers(rightAnswers, allAnswers);
        } catch (SQLException e) {
            LOGGER.error("SQLException with counting of percent of right answers: " + e.getMessage());
            throw new DaoException(e);
        }
    }

    private Double countPercentOfRightAnswers(int rightAnswers, int allAnswers) {
        return Math.round((rightAnswers * 1.0 / allAnswers) * 100) / 1.0;
    }

    @Override
    public Optional<Question> parseResultSetToFindById(ResultSet rs) {
        try {
            return Optional.ofNullable(buildQuestion(rs));
        } catch (SQLException e) {
            LOGGER.error("SQLException with parsing resultset of question while finding by id: " + e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public Map<String, Integer> getCurrentAnswersOfQuestionFromDb(Long questionId) {
        Map<String, Integer> answers = new HashMap<>();
        int rightAnswers = 0;
        int allAnswers = 0;
        try (PreparedStatement ps = connector.getConnection().prepareStatement(FIND_QUESTION_BY_ID)) {
            ps.setLong(1, questionId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                rightAnswers = rs.getInt(RIGHT_ANSWERS);
                allAnswers = rs.getInt(ANSWERS);
            }
            answers.put("rightAnswers", rightAnswers);
            answers.put("AllAnswers", allAnswers);
            return answers;
        } catch (SQLException e) {
            LOGGER.warn("SQLException with getting answers: " + e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public void changeAmountOfAnswersInDb(Long questionId, Integer plusRightAnswers, Integer plusAnswers) {
        try (PreparedStatement ps = connector.getConnection().prepareStatement(UPDATE_QUESTION_ANSWERS)) {
            ps.setInt(1, plusRightAnswers);
            ps.setInt(2, plusAnswers);
            ps.setLong(3, questionId);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn("SQLException with updating question's answers: " + e.getMessage());
            throw new DaoException(e);
        }

    }

    @Override
    public List<Question> findThemeQuestions(Long themeId) {
        List<Question> themeQuestions = new ArrayList<>();
        try (PreparedStatement ps = connector.getConnection().prepareStatement(FIND_QUESTIONS_OF_THEME)) {
            ps.setLong(1, themeId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                themeQuestions.add(buildQuestion(rs));
            }
            return themeQuestions;
        } catch (SQLException e) {
            LOGGER.warn("SQLException with getting questions of the course: " + e.getMessage());
            throw new DaoException(e);
        }
    }

    /*@Override
    public List<Question> findQuestionsForPagination(int startRecord, int recordsPerPage) {
        List<Question> questions = new ArrayList<>();
        try (PreparedStatement preparedStatement = connector.getConnection().prepareStatement(FIND_QUESTIONS_FOR_PAGINATION)) {
            preparedStatement.setInt(1, startRecord);
            preparedStatement.setInt(2, recordsPerPage);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                questions.add((new Question.Builder()
                        .withQuestionType(new QuestionType(rs.getInt(QUESTION_TYPE_ID), rs.getString(QUESTION_TYPE)))
                        .withTheme(new Theme(rs.getLong(THEME_ID), rs.getString(THEME_NAME)))
                        .withId(rs.getLong(QUESTION_ID))
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
            LOGGER.warn("SQLException with finding for pagination: " + e.getMessage());
            throw new DaoException(e);
        }
    }*/

    @Override
    public List<Question> findQuestionsOfThemeForPagination(int startRecord, int recordsPerPage, Long themeId) {
        List<Question> questions = new ArrayList<>();
        try (PreparedStatement preparedStatement = connector.getConnection().prepareStatement(FIND_QUESTIONS_FOR_PAGINATION_ID)) {
            preparedStatement.setLong(1, themeId);
            preparedStatement.setInt(2, startRecord);
            preparedStatement.setInt(3, recordsPerPage);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                questions.add(buildQuestion(rs));
            }
            return questions;
        } catch (SQLException e) {
            LOGGER.warn("SQLException with finding for pagination: " + e.getMessage());
            throw new DaoException(e);
        }
    }


}


