package uitility.language;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public final class LanguageManager {
    private static final String RESOURCE_NAME = "languages/messages";

    private static volatile LanguageManager instance;
    private ResourceBundle resourceBundle;
    private Locale locale;
    private Map<String, Locale> localeMap = new HashMap<String, Locale>()
    {{
        put("en_UK", new Locale("en", "UK"));
        put("ru_RU", new Locale("ru", "Ru"));
    }};


    private LanguageManager() {
    }

    public static LanguageManager getInstance() {
        if (instance == null) {
            synchronized (LanguageManager.class) {
                if (instance == null) {
                    instance = new LanguageManager();
                }
            }
        }
        return instance;
    }

    public void setLanguage(String language) {
        setLocale(language);
        useLanguage();
    }

    public String getMessage(String key) {
        return resourceBundle.getString(key);
    }

    private void setLocale(String language) {
        if(localeMap.containsKey(language)) {
            this.locale = localeMap.get(language);
        }
        else {
            this.locale = Locale.getDefault();
        }
    }

    private void useLanguage() {
        resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME, locale);
    }
}
