package model.service.impl;

import model.dao.TestDao;
import model.dao.factory.DaoFactory;
import model.dao.factory.DbNames;
import model.entity.Test;
import model.service.TestService;
import java.util.List;
import java.util.Optional;

public class TestServiceImpl implements TestService {
    private TestDao testDao;

    public TestServiceImpl() {
        this.testDao = DaoFactory.getDAOFactory(DbNames.MYSQL).getTestDao();
    }

    @Override
    public void addTest(Test test) {
        testDao.add(test);
    }

    @Override
    public List<Test> findAllTests() {
        return testDao.findAll();
    }

    @Override
    public Optional<Test> findTestById(Integer testId) {
        return testDao.findUserById(testId);
    }

    @Override
    public void deleteTestById(Integer testId) {
        testDao.deleteById(testId);
    }

    @Override
    public void deleteAllTests() {
        testDao.deleteAll();
    }

    @Override
    public List<Test> findTestsByParameter(String column, Object value) {
        return testDao.findByParameter(column, value);
    }

}
