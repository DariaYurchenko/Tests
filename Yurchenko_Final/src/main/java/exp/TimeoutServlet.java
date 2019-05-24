package exp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/timeoutservlet"})
public class TimeoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = null;
        if (SessionLocator.flag) {
// "создание" сессии и установка времени инвалидации
            session = req.getSession();
            int timeLive = 10; // десять секунд!
            session.setMaxInactiveInterval(timeLive);
            SessionLocator.flag = false;
        } else  {
// если сессия не существует, то ссылка на нее не будет получена
            session = req.getSession(false);
            if (session == null) {
                SessionLocator.flag = true;
            }
        }
        req.setAttribute("messages", SessionLocator.addMessage(session));
        req.getRequestDispatcher("/jsp/time.jsp").forward(req, resp);
    }
}
