package model.service.impl;

import model.dao.QuestionDao;
import model.dao.factory.DaoFactory;
import model.dao.factory.DbNames;
import model.entity.Question;
import model.service.QuestionService;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class QuestionServiceImpl implements QuestionService {
    private static final int RADIO_POINTS = 1;
    private static final int CHECKBOX_POINTS = 2;
    private static final int TEXT_POINTS = 3;

    private QuestionDao questionDao;

    public QuestionServiceImpl() {
        this.questionDao = DaoFactory.getDAOFactory(DbNames.MYSQL).getQuestionDao();
    }

    @Override
    public List<Question> findAllQuestions() {
        return questionDao.findAll();
    }

    @Override
    public void addQuestion(Question question) {
        questionDao.add(question);
    }

    @Override
    public void deleteQuestionById(Long questionId) {
        questionDao.deleteById(questionId);
    }

    @Override
    public void deleteAllQuestions() {
        questionDao.deleteAll();
    }

    @Override
    public List<Question> findByQuestionParameter(String column, Object value) {
        return questionDao.findByParameter(column, value);
    }

    @Override
    public void updateQuestion(String column, Object value, Long questionId) {
        questionDao.update(column, value, questionId);
    }

    @Override
    public Optional<Question> findQuestionById(Long questionId) {
        return questionDao.findById(questionId);
    }

    @Override
    public List<Question> findQuestionsByTheme(Long themeId) {
        return questionDao.findThemeQuestions(themeId);
    }

    @Override
    public void setAnswers(Long questionId, Integer plusRightAnswers, Integer plusAllAnswers) {
        Map<String, Integer> startAnswers = questionDao.getCurrentAnswersForQuestionFromDb(questionId);

        Integer newRightAnswers = changeQuestionAnswers(startAnswers.get("rightAnswers"), plusRightAnswers);
        Integer newAllAnswers = changeQuestionAnswers(startAnswers.get("AllAnswers"), plusAllAnswers);

        questionDao.changeAmountOfAnswersInDb(questionId, newRightAnswers, newAllAnswers);
    }

    private Integer changeQuestionAnswers(Integer startAnswersAmount, Integer plusAnswersAmount) {
        return startAnswersAmount + plusAnswersAmount;
    }

    /*@Override
    public Double findRightAnswersPercent(Long questionId) {
        Map<String, Integer> answers = questionDao.getCurrentAnswersForQuestionFromDb(questionId);
        int rightAnswers = answers.get("rightAnswers");
        int allAnswers = answers.get("AllAnswers");
        return countPercentOfRightAnswers(rightAnswers, allAnswers);
    }*/

    private Double countPercentOfRightAnswers(int rightAnswers, int allAnswers) {
        return Math.round((rightAnswers * 1.0 / allAnswers) * 100) / 1.0;
    }

    @Override
    public int setQuestionPoints(Question question) {
        if(getQuestionType(question).equalsIgnoreCase("Radio")) {
            return RADIO_POINTS;
        }
        if(getQuestionType(question).equalsIgnoreCase("Checkbox")) {
            return CHECKBOX_POINTS;
        }
        else {
            return TEXT_POINTS;
        }
    }

    private String getQuestionType(Question question) {
        return question.getQuestionType().getType();
    }

    @Override
    public List<Question> findQuestionsForPagination(int startRecord, int recordsPerPage) {
        return questionDao.findForPagination(startRecord, recordsPerPage);
    }

    @Override
    public List<Question> findThemeQuestionsForPagination(int startRecord, int recordsPerPage, Long themeId) {
        return questionDao.findQuestionsOfThemeForPagination(startRecord, recordsPerPage, themeId);
    }

    @Override
    public Optional<Question> findTranslatedQuestion(Long themeId, Long questionId) {
        return questionDao.findTranslatedThemeQuestion(themeId, questionId);
    }

}
