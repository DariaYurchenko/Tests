package model.dao;

import model.entity.Question;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface QuestionDao extends GenericDao<Question> {

    Map<String, Integer> getCurrentAnswersForQuestionFromDb(Integer questionId);

    void changeAmountOfAnswersInDb(Integer questionId, int plusPoints, int plusMaxPoints);

    List<Question> findThemeQuestions(Integer themeId);

    Optional<Question> findTranslatedThemeQuestion(Integer themeId, Integer questionId);

    List<Question> findQuestionsOfThemeForPagination(int startRecord, int recordsPerPage, Integer themeId);

}
