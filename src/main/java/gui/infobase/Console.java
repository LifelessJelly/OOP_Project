package gui.infobase;

import controller.InfobaseMainframe;
import data.Staff;
import gui.DropShadowPanel;
import gui.ImageEmbedded;
import gui.JPanelImageButton;
import controller.ImageBase64;

import java.awt.*;

import javax.swing.*;


public class Console extends JPanel {
    private JPanel displayPanel;
    private final InfobaseMainframe main;
    private final CardLayout card;
    private JPanelImageButton viewApplicantButton;
    private JPanelImageButton viewSummaryButton;
    private JPanelImageButton settingsButton;
    private JMenuItem logout;
    private ApplicantShowAndEditLayer layer;


    public Console(InfobaseMainframe main){
        this.main = main;
        card = new CardLayout();
        this.setLayout(new GridBagLayout());

        initComponents();
        initListeners();
    }

    private void initComponents() {

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(main.isDarkMode() ? Color.BLACK : Color.WHITE);
        titlePanel.setLayout(new GridBagLayout());

        JLabel companyIcon = new JLabel(new ImageIcon(main.getImage("MiniCompanyLogo")));
        GridBagConstraints companyIconConstraints = new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 20, 0), 0, 0);
        titlePanel.add(companyIcon, companyIconConstraints);

        String jobRole = "";
        if (main.getController().getUser().authorised(Staff.MANAGER)){
            jobRole = main.getLocale("Console.JMenu.manager");
        }
        else if (main.getController().getUser().authorised(Staff.HR)){
            jobRole = "(HR)";
        }
        JMenuBar mb = new JMenuBar();
        mb.setBackground(main.isDarkMode() ? Color.BLACK : Color.WHITE);
        mb.setBorder(BorderFactory.createEmptyBorder());
        mb.putClientProperty("JMenuBar.selectionBackground", main.isDarkMode() ? Color.BLACK : Color.WHITE);

        JMenu userMenu = new JMenu();
        userMenu.setIcon(new ImageIcon(ImageBase64.base64ToImage(ImageEmbedded.PROFILE_ICON)));
        userMenu.setText(main.getController().getUser().getDisplayName() + ' ' + jobRole);
        userMenu.setFont(userMenu.getFont().deriveFont(18f));


        logout = new JMenuItem("Logout");
        logout.setFont(logout.getFont().deriveFont(18f));
        userMenu.add(logout);

        mb.add(userMenu);

        GridBagConstraints userLabelConstraints = new GridBagConstraints(1, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
        titlePanel.add(mb, userLabelConstraints);

        GridBagConstraints titlePanelConstraints = new GridBagConstraints(0, 0, 2, 1, 1, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0);

        this.add(titlePanel, titlePanelConstraints);

        JPanel buttonSidePanel = new JPanel();
        buttonSidePanel.setLayout(new GridBagLayout());
        buttonSidePanel.setBackground(main.isDarkMode() ? Color.BLACK : Color.WHITE);
        ((GridBagLayout)buttonSidePanel.getLayout()).rowWeights = new double[]{0, 0, 1e-4};

        viewApplicantButton = new JPanelImageButton(main.getLocale("Console.JPanelImageButton.viewApplicants"), ImageEmbedded.ViewApplicantsIcon.VIEWAPPLICANTS_OFF, ImageBase64.imageToBase64(main.getImage("ViewApplicantsIcon")), 60, 60, JPanelImageButton.BOTTOM);
        viewApplicantButton.setButtonBackground(main.isDarkMode() ? Color.BLACK : Color.WHITE);
        GridBagConstraints viewApplicantConstraints = new GridBagConstraints(0, 0, 1, 1, 1, 0,
                GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
        viewApplicantButton.setFontSize(14f);
        viewApplicantButton.setTextBoxSize(new Dimension(100, 20));
        buttonSidePanel.add(viewApplicantButton, viewApplicantConstraints);


        viewSummaryButton = new JPanelImageButton(main.getLocale("Console.JPanelImageButton.viewSummary"), ImageEmbedded.ViewSummaryIcon.VIEWSUMMARY_OFF, ImageBase64.imageToBase64(main.getImage("ViewSummaryIcon")), 60, 60, JPanelImageButton.BOTTOM);
        viewSummaryButton.setButtonBackground(main.isDarkMode() ? Color.BLACK : Color.WHITE);
        if (!main.getController().getUser().authorised(Staff.MANAGER)) {
            viewSummaryButton.setVisible(false);
        }
        GridBagConstraints viewSummaryConstraints = new GridBagConstraints(0, 1, 1, 1, 1, 0,
                GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
        viewSummaryButton.setFontSize(15f);
        viewSummaryButton.setTextBoxSize(new Dimension(100, 20));
        buttonSidePanel.add(viewSummaryButton, viewSummaryConstraints);


        settingsButton = new JPanelImageButton(main.getLocale("Console.JPanelImageButton.settings"), ImageEmbedded.SettingsIcon.SETTINGS_OFF, ImageBase64.imageToBase64(main.getImage("SettingsIcon")), 60, 60, JPanelImageButton.BOTTOM);
        settingsButton.setButtonBackground(main.isDarkMode() ? Color.BLACK : Color.WHITE);
        GridBagConstraints settingsConstraints = new GridBagConstraints(0, 2, 1, 1, 1, 0,
                GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
        settingsButton.setFontSize(15f);
        settingsButton.setTextBoxSize(new Dimension(100, 20));
        buttonSidePanel.add(settingsButton, settingsConstraints);


        GridBagConstraints buttonPanelConstraints = new GridBagConstraints(0, 1, 1, 1, 0, 1,
                GridBagConstraints.NORTHWEST, GridBagConstraints.VERTICAL, new Insets(0, 0, 0, 0), 0, 0);

        this.add(buttonSidePanel, buttonPanelConstraints);

        displayPanel = new DropShadowPanel(10);
        displayPanel.setLayout(card);
        showApplicantListPage();
//        showApplicantKeyingPage();

        GridBagConstraints displayPanelConstraints = new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTHEAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
        this.add(displayPanel, displayPanelConstraints);
    }

    private void initListeners() {
        viewApplicantButton.addActionListener(e -> {
            if (main.isEditing()) {
                if (getDialog() == JOptionPane.YES_OPTION){
                    showApplicantListPage();
                }
                return;
            }
            showApplicantListPage();
        });

        viewSummaryButton.addActionListener(e -> {
            if (main.isEditing()) {
                if (getDialog() == JOptionPane.YES_OPTION){
                    showSummaryPage();
                }
                return;
            }
            showSummaryPage();
        });

        settingsButton.addActionListener(e -> {
            if (main.isEditing()) {
                if (getDialog() == JOptionPane.YES_OPTION) {
                    showSettingsPage();
                }
                return;
            }
            showSettingsPage();
        });

        logout.addActionListener(e -> main.logout());
    }

    private void addApplicantLayeredPane(){
        layer = new ApplicantShowAndEditLayer(main);
        displayPanel.add(layer, "ApplicantShowAndEditLayer");
        card.show(displayPanel, "ApplicantShowAndEditLayer");
        main.getContentPane().validate();
        main.getContentPane().repaint();
    }

    public void showApplicantListPage(){
        main.setIsEditing(false);
        main.getController().refreshIndices();
        addApplicantLayeredPane();
        System.gc();
    }

    public void showApplicantWithAnimation(){
        main.setIsEditing(false);
        main.getController().refreshIndices();
        layer.switchToApplicantListPage();
        main.getContentPane().validate();
        main.getContentPane().repaint();

        System.gc();
    }

    public void showEditApplicant(int index){
        main.setIsEditing(true);
        layer.switchToEditApplicantPage(index);
        main.getContentPane().validate();
        main.getContentPane().repaint();

        System.gc();
    }

    public void showAddApplicant(){
        AddApplicant addApplicant = new AddApplicant(main);
        main.setIsEditing(true);
        displayPanel.add(addApplicant, "AddApplicant");
        card.show(displayPanel, "AddApplicant");

        System.gc();
    }

    public void showSummaryPage() {
        SummaryPage summaryPage = new SummaryPage(main);
        main.setIsEditing(false);
        main.getController().refreshIndices();
        displayPanel.add(summaryPage, "SummaryPage");
        card.show(displayPanel, "SummaryPage");
    }

    public void showSettingsPage(){
        Settings settings=new Settings(main);
        main.setIsEditing(false);
        displayPanel.add(settings, "Settings");
        card.show(displayPanel,"Settings");
    }

    public void showApplicantKeyingPage(int type, int index){
        ApplicantInfoKeyingPage keyingPage = new ApplicantInfoKeyingPage(index, main, type);
        main.setIsEditing(true);
        displayPanel.add(keyingPage, "ApplicantInfoKeyingPage");
        card.show(displayPanel, "ApplicantInfoKeyingPage");
    }

    private int getDialog(){
        return JOptionPane.showConfirmDialog(null, main.getLocale("Console.JOptionPane.discardConfirm"), "", JOptionPane.YES_NO_OPTION);
    }



}
