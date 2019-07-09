package uitility.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class LoginValidatorTest {
    private String login;
    private boolean isValide;

    public LoginValidatorTest(String login, boolean isValide) {
        this.login = login;
        this.isValide = isValide;
    }

    @Parameterized.Parameters
    public static Collection input() {
        return Arrays.asList(new Object[][] {{"1@gmail.com", true}, {"alex@gmail", false}, {"alex@mail.ru", true},
                {"alex", false}, {"alex@gmail.pkkk", false}});
    }

    @Test
    public void shouldValidateLogin() {
        assertEquals(isValide, LoginValidator.validateLogin(login));
    }
}