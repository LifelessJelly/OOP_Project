package gui.registration;

import controller.RegistrationMainframe;
import data.ApplicantExperience;
import gui.SlidingPanel;

import javax.swing.*;
import java.awt.*;
import java.time.Year;
import java.util.Objects;

public class JobExperiences extends SlidingPanel {
    private GridBagLayout layout;
    private JLabel prevExperienceLabel;
    private JLabel noExperienceSkipLabel;
    private JScrollPane experienceScroller;
    private JList<String> experienceList;
    private JLabel companyLabel;
    private JTextField companyField;
    private JLabel positionLabel;
    private JTextField positionField;
    private JPanel yearPanel;
    private JComboBox<String> startYearBox;
    private JComboBox<String> endYearBox;
    private JPanel addRemovePanel;
    private JPanel nextPrevPagePanel;
    private GridBagConstraints prevExperienceConstraints;
    private GridBagConstraints noExperienceSkipConstraints;
    private GridBagConstraints experienceScrollerConstraints;
    private GridBagConstraints companyConstraints;
    private GridBagConstraints companyFieldConstraints;
    private GridBagConstraints positionConstraints;
    private GridBagConstraints positionFieldConstraints;
    private GridBagConstraints yearConstraints;
    private GridBagConstraints addRemovePanelConstraints;
    private GridBagConstraints nextPrevPagePanelConstraints;

    public JobExperiences(RegistrationMainframe registrationMainframe) {
        this.registrationMainframe = registrationMainframe;
        initComponents();
    }

