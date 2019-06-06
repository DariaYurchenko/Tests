package model.service;

import model.entity.Question;
import java.util.List;
import java.util.Optional;

public interface QuestionService {

    List<Question> findAllQuestions();

    void addQuestion(Question question);

    void deleteQuestionById(Long questionId);

    void deleteAllQuestions();

    List<Question> findByQuestionParameter(String column, Object value);

    void updateQuestion(String column, Object value, Long questionId);

    Optional<Question> findQuestionById(Long questionId);

    List<Question> findQuestionsByTheme(Long questionId);

    void setAnswers(Long questionId, Integer plusRightAnswers, Integer plusAllAnswers);

    int setQuestionPoints(Question question);

    List<Question> findQuestionsForPagination(int startRecord, int recordsPerPage);

    //Double findRightAnswersPercent(Long questionId);

    List<Question> findThemeQuestionsForPagination(int startRecord, int recordsPerPage, Long themeId);

    Optional<Question> findTranslatedQuestion(Long themeId, Long questionId);
}
