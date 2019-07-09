package uitility.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class PasswordValidatorTest {
    private String password;
    private boolean isValide;

    public PasswordValidatorTest(String password, boolean isValide) {
        this.password = password;
        this.isValide = isValide;
    }

    @Parameterized.Parameters
    public static Collection input() {
        return Arrays.asList(new Object[][] {{"derfa133", true}, {"12345", true}, {"hgfsdf", true},
                {"a2", false}, {"123", false}});
    }

    @Test
    public void shouldValidateLogin() {
        assertEquals(isValide, PasswordValidator.validatePassword(password));
    }

}
