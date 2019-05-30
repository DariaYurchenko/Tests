package model.service;

import model.entity.TestInfo;
import java.util.List;

public interface TestInfoService {

    List<TestInfo> findTestsByParameter(String column, Object value);

    List<TestInfo> findUserTestsForPagination(Long userId, int currentPage, int recordsPerPage);

    List<TestInfo> findTestsForPagination(int currentPage, int recordsPerPage);
}
