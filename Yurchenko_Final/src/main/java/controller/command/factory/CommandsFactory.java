package controller.command.factory;

import controller.command.AdministrateUsers;
import controller.command.Command;
import controller.command.UserLogin;
import controller.command.UserRegistration;
import controller.command.UndefinedCommand;
import controller.command.ShowRightAnswers;
import controller.command.SendTestResults;
import controller.command.ShowTestResults;
import controller.command.ShowThemes;
import controller.command.ShowUserResults;
import controller.command.StartTest;
import controller.command.SubmitKey;
import controller.command.ShowQuestion;
import controller.command.DeleteAllUsers;
import controller.command.DeleteUserById;
import controller.command.ChangeLanguage;
import controller.command.SendEmailAgain;
import controller.command.UserLogOut;
import controller.command.AdministrateQuestions;
import controller.command.ShowThemeQuestions;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class CommandsFactory {
    private static volatile CommandsFactory instance;
    private final Map<String, Command> commands;

    public static CommandsFactory getInstance() {
        CommandsFactory commandsFactory = instance;
        if (commandsFactory == null) {
            synchronized (CommandsFactory.class) {
                commandsFactory = instance;
                if (instance == null) {
                    commandsFactory =  new CommandsFactory();
                    instance = commandsFactory;
                }
            }
        }
        return commandsFactory;
    }

    private CommandsFactory() {
        this.commands = new HashMap<>();
        commands.put("REGISTER", new UserRegistration());
        commands.put("LOGIN", new UserLogin());
        commands.put("SHOW_ALL_USERS", new AdministrateUsers());
        commands.put("CHANGE_LANGUAGE", new ChangeLanguage());
        commands.put("SHOW_RESULTS", new ShowTestResults());
        commands.put("START_TEST", new StartTest());
        commands.put("SEND_RESULTS", new SendTestResults());
        commands.put("SUBMIT_KEY", new SubmitKey());
        commands.put("SEND_EMAIL_AGAIN", new SendEmailAgain());
        commands.put("LOGOUT", new UserLogOut());
        commands.put("DELETE_USER_BY_ID", new DeleteUserById());
        commands.put("DELETE_ALL_USERS", new DeleteAllUsers());
        commands.put("SHOW_USER_RESULTS", new ShowUserResults());
        commands.put("SHOW_ALL_QUESTIONS", new AdministrateQuestions());
        commands.put("SHOW_THEMES", new ShowThemes() );
        commands.put("SHOW_THEME_QUESTIONS", new ShowThemeQuestions());
        commands.put("SHOW_QUESTION",new ShowQuestion());
        commands.put("SHOW_ANSWERS", new ShowRightAnswers());
    }

    public Command makeCommand(String command) {
        return Optional.ofNullable(commands.get(command)).orElse(new UndefinedCommand());
    }

}
