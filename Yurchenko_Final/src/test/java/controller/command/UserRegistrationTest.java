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

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class UserRegistrationTest {
    private static final String CORRECT_NAME = "Alex";
    private static final String INVALID_NAME = "Alex2";
    private static final String CORRECT_LASTNAME = "Alexeev";
    private static final String INVALID_LASTNAME = "Alexeev2";
    private static final String CORRECT_PASSWORD = "3123532Kkl";
    private static final String INVALID_PASSWORD = "123";
    private static final String INVALID_EMAIL =  "yurch9gmail.com";
    private static final String CORRECT_EMAIL =  "yurch@gmail.com";
    private static final User USER = new User.Builder().build();
    private static final String STUDENT =  "Student";
    private static final String ADMIN =  "Admin";

    @Mock
    HttpSession session;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    UserService userService;
    @InjectMocks
    UserRegistration userRegistrationCommand;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Parameterized.Parameter
    public String redirectPage;
    @Parameterized.Parameter(1)
    public User user;
    @Parameterized.Parameter(2)
    public String emailFromForm;
    @Parameterized.Parameter(3)
    public String passwordFromForm;
    @Parameterized.Parameter(4)
    public String nameFromForm;
    @Parameterized.Parameter(5)
    public String lastnameFromForm;
    @Parameterized.Parameter(6)
    public String userType;

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {CommandPages.REGISTRATION_PAGE, USER, CORRECT_EMAIL, CORRECT_PASSWORD, CORRECT_NAME, CORRECT_LASTNAME, STUDENT},
                {CommandPages.TESTS, null, CORRECT_EMAIL, CORRECT_PASSWORD, CORRECT_NAME, CORRECT_LASTNAME, STUDENT},
                {CommandPages.ADMIN_PAGE, null, CORRECT_EMAIL, CORRECT_PASSWORD, CORRECT_NAME, CORRECT_LASTNAME, ADMIN},
                {CommandPages.REGISTRATION_PAGE, null, INVALID_EMAIL,  CORRECT_PASSWORD, CORRECT_NAME, CORRECT_LASTNAME, STUDENT},
                {CommandPages.REGISTRATION_PAGE, null, CORRECT_EMAIL,  INVALID_PASSWORD, CORRECT_NAME, CORRECT_LASTNAME, STUDENT},
                {CommandPages.REGISTRATION_PAGE, null, CORRECT_EMAIL,  CORRECT_PASSWORD, INVALID_NAME, CORRECT_LASTNAME, STUDENT},
                {CommandPages.REGISTRATION_PAGE, null, CORRECT_EMAIL,  CORRECT_PASSWORD, CORRECT_NAME, INVALID_LASTNAME, STUDENT},
        });
    }

    @Test
    public void shouldRegisterUser () {
        when(request.getParameter("email")).thenReturn(emailFromForm);
        when(request.getParameter("password")).thenReturn(passwordFromForm);
        when(request.getParameter("name")).thenReturn(nameFromForm);
        when(request.getParameter("lastname")).thenReturn(lastnameFromForm);
        when(request.getParameter("appLocale")).thenReturn("ru_RU");
        when(request.getParameter("userType")).thenReturn(userType);
        when(request.getSession()).thenReturn(session);
        when(userService.findUserByLogin(CORRECT_EMAIL)).thenReturn(Optional.ofNullable(user));
        doNothing().when(request).setAttribute(any(), anyString());
        doNothing().when(session).setAttribute(any(), anyString());

        CommandResult commandResult = userRegistrationCommand.execute(request, response);
        assertEquals(redirectPage, commandResult.getPage());
    }

}
