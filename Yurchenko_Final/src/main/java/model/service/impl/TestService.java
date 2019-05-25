package model.service.impl;

import exception.ServiceException;
import model.dao.TestDao;
import model.dao.connector.Connector;
import model.dao.impl.TestDaoImpl;
import model.entity.Test;
import java.util.List;

public class TestService {
    private TestDao testDao;
    private Connector connector = new Connector();

    public TestService() {
        this.testDao = new TestDaoImpl(connector);
    }

    public void addTestToDatabase(Test test) {
        testDao.create(test);
    }

    public List<Test> findAllTests() {
        return testDao.findAll();
    }

    public Test findTestById(Long id) {
        return testDao.findById(id).orElseThrow(ServiceException::new);
    }

    public void deleteTestById(Long id) {
        testDao.deleteById(id);
    }

    public void deleteAllTests() {
        testDao.deleteAll();
    }

    public List<Test> findTestsByParameter(String column, Object value) {
        return testDao.findByParameter(column, value);
    }

}
