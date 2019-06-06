package uitility.validator;

public final class LoginValidator {
    private static final String LOGIN = "(?i)^\\w+([\\.-]?\\w+)*@(((([a-z0-9]{1,})|([a-z0-9][-][a-z0-9]+))[\\.][a-z0-9])|" +
            "([a-z0-9]+[-]?))+[a-z0-9]{0,}\\.([a-z]{2}|(com|net|org|edu|int|mil|gov|arpa|biz|aero|name|coop|info|pro|museum))$";

    private LoginValidator() {
    }

    public static boolean validateLogin(String login) {
        return login.matches(LOGIN);
    }
}
