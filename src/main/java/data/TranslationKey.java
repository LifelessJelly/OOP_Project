package data;

import java.util.Objects;

public class TranslationKey {
    String language;
    String key;

    public TranslationKey(String language, String key) {
        switch (language){
            case "en": //british english; screw american english
            case "cn":
                break;
            default:
                throw new IllegalArgumentException("Language not recognized");
        }
        this.language = language;
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TranslationKey that = (TranslationKey) o;
        return Objects.equals(language, that.language) && Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(language, key);
    }
}
