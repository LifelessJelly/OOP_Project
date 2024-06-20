package GUI.Registration;

import Controller.ApplicantRegistrator;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class Mainframe extends JFrame {

    private int panelIndex;
    List<SlidingPanel> panels;


    public Mainframe() {
        initComponents();
    }

    private void initComponents() {
        Component contentPane = getContentPane();
        this.setMinimumSize(new Dimension(960, 480));
        this.setLayout(new BorderLayout());
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
            panels.get(panelIndex).slideInLeft();
        }
    }
    public void panelOutroLeft() { //go back one page
        panels.get(panelIndex).slideOutLeft();
        panelIndex--;
    }
    public void panelIntroRight() {
        panels.get(panelIndex).slideOutRight();
    }

}




