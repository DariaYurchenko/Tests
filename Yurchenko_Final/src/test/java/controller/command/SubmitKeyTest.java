package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.service.QuestionService;
import model.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SubmitKeyTest {

    @Mock
    HttpSession session;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    UserService userService;
    @InjectMocks
    SubmitKey submitKey;

    @Test
    public void shouldSubmitKey() {
        when(request.getParameter("login")).thenReturn("yurch@gmail.com");
        when(request.getParameter("key")).thenReturn("111111");
        when(request.getParameter("appLocale")).thenReturn("ru_RU");
        when(userService.findMagicKey(anyString())).thenReturn("111111");

        CommandResult commandResult = submitKey.execute(request, response);
        assertEquals(CommandPages.SUBMIT_KEY, commandResult.getPage());

        verify(userService).changeSubmitKeyStatus("yurch@gmail.com");
    }

    @Test
    public void shouldNotSubmitKey() {
        when(request.getParameter("login")).thenReturn("yurch@gmail.com");
        when(request.getParameter("key")).thenReturn("222222");
        when(request.getParameter("appLocale")).thenReturn("ru_RU");
        when(request.getSession()).thenReturn(session);
        when(userService.findMagicKey(anyString())).thenReturn("111111");
        doNothing().when(request).setAttribute(any(), anyString());

        CommandResult commandResult = submitKey.execute(request, response);
        assertEquals(CommandPages.SUBMIT_KEY, commandResult.getPage());

        verify(userService, never()).changeSubmitKeyStatus("yurch@gmail.com");
    }

}
