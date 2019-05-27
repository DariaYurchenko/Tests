package controller.command;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommandsFactory {
    private final Map<String, Command> commands;

    public CommandsFactory() {
        this.commands = new HashMap<>();
        commands.put("REGISTER", new UserRegistration());
        commands.put("LOGIN", new UserLogin());
        commands.put("SHOW_ALL_USERS", new ShowAllUsers());
        commands.put("CHANGE_LANGUAGE", new ChangeLanguage());
        commands.put("PASS_TESTS", new PassTest());
        commands.put("SHOW_RESULTS", new ShowResults());
        commands.put("START_TEST", new StartTest());
        commands.put("SEND_RESULTS", new SendResults());
        commands.put("CHANGE_PASSWORD", new ChangePassword());
        commands.put("SUBMIT_KEY", new SubmitKey());
        commands.put("SEND_EMAIL_AGAIN", new SendEmailAgain());
        commands.put("LOGOUT", new LogOutUser());
        commands.put("DELETE_USER_BY_ID", new DeleteUserById());
        commands.put("DELETE_ALL_USERS", new DeleteAllUsers());
        commands.put("SHOW_USER_RESULTS", new ShowUserResults());
        commands.put("REGISTER_ADMIN", new RegisterAdmin());
        commands.put("SHOW_ALL_QUESTIONS", new ShowAllQuestions());
        commands.put("SHOW_QUESTIONS_BY_THEME", new ShowThemes() );
        commands.put("SHOW_THEME_QUESTIONS", new ShowThemeQuestions());
        /*commands.put("DELETE_QUESTION_BY_ID", new DeleteQuestionById());
        commands.put("DELETE_ALL_QUESTIONS", new DeleteAllQuestions());*/

    }

    public Command makeCommand(String command) {
        /* return commands.containsKey(command) ?
                 commands.get(command):new UndefinedCommand(); */
        return Optional.ofNullable(commands.get(command)).orElse(new UndefinedCommand());

        /*if(commands.containsKey(command)) {
            return commands.get(command);
        }
        else {
            return new UndefinedCommand();
        }*/
    }
}
