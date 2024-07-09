package GUI.infobase;

import data.Applicant;
import data.ApplicantExperience;
import GUI.ImageEmbedded;
import GUI.JPanelImageButton;

import javax.swing.*;
import java.awt.*;
import java.time.Year;

public class EditApplicant extends JPanel {
    InfobaseMainframe infobaseMainframe;
    Applicant applicant;
    JPanel picturePanel;
    JPanel detailsPanel;
    JButton applicantImageButton;
    JButton imageRemoveButton;
    JLabel applicantNameLabel;
    JTextField applicantNameField;
    JLabel applicantBirthday;
    JPanel birthdayPanel;
    JComboBox<Integer> dayComboBox;
    JComboBox<String> monthComboBox;
    JComboBox<Integer> yearComboBox;
    JLabel applicantNricLabel;
    JTextField applicantNricField;
    JLabel applicantEmail;
    JTextField applicantEmailField;
    JLabel applicantGenderLabel;
    JComboBox<String> applicantGenderComboBox;
    JLabel skillsLabel;
    JScrollPane skillPane;
    JList<String> skillsList;
    JPanel skillsButtonPanel;
    JButton addSkillButton;
    JButton removeSkillButton;
    JButton editSkillButton;
    JTextField editSkillTextField;
    JLabel applicantExperience;
    JScrollPane applicantExperiencePane;
    JList<String> applicantExperiencesList;
    JPanel applicantExperiencesButtonPanel;
    JButton addApplicantExperienceButton;
    JButton removeApplicantExperienceButton;
    JButton editApplicantExperienceButton;
    JLabel companyLabel;
    JTextField companyField;
    JLabel positionLabel;
    JTextField positionField;
    JPanel comboBoxPanel;
    JComboBox<String> startYearComboBox;
    JComboBox<String> endYearComboBox;
    JPanel updateChangesPanel;

    EditApplicant(Applicant applicant, InfobaseMainframe infobaseMainframe) {
        this.applicant = applicant;
        this.infobaseMainframe = infobaseMainframe;
        initComponents();
    }

