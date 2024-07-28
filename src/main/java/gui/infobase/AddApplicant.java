package gui.infobase;

import controller.InfobaseMainframe;
import data.Applicant;
import gui.ImageEmbedded;
import gui.JPanelImageButton;
import subsystems.ImageBase64;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class AddApplicant extends JPanel {
    InfobaseMainframe main;
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
    JTextField skillTextField;
    JPanel updateChangesPanel;
    //String[] currentSkillsArray=new String[];
    ArrayList<String> currentSkillsList = new ArrayList<String>();
    private JPanelImageButton discardChangesButton;
    private JPanelImageButton saveChangesButton;
    private Image placeholderpic= ImageBase64.base64ToImage("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")
            .getScaledInstance(120,120,Image.SCALE_SMOOTH);
    private Image currentImage;
    private double zoomFactor;
    private float alpha;
    DefaultListModel<String> currentSkillsModel = new DefaultListModel<>();
    //TODO: replace with the relevant placeholders

    public AddApplicant(InfobaseMainframe main) {
        this.main = main;
        //this.applicant = applicant;
        //this.main.getController().setApplicantInstance(applicant, index);
        //TODO: change to write instead of read
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
        picturePanel.setBorder(BorderFactory.createLineBorder(Color.black));

        //START INNER COMPONENTS OF PANEL
        {

            //===ADD/REMOVE picture button "group"===//
            //TODO: might need to change the image, add a placeholder image
            //TODO: replace with JFilechooser, maybe funtionality already inside
            applicantImageButton = new JButton(new ImageIcon(placeholderpic));
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

                dayComboBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31});
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

                monthComboBox = new JComboBox<>(new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"});
                monthComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
                monthComboBox.setFont(monthComboBox.getFont().deriveFont(18f));
                monthComboBox.setSelectedIndex(0);
                GridBagConstraints monthComboBoxConstraints = new GridBagConstraints(
                        1, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0);
                birthdayPanel.add(monthComboBox, monthComboBoxConstraints);

                //===YEAR FIELD IN COMBOBOX===//

                yearComboBox = new JComboBox<>();
                for (int i = Year.now().getValue(); i > 1899; --i) {
                    yearComboBox.addItem(i);
                }
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

        //===UPDATE BUTTON PANEL===//

        updateChangesPanel = new JPanel();
        updateChangesPanel.setLayout(new GridBagLayout());
        GridBagConstraints updateChangesPanelConstraints = new GridBagConstraints(2, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
                new Insets(0, 0, 5, 0), 0, 0);
        {

            //===SAVE BUTTON===//
            //TODO: add to hashmap (save->add)
            saveChangesButton = new JPanelImageButton(main.getLocale("EditApplicant.JButton.save_changes"), ImageEmbedded.SAVE_CHANGES, ImageEmbedded.SAVE_CHANGES_COLOURED, 60, 60, JPanelImageButton.LEFT);

            GridBagConstraints saveChangesConstraints = new GridBagConstraints(0, 0, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            updateChangesPanel.add(saveChangesButton, saveChangesConstraints);

            //==DISCARD BUTTON===//
            //TODO: might need to revamp the discard listener
            discardChangesButton = new JPanelImageButton(main.getLocale("EditApplicant.JLabel.discard_changes"), ImageEmbedded.DISCARD_CHANGES, ImageEmbedded.DISCARD_CHANGES_COLOURED, 60, 60, JPanelImageButton.LEFT);

            GridBagConstraints discardChangesConstraints = new GridBagConstraints(0, 1, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            updateChangesPanel.add(discardChangesButton, discardChangesConstraints);
        }
        this.add(updateChangesPanel, updateChangesPanelConstraints);
    }


//===INITIALSE LISTENERS===//
//TODO Listeners done so far: addImageButton, discardChangesButton, saveChangeButton, removeSkill,
//yet todo: editskill, reset image
    private void initListeners() {
        //TODO: add the yummy add function, the whatever at 0 thingy (see how :3)
        applicantImageButton.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("user.dir"));
            FileNameExtensionFilter filter = new FileNameExtensionFilter("All Pictures only", "png", "jpg", "jpeg");
            chooser.addChoosableFileFilter(filter);
            int clicked = chooser.showOpenDialog(this);

            if (clicked == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());
                Image imageFromIcon = icon.getImage();
                Image scaledImage = imageFromIcon.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                applicantImageButton.setIcon(scaledIcon);
                currentImage = scaledIcon.getImage();
                applicantImageButton.setIcon(scaledIcon);
            }
        });

        imageRemoveButton.addActionListener(e-> {
            applicantImageButton.setIcon((Icon)placeholderpic);

        });

        discardChangesButton.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(null, main.getLocale("EditApplicant.JOptionPane.discardConfirm"), "", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                main.showApplicantListPage();
            }
        });

        //TODO: modify to get current skills
        addSkillButton.addActionListener(e -> {
            currentSkillsList.add(skillTextField.getText());
            currentSkillsModel.addElement(skillTextField.getText());
            //main.getController().addSkill(editSkillTextField.getText());
            //getSkillsModel();
            //currentSkillsList.setModel(getSkillsModel());
        });

        removeSkillButton.addActionListener(e->{
            currentSkillsList.remove(skillsList.getSelectedIndex());
        });

        editSkillButton.addActionListener(e-> {
            currentSkillsList.set(skillsList.getSelectedIndex(), skillTextField.getText());
        });

        saveChangesButton.addActionListener(e -> {
            //TODO: change funtionality to add vs change, probably have to modify the applyApplicantEdits
            String skillStringArray[]=new String[currentSkillsModel.getSize()];
            for(int i=0;i<currentSkillsModel.getSize();++i) {
                skillStringArray[i]=currentSkillsModel.getElementAt(i);
            }

            main.getController().addApplicant(applicantNameField.getText(),			                    //adds name
                    dayComboBox.getItemAt(dayComboBox.getSelectedIndex()),				                //adds day
                    monthComboBox.getItemAt(monthComboBox.getSelectedIndex()),				            //adds month
                    yearComboBox.getItemAt(yearComboBox.getSelectedIndex()),				            //adds year
                    applicantEmailField.getText(),                                                      //adds email
                    applicantNricField.getText(),							                            //adds nric
                    applicantGenderComboBox.getItemAt(applicantGenderComboBox.getSelectedIndex()),      //adds gender
                    ImageBase64.imageToBase64(currentImage),                                            //adds image (base64)
                    //TODO add skill string array
                    skillStringArray);

            main.showApplicantListPage();								//goes back to list page
        });

    }

    /*private DefaultListModel<String> getSkillsModel(){
        DefaultListModel<String> skillsModel = new DefaultListModel<>();
        for (String skill : main.getController().getSkills()){
            skillsModel.addElement(skill);
        }
        //only required for when there is data passed through
        return skillsModel;
    }*/

    public double getZoomFactor(){
        return zoomFactor;
    }

    public void incrementKeyframe(double zoomFactor, float alpha){
        this.zoomFactor -= zoomFactor;
        this.alpha += alpha;
        main.getContentPane().validate();
        main.getContentPane().repaint();
    }

    public void decrementKeyframe(double zoomFactor, float alpha){
        this.zoomFactor += zoomFactor;
        this.alpha -= alpha;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(this.getWidth()/2, this.getHeight()/2);
        g2.scale(zoomFactor, zoomFactor);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.translate(-this.getWidth()/2, -this.getHeight()/2);
    }
}