    private void initComponents() {

        this.movingInsets = new Insets(0, 0, 5, 0);
        layout = new GridBagLayout();

        prevExperienceConstraints = new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        noExperienceSkipConstraints = new GridBagConstraints(0, 1, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        experienceScrollerConstraints = new GridBagConstraints(0, 2, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        companyConstraints = new GridBagConstraints(0, 3, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        companyFieldConstraints = new GridBagConstraints(0, 4, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        positionConstraints = new GridBagConstraints(0, 5, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        positionFieldConstraints = new GridBagConstraints(0, 6, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        yearConstraints = new GridBagConstraints(0, 7, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        addRemovePanelConstraints = new GridBagConstraints(0, 8, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        nextPrevPagePanelConstraints = new GridBagConstraints(0, 9, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);

        this.setLayout(layout);

        prevExperienceLabel = new JLabel("Indicate your previous job experience:");
        prevExperienceLabel.setFont(prevExperienceLabel.getFont().deriveFont(22f));
        prevExperienceLabel.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(prevExperienceLabel, prevExperienceConstraints);

        noExperienceSkipLabel = new JLabel("Click next if you don't have any prior experience");
        noExperienceSkipLabel.setFont(noExperienceSkipLabel.getFont().deriveFont(18f));
        noExperienceSkipLabel.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(noExperienceSkipLabel, noExperienceSkipConstraints);

        experienceScroller = new JScrollPane();
        experienceScroller.setMinimumSize(new Dimension(640, 100));
        experienceScroller.setPreferredSize(new Dimension(640, 100));
        experienceScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        experienceList = new JList<>();
        experienceList.setFont(experienceList.getFont().deriveFont(18f));
        experienceList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        experienceScroller.setViewportView(experienceList);
        this.add(experienceScroller, experienceScrollerConstraints);

        companyLabel = new JLabel("Company:");
        companyLabel.setFont(companyLabel.getFont().deriveFont(18f));
        companyLabel.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(companyLabel, companyConstraints);

        companyField = new JTextField();
        companyField.setFont(companyField.getFont().deriveFont(18f));
        companyField.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(companyField, companyFieldConstraints);

        positionLabel = new JLabel("Position:");
        positionLabel.setFont(positionLabel.getFont().deriveFont(18f));
        positionLabel.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(positionLabel, positionConstraints);

        positionField = new JTextField();
        positionField.setFont(positionField.getFont().deriveFont(18f));
        positionField.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(positionField, positionFieldConstraints);

        yearPanel = new JPanel();
        yearPanel.setLayout(new GridLayout());

        startYearBox = new JComboBox<>(new String[]{"Start Year"});
        for (int i = Year.now().getValue(); i > 1899; --i){
            startYearBox.addItem(String.valueOf(i));
        }
        startYearBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        startYearBox.setFont(startYearBox.getFont().deriveFont(18f));
        GridBagConstraints startYearConstraints = new GridBagConstraints(0, 0, 1, 1, 1, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0);
        yearPanel.add(startYearBox, startYearConstraints);

        endYearBox = new JComboBox<>(new String[]{"End Year"});
        endYearBox.setEnabled(false);
        endYearBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        endYearBox.setFont(endYearBox.getFont().deriveFont(18f));
        GridBagConstraints endYearBoxConstraints = new GridBagConstraints(1, 0, 1, 1, 1, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0);
        yearPanel.add(endYearBox, endYearBoxConstraints);
        this.add(yearPanel, yearConstraints);

        addRemovePanel = new JPanel();
        addRemovePanel.setLayout(new GridBagLayout());

        JButton addButton = new JButton("Add");
        addButton.setFont(addButton.getFont().deriveFont(18f));
        GridBagConstraints addButtonConstraints = new GridBagConstraints(0, 0, 1, 1, 1, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0);
        addRemovePanel.add(addButton, addButtonConstraints);

        JButton removeButton = new JButton("Remove");
        removeButton.setFont(removeButton.getFont().deriveFont(18f));
        GridBagConstraints removeButtonConstraints = new GridBagConstraints(1, 0, 1, 1, 1, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0);
        addRemovePanel.add(removeButton, removeButtonConstraints);
        this.add(addRemovePanel, addRemovePanelConstraints);

        nextPrevPagePanel = new JPanel();
        nextPrevPagePanel.setLayout(new GridBagLayout());
        JButton backButton = new JButton("Back");
        backButton.setFont(backButton.getFont().deriveFont(18f));
        backButton.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints backButtonConstraints = new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0);
        nextPrevPagePanel.add(backButton, backButtonConstraints);

        JPanel blankSpacer = new JPanel();
        blankSpacer.setMinimumSize(new Dimension(640, 0));
        blankSpacer.setPreferredSize(new Dimension(640, 0));
        GridBagConstraints blankSpacerConstraints = new GridBagConstraints(1, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0);
        nextPrevPagePanel.add(blankSpacer, blankSpacerConstraints);

        JButton nextButton = new JButton("Next");
        nextButton.setFont(nextButton.getFont().deriveFont(18f));
        nextButton.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints nextButtonConstraints = new GridBagConstraints(2, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0);
        nextPrevPagePanel.add(nextButton, nextButtonConstraints);
        this.add(nextPrevPagePanel, nextPrevPagePanelConstraints);


        startYearBox.addActionListener(e -> {
            if (!Objects.equals(startYearBox.getSelectedItem(), "Start Year")) {
                endYearBox.setEnabled(true);
                endYearBox.removeAllItems();
                endYearBox.addItem("End Year");
                for (int i = Year.now().getValue(); i >= Integer.parseInt((String) Objects.requireNonNull(startYearBox.getSelectedItem())); --i){
                    endYearBox.addItem(String.valueOf(i));
                }
            }
            else {
                endYearBox.setSelectedIndex(0);
                endYearBox.setEnabled(false);
            }
        });
        addButton.addActionListener(e -> {
            registrationMainframe.getController().addExperience(companyField.getText(), positionField.getText(),
                    Integer.parseInt((String) Objects.requireNonNull(startYearBox.getSelectedItem())),
                    Integer.parseInt((String) Objects.requireNonNull(endYearBox.getSelectedItem())));
            experienceList.setModel(getExperienceListModel());
            companyField.setText("");
            positionField.setText("");
            startYearBox.setSelectedIndex(0);
        });

        removeButton.addActionListener(e -> {
            if (experienceList.getSelectedIndex() == -1) {
                //return early
                return;
            }
            registrationMainframe.getController().removeExperience(experienceList.getSelectedIndex());
            experienceList.setModel(getExperienceListModel());
        });

        nextButton.addActionListener(e -> {
            registrationMainframe.getController().registerExperience(registrationMainframe.getController().getExperience());
            registrationMainframe.panelOutroRight();
        });
        backButton.addActionListener(e -> registrationMainframe.panelOutroLeft());
    }

    private DefaultListModel<String> getExperienceListModel() {
        ApplicantExperience[] applicantSkills = registrationMainframe.getController().getExperience();
        DefaultListModel<String> skillsListModel = new DefaultListModel<>();
        for (ApplicantExperience applicantSkill : applicantSkills) {
            skillsListModel.addElement(applicantSkill.toString());
        }
        return skillsListModel;
    }
    @Override
    protected void updateAnimation() {
        layout.setConstraints(prevExperienceLabel, prevExperienceConstraints);
        layout.setConstraints(noExperienceSkipLabel, noExperienceSkipConstraints);
        layout.setConstraints(experienceScroller, experienceScrollerConstraints);
        layout.setConstraints(companyLabel, companyConstraints);
        layout.setConstraints(companyField, companyFieldConstraints);
        layout.setConstraints(positionLabel, positionConstraints);
        layout.setConstraints(positionField, positionFieldConstraints);
        layout.setConstraints(yearPanel, yearConstraints);
        layout.setConstraints(addRemovePanel, addRemovePanelConstraints);
        layout.setConstraints(nextPrevPagePanel, nextPrevPagePanelConstraints);
        this.revalidate();
        this.repaint();
    }
}
