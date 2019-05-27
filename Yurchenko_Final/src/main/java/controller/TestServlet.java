package controller;

import controller.command.Command;
import controller.command.CommandResult;
import controller.command.CommandsFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/tests"})
public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        CommandsFactory factory = new CommandsFactory();
        CommandResult result = factory.makeCommand(command).execute(request, response);
        if(result.getChainCommand() != null) {
            Command newCommand = result.getChainCommand();
            result = newCommand.execute(request, response);
        }
        String page = result.getPage();
        if (result.isRedirect()) {
            response.sendRedirect(page);
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
