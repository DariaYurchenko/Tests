package model.service;

import model.entity.TestInfo;
import java.util.List;

public interface TestInfoService {

    List<TestInfo> findTestsInfoByParameter(String column, Object value);

    List<TestInfo> findUserTestInfoForPagination(Long userId, int currentPage, int recordsPerPage);

    List<TestInfo> findTestInfoForPagination(int currentPage, int recordsPerPage);
}
