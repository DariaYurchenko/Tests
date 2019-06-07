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
    private static final String RADIO = "Radio";
    private static final String CHECKBOX = "Checkbox";

    private QuestionDao questionDao;

    public QuestionServiceImpl() {
        this.questionDao = DaoFactory.getInstance(DbNames.MYSQL).getQuestionDao();
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
    public void deleteQuestionById(Integer questionId) {
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
    public void updateQuestion(String column, Object value, Integer questionId) {
        questionDao.update(column, value, questionId);
    }

    @Override
    public Optional<Question> findQuestionById(Integer questionId) {
        return questionDao.findUserById(questionId);
    }

    @Override
    public List<Question> findQuestionsByTheme(Integer themeId) {
        return questionDao.findThemeQuestions(themeId);
    }

    @Override
    public void setAnswers(Integer questionId, int plusRightAnswers, int plusAllAnswers) {
        Map<String, Integer> startAnswers = questionDao.getCurrentAnswersForQuestionFromDb(questionId);

        int newRightAnswers = changeQuestionAnswers(startAnswers.get("rightAnswers"), plusRightAnswers);
        int newAllAnswers = changeQuestionAnswers(startAnswers.get("AllAnswers"), plusAllAnswers);

        questionDao.changeAmountOfAnswersInDb(questionId, newRightAnswers, newAllAnswers);
    }

    private int changeQuestionAnswers(Integer startAnswersAmount, Integer plusAnswersAmount) {
        return startAnswersAmount + plusAnswersAmount;
    }

    @Override
    public int setQuestionPoints(Question question) {
        if(RADIO.equalsIgnoreCase(getQuestionType(question))) {
            return RADIO_POINTS;
        }
        if(CHECKBOX.equalsIgnoreCase(getQuestionType(question))) {
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
    public List<Question> findThemeQuestionsForPagination(int startRecord, int recordsPerPage, Integer themeId) {
        return questionDao.findQuestionsOfThemeForPagination(startRecord, recordsPerPage, themeId);
    }

    @Override
    public Optional<Question> findTranslatedQuestion(Integer themeId, Integer questionId) {
        return questionDao.findTranslatedThemeQuestion(themeId, questionId);
    }

}
