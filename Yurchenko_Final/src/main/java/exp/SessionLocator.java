package exp;

import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpSession;
public class SessionLocator {
    private final static String BR = "<br/><hr/>";
    public static boolean flag = true;
    public static ArrayList<String> addMessage(HttpSession session) {
        ArrayList<String> messages = new ArrayList<String>();
        if (session != null) { // если сессия существует
            messages.add("Creation Time : " + new  Date(session.getCreationTime()) + BR);
            messages.add("Session id : " + session.getId() + BR);
            messages.add("Session aliv e!" + BR );
        } else { // если сессии уже не существует
            messages.add("Session disabled!" + BR);
        }
        return messages;
    }
}
