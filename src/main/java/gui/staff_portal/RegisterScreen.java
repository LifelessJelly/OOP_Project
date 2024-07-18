package gui.staff_portal;

import com.formdev.flatlaf.ui.FlatLineBorder;
import controller.LoginMainframe;
import gui.ImageEmbedded;
import gui.StretchIcon;
import subsystems.ImageBase64;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.regex.Pattern;
import javax.swing.ButtonGroup;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class RegisterScreen extends JPanel {
    private final JButton registerButton;
    private LoginMainframe main;
    private boolean passwordIsValid;
    private boolean usernameIsValid;
    private static final Color UNMET_REQUIREMENT_COLOUR = new Color(218, 67, 67);
    private static final Color MET_REQUIREMENT_COLOUR = new Color(59, 178, 59);

    public RegisterScreen(LoginMainframe main){
        this.main=main;
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(0, 0, 0, 0));

        Image picture = ImageBase64.base64ToImage(ImageEmbedded.BACKGROUND_PIC);
        JLabel backgroundPic = new JLabel(new StretchIcon(picture, false));
        GridBagConstraints backgroundConstraints = new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);

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
        this.add(backgroundPic, backgroundConstraints);

        JLabel companyLogo = new JLabel(new ImageIcon(ImageBase64.base64ToImage(ImageEmbedded.COMPANY_LOGO)));
        GridBagConstraints companyLogoConstraints = new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
                new Insets(75, 75, 0, 0), 0, 0);
        registrationPanel.add(companyLogo, companyLogoConstraints);

        JPanel domainPanel = new JPanel(new GridBagLayout());
        domainPanel.setOpaque(false);



        JLabel domainLabel = new JLabel("What job role are you assigned?");
        domainLabel.setFont(domainLabel.getFont().deriveFont(18f));
        GridBagConstraints domainLabelConstraints = new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
                new Insets(10, 75, 0, 0), 0, 0);
        registrationPanel.add(domainLabel, domainLabelConstraints);

        ButtonGroup domainButtonGroup = new ButtonGroup();

        JRadioButton HRButton = new JRadioButton("HR");
        HRButton.setOpaque(false);
        HRButton.setFont(HRButton.getFont().deriveFont(18f));
        domainButtonGroup.add(HRButton);
        GridBagConstraints HRButtonConstraints = new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
                new Insets(0, 10, 0, 0), 0, 0);
        domainPanel.add(HRButton, HRButtonConstraints);

        JRadioButton ManagerButton = new JRadioButton("Manager");
        ManagerButton.setOpaque(false);
        ManagerButton.setFont(ManagerButton.getFont().deriveFont(18f));
        domainButtonGroup.add(ManagerButton);
        GridBagConstraints ManagerButtonConstraints = new GridBagConstraints(1, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
                new Insets(0, 10, 0, 0), 0, 0);
        domainPanel.add(ManagerButton, ManagerButtonConstraints);

        GridBagConstraints domainPanelConstraints = new GridBagConstraints(0, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
                new Insets(20, 75, 0, 0), 0, 0);
        registrationPanel.add(domainPanel, domainPanelConstraints);

        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setFont(usernameLabel.getFont().deriveFont(18f));
        GridBagConstraints usernameLabelConstraints = new GridBagConstraints(0, 3, 1, 1, 1, 1,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
                new Insets(10, 75, 0, 0), 0, 0);
        registrationPanel.add(usernameLabel, usernameLabelConstraints);

        JTextField usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(500, 50));
        usernameField.setMinimumSize(new Dimension(500, 50));
        usernameField.setFont(usernameField.getFont().deriveFont(18f));
        GridBagConstraints usernameFieldConstraints = new GridBagConstraints(0, 4, 1, 1, 1, 1,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
                new Insets(10, 75, 0, 0), 0, 0);
        registrationPanel.add(usernameField, usernameFieldConstraints);

        JLabel signedUpAlrWarningLabel = new JLabel("This user has already been registered");
        signedUpAlrWarningLabel.setForeground(UNMET_REQUIREMENT_COLOUR);
        GridBagConstraints signedUpAlrWarningLabelConstraints = new GridBagConstraints(0, 5, 1, 1, 1, 1,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
                new Insets(10, 75, 0, 0), 0, 0);
        registrationPanel.add(signedUpAlrWarningLabel, signedUpAlrWarningLabelConstraints);

        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setFont(passwordLabel.getFont().deriveFont(18f));
        GridBagConstraints passwordLabelConstraints = new GridBagConstraints(0, 6, 1, 1, 1, 1,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
                new Insets(10, 75, 0, 0), 0, 0);
        registrationPanel.add(passwordLabel, passwordLabelConstraints);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(500, 50));
        passwordField.setMinimumSize(new Dimension(500, 50));
        passwordField.setFont(passwordField.getFont().deriveFont(18f));
        GridBagConstraints passwordFieldConstraints = new GridBagConstraints(0, 7, 1, 1, 1, 1,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
                new Insets(10, 75, 0, 0), 0, 0);
        registrationPanel.add(passwordField, passwordFieldConstraints);

        JLabel passwordRequirementLabel = new JLabel("Your password must include at least: ");
        passwordRequirementLabel.setFont(passwordRequirementLabel.getFont().deriveFont(18f));
        GridBagConstraints passwordRequirementConstraint = new GridBagConstraints(0, 8, 1, 1, 1, 1,
                GridBagConstraints.NORTHWEST,GridBagConstraints.NONE,
                new Insets(10, 75, 0, 0), 0, 0);
        registrationPanel.add(passwordRequirementLabel, passwordRequirementConstraint);

        JLabel upperLowerCaseLabel = new JLabel("An upper case (A-Z) and lower case (a-z) alphabet");
        upperLowerCaseLabel.setFont(upperLowerCaseLabel.getFont().deriveFont(18f));
        upperLowerCaseLabel.setForeground(UNMET_REQUIREMENT_COLOUR);
        GridBagConstraints upperLowerCaseConstraints = new GridBagConstraints(0, 9, 1, 1, 1, 1,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
                new Insets(5, 75, 0, 0), 0, 0);
        registrationPanel.add(upperLowerCaseLabel, upperLowerCaseConstraints);

        JLabel numberLabel = new JLabel("A number (0-9)");
        numberLabel.setFont(numberLabel.getFont().deriveFont(18f));
        numberLabel.setForeground(UNMET_REQUIREMENT_COLOUR);
        GridBagConstraints numberConstraints = new GridBagConstraints(0, 10, 1, 1, 1, 1,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
                new Insets(5, 75, 0, 0), 0, 0);
        registrationPanel.add(numberLabel, numberConstraints);

        JLabel symbolLabel = new JLabel("A symbol (!@#$%^&*, etc.)");
        symbolLabel.setFont(symbolLabel.getFont().deriveFont(18f));
        symbolLabel.setForeground(UNMET_REQUIREMENT_COLOUR);
        GridBagConstraints symbolConstraints = new GridBagConstraints(0, 11, 1, 1, 1, 1,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
                new Insets(5, 75, 0, 0), 0, 0);
        registrationPanel.add(symbolLabel, symbolConstraints);

        JLabel lengthLabel = new JLabel("A minimum of 10 characters");
        lengthLabel.setFont(lengthLabel.getFont().deriveFont(18f));
        lengthLabel.setForeground(UNMET_REQUIREMENT_COLOUR);
        GridBagConstraints lengthConstraints = new GridBagConstraints(0, 12, 1, 1, 1, 1,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
                new Insets(5, 75, 0, 0), 0, 0);
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
        GridBagConstraints loginButtonConstraints = new GridBagConstraints(0, 13, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(10, 0, 0, 0), 0, 0);
        registrationPanel.add(registerButton, loginButtonConstraints);
        registerButton.addActionListener(e -> {

        });

        JButton backToLoginButton = new JButton("I am already registered");
        backToLoginButton.setFont(backToLoginButton.getFont().deriveFont(16f));
        backToLoginButton.setBorder(BorderFactory.createEmptyBorder());
        backToLoginButton.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints registerButtonConstraints = new GridBagConstraints(0, 14, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(10, 0, 0, 0), 0, 0);
        registrationPanel.add(backToLoginButton, registerButtonConstraints);


        backToLoginButton.addActionListener(e -> main.showLogin());

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

                if (matchUpper.matcher(passString).find() && matchLower.matcher(passString).find()) {
                    upperLowerCaseLabel.setForeground(MET_REQUIREMENT_COLOUR);
                } else {
                    upperLowerCaseLabel.setForeground(UNMET_REQUIREMENT_COLOUR);
                }

                if (matchNumber.matcher(passString).find()) {
                    numberLabel.setForeground(MET_REQUIREMENT_COLOUR);
                } else {
                    numberLabel.setForeground(UNMET_REQUIREMENT_COLOUR);
                }
                if (matchSymbol.matcher(passString).find()) {
                    symbolLabel.setForeground(MET_REQUIREMENT_COLOUR);
                }
                else {
                    symbolLabel.setForeground(UNMET_REQUIREMENT_COLOUR);
                }
                if (passString.length() >= 10){
                    lengthLabel.setForeground(MET_REQUIREMENT_COLOUR);
                }
                else {
                    lengthLabel.setForeground(UNMET_REQUIREMENT_COLOUR);
                }
                passwordIsValid = matchUpper.matcher(passString).find() && matchLower.matcher(passString).find() && matchNumber.matcher(passString).find() && matchSymbol.matcher(passString).find();
                updateRegisterButton();
            }
        });

        //===COMPONENT LISTENER (WINDOW)===//
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent arg0) {
                int adjustedFontSizeTitle= getWidth()/25;
                int adjustedFontSizeBody= getWidth()/35;

                Font bodyFont=new Font("Comic Sans MS", Font.PLAIN, adjustedFontSizeBody);
                Font smallBodyFont=new Font("Comic Sans MS", Font.PLAIN, (int)(Math.round(adjustedFontSizeBody*0.8)));

//                    lblLoginPage.setFont(new Font("Comic Sans MS", Font.BOLD, adjustedFontSizeTitle));
//                    lblUsername.setFont(bodyFont);
//                    lblPassword.setFont(bodyFont);
//                    lblValidTest.setFont(smallBodyFont);
//                    rdbtnHr.setFont(smallBodyFont);
//                    rdbtnManager.setFont(smallBodyFont);

//                    btnLogin.setFont(bodyFont);

                System.out.println(getWidth()+"X"+getHeight());
            }
        });
    }

    private void updateRegisterButton(){
        registerButton.setEnabled(passwordIsValid && usernameIsValid);
    }


    @Override
    protected void paintComponent(Graphics g) {

    }

}



