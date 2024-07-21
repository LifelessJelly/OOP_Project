package data;

import gui.ImageEmbedded;
import subsystems.ImageBase64;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ThemeTable {

    private static ThemeTable instance;
    public static final int BOREALIS = 1; // Green/Blueish dark theme
    public static final int PORNHUB = 2; // Your classic PornHub theme (please rename)
    public static final int NOSTALGIA = 3; // Greyscale dark theme
    public static final int NIGHT = 4; // Purple + dark theme
    public static final int MINT = 5; // Pastel green light theme
    public static final int TIDE = 6; // cyan/blue light theme
    public static final int UNICORN = 7; //pink accent light theme
    public static final int INK = 8; // black and white theme

    Map<ThemeKey, Image> themeTable;

    private ThemeTable() {
        themeTable = new HashMap<>();

        put(BOREALIS, "MiniCompanyLogo", ImageEmbedded.MINI_COMPANY_LOGO_BOREALIS);
        put(PORNHUB, "MiniCompanyLogo", ImageEmbedded.MINI_COMPANY_LOGO_PORNHUB);
        put(NOSTALGIA, "MiniCompanyLogo", ImageEmbedded.MINI_COMPANY_LOGO_NOSTALGIA);
        put(NIGHT, "MiniCompanyLogo", ImageEmbedded.MINI_COMPANY_LOGO_NIGHT);
        put(MINT, "MiniCompanyLogo", ImageEmbedded.MINI_COMPANY_LOGO_MINT);
        put(TIDE, "MiniCompanyLogo", ImageEmbedded.MINI_COMPANY_LOGO_TIDE);
        put(UNICORN, "MiniCompanyLogo", ImageEmbedded.MINI_COMPANY_LOGO_UNICORN);
        put(INK, "MiniCompanyLogo", ImageEmbedded.MINI_COMPANY_LOGO_INK);
    }

    public static ThemeTable getInstance() {
        if (instance == null) {
            instance = new ThemeTable();
        }
        return instance;
    }

    private void put(int themeId, String key, String base64) {
        themeTable.put(new ThemeKey(themeId, key), ImageBase64.base64ToImage(base64));
    }

    public Image getImage(ThemeKey key) {
        return themeTable.get(key);
    }
}
