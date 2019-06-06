package controller.command;

import controller.command.result.CommandResult;
import model.service.UserService;
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
public class DeleteUserByIdTest {

    @Mock
    UserService userService;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @InjectMocks
    DeleteUserById deleteUserByIdCommand;

    @Test
    public void shouldDeleteUserById() {
        when(request.getParameter("radio")).thenReturn("1");
        CommandResult commandResult = deleteUserByIdCommand.execute(request, response);

        assertEquals(new AdministrateUsers(), commandResult.getChainCommand());
        verify(userService).deleteUserById(1L);
    }

}
