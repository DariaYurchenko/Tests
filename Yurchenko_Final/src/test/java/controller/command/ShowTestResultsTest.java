package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.entity.Answer;
import model.entity.User;
import model.entity.status.AnswerStatus;
import model.service.TestService;
import model.service.UserService;
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
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ShowTestResultsTest {

    @Mock
    HttpSession session;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    UserService userService;
    @Mock
    TestService testService;
    @InjectMocks
    ShowTestResults showTestResultsCommand;

    @Test
    public void shouldShowResultsOfTest() {
        List<Answer> userAnswers = new ArrayList<>(Arrays.asList(new Answer(2, AnswerStatus.CORRECT), new Answer(2, AnswerStatus.CORRECT)));

        User user = new User.Builder()
                .withLogin("yurch@gmail.com")
                .build();

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("userAnswers")).thenReturn(userAnswers);
        when(session.getAttribute("user")).thenReturn(user);
        when(session.getAttribute("theme_id")).thenReturn("1");
        doNothing().when(userService).setRank(anyString(), anyInt(), anyInt());
        doNothing().when(testService).addTest(any());

        CommandResult commandResult = showTestResultsCommand.execute(request, response);
        assertEquals(CommandPages.SHOW_RESULTS, commandResult.getPage());

        verify(userService).setRank(anyString(), anyInt(), anyInt());
        verify(testService).addTest(any());
    }

}
