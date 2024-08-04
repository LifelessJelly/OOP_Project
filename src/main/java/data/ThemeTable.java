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

        put(BOREALIS, "ViewApplicantsIcon", ImageEmbedded.ViewApplicantsIcon.VIEWAPPLICANTS_BOREALIS);
        put(CANDLE, "ViewApplicantsIcon", ImageEmbedded.ViewApplicantsIcon.VIEWAPPLICANTS_CANDLE);
        put(NOSTALGIA, "ViewApplicantsIcon", ImageEmbedded.ViewApplicantsIcon.VIEWAPPLICANTS_NOSTALGIA);
        put(NIGHT, "ViewApplicantsIcon", ImageEmbedded.ViewApplicantsIcon.VIEWAPPLICANTS_NIGHT);
        put(MINT, "ViewApplicantsIcon", ImageEmbedded.ViewApplicantsIcon.VIEWAPPLICANTS_MINT);
        put(TIDE, "ViewApplicantsIcon", ImageEmbedded.ViewApplicantsIcon.VIEWAPPLICANTS_TIDE);
        put(UNICORN, "ViewApplicantsIcon", ImageEmbedded.ViewApplicantsIcon.VIEWAPPLICANTS_UNICORN);
        put(INK, "ViewApplicantsIcon", ImageEmbedded.ViewApplicantsIcon.VIEWAPPLICANTS_INK);

        put(BOREALIS, "SetInterviewIcon", ImageEmbedded.SetInterviewIcon.SETINTERVIEW_BOREALIS);
        put(CANDLE, "SetInterviewIcon", ImageEmbedded.SetInterviewIcon.SETINTERVIEW_CANDLE);
        put(NOSTALGIA, "SetInterviewIcon", ImageEmbedded.SetInterviewIcon.SETINTERVIEW_NOSTALGIA);
        put(NIGHT, "SetInterviewIcon", ImageEmbedded.SetInterviewIcon.SETINTERVIEW_NIGHT);
        put(MINT, "SetInterviewIcon", ImageEmbedded.SetInterviewIcon.SETINTERVIEW_MINT);
        put(TIDE, "SetInterviewIcon", ImageEmbedded.SetInterviewIcon.SETINTERVIEW_TIDE);
        put(UNICORN, "SetInterviewIcon", ImageEmbedded.SetInterviewIcon.SETINTERVIEW_UNICORN);
        put(INK, "SetInterviewIcon", ImageEmbedded.SetInterviewIcon.SETINTERVIEW_INK);

        put(BOREALIS, "AcceptApplicantIcon", ImageEmbedded.AcceptApplicantIcon.ACCEPTAPPLICANT_BOREALIS);
        put(CANDLE, "AcceptApplicantIcon", ImageEmbedded.AcceptApplicantIcon.ACCEPTAPPLICANT_CANDLE);
        put(NOSTALGIA, "AcceptApplicantIcon", ImageEmbedded.AcceptApplicantIcon.ACCEPTAPPLICANT_NOSTALGIA);
        put(NIGHT, "AcceptApplicantIcon", ImageEmbedded.AcceptApplicantIcon.ACCEPTAPPLICANT_NIGHT);
        put(MINT, "AcceptApplicantIcon", ImageEmbedded.AcceptApplicantIcon.ACCEPTAPPLICANT_MINT);
        put(TIDE, "AcceptApplicantIcon", ImageEmbedded.AcceptApplicantIcon.ACCEPTAPPLICANT_TIDE);
        put(UNICORN, "AcceptApplicantIcon", ImageEmbedded.AcceptApplicantIcon.ACCEPTAPPLICANT_UNICORN);
        put(INK, "AcceptApplicantIcon", ImageEmbedded.AcceptApplicantIcon.ACCEPTAPPLICANT_INK);

        put(BOREALIS, "ShortlistAddIcon", ImageEmbedded.ShortlistAddIcon.SHORTLISTADD_BOREALIS);
        put(CANDLE, "ShortlistAddIcon", ImageEmbedded.ShortlistAddIcon.SHORTLISTADD_CANDLE);
        put(NOSTALGIA, "ShortlistAddIcon", ImageEmbedded.ShortlistAddIcon.SHORTLISTADD_NOSTALGIA);
        put(NIGHT, "ShortlistAddIcon", ImageEmbedded.ShortlistAddIcon.SHORTLISTADD_NIGHT);
        put(MINT, "ShortlistAddIcon", ImageEmbedded.ShortlistAddIcon.SHORTLISTADD_MINT);
        put(TIDE, "ShortlistAddIcon", ImageEmbedded.ShortlistAddIcon.SHORTLISTADD_TIDE);
        put(UNICORN, "ShortlistAddIcon", ImageEmbedded.ShortlistAddIcon.SHORTLISTADD_UNICORN);
        put(INK, "ShortlistAddIcon", ImageEmbedded.ShortlistAddIcon.SHORTLISTADD_INK);

        put(BOREALIS, "SetJobRoleIcon", ImageEmbedded.SetJobRoleIcon.SETJOBROLE_BOREALIS);
        put(CANDLE, "SetJobRoleIcon", ImageEmbedded.SetJobRoleIcon.SETJOBROLE_CANDLE);
        put(NOSTALGIA, "SetJobRoleIcon", ImageEmbedded.SetJobRoleIcon.SETJOBROLE_NOSTALGIA);
        put(NIGHT, "SetJobRoleIcon", ImageEmbedded.SetJobRoleIcon.SETJOBROLE_NIGHT);
        put(MINT, "SetJobRoleIcon", ImageEmbedded.SetJobRoleIcon.SETJOBROLE_MINT);
        put(TIDE, "SetJobRoleIcon", ImageEmbedded.SetJobRoleIcon.SETJOBROLE_TIDE);
        put(UNICORN, "SetJobRoleIcon", ImageEmbedded.SetJobRoleIcon.SETJOBROLE_UNICORN);
        put(INK, "SetJobRoleIcon", ImageEmbedded.SetJobRoleIcon.SETJOBROLE_INK);

        put(BOREALIS, "ViewSummaryIcon", ImageEmbedded.ViewSummaryIcon.VIEWSUMMARY_BOREALIS);
        put(CANDLE, "ViewSummaryIcon", ImageEmbedded.ViewSummaryIcon.VIEWSUMMARY_CANDLE);
        put(NOSTALGIA, "ViewSummaryIcon", ImageEmbedded.ViewSummaryIcon.VIEWSUMMARY_NOSTALGIA);
        put(NIGHT, "ViewSummaryIcon", ImageEmbedded.ViewSummaryIcon.VIEWSUMMARY_NIGHT);
        put(MINT, "ViewSummaryIcon", ImageEmbedded.ViewSummaryIcon.VIEWSUMMARY_MINT);
        put(TIDE, "ViewSummaryIcon", ImageEmbedded.ViewSummaryIcon.VIEWSUMMARY_TIDE);
        put(UNICORN, "ViewSummaryIcon", ImageEmbedded.ViewSummaryIcon.VIEWSUMMARY_UNICORN);
        put(INK, "ViewSummaryIcon", ImageEmbedded.ViewSummaryIcon.VIEWSUMMARY_INK);

        put(BOREALIS, "ViewPDFIcon", ImageEmbedded.ViewApplicantPDFIcon.VIEWPDF_BOREALIS);
        put(CANDLE, "ViewPDFIcon", ImageEmbedded.ViewApplicantPDFIcon.VIEWPDF_CANDLE);
        put(NOSTALGIA, "ViewPDFIcon", ImageEmbedded.ViewApplicantPDFIcon.VIEWPDF_NOSTALGIA);
        put(NIGHT, "ViewPDFIcon", ImageEmbedded.ViewApplicantPDFIcon.VIEWPDF_NIGHT);
        put(MINT, "ViewPDFIcon", ImageEmbedded.ViewApplicantPDFIcon.VIEWPDF_MINT);
        put(TIDE, "ViewPDFIcon", ImageEmbedded.ViewApplicantPDFIcon.VIEWPDF_TIDE);
        put(UNICORN, "ViewPDFIcon", ImageEmbedded.ViewApplicantPDFIcon.VIEWPDF_UNICORN);
        put(INK, "ViewPDFIcon", ImageEmbedded.ViewApplicantPDFIcon.VIEWPDF_INK);


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
