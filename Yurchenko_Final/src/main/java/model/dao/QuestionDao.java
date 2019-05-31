package model.dao;

import model.entity.Question;

import java.util.List;
import java.util.Map;

public interface QuestionDao extends GenericDao<Question> {

    Map<String, Integer> getCurrentAnswersOfQuestionFromDb(Long id);

    void changeAmountOfAnswersInDb(Long id, Integer plusPoints, Integer plusMaxPoints);

    List<Question> findThemeQuestions(Long id);

    List<Question> findQuestionsOfThemeForPagination(int startRecord, int recordsPerPage, Long id);

}
