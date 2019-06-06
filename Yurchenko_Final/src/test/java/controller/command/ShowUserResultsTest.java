package controller.command;

import controller.command.result.CommandResult;
import model.entity.TestInfo;
import model.service.TestInfoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static controller.pages.CommandPages.ADMIN_USERS;
import static controller.pages.CommandPages.ADMIN_USER_TESTS;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
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

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Parameterized.Parameter()
    public String page;
    @Parameterized.Parameter(1)
    public String radio;

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {ADMIN_USER_TESTS, "1"},
                {ADMIN_USERS, null}
        });
    }

    @Test
    public void shouldShowThemes() {
        List<TestInfo> testInfos = new ArrayList<>(Arrays.asList(new TestInfo.Builder().build(), new TestInfo.Builder().build()));

        when(request.getParameter("radio")).thenReturn(radio);
        when(request.getParameter("currentPage")).thenReturn("1");
        when(request.getParameter("recordsPerPage")).thenReturn("5");
        when(request.getSession()).thenReturn(session);
        when(testInfoService.findTestsInfoByParameter(anyString(), any())).thenReturn(testInfos);
        when(testInfoService.findUserTestInfoForPagination(1, 1, 5)).thenReturn(testInfos);
        doNothing().when(request).setAttribute(any(), anyString());

        CommandResult commandResult = showUserResultsCommand.execute(request, response);
        assertEquals(page, commandResult.getPage());
    }

}