    //TODO update all the getter methods such that they call the mainframe and then the controller and then the applicant method (damn MVC is so fucking stupid)
    // I also might simplifiy all of this code with BoxLayout instead of GridBagLayout since there is no need for flexibility
    private void initComponents() {

        this.setVisible(true);
        this.setLayout(new GridBagLayout());

        picturePanel = new JPanel();
        picturePanel.setLayout(new GridBagLayout());
        GridBagConstraints picturePanelConstraints = new GridBagConstraints(
                0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTHEAST, GridBagConstraints.NONE,
                new Insets(0, 0, 0, 5), 0, 0);
        picturePanel.setBorder(BorderFactory.createLineBorder(Color.black));

        //START INNER COMPONENTS
        {
            applicantImageButton = new JButton(new ImageIcon(applicant.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH)));
            GridBagConstraints applicantImageButtonConstraints = new GridBagConstraints(
                    0, 0, 1, 1, 0, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            picturePanel.add(applicantImageButton, applicantImageButtonConstraints);

            imageRemoveButton = new JButton("Remove image");
            imageRemoveButton.setFont(imageRemoveButton.getFont().deriveFont(18f));
            GridBagConstraints imageRemoveButtonConstraints = new GridBagConstraints(
                    0, 1, 1, 1, 0, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            picturePanel.add(imageRemoveButton, imageRemoveButtonConstraints);
        }
        //END INNER COMPONENTS
        this.add(picturePanel, picturePanelConstraints);

        detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridBagLayout());
        GridBagConstraints detailsPanelConstraints = new GridBagConstraints(
                1, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.NONE,
                new Insets(0, 0, 5, 0), 0, 0);

        //START INNER COMPONENTS
        {
            applicantNameLabel = new JLabel("Applicant Name:");
            applicantNameLabel.setFont(applicantNameLabel.getFont().deriveFont(18f));
            applicantNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
            GridBagConstraints applicantNameLabelConstraints = new GridBagConstraints(
                    0, 0, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            detailsPanel.add(applicantNameLabel, applicantNameLabelConstraints);

            applicantNameField = new JTextField();
            applicantNameField.setText(applicant.getName());
            applicantNameField.setFont(applicantNameField.getFont().deriveFont(18f));
            GridBagConstraints applicantNameFieldConstraints = new GridBagConstraints(
                    0, 1, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            detailsPanel.add(applicantNameField, applicantNameFieldConstraints);

            applicantBirthday = new JLabel("Birthday:");
            applicantNameLabel.setFont(applicantNameLabel.getFont().deriveFont(18f));
            applicantBirthday.setHorizontalAlignment(SwingConstants.LEFT);
            GridBagConstraints applicantBirthdayConstraints = new GridBagConstraints(
                    0, 2, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            detailsPanel.add(applicantBirthday, applicantBirthdayConstraints);

            birthdayPanel = new JPanel();
            birthdayPanel.setLayout(new GridBagLayout());
            GridBagConstraints birthdayPanelConstraints = new GridBagConstraints(0, 3, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);

            //START INNER COMPONENTS
            {
                dayComboBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31});
                dayComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
                dayComboBox.setFont(dayComboBox.getFont().deriveFont(18f));
                GridBagConstraints dayComboBoxConstraints = new GridBagConstraints(
                        0, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0);
                birthdayPanel.add(dayComboBox, dayComboBoxConstraints);

                monthComboBox = new JComboBox<>(new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"});
                monthComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
                monthComboBox.setFont(monthComboBox.getFont().deriveFont(18f));
                GridBagConstraints monthComboBoxConstraints = new GridBagConstraints(
                        1, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0);
                birthdayPanel.add(monthComboBox, monthComboBoxConstraints);

                yearComboBox = new JComboBox<>();
                for (int i = Year.now().getValue(); i > 1899; --i) {
                    yearComboBox.addItem(i);
                }
                yearComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
                yearComboBox.setFont(yearComboBox.getFont().deriveFont(18f));
                GridBagConstraints yearComboBoxConstraints = new GridBagConstraints(
                        2, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0);
                birthdayPanel.add(yearComboBox, yearComboBoxConstraints);
            }
            //END INNER COMPONENTS
            detailsPanel.add(birthdayPanel, birthdayPanelConstraints);

            applicantNricLabel = new JLabel("NRIC:");
            applicantNricLabel.setFont(applicantNricLabel.getFont().deriveFont(18f));
            applicantNricLabel.setHorizontalAlignment(SwingConstants.LEFT);
            GridBagConstraints applicantNricLabelConstraints = new GridBagConstraints(
                    0, 4, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            detailsPanel.add(applicantNricLabel, applicantNricLabelConstraints);

            applicantNricField = new JTextField();
            applicantNricField.setText(applicant.getNRIC());
            applicantNricField.setFont(applicantNricField.getFont().deriveFont(18f));
            GridBagConstraints applicantNricFieldConstraints = new GridBagConstraints(
                    0, 5, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            detailsPanel.add(applicantNricField, applicantNricFieldConstraints);

            applicantEmail = new JLabel("Email:");
            applicantEmail.setFont(applicantEmail.getFont().deriveFont(18f));
            applicantEmail.setHorizontalAlignment(SwingConstants.LEFT);
            GridBagConstraints applicantEmailConstraints = new GridBagConstraints(
                    0, 6, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            detailsPanel.add(applicantEmail, applicantEmailConstraints);

            applicantEmailField = new JTextField();
            applicantEmailField.setText(applicant.getEmail());
            applicantEmailField.setFont(applicantEmailField.getFont().deriveFont(18f));
            GridBagConstraints applicantEmailFieldConstraints = new GridBagConstraints(
                    0, 7, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            detailsPanel.add(applicantEmailField, applicantEmailFieldConstraints);

            applicantGenderLabel = new JLabel("Gender:");
            applicantGenderLabel.setFont(applicantGenderLabel.getFont().deriveFont(18f));
            applicantGenderLabel.setHorizontalAlignment(SwingConstants.LEFT);
            GridBagConstraints applicantGenderLabelConstraints = new GridBagConstraints(
                    0, 8, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            detailsPanel.add(applicantGenderLabel, applicantGenderLabelConstraints);

            applicantGenderComboBox = new JComboBox<>(new String[] {
                    "Male",
                    "Female",
                    "Transgender Male",
                    "Transgender Female",
                    "Non-binary",
                    "Non-binary Transgender",
                    "Queer",
                    "Binary",
                    "Bigender/Genderfluid",
                    "Intergender",
                    "Xenogender",
                    "Third gender",
                    "Others"
            });
            applicantGenderComboBox.setFont(applicantGenderComboBox.getFont().deriveFont(18f));
            GridBagConstraints applicantGenderComboBoxConstraints = new GridBagConstraints(
                    0, 9, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            detailsPanel.add(applicantGenderComboBox, applicantGenderComboBoxConstraints);

            skillsLabel = new JLabel("Skills:");
            skillsLabel.setFont(skillsLabel.getFont().deriveFont(18f));
            skillsLabel.setHorizontalAlignment(SwingConstants.LEFT);
            GridBagConstraints skillsLabelConstraints = new GridBagConstraints(
                    0, 10, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            detailsPanel.add(skillsLabel, skillsLabelConstraints);

            skillPane = new JScrollPane();
            skillPane.setMinimumSize(new Dimension(0, 100));
            skillPane.setPreferredSize(new Dimension(0, 100));
            GridBagConstraints skillPaneConstraints = new GridBagConstraints(0, 11, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);

            skillsList = new JList<>();
            skillsList.setFont(skillsList.getFont().deriveFont(18f));
            skillsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            skillsList.setModel(getSkillsModel());
            skillPane.setViewportView(skillsList);
            detailsPanel.add(skillPane, skillPaneConstraints);

            skillsButtonPanel = new JPanel();
            skillsButtonPanel.setLayout(new GridBagLayout());
            GridBagConstraints skillsButtonPanelConstraints = new GridBagConstraints(0, 12, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(5, 0, 0, 0), 0, 0);
            {
                addSkillButton = new JButton("Add");
                addSkillButton.setFont(addSkillButton.getFont().deriveFont(18f));
                addSkillButton.setHorizontalAlignment(SwingConstants.CENTER);
                GridBagConstraints addSkillButtonConstraints = new GridBagConstraints(0, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0);
                skillsButtonPanel.add(addSkillButton, addSkillButtonConstraints);

                removeSkillButton = new JButton("Remove");
                removeSkillButton.setFont(removeSkillButton.getFont().deriveFont(18f));
                GridBagConstraints removeSkillButtonConstraints = new GridBagConstraints(1, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0);
                skillsButtonPanel.add(removeSkillButton, removeSkillButtonConstraints);

                editSkillButton = new JButton("Edit");
                editSkillButton.setFont(editSkillButton.getFont().deriveFont(18f));
                GridBagConstraints editSkillButtonConstraints = new GridBagConstraints(2, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0);
                skillsButtonPanel.add(editSkillButton, editSkillButtonConstraints);

                editSkillTextField = new JTextField();
                editSkillTextField.setFont(editSkillTextField.getFont().deriveFont(18f));
                GridBagConstraints editSkillTextFieldConstraints = new GridBagConstraints(0, 1, 3, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0);
                skillsButtonPanel.add(editSkillTextField, editSkillTextFieldConstraints);
            }
            detailsPanel.add(skillsButtonPanel, skillsButtonPanelConstraints);

            applicantExperience = new JLabel("Experience:");
            applicantExperience.setFont(applicantExperience.getFont().deriveFont(18f));
            applicantExperience.setHorizontalAlignment(SwingConstants.LEFT);
            GridBagConstraints applicantExperienceConstraints = new GridBagConstraints(0, 13, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            detailsPanel.add(applicantExperience, applicantExperienceConstraints);

            applicantExperiencePane = new JScrollPane();
            applicantExperiencePane.setMinimumSize(new Dimension(0, 100));
            applicantExperiencePane.setPreferredSize(new Dimension(0, 100));
            GridBagConstraints applicantExperiencePaneConstraints = new GridBagConstraints(0, 14, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);

            applicantExperiencesList = new JList<>();
            applicantExperiencesList.setFont(applicantExperiencesList.getFont().deriveFont(18f));
            applicantExperiencesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            applicantExperiencesList.setModel(getApplicantExperienceModel());
            applicantExperiencePane.setViewportView(applicantExperiencesList);

            detailsPanel.add(applicantExperiencePane, applicantExperiencePaneConstraints);

            applicantExperiencesButtonPanel = new JPanel();
            applicantExperiencesButtonPanel.setLayout(new GridBagLayout());
            GridBagConstraints applicantExperiencesPanelConstraints = new GridBagConstraints(0, 15, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);

            {
                addApplicantExperienceButton = new JButton("Add");
                addApplicantExperienceButton.setFont(addApplicantExperienceButton.getFont().deriveFont(18f));
                addApplicantExperienceButton.setHorizontalAlignment(SwingConstants.CENTER);
                GridBagConstraints addApplicantExperienceConstraints = new GridBagConstraints(0, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0);
                applicantExperiencesButtonPanel.add(addApplicantExperienceButton, addApplicantExperienceConstraints);

                removeApplicantExperienceButton = new JButton("Remove");
                removeApplicantExperienceButton.setFont(removeApplicantExperienceButton.getFont().deriveFont(18f));
                removeApplicantExperienceButton.setHorizontalAlignment(SwingConstants.CENTER);
                GridBagConstraints removeApplicantExperienceConstraints = new GridBagConstraints(1, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0);
                applicantExperiencesButtonPanel.add(removeApplicantExperienceButton, removeApplicantExperienceConstraints);

                editApplicantExperienceButton = new JButton("Edit");
                editApplicantExperienceButton.setFont(editApplicantExperienceButton.getFont().deriveFont(18f));
                editApplicantExperienceButton.setHorizontalAlignment(SwingConstants.CENTER);
                GridBagConstraints editApplicantExperienceConstraints = new GridBagConstraints(2, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0);
                applicantExperiencesButtonPanel.add(editApplicantExperienceButton, editApplicantExperienceConstraints);
            }
            detailsPanel.add(applicantExperiencesButtonPanel, applicantExperiencesPanelConstraints);

            companyLabel = new JLabel("Company:");
            companyLabel.setFont(companyLabel.getFont().deriveFont(18f));
            companyLabel.setHorizontalAlignment(SwingConstants.LEFT);
            GridBagConstraints companyLabelConstraints = new GridBagConstraints(0, 16, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            detailsPanel.add(companyLabel, companyLabelConstraints);

            companyField = new JTextField();
            companyField.setFont(companyField.getFont().deriveFont(18f));
            GridBagConstraints companyFieldConstraints = new GridBagConstraints(0, 17, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            detailsPanel.add(companyField, companyFieldConstraints);

            positionLabel = new JLabel("Position:");
            positionLabel.setFont(positionLabel.getFont().deriveFont(18f));
            positionLabel.setHorizontalAlignment(SwingConstants.LEFT);
            GridBagConstraints positionLabelConstraints = new GridBagConstraints(0, 18, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            detailsPanel.add(positionLabel, positionLabelConstraints);

            positionField = new JTextField();
            positionField.setFont(positionField.getFont().deriveFont(18f));
            GridBagConstraints positionFieldConstraints = new GridBagConstraints(0, 19, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            detailsPanel.add(positionField, positionFieldConstraints);

            comboBoxPanel = new JPanel();
            comboBoxPanel.setLayout(new GridBagLayout());
            GridBagConstraints comboBoxPanelConstraints = new GridBagConstraints(0, 20, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);

            startYearComboBox = new JComboBox<>(new String[]{"Start Year"});
            for (int i = Year.now().getValue(); i > 1899; --i){
                startYearComboBox.addItem(String.valueOf(i));
            }
            startYearComboBox.setFont(startYearComboBox.getFont().deriveFont(18f));
            GridBagConstraints startYearConstraints = new GridBagConstraints(0, 0, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            comboBoxPanel.add(startYearComboBox, startYearConstraints);

            endYearComboBox = new JComboBox<>(new String[]{"End Year"});
            endYearComboBox.setEnabled(false);
            endYearComboBox.setFont(endYearComboBox.getFont().deriveFont(18f));
            GridBagConstraints endYearConstraints = new GridBagConstraints(1, 0, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            comboBoxPanel.add(endYearComboBox, endYearConstraints);

            detailsPanel.add(comboBoxPanel, comboBoxPanelConstraints);
        }
        this.add(detailsPanel, detailsPanelConstraints);

        updateChangesPanel = new JPanel();
        updateChangesPanel.setLayout(new GridBagLayout());
        GridBagConstraints updateChangesPanelConstraints = new GridBagConstraints(2, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
                new Insets(0, 0, 5, 0), 0, 0);
        {
            JPanelImageButton saveChangesButton = new JPanelImageButton("Accept Changes", ImageEmbedded.SAVE_CHANGES, ImageEmbedded.SAVE_CHANGES_COLOURED, 60, 60, JPanelImageButton.LEFT);
            GridBagConstraints saveChangesConstraints = new GridBagConstraints(0, 0, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            updateChangesPanel.add(saveChangesButton, saveChangesConstraints);

            JPanelImageButton discardChangesButton = new JPanelImageButton("Discard Changes", ImageEmbedded.DISCARD_CHANGES, ImageEmbedded.DISCARD_CHANGES_COLOURED, 60, 60, JPanelImageButton.LEFT);
            GridBagConstraints discardChangesConstraints = new GridBagConstraints(0, 1, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            updateChangesPanel.add(discardChangesButton, discardChangesConstraints);
        }
        this.add(updateChangesPanel, updateChangesPanelConstraints);

    }

    private DefaultListModel<String> getSkillsModel(){
        DefaultListModel<String> skillsModel = new DefaultListModel<>();
        for (String skill : applicant.getSkills()){
            skillsModel.addElement(skill);
        }
        return skillsModel;
    }

    private DefaultListModel<String> getApplicantExperienceModel(){
        DefaultListModel<String> applicantExperienceModel = new DefaultListModel<>();
        for (ApplicantExperience experience : applicant.getExperiences()){
            applicantExperienceModel.addElement(experience.toString());
        }
        return applicantExperienceModel;
    }


}
