package controller.command;

public class CommandResult {
    private String page;

    public CommandResult(String page) {
        this.page = page;
    }

    public static CommandResult forward(String page) {
        return new CommandResult(page);
    }

    public String getPage() {
        return page;
    }

}
