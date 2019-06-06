package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.entity.TestInfo;
import model.service.TestInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShowUserResultsTest {

    @Mock
    HttpSession session;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    TestInfoService testInfoService;
    @InjectMocks
    ShowUserResults showUserResultsCommand;

    @Test
    public void shouldShowThemes() {
        List<TestInfo> testInfos = new ArrayList<>(Arrays.asList(new TestInfo.Builder().build(), new TestInfo.Builder().build()));
        when(request.getParameter(anyString())).thenReturn(null);
        when(request.getSession()).thenReturn(session);
        when(testInfoService.findTestsInfoByParameter(anyString(), any())).thenReturn(testInfos);
        when(testInfoService.findUserTestInfoForPagination(any(), anyInt(), anyInt())).thenReturn(testInfos);
        doNothing().when(request).setAttribute(any(), anyString());

        CommandResult commandResult = showUserResultsCommand.execute(request, response);
        assertEquals(CommandPages.ADMIN_USER_TESTS, commandResult.getPage());
    }

}
