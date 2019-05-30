package model.service;

import model.entity.Question;
import java.util.List;

public interface QuestionService {
    int RADIO_POINTS = 1;
    int CHECKBOX_POINTS = 2;
    int TEXT_POINTS = 3;

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
}
