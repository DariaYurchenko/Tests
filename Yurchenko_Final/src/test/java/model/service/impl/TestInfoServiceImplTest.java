package model.service.impl;

import model.dao.TestInfoDao;
import model.entity.TestInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestInfoServiceImplTest {

    @Mock
    TestInfoDao testInfoDao;
    @InjectMocks
    TestInfoServiceImpl testInfoService;

    @Test
    public void shouldFindTestInfoByParameter() {
        String column = "name";
        String name = "Alex";
        when(testInfoDao.findByParameter(column, name)).thenReturn(Arrays.asList(new TestInfo.Builder().build(), new TestInfo.Builder().build()));
        List<TestInfo> tests = testInfoService.findTestsInfoByParameter(column, name);
        assertNotNull(tests);
    }

    @Test
    public void shouldFindTestInfoForPagination() {
        int recordsPerPage = 5;
        int startRecords = 1;

        when(testInfoDao.findForPagination(startRecords, recordsPerPage)).thenReturn(Arrays.asList(new TestInfo.Builder().build(), new TestInfo.Builder().build()));
        List<TestInfo> questions = testInfoService.findTestInfoForPagination(startRecords, recordsPerPage);
        assertNotNull(questions);
    }

    @Test
    public void shouldFindTestsInfoOfUserForPagination() {
        int recordsPerPage = 5;
        int startRecords = 1;
        Integer userId = 1;

        when(testInfoService.findUserTestInfoForPagination( userId, startRecords, recordsPerPage)).thenReturn(Arrays.asList(new TestInfo.Builder().build(), new TestInfo.Builder().build()));
        List<TestInfo> questions = testInfoService.findUserTestInfoForPagination(userId, startRecords, recordsPerPage);
        assertNotNull(questions);
    }

}
