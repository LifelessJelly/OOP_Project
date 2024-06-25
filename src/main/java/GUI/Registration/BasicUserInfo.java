package GUI.Registration;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.time.Year;
import java.util.Arrays;
import java.util.Objects;

import static java.lang.Math.*;

public class BasicUserInfo extends SlidingPanel {
    Mainframe mainframe;
    private int loopCycles = 0;
    JComboBox<Integer> dayBox;
    JComboBox<String> monthBox;
    JComboBox<Integer> yearBox;
    GridBagLayout layout;
    Insets movingInsets;
    Insets fixedInsets;
    private JLabel nricFinLabel;
    private JTextField nricFinField;
    private JLabel emailLabel;
    private JTextField emailField;
    private JLabel genderLabel;
    private JComboBox<String> genderComboBox;
    private JPanel confirmInfoStuffPanel;
    private JLabel errorMessageLabel;
    private JLabel dateOfBirthLabel;
    private JTextField nameField;
    private JLabel nameLabel;
    private JLabel headerLabel;
    private GridBagConstraints confirmInfoStuffConstraints;
    private GridBagConstraints genderBoxConstraints;
    private GridBagConstraints genderConstraints;
    private GridBagConstraints emailFieldConstraints;
    private GridBagConstraints emailConstraints;
    private GridBagConstraints nricFinFieldConstraints;
    private GridBagConstraints nricFinConstraints;
    private GridBagConstraints dateStuffConstraints;
    private GridBagConstraints dateOfBirthConstraints;
    private GridBagConstraints nameFieldConstraints;
    private GridBagConstraints nameConstraints;
    private GridBagConstraints headerConstraints;
    private JPanel dateStuffPanel;
    private JButton nextButton;

    BasicUserInfo(Mainframe mainframe) {
        this.mainframe = mainframe;


        intiComponents();
    }

