package model.service.impl;

import model.dao.TestInfoDao;
import model.dao.connector.Connector;
import model.dao.impl.TestInfoDaoImpl;
import model.entity.TestInfo;

import java.util.List;

public class TestInfoService {
    private TestInfoDao testInfoDao;
    private Connector connector = new Connector();

    public TestInfoService() {
        this.testInfoDao = new TestInfoDaoImpl(connector);
    }

    public List<TestInfo> findTestsByParameter(String column, Object value) {
        return testInfoDao.findByParameter(column, value);
    }

    public List<TestInfo> findTestsForPagination(Long userId, int currentPage, int recordsPerPage) {
        return testInfoDao.findTestsForPagination(userId, currentPage, recordsPerPage);
    }
}
