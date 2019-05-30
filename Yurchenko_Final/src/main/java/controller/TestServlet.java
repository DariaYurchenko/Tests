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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        process(req, resp);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("command");
        CommandsFactory factory = new CommandsFactory();
        CommandResult result = factory.makeCommand(command).execute(req, resp);

        if(checkIfLeadsToAnotherCommand(result)) {
            Command chainCommand = result.getChainCommand();
            result = chainCommand.execute(req, resp);
        }

        chooseRedirectType(req, resp, result);

    }

    private void chooseRedirectType(HttpServletRequest req, HttpServletResponse resp, CommandResult result) throws IOException, ServletException {
        String page = result.getPage();
        if (result.isRedirect()) {
            resp.sendRedirect(page);
        } else {
            req.getRequestDispatcher(page).forward(req, resp);
        }
    }

    private boolean checkIfLeadsToAnotherCommand(CommandResult result) {
        return result.getChainCommand() != null;
    }
}
