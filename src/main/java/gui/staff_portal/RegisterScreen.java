package gui.staff_portal;

import com.formdev.flatlaf.ui.FlatLineBorder;
import controller.LoginMainframe;
import data.Staff;
import gui.ImageEmbedded;
import gui.StretchIcon;
import controller.ImageBase64;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.regex.Pattern;
import javax.swing.ButtonGroup;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class RegisterScreen extends JPanel {
    private JButton registerButton;
    private final LoginMainframe main;
    private boolean passwordIsValid;
    private boolean usernameIsValid;
    private boolean displayNameIsValid;
    private boolean jobRoleSelected;
    private static final Color UNMET_REQUIREMENT_COLOUR = new Color(218, 67, 67);
    private static final Color MET_REQUIREMENT_COLOUR = new Color(59, 178, 59);
    private JRadioButton hrButton;
    private JRadioButton managerButton;
    private JTextField displayNameField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel signedUpAlrWarningLabel;
    private JLabel upperLowerCaseLabel;
    private JLabel numberLabel;
    private JLabel symbolLabel;
    private JLabel lengthLabel;
    private JButton backToLoginButton;

    public RegisterScreen(LoginMainframe main){
        this.main=main;
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(0, 0, 0, 0));
        initComponents();
        initListeners();
    }

    private void initComponents() {

        // can probably be its own class
        JPanel registrationPanel = new JPanel();
        registrationPanel.setLayout(new GridBagLayout());
        registrationPanel.setMinimumSize(new Dimension(750, 100));
        registrationPanel.setPreferredSize(new Dimension(750, 100));
        registrationPanel.setBackground(Color.WHITE);
        registrationPanel.setBorder( new FlatLineBorder( new Insets( 16, 16, 16, 16 ), new Color(0, 0, 0, 0), -20, 90 ) );
        GridBagConstraints loginPanelConstraints = new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(50, 50, 50, 50), 0, 0);
        this.add(registrationPanel, loginPanelConstraints);

        Image picture = ImageBase64.base64ToImage(ImageEmbedded.BACKGROUND_PIC);
        JLabel backgroundPic = new JLabel(new StretchIcon(picture, false));
        GridBagConstraints backgroundConstraints = new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
        this.add(backgroundPic, backgroundConstraints);

        JLabel companyLogo = new JLabel(new ImageIcon(ImageBase64.base64ToImage(ImageEmbedded.COMPANY_LOGO)));
        GridBagConstraints companyLogoConstraints = new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(75, 0, 0, 0), 0, 0);
        registrationPanel.add(companyLogo, companyLogoConstraints);

        JPanel domainPanel = new JPanel(new GridBagLayout());
        domainPanel.setOpaque(false);

        JLabel domainLabel = new JLabel("What job role are you assigned?");
        domainLabel.setFont(domainLabel.getFont().deriveFont(18f));
        GridBagConstraints domainLabelConstraints = new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(10, 0, 0, 0), 0, 0);
        registrationPanel.add(domainLabel, domainLabelConstraints);

        ButtonGroup domainButtonGroup = new ButtonGroup();

        hrButton = new JRadioButton("HR");
        hrButton.setOpaque(false);
        hrButton.setFont(hrButton.getFont().deriveFont(18f));
        domainButtonGroup.add(hrButton);
        GridBagConstraints HRButtonConstraints = new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(0, 10, 0, 0), 0, 0);
        domainPanel.add(hrButton, HRButtonConstraints);


        managerButton = new JRadioButton("Manager");
        managerButton.setOpaque(false);
        managerButton.setFont(managerButton.getFont().deriveFont(18f));
        domainButtonGroup.add(managerButton);
        GridBagConstraints ManagerButtonConstraints = new GridBagConstraints(1, 0, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(0, 10, 0, 0), 0, 0);
        domainPanel.add(managerButton, ManagerButtonConstraints);


        GridBagConstraints domainPanelConstraints = new GridBagConstraints(0, 2, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(20, 0, 0, 0), 0, 0);
        registrationPanel.add(domainPanel, domainPanelConstraints);

        JLabel displayNameLabel = new JLabel("Display name: ");
        displayNameLabel.setFont(displayNameLabel.getFont().deriveFont(18f));
        GridBagConstraints displqyNameConstraints = new GridBagConstraints(0, 3, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(10, 0, 0, 0), 0, 0);
        registrationPanel.add(displayNameLabel, displqyNameConstraints);

        displayNameField = new JTextField();
        displayNameField.setPreferredSize(new Dimension(500, 50));
        displayNameField.setMinimumSize(new Dimension(500, 50));
        displayNameField.setFont(displayNameField.getFont().deriveFont(18f));
        GridBagConstraints displayNameConstraints = new GridBagConstraints(0, 4, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(10, 0, 0, 0), 0, 0);
        registrationPanel.add(displayNameField, displayNameConstraints);

        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setFont(usernameLabel.getFont().deriveFont(18f));
        GridBagConstraints usernameLabelConstraints = new GridBagConstraints(0, 5, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(10, 0, 0, 0), 0, 0);
        registrationPanel.add(usernameLabel, usernameLabelConstraints);

        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(500, 50));
        usernameField.setMinimumSize(new Dimension(500, 50));
        usernameField.setFont(usernameField.getFont().deriveFont(18f));
        GridBagConstraints usernameFieldConstraints = new GridBagConstraints(0, 6, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(10, 0, 0, 0), 0, 0);
        registrationPanel.add(usernameField, usernameFieldConstraints);

        signedUpAlrWarningLabel = new JLabel("");
        signedUpAlrWarningLabel.setForeground(UNMET_REQUIREMENT_COLOUR);
        GridBagConstraints signedUpAlrWarningLabelConstraints = new GridBagConstraints(0, 7, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(10, 0, 0, 0), 0, 0);
        registrationPanel.add(signedUpAlrWarningLabel, signedUpAlrWarningLabelConstraints);

        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setFont(passwordLabel.getFont().deriveFont(18f));
        GridBagConstraints passwordLabelConstraints = new GridBagConstraints(0, 8, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(10, 0, 0, 0), 0, 0);
        registrationPanel.add(passwordLabel, passwordLabelConstraints);

        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(500, 50));
        passwordField.setMinimumSize(new Dimension(500, 50));
        passwordField.setFont(passwordField.getFont().deriveFont(18f));
        GridBagConstraints passwordFieldConstraints = new GridBagConstraints(0, 9, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(10, 0, 0, 0), 0, 0);
        registrationPanel.add(passwordField, passwordFieldConstraints);

        JLabel passwordRequirementLabel = new JLabel("Your password must include at least: ");
        passwordRequirementLabel.setFont(passwordRequirementLabel.getFont().deriveFont(18f));
        GridBagConstraints passwordRequirementConstraint = new GridBagConstraints(0, 10, 1, 1, 1, 1,
                GridBagConstraints.CENTER,GridBagConstraints.NONE,
                new Insets(10, 0, 0, 0), 0, 0);
        registrationPanel.add(passwordRequirementLabel, passwordRequirementConstraint);

        upperLowerCaseLabel = new JLabel("An upper case (A-Z) and lower case (a-z) alphabet");
        upperLowerCaseLabel.setFont(upperLowerCaseLabel.getFont().deriveFont(18f));
        upperLowerCaseLabel.setForeground(UNMET_REQUIREMENT_COLOUR);
        GridBagConstraints upperLowerCaseConstraints = new GridBagConstraints(0, 11, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(5, 0, 0, 0), 0, 0);
        registrationPanel.add(upperLowerCaseLabel, upperLowerCaseConstraints);

        numberLabel = new JLabel("A number (0-9)");
        numberLabel.setFont(numberLabel.getFont().deriveFont(18f));
        numberLabel.setForeground(UNMET_REQUIREMENT_COLOUR);
        GridBagConstraints numberConstraints = new GridBagConstraints(0, 12, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(5, 0, 0, 0), 0, 0);
        registrationPanel.add(numberLabel, numberConstraints);

        symbolLabel = new JLabel("A symbol (!@#$%^&*, etc.)");
        symbolLabel.setFont(symbolLabel.getFont().deriveFont(18f));
        symbolLabel.setForeground(UNMET_REQUIREMENT_COLOUR);
        GridBagConstraints symbolConstraints = new GridBagConstraints(0, 13, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(5, 0, 0, 0), 0, 0);
        registrationPanel.add(symbolLabel, symbolConstraints);

        lengthLabel = new JLabel("A minimum of 10 characters");
        lengthLabel.setFont(lengthLabel.getFont().deriveFont(18f));
        lengthLabel.setForeground(UNMET_REQUIREMENT_COLOUR);
        GridBagConstraints lengthConstraints = new GridBagConstraints(0, 14, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(5, 0, 0, 0), 0, 0);
        registrationPanel.add(lengthLabel, lengthConstraints);

        registerButton = new JButton("REGISTER");
        registerButton.setBackground(new Color(56, 109, 255));
        registerButton.setEnabled(false);
        registerButton.setForeground(Color.WHITE);
        registerButton.setBorder(new FlatLineBorder(new Insets(16, 16, 16, 16 ), new Color(255, 255, 255), -20, 90));
        registerButton.setMinimumSize(new Dimension(500, 50));
        registerButton.setPreferredSize(new Dimension(500, 50));
        registerButton.setFont(registerButton.getFont().deriveFont(20f));
        registerButton.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints loginButtonConstraints = new GridBagConstraints(0, 15, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(10, 0, 0, 0), 0, 0);
        registrationPanel.add(registerButton, loginButtonConstraints);

        backToLoginButton = new JButton("I am already registered");
        backToLoginButton.setFont(backToLoginButton.getFont().deriveFont(16f));
        backToLoginButton.setBackground(Color.WHITE);
        backToLoginButton.setBorder(BorderFactory.createEmptyBorder());
        backToLoginButton.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints registerButtonConstraints = new GridBagConstraints(0, 16, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(10, 0, 0, 0), 0, 0);
        registrationPanel.add(backToLoginButton, registerButtonConstraints);

        //===COMPONENT LISTENER (WINDOW)===//

    }

    private void initListeners() {
        hrButton.addActionListener(e -> {
            jobRoleSelected = true;
            updateRegisterButton();
        });
        managerButton.addActionListener(e -> {
            jobRoleSelected = true;
            updateRegisterButton();
        });
        registerButton.addActionListener(e -> {
            int domainSelected = -1;
            if (hrButton.isSelected()){
                domainSelected = Staff.HR;
            }
            else if (managerButton.isSelected()){
                domainSelected = Staff.MANAGER;
            }
            if (main.registerStaff(displayNameField.getText(), usernameField.getText(), passwordField.getPassword(), domainSelected)){
                signedUpAlrWarningLabel.setText("");
                JOptionPane.showMessageDialog(null, "You are successfully registered. Welcome!");
                main.showLogin();
            }
            else {
                signedUpAlrWarningLabel.setText("This user has already been registered");
            }
        });
        displayNameField.getDocument().addDocumentListener(new DocumentListener() {
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
                displayNameIsValid = !displayNameField.getText().isEmpty();
                updateRegisterButton();
            }
        });

        usernameField.getDocument().addDocumentListener(new DocumentListener() {

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
                usernameIsValid = !usernameField.getText().isEmpty();
                updateRegisterButton();
            }
        });
        passwordField.getDocument().addDocumentListener(new DocumentListener() {


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

                String passString = new String(passwordField.getPassword());
                Pattern matchUpper = Pattern.compile("[A-Z]");
                Pattern matchLower = Pattern.compile("[a-z]");
                Pattern matchNumber = Pattern.compile("[0-9]");
                Pattern matchSymbol = Pattern.compile("[^A-Za-z0-9]");


                upperLowerCaseLabel.setForeground(matchUpper.matcher(passString).find() && matchLower.matcher(passString).find() ? MET_REQUIREMENT_COLOUR : UNMET_REQUIREMENT_COLOUR);
                numberLabel.setForeground(matchNumber.matcher(passString).find() ? MET_REQUIREMENT_COLOUR : UNMET_REQUIREMENT_COLOUR);
                symbolLabel.setForeground(matchSymbol.matcher(passString).find() ? MET_REQUIREMENT_COLOUR : UNMET_REQUIREMENT_COLOUR);
                lengthLabel.setForeground(passString.length() >= 10 ? MET_REQUIREMENT_COLOUR : UNMET_REQUIREMENT_COLOUR);

                passwordIsValid = matchUpper.matcher(passString).find() && matchLower.matcher(passString).find() && matchNumber.matcher(passString).find() && matchSymbol.matcher(passString).find();
                updateRegisterButton();
            }
        });

        backToLoginButton.addActionListener(e -> main.showLogin());

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent arg0) {
                int adjustedFontSizeTitle= getWidth()/25;
                int adjustedFontSizeBody= getWidth()/35;

                Font bodyFont=new Font("Comic Sans MS", Font.PLAIN, adjustedFontSizeBody);
                Font smallBodyFont=new Font("Comic Sans MS", Font.PLAIN, (int)(Math.round(adjustedFontSizeBody*0.8)));

                System.out.println(getWidth()+"X"+getHeight());
            }
        });
    }

    private void updateRegisterButton() {
        registerButton.setEnabled(displayNameIsValid && passwordIsValid && usernameIsValid && jobRoleSelected);
    }
}



