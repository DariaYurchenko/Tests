package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.entity.Question;
import model.service.QuestionService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AdministrateQuestionsTest {

    @Mock
    HttpSession session;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    QuestionService questionService;
    @InjectMocks
    AdministrateQuestions administrateQuestions;

    @Test
    public void shouldShowAllQuestions() {
        List<Question> questions = new ArrayList<>(Arrays.asList(new Question.Builder().build(), new Question.Builder().build()));
        when(request.getSession()).thenReturn(session);
        when(request.getParameter(anyString())).thenReturn(null);
        when(questionService.findAllQuestions()).thenReturn(questions);
        when(questionService.findQuestionsForPagination(anyInt(), anyInt())).thenReturn(questions);

        CommandResult commandResult = administrateQuestions.execute(request, response);
        assertEquals(CommandPages.ADMIN_QUESTIONS, commandResult.getPage());
    }

}
