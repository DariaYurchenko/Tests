package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.entity.User;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShowAllUsersTest {

    @Mock
    HttpSession session;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    UserService userService;
    @InjectMocks
    ShowAllUsers showAllUsers;

    @Test
    public void shouldShowAllUsers() {
        List<User> users = new ArrayList<>(Arrays.asList(new User.Builder().build(), new User.Builder().build()));
        when(request.getSession()).thenReturn(session);
        when(request.getParameter(anyString())).thenReturn(null);
        when(userService.findAll()).thenReturn(users);
        when(userService.findUsersForPagination(anyInt(), anyInt())).thenReturn(users);
        doNothing().when(request).setAttribute(any(), anyString());

        CommandResult commandResult = showAllUsers.execute(request, response);
        assertEquals(CommandPages.SHOW_USERS, commandResult.getPage());
    }

}
