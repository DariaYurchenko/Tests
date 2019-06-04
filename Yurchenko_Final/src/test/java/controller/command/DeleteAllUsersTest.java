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

@RunWith(MockitoJUnitRunner.class)
public class DeleteAllUsersTest {
    private HttpServletRequest request;
    private HttpServletResponse response;

    @Mock
    UserService userService;
    @InjectMocks
    DeleteAllUsers deleteAllUsersCommand;

    @Test
    public void shouldDeleteAllUsers() {
        CommandResult commandResult = deleteAllUsersCommand.execute(request, response);

        assertEquals(new ShowAllUsers(), commandResult.getChainCommand());
        verify(userService).deleteAllUsers();
    }

}
