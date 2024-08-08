package gui.infobase;

import controller.DataIO;
import controller.InfobaseMainframe;
import controller.JsonReaderWriter;
import data.Applicant;
import data.EmailCredential;
import gui.DateSelectorHelper;
import gui.ImageEmbedded;
import gui.JPanelImageButton;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class ApplicantInfoKeyingPage extends JPanel {
    private final InfobaseMainframe main;
    private JPanel picturePanel;
    private JPanel detailsPanel;
    private JLabel applicantImageButton;
    private JLabel applicantNameLabel;
    private JTextField applicantNameField;
    private JLabel applicantBirthday;
    private JPanel birthdayPanel;
    private JComboBox<Integer> dayComboBox;
    private JComboBox<String> monthComboBox;
    private JComboBox<Integer> yearComboBox;
    private JLabel applicantNricLabel;
    private JTextField applicantNricField;
    private JLabel applicantEmail;
    private JTextField applicantEmailField;
    private JLabel applicantGenderLabel;
    private JComboBox<String> applicantGenderComboBox;
    private JLabel skillsLabel;
    private JScrollPane skillPane;
    private JList<String> skillsList;
    private JPanel updateChangesPanel;
    private JPanelImageButton discardChangesButton;
    private JPanelImageButton saveChangesButton;
    private final int stage;
    private JComboBox<Integer> hourComboBox;
    private JComboBox<Integer> minuteComboBox;
    private JTextField applicantAssignedField;
    private JButton editSystemEmailButton;

    public ApplicantInfoKeyingPage(int index, InfobaseMainframe main, int stage) {
        this.main = main;
        this.stage = stage;
        this.main.getController().setApplicantInstance(index);
        initComponents();
        initListeners();
    }

    private void initComponents() {

        this.setVisible(true);
        this.setLayout(new GridBagLayout());

        picturePanel = new JPanel();
        picturePanel.setLayout(new GridBagLayout());
        GridBagConstraints picturePanelConstraints = new GridBagConstraints(
                0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTHEAST, GridBagConstraints.NONE,
                new Insets(0, 0, 0, 5), 0, 0);

        //START INNER COMPONENTS
        {
            applicantImageButton = new JLabel(new ImageIcon(main.getController().getImage()));
            GridBagConstraints applicantImageButtonConstraints = new GridBagConstraints(
                    0, 0, 1, 1, 0, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            picturePanel.add(applicantImageButton, applicantImageButtonConstraints);
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
            applicantNameLabel = new JLabel(main.getLocale("EditApplicant.JLabel.applicant_name"));
            applicantNameLabel.setFont(applicantNameLabel.getFont().deriveFont(18f));
            applicantNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
            GridBagConstraints applicantNameLabelConstraints = new GridBagConstraints(
                    0, 0, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            detailsPanel.add(applicantNameLabel, applicantNameLabelConstraints);

            applicantNameField = new JTextField();
            applicantNameField.setText(main.getController().getName());
            applicantNameField.setEnabled(false);
            applicantNameField.setFont(applicantNameField.getFont().deriveFont(18f));
            GridBagConstraints applicantNameFieldConstraints = new GridBagConstraints(
                    0, 1, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            detailsPanel.add(applicantNameField, applicantNameFieldConstraints);

            applicantBirthday = new JLabel(main.getLocale("EditApplicant.JLabel.birth_date"));
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
                dayComboBox = DateSelectorHelper.comboBoxGetBaseDates();
                dayComboBox.setEnabled(false);
                dayComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
                dayComboBox.setFont(dayComboBox.getFont().deriveFont(18f));
                dayComboBox.setSelectedIndex(Integer.parseInt(main.getController().getBirthdate().substring(8, 10))-1);
                GridBagConstraints dayComboBoxConstraints = new GridBagConstraints(
                        0, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0);
                birthdayPanel.add(dayComboBox, dayComboBoxConstraints);

                monthComboBox = DateSelectorHelper.comboBoxGetBaseMonths();
                monthComboBox.setEnabled(false);
                monthComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
                monthComboBox.setFont(monthComboBox.getFont().deriveFont(18f));
                monthComboBox.setSelectedIndex(Integer.parseInt(main.getController().getBirthdate().substring(5, 7))-1);
                GridBagConstraints monthComboBoxConstraints = new GridBagConstraints(
                        1, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0);
                birthdayPanel.add(monthComboBox, monthComboBoxConstraints);

                yearComboBox = DateSelectorHelper.comboBoxGetBaseYears(1899);
                yearComboBox.setEnabled(false);
                yearComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
                yearComboBox.setFont(yearComboBox.getFont().deriveFont(18f));
                yearComboBox.setSelectedIndex(yearComboBox.getItemCount()-(Integer.parseInt(main.getController().getBirthdate().substring(0, 4))-1899));
                GridBagConstraints yearComboBoxConstraints = new GridBagConstraints(
                        2, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0);
                birthdayPanel.add(yearComboBox, yearComboBoxConstraints);
            }
            //END INNER COMPONENTS
            detailsPanel.add(birthdayPanel, birthdayPanelConstraints);

            applicantNricLabel = new JLabel(main.getLocale("EditApplicant.JLabel.nric"));
            applicantNricLabel.setFont(applicantNricLabel.getFont().deriveFont(18f));
            applicantNricLabel.setHorizontalAlignment(SwingConstants.LEFT);
            GridBagConstraints applicantNricLabelConstraints = new GridBagConstraints(
                    0, 4, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            detailsPanel.add(applicantNricLabel, applicantNricLabelConstraints);

            applicantNricField = new JTextField();
            applicantNricField.setText(main.getController().getNRIC());
            applicantNricField.setEnabled(false);
            applicantNricField.setFont(applicantNricField.getFont().deriveFont(18f));
            GridBagConstraints applicantNricFieldConstraints = new GridBagConstraints(
                    0, 5, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            detailsPanel.add(applicantNricField, applicantNricFieldConstraints);

            applicantEmail = new JLabel(main.getLocale("EditApplicant.JLabel.email"));
            applicantEmail.setFont(applicantEmail.getFont().deriveFont(18f));
            applicantEmail.setHorizontalAlignment(SwingConstants.LEFT);
            GridBagConstraints applicantEmailConstraints = new GridBagConstraints(
                    0, 6, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            detailsPanel.add(applicantEmail, applicantEmailConstraints);

            applicantEmailField = new JTextField();
            applicantEmailField.setText(main.getController().getEmail());
            applicantEmailField.setEnabled(false);
            applicantEmailField.setFont(applicantEmailField.getFont().deriveFont(18f));
            GridBagConstraints applicantEmailFieldConstraints = new GridBagConstraints(
                    0, 7, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            detailsPanel.add(applicantEmailField, applicantEmailFieldConstraints);

            applicantGenderLabel = new JLabel(main.getLocale("EditApplicant.JLabel.gender"));
            applicantGenderLabel.setFont(applicantGenderLabel.getFont().deriveFont(18f));
            applicantGenderLabel.setHorizontalAlignment(SwingConstants.LEFT);
            GridBagConstraints applicantGenderLabelConstraints = new GridBagConstraints(
                    0, 8, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            detailsPanel.add(applicantGenderLabel, applicantGenderLabelConstraints);
            String[] genderList = new String[] {
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
            };
            applicantGenderComboBox = new JComboBox<>(genderList);
            applicantGenderComboBox.setEnabled(false);
            int stringIndex = 0;
            for (int i = 0; i < genderList.length; i++) {
                if (genderList[i].equals(main.getController().getGender())) {
                    stringIndex = i;
                }
            }
            applicantGenderComboBox.setSelectedItem(genderList[stringIndex]);
            applicantGenderComboBox.setFont(applicantGenderComboBox.getFont().deriveFont(18f));
            GridBagConstraints applicantGenderComboBoxConstraints = new GridBagConstraints(
                    0, 9, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            detailsPanel.add(applicantGenderComboBox, applicantGenderComboBoxConstraints);

            skillsLabel = new JLabel(main.getLocale("EditApplicant.JLabel.skills"));
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
            skillsList.setEnabled(false);
            skillsList.setModel(getSkillsModel());
            skillPane.setViewportView(skillsList);
            detailsPanel.add(skillPane, skillPaneConstraints);

        }
        this.add(detailsPanel, detailsPanelConstraints);

        updateChangesPanel = new JPanel();
        updateChangesPanel.setLayout(new GridBagLayout());
        GridBagConstraints updateChangesPanelConstraints = new GridBagConstraints(2, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
                new Insets(0, 0, 5, 0), 0, 0);
        {
            saveChangesButton = new JPanelImageButton(main.getLocale("EditApplicant.JButton.save_changes"), ImageEmbedded.SAVE_CHANGES, ImageEmbedded.SAVE_CHANGES_COLOURED, 60, 60, JPanelImageButton.LEFT);

            GridBagConstraints saveChangesConstraints = new GridBagConstraints(0, 0, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            updateChangesPanel.add(saveChangesButton, saveChangesConstraints);

            discardChangesButton = new JPanelImageButton(main.getLocale("EditApplicant.JLabel.discard_changes"), ImageEmbedded.DISCARD_CHANGES, ImageEmbedded.DISCARD_CHANGES_COLOURED, 60, 60, JPanelImageButton.LEFT);

            GridBagConstraints discardChangesConstraints = new GridBagConstraints(0, 1, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            updateChangesPanel.add(discardChangesButton, discardChangesConstraints);

            editSystemEmailButton = new JButton(main.getLocale("ApplicantInfoKeyingPage.JButton.editSystemEmail"));
            editSystemEmailButton.setFont(editSystemEmailButton.getFont().deriveFont(18f));
            GridBagConstraints editSystemEmailButtonConstraints = new GridBagConstraints(0, 2, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0);
            updateChangesPanel.add(editSystemEmailButton, editSystemEmailButtonConstraints);
        }
        this.add(updateChangesPanel, updateChangesPanelConstraints);

        if (stage == Applicant.SHORTLISTED_PENDING_DATE){
            //TODO do translation
            JLabel setDateLabel = new JLabel(main.getLocale("ApplicantInfoKeyingPage.JLabel.selectTime"));
            setDateLabel.setFont(setDateLabel.getFont().deriveFont(18f));
            setDateLabel.setHorizontalAlignment(SwingConstants.LEFT);
            GridBagConstraints setDateConstraints = new GridBagConstraints(0, 12, 1, 1, 0, 0,
                    GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(50, 0, 0, 0), 0, 0);
            detailsPanel.add(setDateLabel, setDateConstraints);

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

            hourComboBox = DateSelectorHelper.comboBoxGetBaseHour();
            hourComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
            hourComboBox.setFont(hourComboBox.getFont().deriveFont(18f));
            hourComboBox.setSelectedIndex(0);
            GridBagConstraints hourComboBoxConstraints = new GridBagConstraints(3, 0, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            datePanel.add(hourComboBox, hourComboBoxConstraints);

            minuteComboBox = DateSelectorHelper.comboBoxGet15MinuteInterval();
            minuteComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
            minuteComboBox.setFont(minuteComboBox.getFont().deriveFont(18f));
            minuteComboBox.setSelectedIndex(0);
            GridBagConstraints minuteComboBoxConstraints = new GridBagConstraints(4, 0, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            datePanel.add(minuteComboBox, minuteComboBoxConstraints);

            GridBagConstraints datePanelConstraints = new GridBagConstraints(0, 13, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0);

            detailsPanel.add(datePanel, datePanelConstraints);
        } else if (stage == Applicant.ACCEPTED_WAITING_JOB) {
            JLabel applicantAssignedLabel = new JLabel("Applicant's job assigned:");
            applicantAssignedLabel.setFont(applicantAssignedLabel.getFont().deriveFont(18f));
            applicantAssignedLabel.setHorizontalAlignment(SwingConstants.LEFT);
            GridBagConstraints applicantAssignedLabelConstraints = new GridBagConstraints(0, 12, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0);
            detailsPanel.add(applicantAssignedLabel, applicantAssignedLabelConstraints);

            applicantAssignedField = new JTextField();
            applicantAssignedField.setFont(applicantAssignedField.getFont().deriveFont(18f));
            GridBagConstraints applicantAssignedFieldConstraints = new GridBagConstraints(0, 13, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0);
            detailsPanel.add(applicantAssignedField, applicantAssignedFieldConstraints);
        }
    }

    private void initListeners() {
        editSystemEmailButton.addActionListener(e -> {
            EmailBoxPopup(false);
        });

        if (stage == Applicant.SHORTLISTED_PENDING_DATE) {
            saveChangesButton.addActionListener(e -> {
                //Check if credential file is there
                String dir = System.getProperty("user.dir") + "\\email.config";
                File dirFile = new File(dir);
                boolean pass = true;
                if (!dirFile.exists()) {
                    pass = EmailBoxPopup(true);
                }
                if (pass) {
                    if (JOptionPane.showConfirmDialog(null, main.getLocale("ApplicantInfoKeyPage.JOptionPane.addConfirmInterview"), "", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                        long epoch = getDateTimeEpoch(
                                Objects.requireNonNull((Integer) yearComboBox.getSelectedItem()),
                                Objects.requireNonNull((String) monthComboBox.getSelectedItem()),
                                Objects.requireNonNull((Integer) dayComboBox.getSelectedItem()),
                                Objects.requireNonNull((Integer) hourComboBox.getSelectedItem()),
                                Objects.requireNonNull((Integer) minuteComboBox.getSelectedItem()));
                        if (epoch < System.currentTimeMillis()) {
                            JOptionPane.showMessageDialog(null, main.getLocale("ApplicantInfoKeyingPage.JOptionPane.timeError"), "", JOptionPane.ERROR_MESSAGE);
                        } else {
                            main.getController().setInterviewTime(epoch);
                            main.showApplicantListPage();
                        }
                    }
                }
            });
        }
        else if (stage == Applicant.ACCEPTED_WAITING_JOB) {
            saveChangesButton.addActionListener(e -> {
                //Check if credential file is there
                String dir = System.getProperty("user.dir") + "\\email.config";
                File dirFile = new File(dir);
                boolean pass = true;
                if (!dirFile.exists()) {
                    pass = EmailBoxPopup(true);
                }
                if (pass) {
                    main.getController().setJobRole(applicantAssignedField.getText());
                    main.showApplicantListPage();
                }
            });
        }
        discardChangesButton.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(null, main.getLocale("EditApplicant.JOptionPane.discardConfirm"), "", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                main.showApplicantListPage();
            }
        });

    }

    private DefaultListModel<String> getSkillsModel(){
        DefaultListModel<String> skillsModel = new DefaultListModel<>();
        for (String skill : main.getController().getSkills()){
            skillsModel.addElement(skill);
        }
        return skillsModel;
    }

    private long getDateTimeEpoch(int year, String month, int day, int hour, int minute){
        String date = year + "-" + month + "-" + day + " " + hour + ":" + minute;
        DateFormat format = new SimpleDateFormat("yyyy-MMM-dd HH:mm");

        try {
            return format.parse(date).getTime();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean EmailBoxPopup(boolean promptNeeded){
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());

        JLabel usernameLabel = new JLabel(main.getLocale("ApplicantInfoKeyingPage.JOptionPane.username"));
        usernameLabel.setFont(usernameLabel.getFont().deriveFont(18f));
        usernameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints usernameLabelConstraints = new GridBagConstraints(0, 0, 1, 1, 1, 0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 5, 0), 0, 0);
        inputPanel.add(usernameLabel, usernameLabelConstraints);

        JTextField usernameField = new JTextField();
        usernameField.setMinimumSize(new Dimension(400, 40));
        usernameField.setPreferredSize(new Dimension(400, 40));
        usernameField.setFont(usernameField.getFont().deriveFont(18f));
        usernameField.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints usernameFieldConstraints = new GridBagConstraints(0, 1, 1, 1, 1, 0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 5, 0), 0, 0);
        inputPanel.add(usernameField, usernameFieldConstraints);

        JLabel passwordLabel = new JLabel(main.getLocale("ApplicantInfoKeyingPage.JOptionPane.password"));
        passwordLabel.setFont(passwordLabel.getFont().deriveFont(18f));
        passwordLabel.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints passwordLabelConstraints = new GridBagConstraints(0, 2, 1, 1, 1, 0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 5, 0), 0, 0);
        inputPanel.add(passwordLabel, passwordLabelConstraints);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setMinimumSize(new Dimension(400, 40));
        passwordField.setPreferredSize(new Dimension(400, 40));
        passwordField.setFont(passwordField.getFont().deriveFont(18f));
        passwordField.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints passwordFieldConstraints = new GridBagConstraints(0, 3, 1, 1, 1, 1,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 5, 0), 0, 0);
        inputPanel.add(passwordField, passwordFieldConstraints);

        if (promptNeeded){
            JLabel promptLabel = new JLabel(main.getLocale("ApplicantInfoKeyingPage.JOptionPane.prompt"));
            promptLabel.setForeground(new Color(173, 74, 59));
            GridBagConstraints promptConstraints = new GridBagConstraints(0, 4, 1, 1, 1, 1,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 5, 0), 0, 0);
            inputPanel.add(promptLabel, promptConstraints);
        }
        int option = JOptionPane.showConfirmDialog(null, inputPanel, "", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            writeToConfig(usernameField.getText(), passwordField.getPassword());
            return true;
        }
        return false;
    }

    private void writeToConfig(String username, char[] password){
        String dir = System.getProperty("user.dir") + "\\email.config";
        File dirFile = new File(dir);
        if (dirFile.delete()) {
            System.out.println("file deleted");
        }
        DataIO.writeFile(dirFile.getAbsolutePath(),
                JsonReaderWriter.modelToJson(new EmailCredential(username, new String(password))));
    }
}
