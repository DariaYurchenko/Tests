package controller.command;

public class CommandResult {
    private String page;
    private boolean redirect;
    private Command chainCommand;

    public CommandResult(String page, boolean redirect) {
        this.page = page;
        this.redirect = redirect;
    }

    public CommandResult(Command chainCommand) {
        this.chainCommand = chainCommand;
    }

    public static CommandResult forward(String page) {
        return new CommandResult(page, false);
    }

    public static CommandResult redirect(String page) {
        return new CommandResult(page, true);
    }

    public static CommandResult forward(Command chainCommand) {
        return new CommandResult(chainCommand);
    }

    public String getPage() {
        return page;
    }

    public boolean isRedirect() {
        return redirect;
    }

    public Command getChainCommand() {
        return chainCommand;
    }
}
