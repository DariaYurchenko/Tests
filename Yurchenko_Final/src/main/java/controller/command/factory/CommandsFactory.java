package controller.command.factory;

import controller.command.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommandsFactory {
    private static volatile CommandsFactory commandFactory;
    private final Map<String, Command> commands;

    public static CommandsFactory getInstance(){
        if (commandFactory==null){
            synchronized (CommandsFactory.class){
                if (commandFactory==null){
                    commandFactory = new CommandsFactory();
                }
            }
        }
        return commandFactory;
    }

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
        commands.put("SHOW_ALL_QUESTIONS", new ShowAllQuestions());
        commands.put("SHOW_THEMES", new ShowThemes() );
        commands.put("SHOW_THEME_QUESTIONS", new ShowThemeQuestions());

    }

    public Command makeCommand(String command) {
        //return commands.get(command);
        return Optional.ofNullable(commands.get(command)).orElse(new UndefinedCommand());
    }
}
