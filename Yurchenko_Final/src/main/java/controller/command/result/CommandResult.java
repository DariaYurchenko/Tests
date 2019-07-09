package controller.command.result;

import controller.command.Command;

public final class CommandResult {
    private String page;
    private Command chainCommand;

    public CommandResult(String page) {
        this.page = page;
    }

    public CommandResult(Command chainCommand) {
        this.chainCommand = chainCommand;
    }

    public static CommandResult forward(String page) {
        return new CommandResult(page);
    }

    public static CommandResult forward(Command chainCommand) {
        return new CommandResult(chainCommand);
    }

    public String getPage() {
        return page;
    }

    public Command getChainCommand() {
        return chainCommand;
    }

}
