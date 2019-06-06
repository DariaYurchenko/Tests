package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.entity.User;
import model.entity.status.UserStatus;
import model.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uitility.encryption.Encryptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class UserLoginTest {
    private static final String CORRECT_PASSWORD = "3123532Kkl";
    private static final String INCORRECT_PASSWORD = "1234JK2a";
    private static final String INVALID_PASSWORD = "123";
    private static final String INVALID_EMAIL =  "yurch9gmail.com";
    private static final String CORRECT_EMAIL =  "yurch@gmail.com";
    private static final User STUDENT  = new User.Builder()
            .withId(1)
            .withSalt(new byte[] {1})
            .withHash(Encryptor.getSecurePassword(CORRECT_PASSWORD, new byte[] {1}))
            .withUserType(UserStatus.STUDENT)
            .build();
    private static final User ADMIN  = new User.Builder()
            .withId(1)
            .withSalt(new byte[] {1})
            .withHash(Encryptor.getSecurePassword(CORRECT_PASSWORD, new byte[] {1}))
            .withUserType(UserStatus.ADMIN)
            .build();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    HttpSession session;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    UserService userService;
    @InjectMocks
    UserLogin userLoginCommand;

    @Parameterized.Parameter()
    public String redirectPage;
    @Parameterized.Parameter(1)
    public User user;
    @Parameterized.Parameter(2)
    public String emailFromForm;
    @Parameterized.Parameter(3)
    public String passwordFromForm;
    @Parameterized.Parameter(4)
    public String isForgotPassword;

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {CommandPages.START_PAGE, STUDENT, CORRECT_EMAIL, CORRECT_PASSWORD, "FALSE"},
                {CommandPages.START_PAGE, ADMIN, CORRECT_EMAIL, CORRECT_PASSWORD, "FALSE"},
                {CommandPages.LOGIN_PAGE, STUDENT, INVALID_EMAIL, CORRECT_PASSWORD, "FALSE"},
                {CommandPages.LOGIN_PAGE,STUDENT, CORRECT_EMAIL, INCORRECT_PASSWORD, "FALSE"},
                {CommandPages.LOGIN_PAGE, STUDENT, CORRECT_EMAIL, INVALID_PASSWORD, "FALSE"},
                {CommandPages.LOGIN_PAGE, null, CORRECT_EMAIL, INVALID_PASSWORD, "FALSE"},
                {CommandPages.CHANGE_PASSWORD_PAGE, STUDENT, CORRECT_EMAIL, INCORRECT_PASSWORD, "TRUE"},
        });
    }

    @Test
    public void shouldLoginUser() {
        when(request.getParameter("login")).thenReturn(emailFromForm);
        when(request.getParameter("password")).thenReturn(passwordFromForm);
        when(request.getParameter("appLocale")).thenReturn("ru_RU");
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("ifForgotPassword")).thenReturn(isForgotPassword);
        when(userService.findUserByLogin(CORRECT_EMAIL)).thenReturn(Optional.ofNullable(user));
        doNothing().when(request).setAttribute(any(), anyString());
        doNothing().when(session).setAttribute(any(), anyString());

        CommandResult commandResult = userLoginCommand.execute(request, response);
        assertEquals(redirectPage, commandResult.getPage());
    }

}
