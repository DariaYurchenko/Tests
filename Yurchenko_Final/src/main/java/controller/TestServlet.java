package controller;

import controller.command.Command;
import controller.command.result.CommandResult;
import controller.command.factory.CommandsFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/tests")
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
        String page = req.getParameter("page");

        if(page != null) {
            req.getRequestDispatcher("jsp/" + page+".jsp").forward(req, resp);
        }
        else {
            makeCommandAndRedirect(req, resp);
        }
    }

    private void makeCommandAndRedirect(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String command = req.getParameter("command");
        CommandsFactory factory = CommandsFactory.getInstance();
        CommandResult result = factory.makeCommand(command).execute(req, resp);

        if (checkIfLeadsToAnotherCommand(result)) {
            Command chainCommand = result.getChainCommand();
            result = chainCommand.execute(req, resp);
        }
        String page = result.getPage();
        req.getRequestDispatcher(page).forward(req, resp);

    }

    private boolean checkIfLeadsToAnotherCommand(CommandResult result) {
        return result.getChainCommand() != null;
    }

}
