package GUI.Registration;

import Controller.ApplicantRegistrator;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class Mainframe extends JFrame {

    private int panelIndex;
    List<SlidingPanel> panels;
    JPanel radioButtonsPanel;
    JRadioButton darkModeButton;
    JRadioButton lightModeButton;


    public Mainframe() {
        initComponents();
    }

    private void initComponents() {
        Component contentPane = getContentPane();
        this.setMinimumSize(new Dimension(960, 480));
        this.setLayout(new BorderLayout());

        radioButtonsPanel = new JPanel();
        radioButtonsPanel.setLayout(new GridBagLayout());
        darkModeButton = new JRadioButton("Dark Mode");
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
        radioButtonsPanel.add(darkModeButton);
        radioButtonsPanel.add(lightModeButton);
        this.add(radioButtonsPanel, BorderLayout.NORTH);

        darkModeButton.addActionListener(e -> {
            try {
                UIManager.setLookAndFeel(new FlatDarkLaf());
                SwingUtilities.updateComponentTreeUI(this);
                this.pack();
            } catch (UnsupportedLookAndFeelException ex) {
                throw new RuntimeException(ex);
            }
        });

        lightModeButton.addActionListener(e -> {
            try {
                UIManager.setLookAndFeel(new FlatLightLaf());
                SwingUtilities.updateComponentTreeUI(this);
                this.pack();
            } catch (UnsupportedLookAndFeelException ex) {
                throw new RuntimeException(ex);
            }
        });


        ApplicantRegistrator registrator = new ApplicantRegistrator();
        panels = Arrays.asList(new Welcome(this),new BasicUserInfo(this, registrator),new EmailVerficationCode(this, registrator));
        panels.get(0).setVisible(true);
        this.add(panels.get(0));
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

}




