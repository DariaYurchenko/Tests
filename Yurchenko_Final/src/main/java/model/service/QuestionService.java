package model.service;

import model.entity.Question;
import java.util.List;
import java.util.Optional;

public interface QuestionService {

    List<Question> findAllQuestions();

    void addQuestion(Question question);

    void deleteQuestionById(Integer questionId);

    void deleteAllQuestions();

    List<Question> findByQuestionParameter(String column, Object value);

    void updateQuestion(String column, Object value, Integer questionId);

    Optional<Question> findQuestionById(Integer questionId);

    List<Question> findQuestionsByTheme(Integer questionId);

    void setAnswers(Integer questionId, int plusRightAnswers, int plusAllAnswers);

    int setQuestionPoints(Question question);

    List<Question> findQuestionsForPagination(int startRecord, int recordsPerPage);

    List<Question> findThemeQuestionsForPagination(int startRecord, int recordsPerPage, Integer themeId);

    Optional<Question> findTranslatedQuestion(Integer themeId, Integer questionId);
}
