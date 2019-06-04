package controller.command;

import controller.command.result.CommandResult;
import model.service.QuestionService;
import model.service.impl.QuestionServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DeleteAllQuestionsTest {
    private HttpServletRequest request;
    private HttpServletResponse response;

    @Mock
    QuestionService questionService;
    @InjectMocks
    DeleteAllQuestions deleteAllQuestionsCommand;


    @Test
    public void shouldDeleteAllQuestions() {
        CommandResult commandResult = deleteAllQuestionsCommand.execute(request, response);

        assertEquals(new ShowAllQuestions(), commandResult.getChainCommand());
        verify(questionService).deleteAll();
    }

}