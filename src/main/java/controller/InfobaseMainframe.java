package controller;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import data.*;

import gui.infobase.Console;

import javax.swing.*;
import java.awt.*;

public class InfobaseMainframe extends JFrame {

    private static String language = "en";
    private final InfobaseController controller;
    private CardLayout cardLayout;
    private Console console;
    private static final LookAndFeel darkMode = new FlatDarkLaf();
    private static final LookAndFeel lightMode = new FlatLightLaf();
    public static final int BOREALIS = 1; // Green/Blueish dark theme
    public static final int PORNHUB = 2; // Your classic PornHub theme (please rename)
    public static final int NOSTALGIA = 3; // Greyscale dark theme
    public static final int NIGHT = 4; // Purple + dark theme
    public static final int MINT = 5; // Pastel green light theme
    public static final int TIDE = 6; // cyan/blue light theme
    public static final int UNICORN = 7; //pink accent light theme
    public static final int INK = 8; // black and white theme
    private int currentTheme = NIGHT;



    public InfobaseMainframe(Staff user){
        setTheme(NIGHT);

        this.controller = new InfobaseController(user);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);
        this.setPreferredSize(new Dimension(1920, 1080));
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.console = new Console(this);
        this.add(console);
        this.pack();
    }


    public void changeTheme(int theme){

    }
    /**
     * Retrieves the currently set language for translations.
     *
     * @return The language code currently set for translations.
     */
    public String getLanguage() {
        return language;
    }


    /**
     * Sets the language to be used for translations.
     *
     * @param language The language code to set for translations.
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    public void showApplicantListPage(){
        console.showApplicantListPage();
    }


    public void showEditApplicant(int index) {
        console.showEditApplicant(index);
    }

    //TODO the add Applicant switcher is here, might need to change
    public void showAddApplicant(){
        console.showAddApplicant();
    }

    /**
     * Retrieves the translation for the specified key in the current language from the TranslationTable.
     *
     * @param key The key identifying the translation to retrieve.
     * @return The translation corresponding to the provided key in the current language.
     */
    //TODO alter this method to adhere to MVC (currently it's entity -> data)
    public String getLocale(String key){
        TranslationKey translationKey = new TranslationKey(language, key);
        return TranslationTable.getInstance().getTranslation(translationKey);
    }

    public Image getImage(String key){
        ThemeKey themeKey = new ThemeKey(currentTheme, key);
        return ThemeTable.getInstance().getImage(themeKey);
    }


    public InfobaseController getController() {
        return controller;
    }

    public void reload(){
        this.dispose();
        new InfobaseMainframe(controller.getUser());
    }

    public void logout(){
        this.dispose();
        new LoginMainframe();
    }

    public void showShortlistPage() {
        console.showShortlistPage();
    }

    public void setTheme(int theme) {
        currentTheme = theme;
        Color mainthemeColour;
        if (theme <= NIGHT) {
            try {
                UIManager.setLookAndFeel(darkMode);
                mainthemeColour = new Color(47, 47, 47);
            } catch (UnsupportedLookAndFeelException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                UIManager.setLookAndFeel(lightMode);
                mainthemeColour = Color.WHITE;
            } catch (UnsupportedLookAndFeelException e) {
                throw new RuntimeException(e);
            }
        }
        UIManager.put("Button.background", mainthemeColour);
        UIManager.put("CheckBox.background", mainthemeColour);
        UIManager.put("CheckBoxMenuItem.background", mainthemeColour);
        UIManager.put("ComboBox.background", mainthemeColour);
        UIManager.put("ComboBox.buttonBackground", mainthemeColour);
        UIManager.put("Desktop.background", mainthemeColour);
        UIManager.put("EditorPane.background", mainthemeColour);
        UIManager.put("FormattedTextField.background", mainthemeColour);
        UIManager.put("Label.background", mainthemeColour);
        UIManager.put("List.background", mainthemeColour);
        UIManager.put("Panel.background", mainthemeColour);
        UIManager.put("ScrollPane.background", mainthemeColour);
        UIManager.put("ScrollBar.background", mainthemeColour);
        UIManager.put("Table.background", mainthemeColour);
        UIManager.put("EditorPane.background", mainthemeColour);
        UIManager.put("EditorPane.background", mainthemeColour);
        UIManager.put("EditorPane.background", mainthemeColour);
        SwingUtilities.updateComponentTreeUI(this);
    }

    public boolean isDarkMode(){
        return currentTheme <= NIGHT;
    }

    public void showApplicantListPageWithAnimation() {
        console.showApplicantWithAnimation();
    }

}
