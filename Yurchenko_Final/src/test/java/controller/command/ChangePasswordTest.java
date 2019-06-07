package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import uitility.language.LanguageManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ChangePasswordTest {

    @Mock
    UserService userService;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;
    @Mock
    LanguageManager languageManager;
    @InjectMocks
    ChangePassword changePasswordCommand;

    @Test
    public void shouldChangePasswordWithCorrectNewPassword() {
        when(request.getParameter("login")).thenReturn("yurch@gmail.com");
        when(request.getParameter("newPassword")).thenReturn("1223456");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("appLocale")).thenReturn("ru_RU");

        CommandResult commandResult = changePasswordCommand.execute(request, response);
        assertEquals(CommandPages.LOGIN_PAGE, commandResult.getPage());

        verify(userService).changeUsersPassword(anyString(), any(), anyString());
    }

    @Test
    public void shouldChangePasswordWithIncorrectNewPassword() {
        when(request.getParameter("login")).thenReturn("yurch@gmail.com");
        when(request.getParameter("newPassword")).thenReturn("1");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("appLocale")).thenReturn("ru_RU");

        ChangePassword changePasswordCommand = new ChangePassword();
        CommandResult commandResult = changePasswordCommand.execute(request, response);
        assertEquals(CommandPages.CHANGE_PASSWORD_PAGE, commandResult.getPage());
    }

}
