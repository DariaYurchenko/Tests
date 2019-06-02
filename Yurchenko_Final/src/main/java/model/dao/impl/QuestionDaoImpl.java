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

    private static final String INSERT_QUESTION = "INSERT INTO questions(question_type, question_theme_id, question, incorrect_option1, " +
            "incorrect_option2, incorrect_option3, correct_option1, correct_option2, correct_option3) " +
            "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String DELETE_QUESTION = "DELETE FROM questions WHERE question_id = ?;";
    private static final String DELETE_ALL_QUESTIONS = "DELETE FROM questions;";
    private static final String SELECT_ALL_QUESTIONS = "SELECT * FROM questions q JOIN question_type qt" +
            " ON q.question_type=qt.question_type_id JOIN themes t ON q.question_theme_id=t.theme_id;";
    private static final String FIND_QUESTION_BY_ID = "SELECT * FROM questions q JOIN question_type qt" +
            " ON q.question_type=qt.question_type_id JOIN themes t ON q.question_theme_id=t.theme_id " +
            "WHERE question_id = ?;";
    private static final String UPDATE_QUESTION_ANSWERS = "UPDATE questions SET right_answers = ?, " +
            "answers = ? WHERE question_id = ?;";
    private static final String FIND_QUESTIONS_OF_THEME = "SELECT * FROM questions q JOIN question_type qt" +
            " ON q.question_type=qt.question_type_id JOIN themes t ON q.question_theme_id=t.theme_id WHERE t.theme_id = ?";
    private static final String FIND_QUESTION_BY_PARAMETER = "SELECT * FROM questions q JOIN question_type qt" +
            " ON q.question_type=qt.question_type_id JOIN themes t ON q.question_theme_id=t.theme_id WHERE %s = ?;";
    private static final String UPDATE_QUESTION = "UPDATE questions SET %s = ? WHERE question_id = ?;";
    private static final String FIND_QUESTIONS_FOR_PAGINATION = "SELECT * FROM questions q JOIN question_type qt" +
            " ON q.question_type=qt.question_type_id JOIN themes t ON q.question_theme_id=t.theme_id LIMIT ?, ?;";
    private static final String FIND_QUESTIONS_FOR_PAGINATION_ID = "SELECT * FROM questions q JOIN question_type qt" +
            " ON q.question_type=qt.question_type_id JOIN themes t ON q.question_theme_id=t.theme_id WHERE theme_id = ? LIMIT ?, ?;";

    private static final String QUESTION_ID = "question_id";
    private static final String THEME_ID = "theme_id";
    private static final String RIGHT_ANSWERS = "right_answers";
    private static final String ANSWERS = "answers";
    private static final String QUESTION = "question";
    private static final String INCORRECT_OPTION_1 = "incorrect_option1";
    private static final String INCORRECT_OPTION_2 = "incorrect_option2";
    private static final String INCORRECT_OPTION_3 = "incorrect_option3";
    private static final String CORRECT_OPTION_1 = "correct_option1";
    private static final String CORRECT_OPTION_2 = "correct_option2";
    private static final String CORRECT_OPTION_3 = "correct_option3";
    private static final String QUESTION_TYPE_ID = "question_type_id";
    private static final String QUESTION_TYPE = "type";
    private static final String THEME_NAME = "theme_name";

    //Todo:static
    private Map<String, Integer> questionTypeMap = new HashMap<>();
    private Map<String, Integer> questionThemeMap = new HashMap<>();

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
        questionTypeMap.put("Radio", 1);
        questionTypeMap.put("Checkbox", 2);
        questionTypeMap.put("Text", 3);
        return questionTypeMap.get(question.getQuestionType().getType().toLowerCase());
    }

    private long mapThemeToTable(Question question) {
        questionTypeMap.put("collections", 1);
        questionTypeMap.put("if else, switch and loops", 2);
        questionTypeMap.put("inheritance and polymorphism", 3);
        questionTypeMap.put("threads, concurrency", 4);
        questionTypeMap.put("operators", 5);
        questionTypeMap.put("primitive types conversions", 6);
        return questionThemeMap.get(question.getTheme().getThemeName().toLowerCase());
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


