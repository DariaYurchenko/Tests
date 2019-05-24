package controller.comands;

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
    }

    public Command makeCommand(String command) {
        /* return commands.containsKey(command) ?
                 commands.get(command):new UnderfinedCommand(); */
        return Optional.ofNullable(commands.get(command)).orElse(new UnderfinedCommand());

        /*if(commands.containsKey(command)) {
            return commands.get(command);
        }
        else {
            return new UnderfinedCommand();
        }*/
    }
}
