package gui.infobase;

import controller.InfobaseMainframe;
import gui.DateSelectorHelper;
import gui.ImageEmbedded;
import gui.JPanelImageButton;
import subsystems.ImageBase64;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class AddApplicant extends JPanel {
    private InfobaseMainframe main;
    private JPanel picturePanel;
    private JPanel detailsPanel;
    private JButton applicantImageButton;
    private JButton imageRemoveButton;
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
    private JPanel skillsButtonPanel;
    private JButton addSkillButton;
    private JButton removeSkillButton;
    private JButton editSkillButton;
    private JTextField skillTextField;
    private JPanel updateChangesPanel;
    //String[] currentSkillsArray=new String[];
    private JPanelImageButton discardChangesButton;
    private JPanelImageButton addChangesButton;
    private final ImageIcon defaultIcon;
    private Image currentImage;

    DefaultListModel<String> currentSkillsModel = new DefaultListModel<>();
    //TODO: replace with the relevant placeholders

    public AddApplicant(InfobaseMainframe main) {
        this.defaultIcon = new ImageIcon(ImageBase64.base64ToImage(ImageEmbedded.DEFAULT_APPLICANT_IMAGE));
        this.main = main;
        this.main.getController().setApplicantInstance(-1);
        this.currentImage = ImageBase64.base64ToImage(ImageEmbedded.PLACEHOLDER);
        initComponents();
        initListeners();
    }

    private void initComponents() {

        this.setVisible(true);
        this.setLayout(new GridBagLayout());

        //===PICTURE PANEL START===//

        picturePanel = new JPanel();
        picturePanel.setLayout(new GridBagLayout());
        GridBagConstraints picturePanelConstraints = new GridBagConstraints(
                0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTHEAST, GridBagConstraints.NONE,
                new Insets(0, 0, 0, 5), 0, 0);


        //START INNER COMPONENTS OF PANEL
        {

            //===ADD/REMOVE picture button "group"===//
            //TODO: might need to change the image, add a placeholder image
            //TODO: replace with JFilechooser, maybe funtionality already inside
            applicantImageButton = new JButton(defaultIcon);
            GridBagConstraints applicantImageButtonConstraints = new GridBagConstraints(
                    0, 0, 1, 1, 0, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            picturePanel.add(applicantImageButton, applicantImageButtonConstraints);

            imageRemoveButton = new JButton(main.getLocale("EditApplicant.JButton.removeImage"));
            imageRemoveButton.setFont(imageRemoveButton.getFont().deriveFont(18f));
            GridBagConstraints imageRemoveButtonConstraints = new GridBagConstraints(
                    0, 1, 1, 1, 0, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            picturePanel.add(imageRemoveButton, imageRemoveButtonConstraints);


        }
        //END INNER COMPONENTS

        this.add(picturePanel, picturePanelConstraints);
        //adds panel with add/remove buttons

        //===DETAILS PANEL START===//

        detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridBagLayout());
        GridBagConstraints detailsPanelConstraints = new GridBagConstraints(
                1, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.NONE,
                new Insets(0, 0, 5, 0), 0, 0);

        //START INNER COMPONENTS
        {
            //Applicant name label
            applicantNameLabel = new JLabel(main.getLocale("EditApplicant.JLabel.applicant_name"));
            applicantNameLabel.setFont(applicantNameLabel.getFont().deriveFont(18f));
            applicantNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
            GridBagConstraints applicantNameLabelConstraints = new GridBagConstraints(
                    0, 0, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            detailsPanel.add(applicantNameLabel, applicantNameLabelConstraints);

            //===APPLICANT NAME FIELD===//

            applicantNameField = new JTextField();
            applicantNameField.setText("");
            applicantNameField.setFont(applicantNameField.getFont().deriveFont(18f));
            GridBagConstraints applicantNameFieldConstraints = new GridBagConstraints(
                    0, 1, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            detailsPanel.add(applicantNameField, applicantNameFieldConstraints);

            //===APPLICANT BIRTHDAY LABEL===//

            applicantBirthday = new JLabel(main.getLocale("EditApplicant.JLabel.birth_date"));
            applicantNameLabel.setFont(applicantNameLabel.getFont().deriveFont(18f));
            applicantBirthday.setHorizontalAlignment(SwingConstants.LEFT);
            GridBagConstraints applicantBirthdayConstraints = new GridBagConstraints(
                    0, 2, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            detailsPanel.add(applicantBirthday, applicantBirthdayConstraints);

            //===COMBOBOX COMBO HERE===//

            birthdayPanel = new JPanel();
            birthdayPanel.setLayout(new GridBagLayout());
            GridBagConstraints birthdayPanelConstraints = new GridBagConstraints(0, 3, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);

            //START INNER COMPONENTS
            {

                //===DAY FIELD IN COMBOBOX===//

                dayComboBox = DateSelectorHelper.comboBoxGetBaseDates();
                dayComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
                dayComboBox.setFont(dayComboBox.getFont().deriveFont(18f));
                //TODO might need to set to 1, same for other fields.
                dayComboBox.setSelectedIndex(0);
                GridBagConstraints dayComboBoxConstraints = new GridBagConstraints(
                        0, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0);
                birthdayPanel.add(dayComboBox, dayComboBoxConstraints);

                //===MONTH FIELD IN COMBOBOX===//

                monthComboBox = DateSelectorHelper.comboBoxGetBaseMonths();
                monthComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
                monthComboBox.setFont(monthComboBox.getFont().deriveFont(18f));
                monthComboBox.setSelectedIndex(0);
                GridBagConstraints monthComboBoxConstraints = new GridBagConstraints(
                        1, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0);
                birthdayPanel.add(monthComboBox, monthComboBoxConstraints);

                //===YEAR FIELD IN COMBOBOX===//

                yearComboBox = DateSelectorHelper.comboBoxGetBaseYears(1899);
                yearComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
                yearComboBox.setFont(yearComboBox.getFont().deriveFont(18f));
                yearComboBox.setSelectedIndex(0);
                GridBagConstraints yearComboBoxConstraints = new GridBagConstraints(
                        2, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0);
                birthdayPanel.add(yearComboBox, yearComboBoxConstraints);
            }
            //END INNER COMPONENTS
            detailsPanel.add(birthdayPanel, birthdayPanelConstraints);

            //===NRIC LABEL===//

            applicantNricLabel = new JLabel(main.getLocale("EditApplicant.JLabel.nric"));
            applicantNricLabel.setFont(applicantNricLabel.getFont().deriveFont(18f));
            applicantNricLabel.setHorizontalAlignment(SwingConstants.LEFT);
            GridBagConstraints applicantNricLabelConstraints = new GridBagConstraints(
                    0, 4, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            detailsPanel.add(applicantNricLabel, applicantNricLabelConstraints);

            //===NRIC FIELD===//

            applicantNricField = new JTextField();
            applicantNricField.setText("");
            applicantNricField.setFont(applicantNricField.getFont().deriveFont(18f));
            GridBagConstraints applicantNricFieldConstraints = new GridBagConstraints(
                    0, 5, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            detailsPanel.add(applicantNricField, applicantNricFieldConstraints);

            //===EMAIL LABEL===//

            applicantEmail = new JLabel(main.getLocale("EditApplicant.JLabel.email"));
            applicantEmail.setFont(applicantEmail.getFont().deriveFont(18f));
            applicantEmail.setHorizontalAlignment(SwingConstants.LEFT);
            GridBagConstraints applicantEmailConstraints = new GridBagConstraints(
                    0, 6, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            detailsPanel.add(applicantEmail, applicantEmailConstraints);

            //===EMAIL FIELD===//

            applicantEmailField = new JTextField();
            applicantEmailField.setText("");
            applicantEmailField.setFont(applicantEmailField.getFont().deriveFont(18f));
            GridBagConstraints applicantEmailFieldConstraints = new GridBagConstraints(
                    0, 7, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            detailsPanel.add(applicantEmailField, applicantEmailFieldConstraints);

            //===GENDER LABEL===//

            applicantGenderLabel = new JLabel(main.getLocale("EditApplicant.JLabel.gender"));
            applicantGenderLabel.setFont(applicantGenderLabel.getFont().deriveFont(18f));
            applicantGenderLabel.setHorizontalAlignment(SwingConstants.LEFT);
            GridBagConstraints applicantGenderLabelConstraints = new GridBagConstraints(
                    0, 8, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            detailsPanel.add(applicantGenderLabel, applicantGenderLabelConstraints);

            //===GENDER COMBOBOX===//

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

            //===SKILLS LABEL===//

            skillsLabel = new JLabel(main.getLocale("EditApplicant.JLabel.skills"));
            skillsLabel.setFont(skillsLabel.getFont().deriveFont(18f));
            skillsLabel.setHorizontalAlignment(SwingConstants.LEFT);
            GridBagConstraints skillsLabelConstraints = new GridBagConstraints(
                    0, 10, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            detailsPanel.add(skillsLabel, skillsLabelConstraints);

            //===SKILL SCROLL===//

            skillPane = new JScrollPane();
            skillPane.setMinimumSize(new Dimension(0, 100));
            skillPane.setPreferredSize(new Dimension(0, 100));
            GridBagConstraints skillPaneConstraints = new GridBagConstraints(0, 11, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);

            skillsList = new JList<>(currentSkillsModel);
            skillsList.setFont(skillsList.getFont().deriveFont(18f));
            skillsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            skillsList.setModel(currentSkillsModel);
            skillPane.setViewportView(skillsList);
            detailsPanel.add(skillPane, skillPaneConstraints);

            //===button panel- add, remove and edit===//
            //TODO: for now I don't think theres a need to change this
            skillsButtonPanel = new JPanel();
            skillsButtonPanel.setLayout(new GridBagLayout());
            GridBagConstraints skillsButtonPanelConstraints = new GridBagConstraints(0, 12, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(5, 0, 0, 0), 0, 0);
            {

                //===ADD BUTTON===//

                addSkillButton = new JButton(main.getLocale("EditApplicant.JButton.add"));
                addSkillButton.setEnabled(false);
                addSkillButton.setFont(addSkillButton.getFont().deriveFont(18f));
                addSkillButton.setHorizontalAlignment(SwingConstants.CENTER);
                GridBagConstraints addSkillButtonConstraints = new GridBagConstraints(0, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0);
                skillsButtonPanel.add(addSkillButton, addSkillButtonConstraints);

                //===REMOVE BUTTON===//

                removeSkillButton = new JButton(main.getLocale("EditApplicant.JButton.remove"));
                removeSkillButton.setEnabled(false);
                removeSkillButton.setFont(removeSkillButton.getFont().deriveFont(18f));
                GridBagConstraints removeSkillButtonConstraints = new GridBagConstraints(1, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0);
                skillsButtonPanel.add(removeSkillButton, removeSkillButtonConstraints);

                //===EDIT BUTTON===//

                editSkillButton = new JButton(main.getLocale("EditApplicant.JButton.edit"));
                editSkillButton.setEnabled(false);
                editSkillButton.setFont(editSkillButton.getFont().deriveFont(18f));
                GridBagConstraints editSkillButtonConstraints = new GridBagConstraints(2, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0);
                skillsButtonPanel.add(editSkillButton, editSkillButtonConstraints);

                //===EDIT FIELD (skill)===//

                skillTextField = new JTextField();
                skillTextField.setFont(skillTextField.getFont().deriveFont(18f));
                GridBagConstraints skillTextFieldConstraints = new GridBagConstraints(0, 1, 3, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0);
                skillsButtonPanel.add(skillTextField, skillTextFieldConstraints);
            }
            detailsPanel.add(skillsButtonPanel, skillsButtonPanelConstraints);
        }
        this.add(detailsPanel, detailsPanelConstraints);



        updateChangesPanel = new JPanel();
        updateChangesPanel.setLayout(new GridBagLayout());
        GridBagConstraints updateChangesPanelConstraints = new GridBagConstraints(2, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
                new Insets(0, 0, 5, 0), 0, 0);
        {

            addChangesButton = new JPanelImageButton(main.getLocale("EditApplicant.JButton.save_changes"), ImageEmbedded.SAVE_CHANGES, ImageEmbedded.SAVE_CHANGES_COLOURED, 60, 60, JPanelImageButton.LEFT);

            GridBagConstraints addChangesConstraints = new GridBagConstraints(0, 0, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            updateChangesPanel.add(addChangesButton, addChangesConstraints);

            //==DISCARD BUTTON===//
            discardChangesButton = new JPanelImageButton(main.getLocale("EditApplicant.JLabel.discard_changes"), ImageEmbedded.DISCARD_CHANGES, ImageEmbedded.DISCARD_CHANGES_COLOURED, 60, 60, JPanelImageButton.LEFT);

            GridBagConstraints discardChangesConstraints = new GridBagConstraints(0, 1, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            updateChangesPanel.add(discardChangesButton, discardChangesConstraints);
        }
        this.add(updateChangesPanel, updateChangesPanelConstraints);
    }


    private void initListeners() {
        applicantImageButton.addActionListener(e -> {
            JFileChooser chooser = getjFileChooser();
            Action details = chooser.getActionMap().get("viewTypeDetails");
            details.actionPerformed(null);
            int r = chooser.showOpenDialog(null);
            File selectedFile;
            selectedFile = chooser.getSelectedFile().getAbsoluteFile();
            if (selectedFile.length() > 16777216) {
                JOptionPane.showMessageDialog(null, "Image size exceeds 16MiB", "Image too large", JOptionPane.ERROR_MESSAGE);
            }
            //file processing
            if (r == JFileChooser.APPROVE_OPTION) {

                ImageIcon scaledIcon;
                try {
                    scaledIcon = new ImageIcon(ImageIO.read(selectedFile).getScaledInstance(120, 120, Image.SCALE_SMOOTH));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                applicantImageButton.setIcon(scaledIcon);
                currentImage = scaledIcon.getImage();
            }

        });

        imageRemoveButton.addActionListener(e-> {
            applicantImageButton.setIcon(defaultIcon);

        });

        discardChangesButton.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(null, main.getLocale("EditApplicant.JOptionPane.discardConfirm"), "", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                main.showApplicantListPage();
            }
        });

        skillsList.addListSelectionListener(e -> {
            if (skillsList.getSelectedIndex() == -1){
                removeSkillButton.setEnabled(false);
                editSkillButton.setEnabled(false);
                return;
            }
            removeSkillButton.setEnabled(true);
            if (skillTextField.getDocument().getLength() > 0) {
                editSkillButton.setEnabled(true);
            }
        });

        skillTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                removeUpdate(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (skillTextField.getDocument().getLength() > 0) {
                    addSkillButton.setEnabled(true);
                    if (skillsList.getSelectedIndex() != -1) {
                        editSkillButton.setEnabled(true);
                    }
                }
                else {
                    addSkillButton.setEnabled(false);
                    editSkillButton.setEnabled(false);
                }

            }
        });

        addSkillButton.addActionListener(e -> {
            main.getController().addSkill(skillTextField.getText());
            skillsList.setModel(getSkillsModel());
            skillTextField.setText("");
            addSkillButton.setEnabled(false);
        });

        removeSkillButton.addActionListener(e -> {
            main.getController().removeSkill(skillsList.getSelectedIndex());
            skillsList.setModel(getSkillsModel());
            removeSkillButton.setEnabled(false);
        });

        editSkillButton.addActionListener(e -> {
            main.getController().editSkill(skillsList.getSelectedIndex(), skillTextField.getText());
            skillsList.setModel(getSkillsModel());
            skillTextField.setText("");
            editSkillButton.setEnabled(false);
        });

        addChangesButton.addActionListener(e -> {

            String[] skillStringArray =new String[currentSkillsModel.getSize()];
            for(int i=0; i<currentSkillsModel.getSize(); ++i) {
                skillStringArray[i]=currentSkillsModel.getElementAt(i);
            }

            main.getController().addApplicant(applicantNameField.getText(),                      //adds name
                    dayComboBox.getItemAt(dayComboBox.getSelectedIndex()),                              //adds day
                    monthComboBox.getItemAt(monthComboBox.getSelectedIndex()),                          //adds month
                    yearComboBox.getItemAt(yearComboBox.getSelectedIndex()),                            //adds year
                    applicantEmailField.getText(),                                                      //adds email
                    applicantNricField.getText(),                                                       //adds nric
                    applicantGenderComboBox.getItemAt(applicantGenderComboBox.getSelectedIndex()),      //adds gender
                    ImageBase64.imageToBase64(currentImage),                                            //adds image (base64)
                    skillStringArray,
                    "");

            main.showApplicantListPage();                                                               //goes back to list page
        });

        dayComboBox.addActionListener(DateSelectorHelper.datesUpdater(dayComboBox, monthComboBox, yearComboBox));

        monthComboBox.addActionListener(DateSelectorHelper.datesUpdater(dayComboBox, monthComboBox, yearComboBox));

        yearComboBox.addActionListener(DateSelectorHelper.datesUpdater(dayComboBox, monthComboBox, yearComboBox));

    }

    private static JFileChooser getjFileChooser() {
        JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        chooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    String filename = f.getName().toLowerCase();
                    return filename.endsWith(".jpg") || filename.endsWith(".png") || filename.endsWith(".jpeg");
                }
            }
            @Override
            public String getDescription() {
                return "Image files (*.png, *.jpg, *.jpeg)";
            }
        });
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setPreferredSize(new Dimension(640, 480));
        return chooser;
    }

    private DefaultListModel<String> getSkillsModel(){
        DefaultListModel<String> skillsModel = new DefaultListModel<>();
        for (String skill : main.getController().getSkills()){
            skillsModel.addElement(skill);
        }
        //only required for when there is data passed through
        return skillsModel;
    }


}
