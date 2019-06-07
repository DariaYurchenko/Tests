package model.service.impl;

import model.dao.TestInfoDao;
import model.dao.factory.DaoFactory;
import model.dao.factory.DbNames;
import model.entity.TestInfo;
import model.service.TestInfoService;
import java.util.List;

public class TestInfoServiceImpl implements TestInfoService {
    private TestInfoDao testInfoDao;

    public TestInfoServiceImpl() {
        this.testInfoDao = DaoFactory.getInstance(DbNames.MYSQL).getTestInfoDao();
    }

    @Override
    public List<TestInfo> findTestsInfoByParameter(String column, Object value) {
        return testInfoDao.findByParameter(column, value);
    }

    @Override
    public List<TestInfo> findUserTestInfoForPagination(Integer userId, int currentPage, int recordsPerPage) {
        return testInfoDao.findUserTestsForPagination(userId, currentPage, recordsPerPage);
    }

    @Override
    public List<TestInfo> findTestInfoForPagination(int currentPage, int recordsPerPage) {
        return testInfoDao.findForPagination(currentPage, recordsPerPage);
    }
}
