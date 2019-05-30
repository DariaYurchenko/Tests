package model.service.impl;

import model.dao.TestInfoDao;
import model.dao.connector.Connector;
import model.dao.impl.TestInfoDaoImpl;
import model.entity.TestInfo;
import model.service.TestInfoService;

import java.util.List;

public class TestInfoServiceImpl implements TestInfoService {
    private TestInfoDao testInfoDao;

    public TestInfoServiceImpl() {
        this.testInfoDao = new TestInfoDaoImpl(new Connector());
    }

    @Override
    public List<TestInfo> findTestsByParameter(String column, Object value) {
        return testInfoDao.findByParameter(column, value);
    }

    @Override
    public List<TestInfo> findUserTestsForPagination(Long userId, int currentPage, int recordsPerPage) {
        return testInfoDao.findUserTestsForPagination(userId, currentPage, recordsPerPage);
    }

    @Override
    public List<TestInfo> findTestsForPagination(int currentPage, int recordsPerPage) {
        return testInfoDao.findForPagination(currentPage, recordsPerPage);
    }
}
