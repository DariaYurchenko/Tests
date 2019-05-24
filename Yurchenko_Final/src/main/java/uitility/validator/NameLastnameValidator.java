package uitility.validator;

public final class NameLastnameValidator {
    private static final String NAME_OR_LASTNAME = "^[a-zA-Zа-яА-ЯёЁ]{1,30}$";

    public static boolean validateNameLastname(String nameOrLastname) {
        return nameOrLastname.matches(NAME_OR_LASTNAME);
    }
}
