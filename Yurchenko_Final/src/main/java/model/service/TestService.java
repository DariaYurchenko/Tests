package model.service;

import model.entity.Test;
import java.util.List;
import java.util.Optional;

public interface TestService {

    void addTest(Test test);

    List<Test> findAllTests();

    Optional<Test> findTestById(Long testId);

    void deleteTestById(Long testId);

    void deleteAllTests();

    List<Test> findTestsByParameter(String column, Object value);
}
