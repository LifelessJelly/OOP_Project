package data;

import gui.ImageEmbedded;
import subsystems.ImageBase64;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ThemeTable {

    private static ThemeTable instance;
    public static final int BOREALIS = 1; // Green/Blueish dark theme
    public static final int CANDLE = 2; // Your classic PornHub theme (please rename)
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
        put(CANDLE, "MiniCompanyLogo", ImageEmbedded.MINI_COMPANY_LOGO_CANDLE);
        put(NOSTALGIA, "MiniCompanyLogo", ImageEmbedded.MINI_COMPANY_LOGO_NOSTALGIA);
        put(NIGHT, "MiniCompanyLogo", ImageEmbedded.MINI_COMPANY_LOGO_NIGHT);
        put(MINT, "MiniCompanyLogo", ImageEmbedded.MINI_COMPANY_LOGO_MINT);
        put(TIDE, "MiniCompanyLogo", ImageEmbedded.MINI_COMPANY_LOGO_TIDE);
        put(UNICORN, "MiniCompanyLogo", ImageEmbedded.MINI_COMPANY_LOGO_UNICORN);
        put(INK, "MiniCompanyLogo", ImageEmbedded.MINI_COMPANY_LOGO_INK);

        put(BOREALIS, "SettingsIcon", ImageEmbedded.SettingsIcon.SETTINGS_BOREALIS);
        put(CANDLE, "SettingsIcon", ImageEmbedded.SettingsIcon.SETTINGS_CANDLE);
        put(NOSTALGIA, "SettingsIcon", ImageEmbedded.SettingsIcon.SETTINGS_NOSTALGIA);
        put(NIGHT, "SettingsIcon", ImageEmbedded.SettingsIcon.SETTINGS_NIGHT);
        put(MINT, "SettingsIcon", ImageEmbedded.SettingsIcon.SETTINGS_MINT);
        put(TIDE, "SettingsIcon", ImageEmbedded.SettingsIcon.SETTINGS_TIDE);
        put(UNICORN, "SettingsIcon", ImageEmbedded.SettingsIcon.SETTINGS_UNICORN);
        put(INK, "SettingsIcon", ImageEmbedded.SettingsIcon.SETTINGS_INK);

        put(BOREALIS, "AddApplicantJsonIcon", ImageEmbedded.AddApplicantJsonIcon.ADDJSON_BOREALIS);
        put(CANDLE, "AddApplicantJsonIcon", ImageEmbedded.AddApplicantJsonIcon.ADDJSON_CANDLE);
        put(NOSTALGIA, "AddApplicantJsonIcon", ImageEmbedded.AddApplicantJsonIcon.ADDJSON_NOSTALGIA);
        put(NIGHT, "AddApplicantJsonIcon", ImageEmbedded.AddApplicantJsonIcon.ADDJSON_NIGHT);
        put(MINT, "AddApplicantJsonIcon", ImageEmbedded.AddApplicantJsonIcon.ADDJSON_MINT);
        put(TIDE, "AddApplicantJsonIcon", ImageEmbedded.AddApplicantJsonIcon.ADDJSON_TIDE);
        put(UNICORN, "AddApplicantJsonIcon", ImageEmbedded.AddApplicantJsonIcon.ADDJSON_UNICORN);
        put(INK, "AddApplicantJsonIcon", ImageEmbedded.AddApplicantJsonIcon.ADDJSON_INK);

        put(BOREALIS, "AddApplicantIcon", ImageEmbedded.AddApplicantIcon.ADDAPPLICANT_BOREALIS);
        put(CANDLE, "AddApplicantIcon", ImageEmbedded.AddApplicantIcon.ADDAPPLICANT_CANDLE);
        put(NOSTALGIA, "AddApplicantIcon", ImageEmbedded.AddApplicantIcon.ADDAPPLICANT_NOSTALGIA);
        put(NIGHT, "AddApplicantIcon", ImageEmbedded.AddApplicantIcon.ADDAPPLICANT_NIGHT);
        put(MINT, "AddApplicantIcon", ImageEmbedded.AddApplicantIcon.ADDAPPLICANT_MINT);
        put(TIDE, "AddApplicantIcon", ImageEmbedded.AddApplicantIcon.ADDAPPLICANT_TIDE);
        put(UNICORN, "AddApplicantIcon", ImageEmbedded.AddApplicantIcon.ADDAPPLICANT_UNICORN);
        put(INK, "AddApplicantIcon", ImageEmbedded.AddApplicantIcon.ADDAPPLICANT_INK);

        put(BOREALIS, "EditApplicantIcon", ImageEmbedded.EditApplicantIcon.EDITAPPLICANT_BOREALIS);
        put(CANDLE, "EditApplicantIcon", ImageEmbedded.EditApplicantIcon.EDITAPPLICANT_CANDLE);
        put(NOSTALGIA, "EditApplicantIcon", ImageEmbedded.EditApplicantIcon.EDITAPPLICANT_NOSTALGIA);
        put(NIGHT, "EditApplicantIcon", ImageEmbedded.EditApplicantIcon.EDITAPPLICANT_NIGHT);
        put(MINT, "EditApplicantIcon", ImageEmbedded.EditApplicantIcon.EDITAPPLICANT_MINT);
        put(TIDE, "EditApplicantIcon", ImageEmbedded.EditApplicantIcon.EDITAPPLICANT_TIDE);
        put(UNICORN, "EditApplicantIcon", ImageEmbedded.EditApplicantIcon.EDITAPPLICANT_UNICORN);
        put(INK, "EditApplicantIcon", ImageEmbedded.EditApplicantIcon.EDITAPPLICANT_INK);

        put(BOREALIS, "DeleteApplicantIcon", ImageEmbedded.DeleteApplicantIcon.DELETEAPPLICANT_BOREALIS);
        put(CANDLE, "DeleteApplicantIcon", ImageEmbedded.DeleteApplicantIcon.DELETEAPPLICANT_CANDLE);
        put(NOSTALGIA, "DeleteApplicantIcon", ImageEmbedded.DeleteApplicantIcon.DELETEAPPLICANT_NOSTALGIA);
        put(NIGHT, "DeleteApplicantIcon", ImageEmbedded.DeleteApplicantIcon.DELETEAPPLICANT_NIGHT);
        put(MINT, "DeleteApplicantIcon", ImageEmbedded.DeleteApplicantIcon.DELETEAPPLICANT_MINT);
        put(TIDE, "DeleteApplicantIcon", ImageEmbedded.DeleteApplicantIcon.DELETEAPPLICANT_TIDE);
        put(UNICORN, "DeleteApplicantIcon", ImageEmbedded.DeleteApplicantIcon.DELETEAPPLICANT_UNICORN);
        put(INK, "DeleteApplicantIcon", ImageEmbedded.DeleteApplicantIcon.DELETEAPPLICANT_INK);


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