    private void intiComponents() {

        movingInsets = new Insets(0, 0, 5, 0);
        fixedInsets = new Insets(0, 0, 5, 0);
        
        layout = new GridBagLayout();
        headerConstraints = new GridBagConstraints(0, 0, 3, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        nameConstraints = new GridBagConstraints(0, 1, 3, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        nameFieldConstraints = new GridBagConstraints(0, 2, 3, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        dateOfBirthConstraints = new GridBagConstraints(0, 3, 3, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        dateStuffConstraints = new GridBagConstraints(0, 4, 3, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        nricFinConstraints = new GridBagConstraints(0, 5, 3, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        nricFinFieldConstraints = new GridBagConstraints(0, 6, 3, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        emailConstraints = new GridBagConstraints(0, 7, 3, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        emailFieldConstraints = new GridBagConstraints(0, 8, 3, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        genderConstraints = new GridBagConstraints(0, 9, 3, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        genderBoxConstraints = new GridBagConstraints(0, 10, 3, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        confirmInfoStuffConstraints = new GridBagConstraints(0, 11, 3, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0,0);

        this.setLayout(layout);


        headerLabel = new JLabel("Your basic information:");
        headerLabel.setHorizontalAlignment(SwingConstants.LEFT);
        headerLabel.setFont(headerLabel.getFont().deriveFont(22f));
        this.add(headerLabel, headerConstraints);

        nameLabel = new JLabel("Name:");
        nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        nameLabel.setFont(nameLabel.getFont().deriveFont(18f));
        this.add(nameLabel, nameConstraints);

        nameField = new JTextField();
        nameField.setHorizontalAlignment(SwingConstants.LEFT);
        nameField.setFont(nameField.getFont().deriveFont(18f));
        nameField.setEditable(true);
        this.add(nameField, nameFieldConstraints);

        dateOfBirthLabel = new JLabel("Date of Birth:");
        dateOfBirthLabel.setHorizontalAlignment(SwingConstants.LEFT);
        dateOfBirthLabel.setFont(nameLabel.getFont().deriveFont(18f));
        this.add(dateOfBirthLabel, dateOfBirthConstraints);

        dateStuffPanel = new JPanel();
        GridBagConstraints dayBoxConstraints = new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                fixedInsets, 0, 0);
        GridBagConstraints monthBoxConstraints = new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                fixedInsets, 0, 0);
        GridBagConstraints yearBoxConstraints = new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                fixedInsets, 0, 0);
        dateStuffPanel.setLayout(new GridBagLayout());


        dayBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31});
        dayBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        dayBox.setFont(dayBox.getFont().deriveFont(18f));
        dayBox.setEditable(false);
        dateStuffPanel.add(dayBox, dayBoxConstraints);
        monthBox = new JComboBox<>(new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"});
        monthBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        monthBox.setFont(monthBox.getFont().deriveFont(18f));
        monthBox.setEditable(false);
        dateStuffPanel.add(monthBox, monthBoxConstraints);

        yearBox = new JComboBox<>();
        for (int i = Year.now().getValue(); i > 1899; --i){
            yearBox.addItem(i);
        }
        yearBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        yearBox.setFont(yearBox.getFont().deriveFont(18f));
        yearBox.setEditable(false);
        dateStuffPanel.add(yearBox, yearBoxConstraints);

        monthBox.addActionListener(e -> {
            int selectedDate = dayBox.getSelectedIndex()+1;
            switch ((String)(Objects.requireNonNull(monthBox.getSelectedItem()))){
                case "January":
                case "March":
                case "May":
                case "July":
                case "August":
                case "October":
                case "December":
                    dayBox.setModel(new DefaultComboBoxModel<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31}));
                    dayBox.setSelectedIndex(selectedDate-1);

                    break;
                case "April":
                case "June":
                case "September":
                case "November":
                    if (selectedDate > 30){
                        selectedDate = 30;
                    }
                    dayBox.setModel(new DefaultComboBoxModel<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30}));
                    dayBox.setSelectedIndex(selectedDate-1);
                    break;
                default: // February case
                    handleFebruaryDates(selectedDate);
            }

        });

        yearBox.addActionListener(e -> {
            if (Objects.equals(monthBox.getSelectedItem(), "February")){
                handleFebruaryDates(dayBox.getSelectedIndex()+1);
            }
        });
        this.add(dateStuffPanel, dateStuffConstraints);

        nricFinLabel = new JLabel("NRIC/FIN:");
        nricFinLabel.setHorizontalAlignment(SwingConstants.LEFT);
        nricFinLabel.setFont(nricFinLabel.getFont().deriveFont(18f));
        this.add(nricFinLabel, nricFinConstraints);

        nricFinField = new JTextField();
        nricFinField.setHorizontalAlignment(SwingConstants.LEFT);
        nricFinField.setFont(nricFinField.getFont().deriveFont(18f));
        this.add(nricFinField, nricFinFieldConstraints);

        emailLabel = new JLabel("E-mail:");
        emailLabel.setHorizontalAlignment(SwingConstants.LEFT);
        emailLabel.setFont(emailLabel.getFont().deriveFont(18f));
        this.add(emailLabel, emailConstraints);

        emailField = new JTextField();
        emailField.setHorizontalAlignment(SwingConstants.LEFT);
        emailField.setFont(emailField.getFont().deriveFont(18f));
        this.add(emailField, emailFieldConstraints);

        genderLabel = new JLabel("Gender:");
        genderLabel.setHorizontalAlignment(SwingConstants.LEFT);
        genderLabel.setFont(genderLabel.getFont().deriveFont(18f));
        this.add(genderLabel, genderConstraints);

        genderComboBox = new JComboBox<>(new String[] {
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
        genderComboBox.setEditable(false);
        genderComboBox.setFont(genderComboBox.getFont().deriveFont(18f));
        this.add(genderComboBox, genderBoxConstraints);

        confirmInfoStuffPanel = new JPanel();

        confirmInfoStuffPanel.setLayout(new GridBagLayout());
        GridBagConstraints errorMessageConstraints = new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                fixedInsets, 0,0);
        GridBagConstraints nextButtonConstraints = new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 0), 0, 0);


        errorMessageLabel = new JLabel("");
        errorMessageLabel.setForeground(new Color(173, 74, 59));
        errorMessageLabel.setPreferredSize(new Dimension(400, 20));
        errorMessageLabel.setMinimumSize(new Dimension(400, 20));
        errorMessageLabel.setHorizontalAlignment(SwingConstants.LEFT);
        errorMessageLabel.setFont(errorMessageLabel.getFont().deriveFont(18f));
        confirmInfoStuffPanel.add(errorMessageLabel, errorMessageConstraints);

        nextButton = new JButton("Next");
        nextButton.setEnabled(false);
        nextButton.setHorizontalAlignment(SwingConstants.LEFT);
        nextButton.setFont(nextButton.getFont().deriveFont(18f));
        confirmInfoStuffPanel.add(nextButton, nextButtonConstraints);


        nextButton.addActionListener(e -> {
            Border defaultBorder = new JTextField().getBorder();
            Border errorBorder = BorderFactory.createLineBorder(new Color(173, 74, 59), 3, true);
            //Confirm all textfields are not null
            boolean fieldIsMissing = false;
            java.util.List<JTextField> textFields = Arrays.asList(nameField, nricFinField, emailField);
            for (JTextField textField : textFields) {
                if (textField.getText().isEmpty()) {
                    textField.setBorder(errorBorder);
                    fieldIsMissing = true;
                }
                else {
                    textField.setBorder(defaultBorder);
                }
            }
            if (fieldIsMissing){
                errorMessageLabel.setText("Please fill in all fields ");
            }
            else {
                errorMessageLabel.setText("");
                if (!isNRICFINValid(nricFinField.getText())) {
                    errorMessageLabel.setText("Invalid NRIC/FIN number ");
                    nricFinField.setBorder(errorBorder);
                }
                else {
                    nricFinField.setBorder(defaultBorder);
                    errorMessageLabel.setText("");
                    mainframe.getRegistrator().registerBasicInfo(
                            nameField.getText(),
                            (Integer) Objects.requireNonNull(dayBox.getSelectedItem()),
                            (String) Objects.requireNonNull(monthBox.getSelectedItem()),
                            (Integer) Objects.requireNonNull(yearBox.getSelectedItem()),
                            nricFinField.getText(),
                            emailField.getText(),
                            (String) Objects.requireNonNull(genderComboBox.getSelectedItem()));
                    nextButton.setEnabled(false);
                    mainframe.panelOutroRight();
                }
            }
        });

        this.add(confirmInfoStuffPanel, confirmInfoStuffConstraints);

        
        this.setVisible(false);
    }

    private void handleFebruaryDates(int selectedDate){
        boolean isLeapYear = false;
        int selectedYear = (Integer)(Objects.requireNonNull(yearBox.getSelectedItem()));
        if (selectedYear % 4 == 0 && selectedYear % 100 != 0 || selectedYear % 400 == 0){
            isLeapYear = true;
        }
        if (selectedDate > 29 && isLeapYear){
            selectedDate = 29;
        }
        else if (selectedDate > 28 && !isLeapYear){
            selectedDate = 28;

        }
        if (isLeapYear){
            dayBox.setModel(new DefaultComboBoxModel<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29}));
        }
        else {
            dayBox.setModel(new DefaultComboBoxModel<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28}));
        }
        dayBox.setSelectedIndex(selectedDate-1);
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
//        return switch (nricFin.charAt(0)) {
//            case 'T' -> new char[]{'G', 'F', 'E', 'D', 'C', 'B', 'A', 'J', 'Z', 'I', 'H'};
//            case 'S' -> new char[]{'J', 'Z', 'I', 'H', 'G', 'F', 'E', 'D', 'C', 'B', 'A'};
//            case 'G' -> new char[]{'R', 'Q', 'P', 'N', 'M', 'L', 'K', 'X', 'W', 'U', 'T'};
//            case 'F' -> new char[]{'X', 'W', 'U', 'T', 'R', 'Q', 'P', 'N', 'M', 'L', 'K'};
//            default -> null;
//        };
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

    @Override
    public void slideInLeft() {
        movingInsets.right = getStartPos();
        this.setVisible(true);
        updateAnimation();
        loopCycles = 0;
        new Timer(10, e -> {
            if (loopCycles < 40){
                movingInsets.right = (int) round(pow(movingInsets.right, (1/1.05))-1.5);
                if (movingInsets.right < 0){
                    movingInsets.right = 0;
                }
                updateAnimation();
                ++loopCycles;
            }
            else {
                ((Timer)e.getSource()).stop();
                dayBox.setEditable(true);
                monthBox.setEditable(true);
                yearBox.setEditable(true);
                genderComboBox.setEditable(true);
                nextButton.setEnabled(true);
            }
        }).start();
    }

    @Override
    protected void updateAnimation() {
        layout.setConstraints(headerLabel, headerConstraints);
        layout.setConstraints(nameLabel, nameConstraints);
        layout.setConstraints(nameField, nameFieldConstraints);
        layout.setConstraints(dateOfBirthLabel, dateOfBirthConstraints);
        layout.setConstraints(dateStuffPanel, dateStuffConstraints);
        layout.setConstraints(nricFinLabel, nricFinConstraints);
        layout.setConstraints(nricFinField, nricFinFieldConstraints);
        layout.setConstraints(emailLabel, emailConstraints);
        layout.setConstraints(emailField, emailFieldConstraints);
        layout.setConstraints(genderLabel, genderConstraints);
        layout.setConstraints(genderComboBox, genderBoxConstraints);
        layout.setConstraints(confirmInfoStuffPanel, confirmInfoStuffConstraints);
        this.revalidate();
        this.repaint();
    }

    @Override
    public void slideInRight() {
        movingInsets.left = getStartPos();
        this.setVisible(true);
        updateAnimation();
        loopCycles = 0;
        new Timer(10, e -> {
            if (loopCycles < 40){
                movingInsets.left = (int) round(pow(movingInsets.left, (1/1.05))-1.5);
                if (movingInsets.left < 0){
                    movingInsets.left = 0;
                }
                updateAnimation();
                ++loopCycles;
            }
            else {
                ((Timer)e.getSource()).stop();
                dayBox.setEditable(true);
                monthBox.setEditable(true);
                yearBox.setEditable(true);
                genderComboBox.setEditable(true);
                nextButton.setEnabled(true);
            }
        }).start();

    }
    @Override
    public void slideOutRight(){
        dayBox.setEditable(false);
        monthBox.setEditable(false);
        yearBox.setEditable(false);
        genderComboBox.setEditable(false);
        loopCycles = 0;
        new Timer(10, e -> {
            if (loopCycles < 40){
                movingInsets.left = (int) pow(movingInsets.left + 1.5, 1.05);
                updateAnimation();
                ++loopCycles;
            }
            else {
                ((Timer)e.getSource()).stop();
                setVisible(false);
                mainframe.panelIntroLeft();
            }
        }).start();

    }

    private int getStartPos(){
        int startPos = 0;
        for (int i = 0; i < 40; ++i ){
            startPos = (int) pow(startPos + 1.5, 1.05);
        }
        return startPos;
    }
}
