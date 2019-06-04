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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShowThemeQuestionsTest {

    @Mock
    HttpSession session;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    QuestionService questionService;
    @InjectMocks
    ShowThemeQuestions showThemeQuestionsCommand;

    @Test
    public void shouldShowAllThemeQuestions() {
        List<Question> questions = new ArrayList<>(Arrays.asList(new Question.Builder().build(), new Question.Builder().build()));
        when(request.getSession()).thenReturn(session);
        when(request.getParameter(anyString())).thenReturn(null);
        when(questionService.findAll()).thenReturn(questions);
        when(questionService.findQuestionsForPagination(anyInt(), anyInt())).thenReturn(questions);
        doNothing().when(request).setAttribute(any(), anyString());

        CommandResult commandResult = showThemeQuestionsCommand.execute(request, response);
        assertEquals(CommandPages.SHOW_QUESTIONS, commandResult.getPage());
    }

}
