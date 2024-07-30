package gui.infobase;

import controller.InfobaseMainframe;
import gui.DateSelectorHelper;

import javax.swing.*;
import java.awt.*;

public class ApplicantInfoKeyingPage extends JPanel {
    private final InfobaseMainframe main;
    private JComboBox<Integer> dayComboBox;
    private JComboBox<String> monthComboBox;
    private JComboBox<Integer> yearComboBox;

    public ApplicantInfoKeyingPage(int index, InfobaseMainframe main) {
        this.main = main;
        this.setLayout(new GridBagLayout());

        initComponents();
        initListeners();
    }

    private void initComponents() {
        JPanel datePanel = new JPanel();
        datePanel.setLayout(new GridBagLayout());
        dayComboBox = DateSelectorHelper.comboBoxGetBaseDates();
        dayComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        dayComboBox.setFont(dayComboBox.getFont().deriveFont(18f));
        dayComboBox.setSelectedIndex(0);
        GridBagConstraints dayComboBoxConstraints = new GridBagConstraints(
                0, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 0), 0, 0);
        datePanel.add(dayComboBox, dayComboBoxConstraints);

        monthComboBox = DateSelectorHelper.comboBoxGetBaseMonths();
        monthComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        monthComboBox.setFont(monthComboBox.getFont().deriveFont(18f));
        monthComboBox.setSelectedIndex(0);
        GridBagConstraints monthComboBoxConstraints = new GridBagConstraints(
                1, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 0), 0, 0);
        datePanel.add(monthComboBox, monthComboBoxConstraints);

        yearComboBox = DateSelectorHelper.comboBoxGetBaseYearsReverse(2100);
        yearComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        yearComboBox.setFont(yearComboBox.getFont().deriveFont(18f));
        yearComboBox.setSelectedIndex(0);
        GridBagConstraints yearComboBoxConstraints = new GridBagConstraints(
                2, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 0), 0, 0);
        datePanel.add(yearComboBox, yearComboBoxConstraints);

        GridBagConstraints datePanelConstraints = new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 5, 0), 0, 0);

        this.add(datePanel, datePanelConstraints);

        JTextArea emailTextArea = new JTextArea();
        emailTextArea.setText("");


    }

    private void initListeners() {
        dayComboBox.addActionListener(DateSelectorHelper.datesUpdater(dayComboBox, monthComboBox, yearComboBox));

        monthComboBox.addActionListener(DateSelectorHelper.datesUpdater(dayComboBox, monthComboBox, yearComboBox));

        yearComboBox.addActionListener(DateSelectorHelper.datesUpdater(dayComboBox, monthComboBox, yearComboBox));
    }
}
