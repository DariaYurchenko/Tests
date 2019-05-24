package controller.comands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Command {

    public abstract CommandResult execute(HttpServletRequest req, HttpServletResponse resp);
}
