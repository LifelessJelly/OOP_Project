package gui.infobase;

import controller.InfobaseMainframe;
import javafx.scene.control.ComboBox;

import javax.swing.*;
import java.awt.*;

public class ApplicantInfoKeyingPage extends JPanel {
    private InfobaseMainframe main;

    public ApplicantInfoKeyingPage(int index, InfobaseMainframe main) {
        this.main = main;
        this.setLayout(new GridBagLayout());

        initComponents();
        initListeners();
    }

    private void initComponents() {
        JPanel datePanels = new JPanel();
        datePanels.setLayout(new GridBagLayout());
        ComboBox<String> dayComboBox = new ComboBox<>();

    }

    private void initListeners() {

    }
}
