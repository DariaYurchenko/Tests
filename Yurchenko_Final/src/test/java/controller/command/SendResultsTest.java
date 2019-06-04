package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.entity.TestInfo;
import model.entity.User;
import model.entity.status.TestStatus;
import model.service.TestInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SendResultsTest {

    @Mock
    HttpSession session;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    TestInfoService testInfoService;
    @InjectMocks
    SendResults sendResults;

    @Test
    public void shouldSendResults() {
        User user = new User.Builder().
                withLogin("yurch@gmail.com")
                .build();

        List<TestInfo> testInfoList = new ArrayList<>(Arrays.asList(new TestInfo.Builder()
                        .withUserLogin("yurch@gmail.com")
                        .withTestStatus(TestStatus.PASSED)
                        .withMaxPoints(3)
                        .withPercentOfRightAnswers(50.0)
                        .withUserRank(50.0)
                        .withDate(LocalDate.now())
                        .withTheme("blabla")
                        .withUserName("Alex")
                        .withUserLastName("Alexeev")
                        .withUserPoints(1)
                        .build()));

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(session.getAttribute("appLocale")).thenReturn("ru_RU");
        doNothing().when(request).setAttribute(any(), anyString());
        when(testInfoService.findTestsByParameter("login", user.getLogin())).thenReturn(testInfoList);

        CommandResult commandResult = sendResults.execute(request, response);
        assertEquals(CommandPages.SHOW_RESULTS, commandResult.getPage());

    }

}
