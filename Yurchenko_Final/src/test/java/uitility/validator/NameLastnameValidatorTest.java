package uitility.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class NameLastnameValidatorTest {
    private String nameOrLastname;
    private boolean isValide;

    public NameLastnameValidatorTest(String nameOrLastname, boolean isValide) {
        this.nameOrLastname = nameOrLastname;
        this.isValide = isValide;
    }

    @Parameterized.Parameters
    public static Collection input() {
        return Arrays.asList(new Object[][] {{"Alex", true}, {"Alex4", false}, {"Alex.", false},
                {"de Servantes Saavedra", true}, {"Martynes-Garsia", true}, {"d'Ark", true}});
    }

    @Test
    public void shouldValidateLogin() {
        assertEquals(isValide, NameLastnameValidator.validateNameLastname(nameOrLastname));
    }

}
