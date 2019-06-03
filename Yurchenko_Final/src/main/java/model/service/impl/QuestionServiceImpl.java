package model.service.impl;

import exception.ServiceException;
import model.dao.QuestionDao;
import model.dao.connector.Connector;
import model.dao.factory.DaoFactory;
import model.dao.factory.DbNames;
import model.dao.impl.QuestionDaoImpl;
import model.entity.Question;
import model.service.QuestionService;

import java.util.List;
import java.util.Map;
//TODO: rewrite OPTIONAL. in tests also
public class QuestionServiceImpl implements QuestionService {
    private static final int RADIO_POINTS = 1;
    private static final int CHECKBOX_POINTS = 2;
    private static final int TEXT_POINTS = 3;

    private QuestionDao questionDao;

    public QuestionServiceImpl() {
        this.questionDao = DaoFactory.getDAOFactory(DbNames.MYSQL).getQuestionDao();
    }

    @Override
    public List<Question> findAll() {
        return questionDao.findAll();
    }

    @Override
    public void addQuestion(Question question) {
        questionDao.add(question);
    }

    @Override
    public void deleteById(Long questionId) {
        questionDao.deleteById(questionId);
    }

    @Override
    public void deleteAll() {
        questionDao.deleteAll();
    }

    @Override
    public List<Question> findByParameter(String column, Object value) {
        return questionDao.findByParameter(column, value);
    }

    @Override
    public void update(String column, Object value, Long questionId) {
        questionDao.update(column, value, questionId);
    }

    @Override
    public Question findById(Long questionId) throws ServiceException {
        return questionDao.findById(questionId).orElseThrow(ServiceException::new);
    }

    @Override
    public List<Question> findQuestionsByTheme(Long themeId) {
        return questionDao.findThemeQuestions(themeId);
    }

    @Override
    public void setAnswers(Long questionId, Integer plusRightAnswers, Integer plusAllAnswers) {
        Map<String, Integer> startAnswers = questionDao.getCurrentAnswersOfQuestionFromDb(questionId);

        Integer newRightAnswers = changeAnswers(startAnswers.get("rightAnswers"), plusRightAnswers);
        Integer newAllAnswers = changeAnswers(startAnswers.get("AllAnswers"), plusAllAnswers);

        questionDao.changeAmountOfAnswersInDb(questionId, newRightAnswers, newAllAnswers);
    }

    private Integer changeAnswers(Integer startAnswersAmount, Integer plusAnswersAmount) {
        return startAnswersAmount + plusAnswersAmount;
    }

    @Override
    public Double findCurrentAnswers(Long questionId) {
        Map<String, Integer> answers = questionDao.getCurrentAnswersOfQuestionFromDb(questionId);
        int rightAnswers = answers.get("rightAnswers");
        int allAnswers = answers.get("AllAnswers");
        return countPercentOfRightAnswers(rightAnswers, allAnswers);
    }

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

    //TODO: the same methods
    @Override
    public List<Question> findQuestionsForPagination(int startRecord, int recordsPerPage) {
        return questionDao.findForPagination(startRecord, recordsPerPage);
    }

    @Override
    public List<Question> findQuestionsForPagination(int startRecord, int recordsPerPage, Long questionId) {
        return questionDao.findQuestionsOfThemeForPagination(startRecord, recordsPerPage, questionId);
    }

    @Override
    public Question getRus(Long themeId, Long questionId) {
        return questionDao.findThemeQuestionsRus(themeId, questionId).orElseThrow(ServiceException::new);
    }


}
