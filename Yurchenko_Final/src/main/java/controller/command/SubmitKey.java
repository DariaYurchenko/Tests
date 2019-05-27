package controller.command;

import controller.pages.Pages;
import model.dao.UserDao;
import model.dao.connector.Connector;
import model.dao.impl.UserDaoImpl;
import model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubmitKey extends Command implements Pages {
    private UserDao userDao;
    private Connector connector = new Connector();

    public SubmitKey() {
        this.userDao = new UserDaoImpl(connector);
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String key = req.getParameter("key");

       /* if(key == null) {
            return new CommandResult(TESTS);
        }*/
        /*User user = userDao.findUserByLogin(login).get();
        Long id = user.getUserId();*/


        String userKey = userDao.getMagicKey(login);
        if(userKey.equals(key)) {
            userDao.update("submited", 2, login);
        }

        return CommandResult.forward(SUBMIT_KEY);
    }
}
