package GUI.Registration;

import Controller.RegistrationController;
import GUI.SlidingPanel;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class RegistrationMainframe extends JFrame {

    private int panelIndex;
    List<SlidingPanel> panels;
    JPanel radioButtonsPanel;
    JRadioButton darkModeButton;
    JRadioButton lightModeButton;
    private final RegistrationController registrationController;
    GridBagConstraints mainPanelConstraints;
    private final static boolean BORDER_DEBUG_MODE = false;


    public RegistrationMainframe() {
        this.registrationController = new RegistrationController();
        mainPanelConstraints = new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
        initComponents();
    }

    private void initComponents() {
        this.setMinimumSize(new Dimension(1080, 720));
        this.setPreferredSize(new Dimension(1080, 720));
        this.setMinimumSize(new Dimension(0, 0));
        this.setLayout(new GridBagLayout());
        this.setTitle("DongHua JinLong Iron Glycine");

        LookAndFeel lightMode = new FlatLightLaf();
        LookAndFeel darkMode = new FlatDarkLaf();

        JPanel panelWest = new JPanel();
        panelWest.setMinimumSize(new Dimension(120, 0));
        panelWest.setPreferredSize(new Dimension(120, 0));
        GridBagConstraints westPanelConstraints = new GridBagConstraints(0, 1, 1, 1, 0.5, 0.1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
        if (BORDER_DEBUG_MODE) {
            panelWest.setBorder(BorderFactory.createLineBorder(Color.RED));
        }
        this.add(panelWest, westPanelConstraints);

        JPanel panelEast = new JPanel();
        panelEast.setMinimumSize(new Dimension(120, 0));
        panelEast.setPreferredSize(new Dimension(120, 0));
        GridBagConstraints eastPanelConstraints = new GridBagConstraints(2, 1, 1, 1, 0.5, 0.1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
        if (BORDER_DEBUG_MODE) {
            panelEast.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        }
        this.add(panelEast, eastPanelConstraints);

        JPanel panelSouth = new JPanel();
        panelSouth.setMinimumSize(new Dimension(0, 60));
        panelSouth.setPreferredSize(new Dimension(0, 60));
        GridBagConstraints southPanelConstraints = new GridBagConstraints(0, 2, 3, 1, 0.1, 0.1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
        if (BORDER_DEBUG_MODE) {
            panelSouth.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        }
        this.add(panelSouth, southPanelConstraints);


        radioButtonsPanel = new JPanel();
        radioButtonsPanel.setLayout(new GridBagLayout());
        ((GridBagLayout) radioButtonsPanel.getLayout()).columnWidths = new int[]{0, 0, 0};
        ((GridBagLayout) radioButtonsPanel.getLayout()).columnWeights = new double[]{0, 0, 1.0E-4};
        ((GridBagLayout) radioButtonsPanel.getLayout()).rowHeights = new int[]{0, 0};
        ((GridBagLayout) radioButtonsPanel.getLayout()).rowWeights = new double[]{0, 0, 1.0E-4};
        darkModeButton = new JRadioButton("Dark Mode");
        darkModeButton.setFont(darkModeButton.getFont().deriveFont(18f));
        darkModeButton.setSelected(true);
        GridBagConstraints darkModeButtonConstraints = new GridBagConstraints(0, 0, 1, 1, 0.0, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 5), 0, 0);
        lightModeButton = new JRadioButton("Light Mode");
        lightModeButton.setFont(lightModeButton.getFont().deriveFont(18f));
        GridBagConstraints lightModeButtonConstraints = new GridBagConstraints(1, 0, 1, 1, 0.0, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 5), 0, 0);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(darkModeButton);
        buttonGroup.add(lightModeButton);
        radioButtonsPanel.add(darkModeButton, darkModeButtonConstraints);
        radioButtonsPanel.add(lightModeButton, lightModeButtonConstraints);
        radioButtonsPanel.setMinimumSize(new Dimension(0, 60));
        radioButtonsPanel.setPreferredSize(new Dimension(0, 60));
        GridBagConstraints radioButtonsPanelConstraints = new GridBagConstraints(0, 0, 3, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
        if (BORDER_DEBUG_MODE) {
            radioButtonsPanel.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        }
        this.add(radioButtonsPanel, radioButtonsPanelConstraints);

        darkModeButton.addActionListener(e -> {
            try {
                UIManager.setLookAndFeel(darkMode);
                SwingUtilities.updateComponentTreeUI(this);
            } catch (UnsupportedLookAndFeelException ex) {
                throw new RuntimeException(ex);
            }
        });

        lightModeButton.addActionListener(e -> {

            try {
                UIManager.setLookAndFeel(lightMode);
                SwingUtilities.updateComponentTreeUI(this);
            } catch (UnsupportedLookAndFeelException ex) {
                throw new RuntimeException(ex);
            }
        });
        panels = Arrays.asList(
                new Welcome(this),
                new BasicUserInfo(this),
                new EmailVerification(this),
                new SkillsInfo(this),
                new JobExperiences(this),
                new UploadImage(this),
                new EndScreen(this));
        panels.get(0).setVisible(true);
        this.add(panels.get(0), mainPanelConstraints);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(getOwner());
    }

    public RegistrationController getController(){
        return registrationController;
    }



    public void panelOutroRight() { //go forward one page
        panels.get(panelIndex).slideOutRight();
        ++panelIndex;
    }
    public void panelIntroLeft() { // next page
        if (panelIndex >= panels.size()) {
            this.dispose();
        }
        else {
            this.getContentPane().add(panels.get(panelIndex), mainPanelConstraints);
            SwingUtilities.updateComponentTreeUI(this);
            this.getContentPane().remove(panels.get(panelIndex - 1));
            panels.get(panelIndex).slideInLeft();

        }
    }
    public void panelOutroLeft() { //go back one page
        if (panelIndex >= panels.size()) {
            System.out.println("Something has gone very wrong. Current panel index: " + panelIndex + ", Current panel size: " + panels.size() + ", Current Panel Objects: " + panels);
        }
        panels.get(panelIndex).slideOutLeft();
        --panelIndex;
    }
    public void panelIntroRight() {
        this.getContentPane().add(panels.get(panelIndex), mainPanelConstraints);
        SwingUtilities.updateComponentTreeUI(this);
        this.getContentPane().remove(panels.get(panelIndex+1));
        panels.get(panelIndex).slideInRight();
    }


}




