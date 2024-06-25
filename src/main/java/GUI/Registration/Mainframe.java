package GUI.Registration;

import Controller.Controller;
import Controller.ApplicantRegistrator;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class Mainframe extends JFrame {

    private int panelIndex;
    List<SlidingPanel> panels;
    JPanel radioButtonsPanel;
    JRadioButton darkModeButton;
    JRadioButton lightModeButton;
    Controller controller;


    public Mainframe() {
        this.controller = new Controller();
        initComponents();
    }

    private void initComponents() {
        Component contentPane = getContentPane();
        this.setMinimumSize(new Dimension(1080, 720));
        this.setLayout(new BorderLayout());

        JPanel panelWest = new JPanel();
        panelWest.setMinimumSize(new Dimension(120, 0));
        panelWest.setPreferredSize(new Dimension(120, 0));
        this.add(panelWest, BorderLayout.WEST);

        JPanel panelEast = new JPanel();
        panelEast.setMinimumSize(new Dimension(120, 0));
        panelEast.setPreferredSize(new Dimension(120, 0));
        this.add(panelEast, BorderLayout.EAST);

        JPanel panelSouth = new JPanel();
        panelSouth.setMinimumSize(new Dimension(0, 60));
        panelSouth.setPreferredSize(new Dimension(0, 60));
        this.add(panelSouth, BorderLayout.SOUTH);


        radioButtonsPanel = new JPanel();
        radioButtonsPanel.setLayout(new GridBagLayout());
        ((GridBagLayout) radioButtonsPanel.getLayout()).columnWidths = new int[]{0, 0, 0};
        ((GridBagLayout) radioButtonsPanel.getLayout()).columnWeights = new double[]{0, 0, 1.0E-4};
        darkModeButton = new JRadioButton("Dark Mode");
        darkModeButton.setSelected(true);
        GridBagConstraints darkModeButtonConstraints = new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 5), 0, 0);
        lightModeButton = new JRadioButton("Light Mode");
        GridBagConstraints lightModeButtonConstraints = new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 5), 0, 0);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(darkModeButton);
        buttonGroup.add(lightModeButton);
        radioButtonsPanel.add(darkModeButton, darkModeButtonConstraints);
        radioButtonsPanel.add(lightModeButton, lightModeButtonConstraints);
        radioButtonsPanel.setMinimumSize(new Dimension(0, 60));
        radioButtonsPanel.setPreferredSize(new Dimension(0, 60));
        this.add(radioButtonsPanel, BorderLayout.NORTH);

        darkModeButton.addActionListener(e -> {
            try {
                UIManager.setLookAndFeel(new FlatDarkLaf());
                SwingUtilities.updateComponentTreeUI(this);
            } catch (UnsupportedLookAndFeelException ex) {
                throw new RuntimeException(ex);
            }
        });

        lightModeButton.addActionListener(e -> {
            try {
                UIManager.setLookAndFeel(new FlatLightLaf());
                SwingUtilities.updateComponentTreeUI(this);
            } catch (UnsupportedLookAndFeelException ex) {
                throw new RuntimeException(ex);
            }
        });
        panels = Arrays.asList(new Welcome(this), new BasicUserInfo(this), new EmailVerification(this), new SkillsInfo(this));
        panels.get(0).setVisible(true);
        this.add(panels.get(0), BorderLayout.CENTER);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(getOwner());
    }



    public void panelOutroRight() { //go forward one page
        panels.get(panelIndex).slideOutRight();
        panelIndex++;

    }
    public void panelIntroLeft() { // next page
        if (panelIndex >= panels.size()) {
            this.dispose();
        }
        else {
            this.add(panels.get(panelIndex));
            SwingUtilities.updateComponentTreeUI(this);
            panels.get(panelIndex).slideInLeft();
        }
    }
    public void panelOutroLeft() { //go back one page
        panels.get(panelIndex).slideOutLeft();
        panelIndex--;
    }
    public void panelIntroRight() {
        this.remove(panels.get(panelIndex+1));
        panels.get(panelIndex).slideInRight();
    }

    public ApplicantRegistrator getRegistrator() {
        return controller.getRegistrator();
    }

}




