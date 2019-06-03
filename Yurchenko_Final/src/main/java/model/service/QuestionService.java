package model.service;

import model.entity.Question;
import java.util.List;

public interface QuestionService {

    List<Question> findAll();

    void addQuestion(Question question);

    void deleteById(Long questionId);

    void deleteAll();

    List<Question> findByParameter(String column, Object value);

    void update(String column, Object value, Long questionId);

    Question findById(Long questionId);

    List<Question> findQuestionsByTheme(Long questionId);

    void setAnswers(Long questionId, Integer plusRightAnswers, Integer plusAllAnswers);

    int setQuestionPoints(Question question);

    List<Question> findQuestionsForPagination(int startRecord, int recordsPerPage);

    Double findCurrentAnswers(Long questionId);

    List<Question> findQuestionsForPagination(int startRecord, int recordsPerPage, Long questionId);

    Question getRus(Long themeId, Long questionId);
}
