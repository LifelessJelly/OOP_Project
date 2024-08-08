package gui.infobase;

import controller.InfobaseMainframe;
import gui.DateSelectorHelper;
import gui.ImageEmbedded;
import gui.JPanelImageButton;
import controller.ImageBase64;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class EditApplicant extends JPanel {
    final InfobaseMainframe main;
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
    private JPanelImageButton discardChangesButton;
    private JPanelImageButton saveChangesButton;
    private double zoomFactor;
    private float alpha;
    private Image currentImage;


    public EditApplicant(int index, InfobaseMainframe main) {
        this.main = main;
        this.main.getController().setApplicantInstance(index);
        this.currentImage=main.getController().getImage();
        this.zoomFactor = 1.2;
        this.alpha = 0;
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
            applicantImageButton = new JButton(new ImageIcon(main.getController().getImage()));
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
                dayComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
                dayComboBox.setFont(dayComboBox.getFont().deriveFont(18f));
                dayComboBox.setSelectedIndex(Integer.parseInt(main.getController().getBirthdate().substring(8, 10))-1);
                GridBagConstraints dayComboBoxConstraints = new GridBagConstraints(
                        0, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0);
                birthdayPanel.add(dayComboBox, dayComboBoxConstraints);

                monthComboBox = DateSelectorHelper.comboBoxGetBaseMonths();
                monthComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
                monthComboBox.setFont(monthComboBox.getFont().deriveFont(18f));
                monthComboBox.setSelectedIndex(Integer.parseInt(main.getController().getBirthdate().substring(5, 7))-1);
                GridBagConstraints monthComboBoxConstraints = new GridBagConstraints(
                        1, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0);
                birthdayPanel.add(monthComboBox, monthComboBoxConstraints);

                yearComboBox = DateSelectorHelper.comboBoxGetBaseYears(1899);
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
                addSkillButton = new JButton(main.getLocale("EditApplicant.JButton.add"));
                addSkillButton.setEnabled(false);
                addSkillButton.setFont(addSkillButton.getFont().deriveFont(18f));
                addSkillButton.setHorizontalAlignment(SwingConstants.CENTER);
                GridBagConstraints addSkillButtonConstraints = new GridBagConstraints(0, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0);
                skillsButtonPanel.add(addSkillButton, addSkillButtonConstraints);

                removeSkillButton = new JButton(main.getLocale("EditApplicant.JButton.remove"));
                removeSkillButton.setEnabled(false);
                removeSkillButton.setFont(removeSkillButton.getFont().deriveFont(18f));
                GridBagConstraints removeSkillButtonConstraints = new GridBagConstraints(1, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0);
                skillsButtonPanel.add(removeSkillButton, removeSkillButtonConstraints);

                editSkillButton = new JButton(main.getLocale("EditApplicant.JButton.edit"));
                editSkillButton.setEnabled(false);
                editSkillButton.setFont(editSkillButton.getFont().deriveFont(18f));
                GridBagConstraints editSkillButtonConstraints = new GridBagConstraints(2, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0);
                skillsButtonPanel.add(editSkillButton, editSkillButtonConstraints);

                skillTextField = new JTextField();
                skillTextField.setFont(skillTextField.getFont().deriveFont(18f));
                GridBagConstraints editSkillTextFieldConstraints = new GridBagConstraints(0, 1, 3, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0);
                skillsButtonPanel.add(skillTextField, editSkillTextFieldConstraints);
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
            saveChangesButton = new JPanelImageButton(main.getLocale("EditApplicant.JButton.save_changes"), ImageEmbedded.SAVE_CHANGES, ImageEmbedded.SAVE_CHANGES_COLOURED, 60, 60, JPanelImageButton.LEFT);

            GridBagConstraints saveChangesConstraints = new GridBagConstraints(0, 0, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            updateChangesPanel.add(saveChangesButton, saveChangesConstraints);

            discardChangesButton = new JPanelImageButton(main.getL  ocale("EditApplicant.JLabel.discard_changes"), ImageEmbedded.DISCARD_CHANGES, ImageEmbedded.DISCARD_CHANGES_COLOURED, 60, 60, JPanelImageButton.LEFT);

            GridBagConstraints discardChangesConstraints = new GridBagConstraints(0, 1, 1, 1, 0, 0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0);
            updateChangesPanel.add(discardChangesButton, discardChangesConstraints);
        }
        this.add(updateChangesPanel, updateChangesPanelConstraints);
    }

    private void initListeners() {
        imageRemoveButton.addActionListener(e-> {
            applicantImageButton.setIcon(new ImageIcon(ImageBase64.base64ToImage(ImageEmbedded.DEFAULT_APPLICANT_IMAGE)));
            currentImage = ImageBase64.base64ToImage(ImageEmbedded.DEFAULT_APPLICANT_IMAGE);
        });
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
        saveChangesButton.addActionListener(e -> {

            if (JOptionPane.showConfirmDialog(null, main.getLocale("EditApplicant.JOptionPane.editConfirm"), "", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                //do checks
                boolean canContinue;

                // This would be much more readable in C than Java im sorry
                // basically,
                // canContinue = canContinue * checkEmptyFields(field)

                canContinue = (checkEmptyFields(applicantNameField) ? 1 : 0) != 0;
                canContinue = (((canContinue ? 1 : 0) * (checkEmptyFields(applicantEmailField) ? 1 : 0)) != 0);
                canContinue = (((canContinue ? 1 : 0) * (checkEmptyFields(applicantNricField) ? 1 : 0)) != 0);
                if (!canContinue) {
                    JOptionPane.showMessageDialog(null, main.getLocale("AddApplicant.JOptionPane.emptyFieldError"), "", JOptionPane.ERROR_MESSAGE);
                } else if (!isNRICFINValid(applicantNricField.getText())) {
                    JOptionPane.showMessageDialog(null, main.getLocale("AddApplicant.JOptionPane.wrongNricError"), "", JOptionPane.ERROR_MESSAGE);
                } else {

                    main.getController().applyApplicantEdits(applicantNameField.getText(),
                            dayComboBox.getItemAt(dayComboBox.getSelectedIndex()),
                            monthComboBox.getItemAt(monthComboBox.getSelectedIndex()),
                            yearComboBox.getItemAt(yearComboBox.getSelectedIndex()),
                            applicantNricField.getText(),
                            applicantEmailField.getText(),
                            applicantGenderComboBox.getItemAt(applicantGenderComboBox.getSelectedIndex()),
                            ImageBase64.imageToBase64(currentImage));
                    main.showApplicantListPageWithAnimation();
                }
            }
        });
        discardChangesButton.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(null, main.getLocale("EditApplicant.JOptionPane.discardConfirm"), "", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                main.showApplicantListPageWithAnimation();
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

    private DefaultListModel<String> getSkillsModel(){
        DefaultListModel<String> skillsModel = new DefaultListModel<>();
        for (String skill : main.getController().getSkills()){
            skillsModel.addElement(skill);
        }
        return skillsModel;
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

    private static boolean checkEmptyFields(JTextField field){
        Border passBorder = new JTextField().getBorder();
        Border failBorder = BorderFactory.createLineBorder(new Color(173, 74, 59), 1, true);
        if (field.getText().isEmpty()) {
            field.setBorder(failBorder);
            return false;
        }
        else {
            field.setBorder(passBorder);
            return true;
        }
    }
    private boolean isNRICFINValid(String nricFin){
        // information taken from https://www.ngiam.net/NRIC/ppframe.htm
        nricFin = nricFin.toUpperCase();

        if (nricFin.equals("ROOT")){
            return true;
        }

        if ((nricFin.length() != 9) || (nricFin.charAt(0) != 'T' && nricFin.charAt(0) != 'S' && nricFin.charAt(0) != 'F' && nricFin.charAt(0) != 'G')){
            return false;
        }
        int sum = 0;
        int[] weights = new int[]{0, 2, 7, 6, 5, 4, 3, 2};
        char[] checkDigit = getChars(nricFin);

        for (int i = 1; i < nricFin.length()-1; ++i){
            int digit = Character.getNumericValue(nricFin.charAt(i));
            sum += digit * weights[i];
        }
        assert checkDigit != null;
        char calculatedLetter = checkDigit[sum % 11];
        return calculatedLetter == nricFin.charAt(nricFin.length() - 1);
    }

    private static char[] getChars(String nricFin) {
        switch (nricFin.charAt(0)){
            case 'T':
                return new char[]{'G', 'F', 'E', 'D', 'C', 'B', 'A', 'J', 'Z', 'I', 'H'};
            case 'S':
                return new char[]{'J', 'Z', 'I', 'H', 'G', 'F', 'E', 'D', 'C', 'B', 'A'};
            case 'G':
                return new char[]{'R', 'Q', 'P', 'N', 'M', 'L', 'K', 'X', 'W', 'U', 'T'};
            case 'F':
                return new char[]{'X', 'W', 'U', 'T', 'R', 'Q', 'P', 'N', 'M', 'L', 'K'};
        }
        //should never reach here
        return null;
    }

}
