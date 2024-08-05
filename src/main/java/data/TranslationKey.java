package data;

import java.util.Objects;

/**
 * Represents a translation key consisting of a language code and a corresponding key.
 *
 * <p>This class is used to identify a specific translation based on the language
 * and the key provided. It validates the language during instantiation, allowing only
 * recognized language codes.</p>
 *
 * <p>Supported languages:</p>
 * <ul>
 *     <li>en - British English</li>
 *     <li>cn - Chinese</li>
 * </ul>
 *
 * <p>Instances of this class can be compared for equality based on their language
 * and key values, and they provide a hash code for use in hash-based collections.</p>
 *
 */
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
