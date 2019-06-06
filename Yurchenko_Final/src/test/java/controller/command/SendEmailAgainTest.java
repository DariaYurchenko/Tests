package controller.command;

import com.sun.org.apache.bcel.internal.generic.LADD;
import controller.command.result.CommandResult;
import model.entity.User;
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

import static controller.pages.CommandPages.NOT_SUBMIT_EMAIL;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SendEmailAgainTest {

    @Mock
    HttpSession session;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    UserService userService;
    @InjectMocks
    SendEmailAgain sendEmailAgainCommand;

   /* @Test
    public void shouldSendEmailAgain() {
        User user = new User.Builder().
                withLogin("yurch@gmail.com")
                .build();

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(session.getAttribute("appLocale")).thenReturn("ru_RU");
        when(userService.findMagicKey(user.getLogin())).thenReturn("1111111");
        doNothing().when(request).setAttribute(any(), anyString());

        CommandResult commandResult = sendEmailAgainCommand.execute(request, response);
        assertEquals(NOT_SUBMIT_EMAIL, commandResult.getPage());
        verify(userService).findMagicKey(user.getLogin());
    }*/

}
