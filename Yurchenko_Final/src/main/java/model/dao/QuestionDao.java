package model.dao;

import model.entity.Question;

import java.util.List;
import java.util.Map;

public interface QuestionDao extends GenericDao<Question> {
    static final String INSERT_QUESTION = "INSERT INTO questions(question_type, question_theme_id, question, incorrect_option1, " +
            "incorrect_option2, incorrect_option3, correct_option1, correct_option2, correct_option3) " +
            "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
    static final String DELETE_QUESTION = "DELETE FROM questions WHERE question_id = ?;";
    static final String DELETE_ALL_QUESTIONS = "DELETE FROM questions;";
    static final String SELECT_ALL_QUESTIONS = "SELECT * FROM questions q JOIN question_type qt" +
            " ON q.question_type=qt.question_type_id JOIN themes t ON q.question_theme_id=t.theme_id;";
    static final String FIND_QUESTION_BY_ID = "SELECT * FROM questions q JOIN question_type qt" +
            " ON q.question_type=qt.question_type_id JOIN themes t ON q.question_theme_id=t.theme_id " +
            "WHERE question_id = ?;";
    static final String UPDATE_QUESTION_ANSWERS = "UPDATE questions SET right_answers = ?, " +
            "answers = ? WHERE question_id = ?;";
    static final String FIND_QUESTIONS_OF_THEME = "SELECT * FROM questions q JOIN question_type qt" +
            " ON q.question_type=qt.question_type_id JOIN themes t ON q.question_theme_id=t.theme_id WHERE t.theme_id = ?";
    static final String FIND_QUESTION_BY_PARAMETER = "SELECT * FROM questions q JOIN question_type qt" +
            " ON q.question_type=qt.question_type_id JOIN themes t ON q.question_theme_id=t.theme_id WHERE %s = ?;";
    static final String UPDATE_QUESTION = "UPDATE questions SET %s = ? WHERE question_id = ?;";
    static final String FIND_QUESTIONS_FOR_PAGINATION = "SELECT * FROM questions q JOIN question_type qt" +
            " ON q.question_type=qt.question_type_id JOIN themes t ON q.question_theme_id=t.theme_id LIMIT ?, ?;";
    static final String FIND_QUESTIONS_FOR_PAGINATION_ID = "SELECT * FROM questions q JOIN question_type qt" +
            " ON q.question_type=qt.question_type_id JOIN themes t ON q.question_theme_id=t.theme_id WHERE theme_id = ? LIMIT ?, ?;";

    static final String QUESTION_ID = "question_id";
    static final String TYPE = "question_type";
    static final String THEME_ID = "theme_id";
    static final String RIGHT_ANSWERS = "right_answers";
    static final String ANSWERS = "answers";
    static final String QUESTION = "question";
    static final String INCORRECT_OPTION_1 = "incorrect_option1";
    static final String INCORRECT_OPTION_2 = "incorrect_option2";
    static final String INCORRECT_OPTION_3 = "incorrect_option3";
    static final String CORRECT_OPTION_1 = "correct_option1";
    static final String CORRECT_OPTION_2 = "correct_option2";
    static final String CORRECT_OPTION_3 = "correct_option3";
    static final String QUESTION_TYPE_ID = "question_type_id";
    static final String QUESTION_TYPE = "type";
    static final String THEME_NAME = "theme_name";

    Map<String, Integer> getCurrentAmountOfAnswers(Long id);

    void changeAmountOfAnswers(Long id, Integer plusPoints, Integer plusMaxPoints);

    List<Question> findThemeQuestions(Long id);

    List<Question> findQuestionsForPagination(int startRecord, int recordsPerPage);

    List<Question> findQuestionsForPaginationId(int startRecord, int recordsPerPage, Long id);

}
