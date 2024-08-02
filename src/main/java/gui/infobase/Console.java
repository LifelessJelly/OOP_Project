package gui.infobase;

import controller.InfobaseMainframe;
import data.Applicant;
import data.Staff;
import gui.DropShadowPanel;
import gui.ImageEmbedded;
import gui.JPanelImageButton;
import subsystems.ImageBase64;

import java.awt.*;

import javax.swing.*;


public class Console extends JPanel {
    private JPanel displayPanel;
    private String miau="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";
    private final InfobaseMainframe main;
    private final CardLayout card;
    private EditApplicant editApplicant;
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
            jobRole = "(Manager)";
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

        viewApplicantButton = new JPanelImageButton("View Applicants", miau, miau, 60, 60, JPanelImageButton.BOTTOM);
        viewApplicantButton.setButtonBackground(main.isDarkMode() ? Color.BLACK : Color.WHITE);
        GridBagConstraints viewApplicantConstraints = new GridBagConstraints(0, 0, 1, 1, 1, 0,
                GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
        viewApplicantButton.setFontSize(14f);
        viewApplicantButton.setTextBoxSize(new Dimension(100, 20));
        buttonSidePanel.add(viewApplicantButton, viewApplicantConstraints);


        viewSummaryButton = new JPanelImageButton("View Summary", miau, miau, 60, 60, JPanelImageButton.BOTTOM);
        viewSummaryButton.setButtonBackground(main.isDarkMode() ? Color.BLACK : Color.WHITE);
        if (!main.getController().getUser().authorised(Staff.MANAGER)) {
            viewSummaryButton.setVisible(false);
        }
        GridBagConstraints viewSummaryConstraints = new GridBagConstraints(0, 1, 1, 1, 1, 0,
                GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
        viewSummaryButton.setFontSize(15f);
        viewSummaryButton.setTextBoxSize(new Dimension(100, 20));
        buttonSidePanel.add(viewSummaryButton, viewSummaryConstraints);


        settingsButton = new JPanelImageButton("Settings", miau, miau, 60, 60, JPanelImageButton.BOTTOM);
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

        System.out.println("panel width: " + displayPanel.getSize().getWidth() + " panel height: " + displayPanel.getSize().getHeight());

    }

    private void initListeners() {
        viewApplicantButton.addActionListener(e -> showApplicantListPage());
        viewSummaryButton.addActionListener(e -> showSummaryPage());
        settingsButton.addActionListener(e -> {
            if (main.getLanguage().equals("en")){
                main.setLanguage("cn");
            }
            else {
                main.setLanguage("en");
            }
            main.reload();
        });
        logout.addActionListener(e -> {
            main.logout();
        });
    }

    private void addApplicantLayeredPane(){
        layer = new ApplicantShowAndEditLayer(main);
        displayPanel.add(layer, "ApplicantShowAndEditLayer");
        card.show(displayPanel, "ApplicantShowAndEditLayer");
        main.getContentPane().validate();
        main.getContentPane().repaint();
    }

    public void showApplicantListPage(){
        addApplicantLayeredPane();
        System.gc();
    }

    public void showApplicantWithAnimation(){
        layer.switchToApplicantListPage();
        main.getContentPane().validate();
        main.getContentPane().repaint();

        System.gc();
    }

    public void showEditApplicant(int index){
        layer.switchToEditApplicantPage(index);
        main.getContentPane().validate();
        main.getContentPane().repaint();

        System.gc();
    }

    public void showAddApplicant(){
        AddApplicant addApplicant = new AddApplicant(main);
        displayPanel.add(addApplicant, "AddApplicant");
        card.show(displayPanel, "AddApplicant");

        System.gc();
    }

    public void showSummaryPage() {
        SummaryPage summaryPage = new SummaryPage(main);
        displayPanel.add(summaryPage, "SummaryPage");
        card.show(displayPanel, "SummaryPage");
    }

    public void showApplicantKeyingPage(int type, int index){
        ApplicantInfoKeyingPage keyingPage = new ApplicantInfoKeyingPage(index, main, type);
        displayPanel.add(keyingPage, "ApplicantInfoKeyingPage");
        card.show(displayPanel, "ApplicantInfoKeyingPage");
    }



}
