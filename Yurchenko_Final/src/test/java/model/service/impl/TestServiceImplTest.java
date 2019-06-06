package model.service.impl;

import model.dao.TestDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestServiceImplTest {

    @Mock
    TestDao testDao;
    @InjectMocks
    TestServiceImpl testService;

    @Test
    public void shouldFindAllTests() {
        when(testDao.findAll()).thenReturn(Arrays.asList(new model.entity.Test.Builder().build(), new model.entity.Test.Builder().build()));
        List<model.entity.Test> tests = testService.findAllTests();
        assertNotNull(tests);
    }

    @Test
    public void shouldAddTest() {
        Mockito.doNothing().when(testDao).add(new model.entity.Test.Builder().build());
        testService.addTest(new model.entity.Test.Builder().build());
        verify(testDao).add(new model.entity.Test.Builder().build());
    }

    @Test
    public void shouldDeleteTestById() {
        Integer testId = 1;
        Mockito.doNothing().when(testDao).deleteById(testId);
        testService.deleteTestById(testId);
        verify(testDao).deleteById(testId);
    }

    @Test
    public void shouldDeleteAll() {
        Mockito.doNothing().when(testDao).deleteAll();
        testService.deleteAllTests();
        verify(testDao).deleteAll();
    }

    @Test
    public void shouldFindTestByParameter() {
        String column = "name";
        String name = "Alex";
        when(testDao.findByParameter(column, name)).thenReturn(Arrays.asList(new model.entity.Test.Builder().build(), new model.entity.Test.Builder().build()));
        List<model.entity.Test> tests = testService.findTestsByParameter(column, name);
        assertNotNull(tests);
    }

   @Test
    public void shouldFindQuestionById() {
        Integer testId = 1;
        when(testDao.findUserById(testId)).thenReturn(Optional.ofNullable(new model.entity.Test.Builder().build()));
        model.entity.Test actual = testService.findTestById(testId).get();
        assertNotNull(actual);
    }

}
