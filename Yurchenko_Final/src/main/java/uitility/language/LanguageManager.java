package uitility.language;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class LanguageManager {
    private static final String RESOURCE_NAME = "languages/messages";
    private static final Map<String, Locale> LOCALE_MAP = new HashMap<>();
    private static final String RU = "ru_RU";
    private static final String UK = "en_UK";

    private static volatile LanguageManager instance;
    private ResourceBundle resourceBundle;
    private Locale locale;

    private LanguageManager() {
    }

    public static LanguageManager getInstance() {
        LanguageManager languageManagerInstance = instance;
        if (languageManagerInstance == null) {
            synchronized (LanguageManager.class) {
                languageManagerInstance = instance;
                if (instance == null) {
                    languageManagerInstance =  new LanguageManager();
                    instance = languageManagerInstance;
                }
            }
        }
        return languageManagerInstance;
    }

    public void setLanguage(String language) {
        setLocale(language);
        useLanguage();
    }

    public String getMessage(String key) {
        return resourceBundle.getString(key);
    }

    private void setLocale(String language) {
        setUpLocaleMap();
        if(LOCALE_MAP.containsKey(language)) {
            this.locale = LOCALE_MAP.get(language);
        }
        else {
            this.locale = Locale.getDefault();
        }
    }

    private void useLanguage() {
        resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME, locale);
    }

    private void setUpLocaleMap() {
        LOCALE_MAP.put(UK, new Locale("en", "UK"));
        LOCALE_MAP.put(RU, new Locale("ru", "Ru"));
    }
}
