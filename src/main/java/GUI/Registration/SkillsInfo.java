package GUI.Registration;

import GUI.SlidingPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SkillsInfo extends SlidingPanel {
    JLabel tickSkillsetsLabel;
    JCheckBox programmingCheckBox;
    JCheckBox industrialCheckBox;
    JCheckBox artisticCheckBox;
    JCheckBox communicationCheckBox;
    JCheckBox otherCheckBox;
    JScrollPane otherSkillsetsScrollPane;
    JList<String> otherSkillsetsList;
    JPanel addRemoveButtonsPanel;
    JButton addButton;
    JButton removeButton;
    JTextField skillsField;
    JPanel nextBackButtonsPanel;
    JButton nextButton;
    JPanel blankSpacer;
    JButton backButton;
    GridBagConstraints tickSkillsetsConstraints;
    GridBagConstraints programmingPanelConstraints;
    GridBagConstraints industrialPanelConstraints;
    GridBagConstraints artisticPanelConstraints;
    GridBagConstraints communicationPanelConstraints;
    GridBagConstraints otherPanelConstraints;
    GridBagConstraints otherSkillsetsScrollConstraints;
    GridBagConstraints addRemoveButtonConstraints;
    GridBagConstraints skillsFieldConstraints;
    GridBagConstraints nextBackButtonConstraints;
    GridBagLayout layout;

    public SkillsInfo(RegistrationMainframe registrationMainframe){
        this.registrationMainframe = registrationMainframe;
        initComponents();
    }

    private void initComponents() {

        this.movingInsets = new Insets(0, 0, 5, 0);
        layout = new GridBagLayout();
        this.setLayout(layout);

        tickSkillsetsConstraints = new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        programmingPanelConstraints = new GridBagConstraints(0, 1, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        industrialPanelConstraints = new GridBagConstraints(0, 2, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        artisticPanelConstraints = new GridBagConstraints(0, 3, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        communicationPanelConstraints = new GridBagConstraints(0, 4, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        otherPanelConstraints = new GridBagConstraints(0, 5, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        otherSkillsetsScrollConstraints = new GridBagConstraints(0, 6, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        addRemoveButtonConstraints = new GridBagConstraints(0, 7, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        skillsFieldConstraints = new GridBagConstraints(0, 8, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        nextBackButtonConstraints = new GridBagConstraints(0, 9, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);



        tickSkillsetsLabel = new JLabel("Please select the following skillsets you have:");
        tickSkillsetsLabel.setFont(tickSkillsetsLabel.getFont().deriveFont(22f));
        tickSkillsetsLabel.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(tickSkillsetsLabel, tickSkillsetsConstraints);

        programmingCheckBox = new JCheckBox("Programming");
        programmingCheckBox.setFont(programmingCheckBox.getFont().deriveFont(20f));
        this.add(programmingCheckBox, programmingPanelConstraints);

        industrialCheckBox = new JCheckBox("Industrial");
        industrialCheckBox.setFont(industrialCheckBox.getFont().deriveFont(20f));
        this.add(industrialCheckBox, industrialPanelConstraints);

        artisticCheckBox = new JCheckBox("Artistic");
        artisticCheckBox.setFont(artisticCheckBox.getFont().deriveFont(20f));
        this.add(artisticCheckBox, artisticPanelConstraints);

        communicationCheckBox = new JCheckBox("Communication");
        communicationCheckBox.setFont(communicationCheckBox.getFont().deriveFont(20f));
        this.add(communicationCheckBox, communicationPanelConstraints);

        otherCheckBox = new JCheckBox("Other");
        otherCheckBox.setFont(otherCheckBox.getFont().deriveFont(20f));
        this.add(otherCheckBox, otherPanelConstraints);

        otherSkillsetsScrollPane = new JScrollPane();
        otherSkillsetsList = new JList<>();
        otherSkillsetsList.setFont(otherSkillsetsList.getFont().deriveFont(20f));
        otherSkillsetsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        otherSkillsetsScrollPane.setViewportView(otherSkillsetsList);
        otherSkillsetsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        otherSkillsetsScrollPane.setVisible(false);
        this.add(otherSkillsetsScrollPane, otherSkillsetsScrollConstraints);

        addRemoveButtonsPanel = new JPanel();
        addRemoveButtonsPanel.setLayout(new GridBagLayout());
        addButton = new JButton("Add Skill");
        GridBagConstraints addButtonConstraints = new GridBagConstraints(0, 0, 1, 1, 1, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0);
        addButton.setFont(addButton.getFont().deriveFont(20f));
        addRemoveButtonsPanel.add(addButton, addButtonConstraints);

        removeButton = new JButton("Remove Skill");
        GridBagConstraints removeButtonConstraints = new GridBagConstraints(1, 0, 1, 1, 1, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0);
        removeButton.setFont(removeButton.getFont().deriveFont(20f));
        addRemoveButtonsPanel.add(removeButton, removeButtonConstraints);
        addRemoveButtonsPanel.setVisible(false);
        this.add(addRemoveButtonsPanel, addRemoveButtonConstraints);

        skillsField = new JTextField();
        skillsField.setFont(skillsField.getFont().deriveFont(20f));
        skillsField.setVisible(false);
        this.add(skillsField, skillsFieldConstraints);

        nextBackButtonsPanel = new JPanel();
        nextBackButtonsPanel.setLayout(new GridBagLayout());
        nextButton = new JButton("Next");
        nextButton.setFont(nextButton.getFont().deriveFont(20f));
        GridBagConstraints nextButtonConstraints = new GridBagConstraints(2, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0);
        nextBackButtonsPanel.add(nextButton, nextButtonConstraints);

        blankSpacer = new JPanel();
        blankSpacer.setMinimumSize(new Dimension(640, 0));
        blankSpacer.setPreferredSize(new Dimension(640, 0));
        GridBagConstraints blankSpacerConstraints = new GridBagConstraints(1, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0);
        nextBackButtonsPanel.add(blankSpacer, blankSpacerConstraints);

        backButton = new JButton("Back");
        backButton.setFont(backButton.getFont().deriveFont(20f));
        GridBagConstraints backButtonConstraints = new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0);
        nextBackButtonsPanel.add(backButton, backButtonConstraints);

        this.add(nextBackButtonsPanel, nextBackButtonConstraints);


        otherCheckBox.addActionListener(e -> {
            otherSkillsetsScrollPane.setVisible(otherCheckBox.isSelected());
            addRemoveButtonsPanel.setVisible(otherCheckBox.isSelected());
            skillsField.setVisible(otherCheckBox.isSelected());
            updateAnimation();
        });

        addButton.addActionListener(e -> {
            if (skillsField.getText().isEmpty()){
                return;
            }
            registrationMainframe.getController().addSkill(skillsField.getText());
            otherSkillsetsList.setModel(getSkillsListModel());
            skillsField.setText("");
        });
        removeButton.addActionListener(e -> {
            if (otherSkillsetsList.getSelectedIndex() == -1) {
                //return early
                return;
            }
            registrationMainframe.getController().removeSkill(otherSkillsetsList.getSelectedIndex());
            otherSkillsetsList.setModel(getSkillsListModel());
        });
        backButton.addActionListener(e -> registrationMainframe.panelOutroLeft());
        nextButton.addActionListener(e -> {
            ArrayList<String> defaultSkillsArrList = new ArrayList<>();
            for (Component component : this.getComponents()) {
                if (component instanceof JCheckBox) {
                    if (((JCheckBox) component).isSelected()) {
                        defaultSkillsArrList.add(((JCheckBox) component).getText());
                    }
                }
            }
            registrationMainframe.getController().registerAllSkills(defaultSkillsArrList.toArray(new String[0]), registrationMainframe.getController().getSkills());
            registrationMainframe.panelOutroRight();
        });

    }

    private DefaultListModel<String> getSkillsListModel() {
        String[] applicantSkills = registrationMainframe.getController().getSkills();
        DefaultListModel<String> skillsListModel = new DefaultListModel<>();
        for (String applicantSkill : applicantSkills) {
            skillsListModel.addElement(applicantSkill);
        }
        return skillsListModel;
    }


    @Override
    protected void updateAnimation(){
        layout.setConstraints(tickSkillsetsLabel, tickSkillsetsConstraints);
        layout.setConstraints(programmingCheckBox, programmingPanelConstraints);
        layout.setConstraints(industrialCheckBox, industrialPanelConstraints);
        layout.setConstraints(artisticCheckBox, artisticPanelConstraints);
        layout.setConstraints(communicationCheckBox, communicationPanelConstraints);
        layout.setConstraints(otherCheckBox, otherPanelConstraints);
        layout.setConstraints(otherSkillsetsScrollPane, otherSkillsetsScrollConstraints);
        layout.setConstraints(addRemoveButtonsPanel, addRemoveButtonConstraints);
        layout.setConstraints(skillsField, skillsFieldConstraints);
        layout.setConstraints(nextBackButtonsPanel, nextBackButtonConstraints);
        this.revalidate();
        this.repaint();
    }
}
