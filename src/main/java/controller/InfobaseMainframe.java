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
    private boolean isEditing;
    private final Console console;
    private static final LookAndFeel darkMode = new FlatDarkLaf();
    private static final LookAndFeel lightMode = new FlatLightLaf();
    public static final int BOREALIS = 1; // Green/Blueish dark theme
    public static final int CANDLE = 2; // Your classic PornHub theme (please rename)
    public static final int NOSTALGIA = 3; // Greyscale dark theme
    public static final int NIGHT = 4; // Purple + dark theme
    public static final int MINT = 5; // Pastel green light theme
    public static final int TIDE = 6; // cyan/blue light theme
    public static final int UNICORN = 7; //pink accent light theme
    public static final int INK = 8; // black and white theme
    private static int currentTheme = NIGHT;



    public InfobaseMainframe(Staff user){
        setTheme(currentTheme);

        this.controller = new InfobaseController(user);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        CardLayout cardLayout = new CardLayout();
        this.setLayout(cardLayout);
        this.setPreferredSize(new Dimension(1920, 1080));
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.console = new Console(this);
        this.add(console);
        this.pack();
    }

    /**
     * Changes the current theme of the application.
     * <p>
     * This method updates the application's theme to the specified theme identifier.
     *
     * @param theme the integer identifier of the new theme to be set
     */
    public void changeTheme(int theme){
        currentTheme = theme;
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
        InfobaseMainframe.language = language;
    }

    public void showApplicantListPage(){
        console.showApplicantListPage();
    }

    /**
     * Displays the edit interface for an applicant based on the specified index.
     * <p>
     * This method invokes the console's showEditApplicant method to present
     * the editing options for the applicant at the given index.
     *
     * @param index the index of the applicant to be edited
     */
    public void showEditApplicant(int index) {
        console.showEditApplicant(index);
    }

    /**
     * Displays the interface for adding a new applicant.
     * <p>
     * This method invokes the console's showAddApplicant method to present
     * the options for entering details of a new applicant.
     */
    public void showAddApplicant(){
        console.showAddApplicant();
    }

    /**
     * Retrieves the translation for the specified key in the current language from the TranslationTable.
     *
     * @param key The key identifying the translation to retrieve.
     * @return The translation corresponding to the provided key in the current language.
     */

    public String getLocale(String key){
        return controller.getLocale(language, key);
    }

    public Image getImage(String key){
        return controller.getImage(currentTheme, key);
    }


    public InfobaseController getController() {
        return controller;
    }

    /**
     * Reloads the current instance by disposing of it and creating a new
     * instance of InfobaseMainframe.
     * <p>
     * This method first calls the dispose() method to release resources
     * associated with the current instance, and then initializes a new
     * InfobaseMainframe using the current user's information obtained
     * from the controller.
     */
    public void reload(){
        this.dispose();
        controller.removeTemp();
        new InfobaseMainframe(controller.getUser());
    }


    public void logout(){
        this.dispose();
        new LoginMainframe();
    }

    /**
     * Sets the application's theme based on the specified theme identifier.
     *
     * <p>This method updates the current theme and applies the corresponding
     * look and feel to the user interface. If the provided theme is less than
     * or equal to {@code NIGHT}, a dark mode theme is applied; otherwise, a
     * light mode theme is used. The background color for various UI components
     * is also updated to match the selected theme.</p>
     *
     * @param theme an integer representing the theme to be set.
     *              Values less than or equal to {@code NIGHT} will apply
     *              dark mode, while values greater than {@code NIGHT} will
     *              apply light mode.
     * @throws RuntimeException if the look and feel cannot be set due to
     *                          an unsupported look and feel exception.
     */
    private void setTheme(int theme) {
        currentTheme = theme;
        Color mainThemeColour;
        if (theme <= NIGHT) {
            try {
                UIManager.setLookAndFeel(darkMode);
                mainThemeColour = new Color(47, 47, 47);
            } catch (UnsupportedLookAndFeelException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                UIManager.setLookAndFeel(lightMode);
                mainThemeColour = Color.WHITE;
            } catch (UnsupportedLookAndFeelException e) {
                throw new RuntimeException(e);
            }
        }
        UIManager.put("Button.background", mainThemeColour);
        UIManager.put("ToolTip.background", mainThemeColour);
        UIManager.put("CheckBox.background", mainThemeColour);
        UIManager.put("CheckBoxMenuItem.background", mainThemeColour);
        UIManager.put("ComboBox.background", mainThemeColour);
        UIManager.put("ComboBox.buttonBackground", mainThemeColour);
        UIManager.put("Desktop.background", mainThemeColour);
        UIManager.put("EditorPane.background", mainThemeColour);
        UIManager.put("FormattedTextField.background", mainThemeColour);
        UIManager.put("OptionPane.background", mainThemeColour);
        UIManager.put("Label.background", mainThemeColour);
        UIManager.put("List.background", mainThemeColour);
        UIManager.put("Panel.background", mainThemeColour);
        UIManager.put("ScrollPane.background", mainThemeColour);
        UIManager.put("ScrollBar.background", mainThemeColour);
        UIManager.put("Table.background", mainThemeColour);

        SwingUtilities.updateComponentTreeUI(this);
    }

    public int getCurrentTheme(){
        return currentTheme;
    }

    public boolean isDarkMode(){
        return currentTheme <= NIGHT;
    }

    /**
     * Displays the applicant list page with an animation effect.
     *
     * <p>This method invokes the {@link Console#showApplicantWithAnimation()} method
     * to present the applicant list in a visually engaging manner.</p>
     */
    public void showApplicantListPageWithAnimation() {
        console.showApplicantWithAnimation();
    }

    /**
     * Displays the applicant keying page for a specific applicant type and index.
     *
     * <p>This method calls the {@link Console#showApplicantKeyingPage(int, int)} method
     * to present the keying interface for the applicant identified by the given type and index.</p>
     *
     * @param type  an integer representing the type of applicant. This value determines
     *              which category of applicants is being displayed.
     * @param index an integer representing the index of the applicant within the specified type.
     *              This value indicates which specific applicant's details are to be shown.
     */
    public void showApplicantKeyPage(int type, int index) {
        console.showApplicantKeyingPage(type, index);
    }

    public boolean isEditing() {
        return isEditing;
    }

    public void setIsEditing(boolean b) {
        isEditing = b;
    }
}
