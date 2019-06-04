package controller.command;

import controller.command.result.CommandResult;
import model.service.QuestionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DeleteQuestionByIdTest {

    @Mock
    QuestionService questionService;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @InjectMocks
    DeleteQuestionById deleteQuestionByIdCommand;

    @Test
    public void shouldDeleteQuestionById() {
        when(request.getParameter("radio")).thenReturn("1");
        CommandResult commandResult = deleteQuestionByIdCommand.execute(request, response);

        assertEquals(new ShowAllQuestions(), commandResult.getChainCommand());
        verify(questionService).deleteById(1L);
    }

}