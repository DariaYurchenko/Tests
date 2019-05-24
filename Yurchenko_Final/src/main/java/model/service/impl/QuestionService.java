package model.service.impl;

import exception.ServiceException;
import model.dao.QuestionDao;
import model.dao.connector.Connector;
import model.dao.impl.QuestionDaoImpl;
import model.entity.Question;
import java.util.List;
import java.util.Map;

public class QuestionService {
    private QuestionDao questionDao;
    private Connector connector = new Connector();

    public QuestionService() {
        this.questionDao = new QuestionDaoImpl(connector);
    }

    public List<Question> findAll() {
        return questionDao.findAll();
    }

    public void addQuestion(Question question) {
        questionDao.create(question);
    }

    public void deleteById(Long id) {
        questionDao.deleteById(id);
    }

    public void deleteAll() {
        questionDao.deleteAll();
    }

    public List<Question> findByParameter(String column, Object value) {
        return questionDao.findByParameter(column, value);
    }

    public void update(String column, Object value, Long id) {
        questionDao.update(column, value, id);
    }

    public Question findById(Long id) throws ServiceException {
        return questionDao.findById(id).orElseThrow(ServiceException::new);
    }

    public List<Question> findQuestionsByTheme(Long id) {
        return questionDao.findThemeQuestions(id);
    }

    public void setAnswers(Long id, Integer plusRightAnswers, Integer plusAllAnswers) {
        Map<String, Integer> startAnswers = questionDao.getCurrentAmountOfAnswers(id);

        Integer newRightAnswers = changeAnswers(startAnswers.get("rightAnswers"), plusRightAnswers);
        Integer newAllAnswers = changeAnswers(startAnswers.get("answers"), plusAllAnswers);

        questionDao.changeAmountOfAnswers(id, newRightAnswers, newAllAnswers);
    }

    private Integer changeAnswers(Integer startAnswersAmount, Integer plusAnswersAmount) {
        return startAnswersAmount + plusAnswersAmount;
    }


}
