package uitility.validator;

public final class PasswordValidator {
    private static final String PASSWORD = ".{5,}$";

    private PasswordValidator() {
    }

    public static boolean validatePassword(String password) {
        return password.matches(PASSWORD);
    }
}
