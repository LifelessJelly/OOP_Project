package data;

import java.util.Objects;

public class ThemeKey {
    private int themeId;
    private String key;

    public ThemeKey(int themeId, String key) {
        this.themeId = themeId;
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThemeKey themeKey = (ThemeKey) o;
        return themeId == themeKey.themeId && Objects.equals(key, themeKey.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(themeId, key);
    }
}
