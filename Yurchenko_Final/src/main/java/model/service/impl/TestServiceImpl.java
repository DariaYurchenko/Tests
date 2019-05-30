package model.service.impl;

import exception.ServiceException;
import model.dao.TestDao;
import model.dao.connector.Connector;
import model.dao.impl.TestDaoImpl;
import model.entity.Test;
import model.service.TestService;

import java.util.List;

public class TestServiceImpl implements TestService {
    private TestDao testDao;

    public TestServiceImpl() {
        this.testDao = new TestDaoImpl(new Connector());
    }

    @Override
    public void addTestToDatabase(Test test) {
        testDao.add(test);
    }

    @Override
    public List<Test> findAllTests() {
        return testDao.findAll();
    }

    @Override
    public Test findTestById(Long testId) {
        return testDao.findById(testId).orElseThrow(ServiceException::new);
    }

    @Override
    public void deleteTestById(Long testId) {
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
