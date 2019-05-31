package model.dao;

import model.entity.TestInfo;

import java.util.List;

public interface TestInfoDao extends GenericDao<TestInfo> {

    List<TestInfo> findUserTestsForPagination(Long userId, int currentPage, int recordsPerPage);
}
