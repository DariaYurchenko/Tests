package model.service.impl;

import model.dao.TestInfoDao;
import model.dao.connector.Connector;
import model.dao.factory.DaoFactory;
import model.dao.factory.DbNames;
import model.dao.impl.TestInfoDaoImpl;
import model.entity.TestInfo;
import model.service.TestInfoService;

import java.util.List;

public class TestInfoServiceImpl implements TestInfoService {
    private TestInfoDao testInfoDao;

    public TestInfoServiceImpl() {
        this.testInfoDao = DaoFactory.getDAOFactory(DbNames.MYSQL).getTestInfoDao();
    }

    @Override
    public List<TestInfo> findTestsByParameter(String column, Object value) {
        return testInfoDao.findByParameter(column, value);
    }

    //TODO: similar methods
    @Override
    public List<TestInfo> findUserTestsForPagination(Long userId, int currentPage, int recordsPerPage) {
        return testInfoDao.findUserTestsForPagination(userId, currentPage, recordsPerPage);
    }

    @Override
    public List<TestInfo> findTestsForPagination(int currentPage, int recordsPerPage) {
        return testInfoDao.findForPagination(currentPage, recordsPerPage);
    }
}
