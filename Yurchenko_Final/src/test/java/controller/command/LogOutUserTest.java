package controller.command;

import controller.command.result.CommandResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static controller.pages.CommandPages.LOGIN_PAGE;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LogOutUserTest {
    private static final String expectedPage = LOGIN_PAGE;

    @Mock
    HttpSession session;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @InjectMocks
    LogOutUser logOutUserCommand;

    @Test
    public void deleteSession() {
        when(request.getSession()).thenReturn(session);

        CommandResult execute = logOutUserCommand.execute(request, response);

        assertEquals(execute.getPage(), expectedPage);
        verify(session).invalidate();
    }

}
