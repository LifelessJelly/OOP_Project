package controller;

import data.Applicant;
import data.TranslationKey;
import data.TranslationTable;

import gui.infobase.EditApplicant;
import gui.infobase.ApplicantListPage;
import gui.infobase.ThemeChooser;
import subsystems.JsonReaderWriter;

import javax.swing.*;
import java.awt.*;

public class InfobaseMainframe extends JFrame {

    private String language;
    private final InfobaseController controller;
    private CardLayout cardLayout;



    public InfobaseMainframe(){
        this.language = "en";
        this.controller = new InfobaseController();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);
        this.setMinimumSize(new Dimension(1920, 1080));
        this.setPreferredSize(new Dimension(1920, 1080));
        this.add(new ApplicantListPage(this), "ApplicantListPage");
        cardLayout.show(this.getContentPane(), "ApplicantListPage");
//        ThemeChooser.setDarkBorealis();
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



    public void showEditApplicant(Applicant applicant) {

        this.add(new EditApplicant(applicant, this), "EditApplicant");
        cardLayout.show(this.getContentPane(), "EditApplicant");

        System.out.println("screen added successfully");
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

    public static void main(String[] args) {
        new InfobaseMainframe();
    }

    public InfobaseController getController() {
        return controller;
    }

    public void reload(){
        this.dispose();
        new InfobaseMainframe();
    }
}
