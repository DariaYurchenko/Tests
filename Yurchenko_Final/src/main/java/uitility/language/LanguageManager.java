package uitility.language;

import java.util.Locale;
import java.util.ResourceBundle;

public final class LanguageManager {
    //TODO: double check
    public static final LanguageManager INSTANCE = new LanguageManager();
    //TODO:rename
    private static final String RESOURCE_NAME = "messages/messages";

    private ResourceBundle resourceBundle;
    private Locale locale;

    private LanguageManager() {
    }

    public void setLanguage(String language) {
        setLocale(language);
        useLanguage();
    }

    public String getMessage(String key) {
        return resourceBundle.getString(key);
    }

    //TODO:map
    private void setLocale(String language) {
        if(language.equalsIgnoreCase("en_UK")) {
            this.locale = new Locale("en", "UK");
        }
        if(language.equalsIgnoreCase("ru_RU")) {
            this.locale = new Locale("ru", "Ru");
        }
        else {
            this.locale = Locale.getDefault();
        }
    }

    private void useLanguage() {
        resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME, locale);
    }
}
