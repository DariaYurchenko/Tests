package model.service;

import model.entity.Test;
import java.util.List;

public interface TestService {

    void addTestToDatabase(Test test);

    List<Test> findAllTests();

    Test findTestById(Long testId);

    void deleteTestById(Long testId);

    void deleteAllTests();

    List<Test> findTestsByParameter(String column, Object value);
}
