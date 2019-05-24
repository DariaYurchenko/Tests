package uitility.validator;

public final class LoginValidator {
    private static final String LOGIN = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    public static boolean validateLogin(String login) {
        return login.matches(LOGIN);
    }
}
